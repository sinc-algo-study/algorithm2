package algo220808.ct_디버깅;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 만약, 정답이 3보다 큰 값이면 -1을 출력한다. 또, 불가능한 경우에도 -1을 출력한다.
 */

public class Main {

    static int N, M, H, ans = -1;
    static int[][] map;

    public static boolean isPossible() {
        for(int i = 1; i <= N; i++) {  // column
            int r = 1;
            int c = i;  // 다 내려간 후 c == i인지 검사

            for(int j = 1; j <= H; j++) {
                if(map[r][c] == 1) {  // 왼 -> 오
                    c += 1;
                }else if(map[r][c-1] == 1) {  // 오 -> 왼
                    c -= 1;
                }
                r += 1;  // 다음 행으로 이등
            }

            if(c != i) {
                return false;
            }
        }
        return true;
    }

    public static void dfs(int r, int deptNow, int dept) {
        if(ans != -1) return;
        if(deptNow == dept) {
            if(isPossible()) {
                ans = dept;
            }
            return;
        }

        for(int i = r; i <= H; i++) {
            for(int j = 1; j < N; j++) {
                // 1. 내가 가는 줄 없는가
                // 2. 나에게 오는 줄 없는가
                // 3. 내가 줄을 놔도 되는가?
                // (단, 두 가로선이 연속하거나 서로 접하면 안 된다. 또, 가로선은 점선 위에 있어야 한다.)
                if(map[i][j] == 0 && map[i][j-1] == 0 && map[i][j+1] == 0) {
                    map[i][j] = 1;
                    dfs(i, deptNow + 1, dept);
                    map[i][j] = 0;
                }
            }
        }


    }

    public static void process() {
        for(int i = 0; i <= 3; i++) {
            dfs(1, 0, i);
            if(ans != -1) {
                break;
            }
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 1 ~ H 일때, 0 ~ H+1 행 필요
        // 열은 1 ~ N만 사용

        map = new int[H+2][N+1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
        }
    }

    public static void output() {
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
        output();
    }

}