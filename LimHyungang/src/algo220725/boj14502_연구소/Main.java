package algo220725.boj14502_연구소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    int r, c;
    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {

    static int N, M, ANS;
    static int[][] map, temp;
    static boolean[][] dCheck, bCheck;
    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};
    static Queue<Pair> que;

    public static void initBfs(int SR, int SC) {
        que = new ArrayDeque<>();
        que.add(new Pair(SR, SC));
        bCheck[SR][SC] = true;
    }

    public static void bfs(int SR, int SC) {
        initBfs(SR, SC);

        while(!que.isEmpty()) {
            Pair p = que.poll();

            for(int i = 0; i < 4; i++) {
                int nr = p.r + rArr[i];
                int nc = p.c + cArr[i];

                if(!(-1 < nr && nr < N && -1 < nc && nc < M)) continue;
                if(bCheck[nr][nc] || temp[nr][nc] != 0) continue;

                bCheck[nr][nc] = true;
                temp[nr][nc] = 2;
                que.add(new Pair(nr, nc));
            }
        }
    }

    public static int getSafeZone() {
        int safe = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(temp[i][j] == 0)
                    safe += 1;
            }
        }
        return safe;
    }

    public static void spreadVirus() {
        bCheck = new boolean[N][M];
        temp = new int[N][M];
        for(int i = 0; i < N; i++) {
            temp[i] = map[i].clone();
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(bCheck[i][j] || temp[i][j] != 2) continue;
                bfs(i, j);
            }
        }

        ANS = Math.max(ANS, getSafeZone());
    }

    public static void dfs(int dept, int row) {
        if(dept == 3) {
            spreadVirus();
            return;
        }

        for(int i = row; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(dCheck[i][j] || map[i][j] != 0) continue;
                dCheck[i][j] = true;
                map[i][j] = 1;
                dfs(dept + 1, i);
                map[i][j] = 0;
                dCheck[i][j] = false;
            }
        }
    }

    public static void process() {
        dfs(0, 0);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dCheck = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
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
