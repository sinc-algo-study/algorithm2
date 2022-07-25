package algo220725.boj14503_로봇청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 로봇 청소기는 다음과 같이 작동한다.
 *
 * 1. 현재 위치를 청소한다.
 * 2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
 *   1. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
 *   2. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
 *   3. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
 *   4. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
 *
 * 로봇 청소기는 이미 청소되어있는 칸을 또 청소하지 않으며, 벽을 통과할 수 없다.
 *
 */

public class Main {

    static int N, M, R, C, D, ANS;
    static int[][] map;
    static boolean[][] check;
    static int[] rArr = {-1, 0, 1, 0};  // 북 동 남 서
    static int[] cArr = {0, 1, 0, -1};

    public static void one() {
        check[R][C] = true;
        ANS += 1;
    }

    public static boolean two() {
        for(int i = 0; i < 4; i++) {
            int nr = R + rArr[(D + 3) % 4];
            int nc = C + cArr[(D + 3) % 4];

            if(!check[nr][nc] && map[nr][nc] == 0) {
                R = nr;
                C = nc;
                D = (D + 3) % 4;
                return true;
            }else {
                D = (D + 3) % 4;
            }
        }

        return false;
    }

    public static void process() {
        while(true) {
            if(!check[R][C] && map[R][C] == 0) {
                one();
            }

            if(!two()) {
                int nr = R + rArr[(D + 2) % 4];
                int nc = C + cArr[(D + 2) % 4];

                if(map[nr][nc] == 0) {
                    R = nr;
                    C = nc;
                }else {
                    break;
                }
            }
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        check = new boolean[N][M];
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
