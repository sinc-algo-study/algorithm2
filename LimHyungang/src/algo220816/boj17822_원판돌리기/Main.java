package algo220816.boj17822_원판돌리기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, T, CNT, SUM;
    static boolean flag;
    static int[][] map;
    static int[][] tasks;
    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    public static void afterAfterRotate() {
        double avg = (double)SUM / (double)CNT;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) continue;

                if(map[i][j] > avg) {
                    map[i][j] -= 1;
                }else if(map[i][j] < avg) {
                    map[i][j] += 1;
                }
            }
        }
    }

    public static void afterRotate() {
        flag = false;

        // 검사하면서 바로바로 0으로 바꾸면 다른 원소가 체크되지 않을 수 있음
        // 0으로 바꿀지 말지 체크만 먼저 해놓고 나중에 한번에 변경
        // 최대 50 * 50 = 2,500 크기이고 T <= 50 이니까 부담되진 않을 듯?
        boolean[][] check = new boolean[N][M];

        // 10,000
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) continue;

                for(int d = 0; d < 4; d++) {
                    int nr = i + rArr[d];
                    int nc = j + cArr[d];

                    if(!(-1 < nr && nr < N)) continue;  // row 만 범위 체크 실시
                    if(!(-1 < nc && nc < M)) {
                        if(nc == M) nc = 0;
                        if(nc == -1) nc = M - 1;  // 왜 never used 뜨지?
                    }

                    if(map[i][j] == map[nr][nc]) {
                        flag = true;
                        check[i][j] = true;
                        check[nr][nc] = true;
                    }
                }
            }
        }

        // 2,500
        CNT = 0;
        SUM = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(check[i][j]) map[i][j] = 0;
                if(map[i][j] != 0) {
                    CNT += 1;
                    SUM += map[i][j];
                }
            }
        }
    }

    public static void rotate(int x, int d, int k) {
        for(int i = 0; i < N; i++) {
            if((i+1) % x != 0) continue;

            int[] temp = new int[M];
            for(int j = 0; j < M; j++) {
                if(d == 0) {  // 시계 방향
                    temp[(j + k) % M] = map[i][j];
                }else {       // 반 시계 방향
                    // k < M 이기 때문에 M-k 로 가능
                    // k 가 M 이상일 경우엔? -> 분기 처리하여 % 해주고서 더해야하나?
                    temp[(j + (M-k)) % M] = map[i][j];
                }
            }
            map[i] = temp;
        }
    }

    public static void process() {
        for(int[] task : tasks) {  // 50
            int x = task[0];
            int d = task[1];
            int k = task[2];
            rotate(x, d, k);     // 2,500
            afterRotate();       // 12,500
            if(flag) continue;
            afterAfterRotate();  // 2,500
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        tasks = new int[T][3];
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            tasks[i][0] = Integer.parseInt(st.nextToken());
            tasks[i][1] = Integer.parseInt(st.nextToken());
            tasks[i][2] = Integer.parseInt(st.nextToken());
        }
    }

    public static void output() {
        int ans = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                ans += map[i][j];
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
