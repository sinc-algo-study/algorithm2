package algo220808.ct_이상한체스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 각 말들의 모든 방향에 대한 조합을 구하고 갈 수 없는 좌표를 체크한다
 *
 * 말은 최대 8개
 * 전부 1번 말이라고 가정한다면 한 말은 4가지 방향 선택 가능
 * 4^8 = 2^16 = 약 6만 가지 조합 가능
 *
 * 조합을 짜는 문제는 맞지만 조금 다른 형식인 듯?
 * 어떤 말을 사용할지/말지에 대한 조합이 아니라..
 * 말을 어느 방향으로 놓을 것인가의 문제
 * 근데 각 말마다 방향 설정이 다 달라지니까 그게 문제인건데..
 *
 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
 *
 * 일단 정답 맞긴 했는데.. 메모리가 좀 불안.
 * 제한 범위가 조금만 더 커져도 메모리 초과 나올 듯.
 *
 * 대안은?
 *
 * 나는 말 하나의 방향이 정해질때마다 배열을 복사하고 즉석에서 좌표를 채운다.
 * -> 복사되는 배열을 줄여야 한다.
 *
 * 그럼 복사하지 않고 대신 백트래킹 시에 방문했던 배열의 좌표들을 초기화?
 * -> 너무 복잡
 *
 * 각 말의 방향을 정할 때, 말의 번호와 이 말이 바라보는 방향들을 따로 저장.
 * 그럼 최대 총 8개의 객체가 나올 것.
 * ->
 * dfs 를 다 돌면 이것들을 기준으로 마지막에 visitable 배열을 채운다.
 *
 * 핵심은,
 * 그때그떄 채우기 위해 배열을 그때그때 복사하는 게 아니라..
 * 그떄그때는 말의 방향만 따로 저장하고, 그 채우는 것은 마지막에 하나로만 하는 것ㄴ
 *
 */

class Chess {
    int num, r, c;
    Chess(int num, int r, int c) {
        this.num = num;
        this.r = r;
        this.c = c;
    }
}

public class Main {

    static int N, M, CNT, ANS = Integer.MAX_VALUE;
    static int[][] map;
    static ArrayList<Chess> list;
    static int[] rArr = {-1, 0, 1, 0};
    static int[] cArr = {0, 1, 0, -1};

    public static int calculate(int[][] arr) {
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(arr[i][j] == 0) {
                    cnt += 1;
                }
            }
        }
        return cnt;
    }

    public static int[][] deepCopyArr(int[][] arr) {
        int[][] temp = new int[N][M];
        for(int i = 0; i < N; i++) {
            temp[i] = arr[i].clone();
        }
        return temp;
    }

    public static boolean isValid(int r, int c, int[][] arr) {
        // 범위를 넘어가거나 6을 만나면 invalid
        return (-1 < r && r < N && -1 < c && c < M) && arr[r][c] != 6;
    }

    public static void fillArr(int r, int c, int[][] arr, int[] dirs) {
        // 주어진 모든 방향에 대하여 채운다
        for(int dir : dirs) {
            int nr = r + rArr[dir], nc = c + cArr[dir];
            while (isValid(nr, nc, arr)) {
                if (arr[nr][nc] == 0)  // 방문한 빈칸에 대하여 -1로 마킹
                    arr[nr][nc] = -1;
                nr += rArr[dir];
                nc += cArr[dir];
            }
        }
    }

    public static void doOne(int r, int c, int idx, int[][] arr) {
        for(int i = 0; i < 4; i++) {
            // i번째 방향에 대해 map 채움
            int[][] temp = deepCopyArr(arr);
            fillArr(r, c, temp, new int[]{i});
            dfs(idx + 1, temp);
            // 원상복구는 굳이 할 필요 없음. 어차피 temp 는 새로 만들어지니까
        }
    }
    public static void doTwo(int r, int c, int idx, int[][] arr) {
        for(int i = 0; i < 2; i++) {
            int[][] temp = deepCopyArr(arr);
            fillArr(r, c, temp, new int[]{i, i + 2});
            dfs(idx + 1, temp);
        }
    }
    public static void doThree(int r, int c, int idx, int[][] arr) {
        for(int i = 0; i < 4; i++) {
            int[][] temp = deepCopyArr(arr);
            fillArr(r, c, temp, new int[]{i, (i+1) % 4});
            dfs(idx + 1, temp);
        }
    }
    public static void doFour(int r, int c, int idx, int[][] arr) {
        for(int i = 0; i < 4; i++) {
            int[][] temp = deepCopyArr(arr);
            fillArr(r, c, temp, new int[]{i, (i+1) % 4, (i+2) % 4});
            dfs(idx + 1, temp);
        }
    }
    public static void doFive(int r, int c, int idx, int[][] arr) {
        // 한가지 경우밖에 없음
        int[][] temp = deepCopyArr(arr);
        fillArr(r, c, temp, new int[]{0, 1, 2, 3});
        dfs(idx + 1, temp);
    }

    public static void dfs(int idx, int[][] arr) {
        if(idx == CNT) {
            ANS = Math.min(ANS, calculate(arr));
            return;
        }

        Chess chess = list.get(idx);
        switch(chess.num) {
            case 1: doOne(chess.r, chess.c, idx, arr);   break;
            case 2: doTwo(chess.r, chess.c, idx, arr);   break;
            case 3: doThree(chess.r, chess.c, idx, arr); break;
            case 4: doFour(chess.r, chess.c, idx, arr);  break;
            case 5: doFive(chess.r, chess.c, idx, arr);  break;
            default: break;
        }
    }

    public static void process() {
        dfs(0, map);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0 && map[i][j] != 6) {
                    list.add(new Chess(map[i][j], i, j));
                    CNT += 1;
                }
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
