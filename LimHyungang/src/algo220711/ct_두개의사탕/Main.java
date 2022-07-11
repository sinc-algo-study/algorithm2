package algo220711.ct_두개의사탕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Status {
    int rr, rc, br, bc, dept;
    boolean red, blue;
    Status(int rr, int rc, int br, int bc,  int dept, boolean red, boolean blue) {
        this.rr = rr;
        this.rc = rc;
        this.br = br;
        this.bc = bc;
        this.dept = dept;
        this.red = red;
        this.blue = blue;
    }
}


public class Main {

    static int N, M, ANS = -1;
    static int RR, RC, BR, BC;  // 각 구슬의 최초 위치
    static int ER, EC;          // exit row, exit col;
    static char[][] map;
    static boolean[][][][] check;  // 총 1만 칸. 가능할 듯?

    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    public static void bfs() {
        Queue<Status> que = new ArrayDeque<>();
        check[RR][RC][BR][BC] = true;
        que.add(new Status(RR, RC, BR, BC, 0, false, false));

        while(!que.isEmpty()) {
            Status s = que.poll();

            if(s.dept > 10)
                return;
            if(s.red && !s.blue) {
                ANS = s.dept;
                return;
            }

            for(int i = 0; i < 4; i++) {
                int nrr = s.rr, nrc = s.rc, nbr = s.br, nbc = s.bc;
                boolean nextRed = s.red, nextBlue = s.blue;

                while(true) {  // move red
                    if(nextRed) break;

                    nrr += rArr[i];
                    nrc += cArr[i];

                    // 이동 중간에 구슬이 빠지는 경우
                    if(nrr == ER && nrc == EC) {
                        nextRed = true;
                        break;
                    }

                    // 장애물이거나, 구슬이 겹치거나
                    if(map[nrr][nrc] == '#' || (map[nrr][nrc] == '.' && nrr == nbr && nrc == nbc)) {
                        nrr -= rArr[i];
                        nrc -= cArr[i];
                        break;  // 빈 칸이 아니면 탈출
                    }
                }

                while(true) {  // move blue
                    if(nextBlue) break;

                    nbr += rArr[i];
                    nbc += cArr[i];

                    // 이동 중간에 구슬이 빠지는 경우
                    if(nbr == ER && nbc == EC) {
                        nextBlue = true;
                        break;
                    }

                    if(map[nbr][nbc] == '#' || (map[nbr][nbc] == '.' && nbr == nrr && nbc == nrc)) {
                        nbr -= rArr[i];
                        nbc -= cArr[i];
                        break;  // 빈 칸이 아니면 탈출
                    }
                }

                while(true) {  // move red
                    if(nextRed) break;

                    nrr += rArr[i];
                    nrc += cArr[i];

                    // 이동 중간에 구슬이 빠지는 경우
                    if(nrr == ER && nrc == EC) {
                        nextRed = true;
                        break;
                    }

                    if(map[nrr][nrc] == '#' || (map[nrr][nrc] == '.' && nrr == nbr && nrc == nbc)) {
                        nrr -= rArr[i];
                        nrc -= cArr[i];
                        break;  // 빈 칸이 아니면 탈출
                    }
                }

                while(true) {  // move blue
                    if(nextBlue) break;

                    nbr += rArr[i];
                    nbc += cArr[i];

                    // 이동 중간에 구슬이 빠지는 경우
                    if(nbr == ER && nbc == EC) {
                        nextBlue = true;
                        break;
                    }

                    if(map[nbr][nbc] == '#' || (map[nbr][nbc] == '.' && nbr == nrr && nbc == nrc)) {
                        nbr -= rArr[i];
                        nbc -= cArr[i];
                        break;  // 빈 칸이 아니면 탈출
                    }
                }

                if(nextBlue) continue;
                if(!(-1 < nrr && nrr < N && -1 < nrc && nrc < M)) continue;
                if(!(-1 < nbr && nbr < N && -1 < nbc && nbc < M)) continue;
                if(check[nrr][nrc][nbr][nbc]) continue;
                check[nrr][nrc][nbr][nbc] = true;
                que.add(new Status(nrr, nrc, nbr, nbc, s.dept + 1, nextRed, nextBlue));
            }
        }
    }

    public static void process() {
        bfs();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        check = new boolean[N][M][N][M];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'O') {
                    ER = i; EC = j;
                }else if(map[i][j] == 'R') {
                    map[i][j] = '.';
                    RR = i; RC = j;
                }else if(map[i][j] == 'B') {
                    map[i][j] = '.';
                    BR = i; BC = j;
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
