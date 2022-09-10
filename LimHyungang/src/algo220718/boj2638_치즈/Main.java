package algo220718.boj2638_치즈;

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

    static int N, M, CNT, ANS;
    static int[][] map, visit;
    static boolean[][] check;
    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    public static void bfs() {
        Queue<Pair> que = new ArrayDeque<>();
        visit = new int[N][M];
        check = new boolean[N][M];
        check[0][0] = true;
        que.add(new Pair(0, 0));

        while(!que.isEmpty()) {
            Pair p = que.poll();

            for(int i = 0; i < 4; i++) {
                int nr = p.r + rArr[i];
                int nc = p.c + cArr[i];

                if(!(-1 < nr && nr < N && -1 < nc && nc < M)) continue;
                if(check[nr][nc]) continue;

                if(map[nr][nc] == 0) {
                    check[nr][nc] = true;
                    que.add(new Pair(nr, nc));
                }else if(map[nr][nc] == 1) {
                    visit[nr][nc] += 1;
                }
            }
        }
    }

    public static void removeCheese() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(visit[i][j] >= 2) {
                    map[i][j] = 0;
                    CNT -= 1;
                }
            }
        }
    }

    public static void process() {
        while(CNT > 0) {
            bfs();
            removeCheese();
            ANS += 1;
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
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
