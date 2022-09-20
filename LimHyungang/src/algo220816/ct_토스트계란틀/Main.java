package algo220816.ct_토스트계란틀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Group {
    int sum, cnt;
    ArrayList<int[]> list;
    Group(int sum, int cnt) {
        this.sum = sum;
        this.cnt = cnt;
        this.list = new ArrayList<>();
    }
}

public class Main {

    static int N, L, R, ANS;
    static int[][] map;
    static boolean[][] check;
    static ArrayList<Group> groups;
    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    public static void sumAndDivide() {
        for(Group group : groups) {
            int avg = group.sum / group.cnt;
            for(int[] arr : group.list) {
                map[arr[0]][arr[1]] = avg;
            }
        }
    }

    public static Group bfs(int SR, int SC) {
        Group group = new Group(map[SR][SC], 1);
        group.list.add(new int[]{SR, SC});
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{SR, SC});
        check[SR][SC] = true;

        while(!que.isEmpty()) {
            int[] p = que.poll();

            for(int i = 0; i < 4; i++) {
                int nr = p[0] + rArr[i];
                int nc = p[1] + cArr[i];

                if(!(-1 < nr && nr < N && -1 < nc && nc < N)) continue;
                int gap = Math.abs(map[p[0]][p[1]] - map[nr][nc]);
                if(check[nr][nc] || !(L <= gap && gap <= R)) continue;

                group.sum += map[nr][nc];
                group.cnt += 1;
                group.list.add(new int[]{nr, nc});
                check[nr][nc] = true;
                que.add(new int[]{nr, nc});
            }
        }

        return group;
    }

    public static ArrayList<Group> getGroups() {
        ArrayList<Group> groups = new ArrayList<>();

        check = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(check[i][j]) continue;
                Group g = bfs(i, j);
                if(g.cnt >= 2) {  // 두 칸 이상만 그룹을 지을 수 있다
                    groups.add(g);
                }
            }
        }

        return groups;
    }

    public static void process() {
        while(true) {
            groups = getGroups();
            if(groups.isEmpty()) break;  // 합칠 그룹이 없음
            ANS += 1;
            sumAndDivide();
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        check = new boolean[N][N];
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