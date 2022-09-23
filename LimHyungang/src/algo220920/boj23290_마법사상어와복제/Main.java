package algo220920.boj23290_마법사상어와복제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int M, S, SR, SC;
    static List<Integer>[][] map, temp, copy;
    static int[][] smell;
    static int[] rArr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] cArr = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] turn = {0, 8, 1, 2, 3, 4, 5, 6, 7};
    static int[] convert = {0, 3, 1, 7, 5};

    public static boolean inRange(int r, int c) {
        return 1 <= r && r <= 4 && 1 <= c && c <= 4;
    }

    public static boolean existSmell(int r, int c) {
        return smell[r][c] != 0;
    }

    public static boolean existShark(int r, int c) {
        return r == SR && c == SC;
    }

    public static boolean isPossible(int r, int c) {
        return inRange(r, c) && !existSmell(r, c) && !existShark(r, c);
    }

    public static void initTemp() {
        temp = new ArrayList[5][5];
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                temp[i][j] = new ArrayList<>();
            }
        }
    }

    public static void tempToMap() {
        map = new ArrayList[5][5];
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                map[i][j] = new ArrayList<>();
                map[i][j].addAll(temp[i][j]);
            }
        }
    }

    public static void moveFish(int r, int c, List<Integer> list) {
        for(int f = 0; f < list.size(); f++) {
            int d = list.get(f);
            int nr = r + rArr[d];
            int nc = c + cArr[d];

            if(isPossible(nr, nc)) {  // 현재 방향으로 이동 가능
                temp[nr][nc].add(d);
            }else {  // 현재 방향으로 이동 불가능
                // 반시계로 돌며 모든 방향 체크
                for(int i = 0; i < 8; i++) {
                    d = turn[d];
                    nr = r + rArr[d];
                    nc = c + cArr[d];
                    if(isPossible(nr, nc)) {
                        temp[nr][nc].add(d);
                        break;
                    }

                    if(i == 7) {  // 이동할 수 없다면 temp 의 현재 좌표로 옮긴다
                        temp[r][c].add(d);
                    }
                }
            }
        }
    }

    public static int[] searchPath(int d1, int d2, int d3) {
        int cnt = 0;  // 이 경로에서 먹을 수 있는 물고기의 수
        int r = SR, c = SC;
        boolean[][] check = new boolean[5][5];  // 같은 물고기를 두 번 먹을 수 없다

        int nr = r + rArr[d1];
        int nc = c + cArr[d1];
        if(!inRange(nr, nc)) return null;
        if(!check[nr][nc]) {
            check[nr][nc] = true;
            cnt += map[nr][nc].size();
        }

        nr += rArr[d2];
        nc += cArr[d2];
        if(!inRange(nr, nc)) return null;
        if(!check[nr][nc]) {
            check[nr][nc] = true;
            cnt += map[nr][nc].size();
        }

        nr += rArr[d3];
        nc += cArr[d3];
        if(!inRange(nr, nc)) return null;
        if(!check[nr][nc]) {
            check[nr][nc] = true;
            cnt += map[nr][nc].size();
        }

        return new int[]{nr, nc, cnt};
    }

    public static void eatFish(int turn) {
        if(!map[SR][SC].isEmpty()) {
            map[SR][SC].clear();
            smell[SR][SC] = turn;  // 이전 턴에서 남긴 냄새는 고려할 필요 없다
        }
    }

    public static void doFive() {
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                map[i][j].addAll(copy[i][j]);
            }
        }
    }

    public static void doFour(int turn) {
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                if(smell[i][j] == turn - 2) {
                    smell[i][j] = 0;
                }
            }
        }
    }

    public static void doThree(int turn) {
        int maxCnt = 0;
        int minPath = 999;
        int d1 = 1, d2 = 1, d3 = 1;
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                for(int k = 1; k <= 4; k++) {
                    int[] result = searchPath(convert[i], convert[j], convert[k]);
                    if(result == null) continue;
                    int cnt = result[2];
                    int path = i * 100 + j * 10 + k;
                    if(cnt > maxCnt || (cnt == maxCnt && path < minPath)) {
                        maxCnt = cnt;
                        minPath = path;
                        d1 = convert[i];
                        d2 = convert[j];
                        d3 = convert[k];
                    }
                }
            }
        }

        // 최종 선정된 경로대로 이동
        SR += rArr[d1]; SC += cArr[d1];
        eatFish(turn);
        SR += rArr[d2]; SC += cArr[d2];
        eatFish(turn);
        SR += rArr[d3]; SC += cArr[d3];
        eatFish(turn);
    }

    public static void doTwo() {
        initTemp();
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                moveFish(i, j, map[i][j]);
            }
        }
        tempToMap();
    }

    public static void doOne() {
        copy = new ArrayList[5][5];
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                copy[i][j] = new ArrayList<>();
                copy[i][j].addAll(map[i][j]);
            }
        }
    }

    public static void process() {
        for(int s = 1; s <= S; s++) {
            doOne();
            doTwo();
            doThree(s);
            doFour(s);
            doFive();
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        smell = new int[5][5];
        map = new ArrayList[5][5];
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map[r][c].add(d);
        }

        st = new StringTokenizer(br.readLine());
        SR = Integer.parseInt(st.nextToken());
        SC = Integer.parseInt(st.nextToken());
    }

    public static void output() {
        int ans = 0;
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                ans += map[i][j].size();
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
        output();
    }
}
