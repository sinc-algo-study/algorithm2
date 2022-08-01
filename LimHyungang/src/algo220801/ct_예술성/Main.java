package algo220801.ct_예술성;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 1. 점수 구하기 ( = getPoint() )
 *   1.1 그룹 나누기 ( = getGroups() )
 *   1.2 그룹의 구성요소 구하기 ( = calcAdjacent() )
 *   -> 번호, 개수, 인접그룹과 인접한 변 개수
 *
 *   (그룹을 나누면서 인접그룹과의 인접 변 개수까지 구할 수 없다.
 *   아직 그룹이 다 나눠지지 않은 상태라 인접 그룹이 무슨 그룹인지 알 수 없음)
 *   -> 어차피 최대 29 x 29 칸이고, 3회전까지 밖에 없어서 시간 괜찮을 듯
 *
 *   동일한 번호의 그룹이 여러 개 존재할 수 있다
 *   -> arr[num] 으로 그룹 관리 불가
 *   -> 그럼 어떻게 관리해야하지..?
 *   -> map[i][j] 의 번호를 그룹 번호로 쓰지 않고 그룹 번호를 따로 돌린다.
 *
 * 2. 회전
 *   2.1 십자 회전 (반시계)
 *   2.2 비 십자 회전 (시계)
 *   -> 원본 map 유지한 상태에서 그냥 통째로 돌려버린다 (십자 회전 해결)
 *   -> 원본 map 을 기준으로 비십자 영역을 돌려서 temp에 넣는다 (비십자 회전 해결)
 *   -> map = temp 업데이트는 마지막에 수행하면 된다
 */

class Group {
    int num, cnt;
    int[] arr;  // 그룹 다 구하고서 사이즈 정해서 초기화 시켜줄 것
    Group(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }
}

public class Main {

    static int N, ANS, groupNum;
    static int[][] map, temp, groupMap;
    static ArrayList<Group> groups;

    // boolean[][] check 는 네이밍 애매해서 그냥 로컬로 둠

    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    // 그룹을 나누며 각 그룹들에 대한 num, cnt 를 구하는 dfs
    // + 각 위치가 어느 그룹에 속하는지 표시
    public static int dfs1(int r, int c, int groupNum, boolean[][] check) {
        int cnt = 1;
        for(int i = 0; i < 4; i++) {
            int nr = r + rArr[i];
            int nc = c + cArr[i];

            if(!(-1 < nr && nr < N && -1 < nc && nc < N)) continue;
            if(check[nr][nc] || map[r][c] != map[nr][nc]) continue;

            check[nr][nc] = true;
            groupMap[nr][nc] = groupNum;
            cnt += dfs1(nr, nc, groupNum, check);
        }
        return cnt;
    }

    public static ArrayList<Group> getGroups() {
        ArrayList<Group> result = new ArrayList<>();
        boolean[][] check = new boolean[N][N];
        groupMap = new int[N][N];

        groupNum = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(check[i][j]) continue;

                check[i][j] = true;
                groupMap[i][j] = groupNum;
                int cnt = dfs1(i, j, groupNum, check);  // 이번 그룹의 블럭 개수
                result.add(new Group(map[i][j], cnt));
                groupNum += 1;
            }
        }

        return result;
    }

    // 각 그룹들에 대한 인접 그룹과 맞닿은 변의 개수를 구하는 dfs
    public static void dfs2(int r, int c, boolean[][] check) {
        for(int i = 0; i < 4; i++) {
            int nr = r + rArr[i];
            int nc = c + cArr[i];

            if(!(-1 < nr && nr < N && -1 < nc && nc < N)) continue;

            if(groupMap[r][c] != groupMap[nr][nc]) {
                // 현재 그룹과 groupMap[nr][nc] 그룹의 인접한 변의 개수 += 1
                groups.get(groupMap[r][c]).arr[groupMap[nr][nc]] += 1;
            }else {
                if(check[nr][nc]) continue;
                check[nr][nc] = true;
                dfs2(nr, nc, check);
            }
        }
    }

    public static void calcAdjacent() {
        for(Group group : groups) {
            group.arr = new int[groupNum];
        }

        boolean[][] check = new boolean[N][N];

        // (1,2) 를 구하면 자동으로 (2,1) 도 결정된다
        // 근데 이거까지 고려하자니 너무 복잡. 어차피 N이 작으니까 걍 한번씩 전부 구하자
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(check[i][j]) continue;

                check[i][j] = true;
                dfs2(i, j, check);
            }
        }
    }

    public static int getPoint() {
        int point = 0;

        groups = getGroups();
        calcAdjacent();

        boolean[][] gCheck = new boolean[groups.size()][groups.size()];
        for(int i = 0; i < groupNum; i++) {
            Group g1 = groups.get(i);

            for(int j = 0; j < groupNum; j++) {
                if(i == j || gCheck[i][j]) continue;

                gCheck[i][j] = true;  // (1,2)를 봤으면
                gCheck[j][i] = true;  // (2,1)도 본 것이다

                Group g2 = groups.get(j);
                point += (g1.cnt + g2.cnt) * g1.num * g2.num * g1.arr[j];
            }
        }

        return point;
    }

    public static void sideRotate(int r, int c, int size) {
        // 멋있게 한 번에 돌리고 싶지만...
        // 인덱스 수식을 못 짜겠다..

        int[][] sub = new int[size][size];
        for(int i = r; i < r + size; i++) {
            for(int j = c; j < c + size; j++) {
                sub[i - r][j - c] = map[i][j];
            }
        }

        // sub 를 돌리고
        int[][] subTemp = new int[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                subTemp[j][size - i - 1] = sub[i][j];
            }
        }

        // 그걸 temp 에 넣는다
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                temp[i + r][j + c] = subTemp[i][j];
            }
        }
    }

    public static void crossRotate() {
        // 이름은 십자 회전이지만 사실은 그냥 통째로 돌리는 것
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                temp[N - j - 1][i] = map[i][j];
            }
        }
    }

    public static void rotate() {
        temp = new int[N][N];

        // 십자 회전
        crossRotate();

        // 나머지 회전
        int pivot = N / 2;
        sideRotate(0, 0, pivot);
        sideRotate(0, pivot + 1, pivot);
        sideRotate(pivot + 1, 0, pivot);
        sideRotate(pivot + 1, pivot + 1, pivot);

        map = temp;
    }

    public static void process() {
        ANS = getPoint();
        for(int i = 0; i < 3; i++) {
            rotate();
            ANS += getPoint();
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void output() {
        System.out.println(ANS);
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
        output();
    }
}
