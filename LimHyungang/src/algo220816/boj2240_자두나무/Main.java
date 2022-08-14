package algo220816.boj2240_자두나무;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int T, W;
    static int[] arr;
    static int[][][] dp;

    public static void process() {
        if(arr[1] == 1) {
            dp[1][0][1] = 1;
            dp[1][1][2] = 0;
        }else {
            dp[1][0][1] = 0;
            dp[1][1][2] = 1;
        }

        // 점화식에서 j-1 에서 인덱스 에러 방지하기 위해 j==0인 경우는 미리 초기화
        for(int i = 1; i <= T; i++) {
            if(arr[i] == 1) {
                // 사실 한번도 움직이지 않고 2에 도달하는 경우는 없다
                // dp[i][0][2] 은 그냥 초기값 0을 그대로 두면 된다
                dp[i][0][1] = dp[i-1][0][1] + 1;
//                dp[i][0][2] = dp[i-1][0][2];
            }else {
                dp[i][0][1] = dp[i-1][0][1];
//                dp[i][0][2] = dp[i-1][0][2] + 1;
            }
        }

        for(int i = 2; i <= T; i++) {
            int plum = arr[i];
            for(int j = 1; j <= W; j++) {
                if(plum == 1) {
                    dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][2]) + 1;
                    dp[i][j][2] = Math.max(dp[i-1][j][2], dp[i-1][j-1][1]);
                }else {
                    dp[i][j][2] = Math.max(dp[i-1][j][2], dp[i-1][j-1][1]) + 1;
                    dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][2]);
                }
            }
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[T+1];
        for(int i = 1; i <= T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        dp = new int[T+1][W+1][3];
    }

    public static void output() {
//        int ans = 0;
//        for(int i = 0; i <= W; i++) {
//            ans = Math.max(ans, Arrays.stream(dp[T][i]).max().getAsInt());
//        }
//        System.out.println(ans);

        int ans = Arrays.stream(dp[T])
                .flatMapToInt(Arrays::stream)
                .max()
                .getAsInt();
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
        output();
    }
}