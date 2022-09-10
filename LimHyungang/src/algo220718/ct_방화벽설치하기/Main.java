package algo220718.ct_방화벽설치하기;

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
    static boolean[][] bCheck, dCheck;
    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    public static void updateSafeZone() {
        int safeZone = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(temp[i][j] == 0) {
                    safeZone += 1;
                }
            }
        }
        ANS = Math.max(ANS, safeZone);
    }

    public static void bfs(int SR, int SC) {
        Queue<Pair> que = new ArrayDeque<>();
        bCheck[SR][SC] = true;
        que.add(new Pair(SR, SC));

        while(!que.isEmpty()) {
            Pair p = que.poll();

            for(int i = 0; i < 4; i++) {
                int nr = p.r + rArr[i];
                int nc = p.c + cArr[i];

                if(!(-1 < nr && nr < N && -1 < nc && nc < M)) continue;
                if(bCheck[nr][nc] || temp[nr][nc] != 0) continue;

                temp[nr][nc] = 2;
                bCheck[nr][nc] = true;
                que.add(new Pair(nr, nc));
            }
        }
    }

    public static void spreadFire() {
        temp = new int[N][M];
        bCheck = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            temp[i] = map[i].clone();
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(bCheck[i][j] || temp[i][j] != 2) continue;
                bfs(i, j);
            }
        }

        updateSafeZone();
    }

    public static void dfs(int dept) {
        if(dept == 3) {
            spreadFire();
            return;
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(dCheck[i][j] || map[i][j] != 0) continue;

                dCheck[i][j] = true;
                map[i][j] = 1;
                dfs(dept + 1);
                map[i][j] = 0;
                dCheck[i][j] = false;
            }
        }
    }

    public static void process() {
        dfs(0);
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