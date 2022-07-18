package algo220718.ct_외주수최대화하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 완료하는데 걸리는 기한 T
 * 완료 했을 때의 수익 P
 *
 * 두 개 이상의 외주 작업은 동시에 수행할 수 없으며,
 * 휴가 기간 이후로는 일을 할 수 없다
 *
 * 외주 수익의 최대값을 출력하는 코드를 작성하라.
 *
 *
 * 단순한 조합 문제라고 생각했는데.. 무작정 조합을 짤 수 없다.
 * 외주의 시작 일자를 반드시 지켜야 한다.
 *
 */

public class Main {

    static int N, ANS;
    static int[][] works;  // [n][0] == T, [n][1] == P
    static boolean[] check;

    public static void dfs(int day, int profit) {
        if(day == N) {
            ANS = Math.max(ANS, profit);
            return;
        }

        // day 일 까지는 이미 완료된 것
        // 다음날에 시작되는 일을 할 것인가? 말 것인가?

        int T = works[day + 1][0];
        int P = works[day + 1][1];
        if(day + T <= N) {
            dfs(day + T, profit + P);
        }
        dfs(day + 1, profit);
    }

    public static void process() {
        dfs(0, 0);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        works = new int[N+1][2];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            works[i][0] = Integer.parseInt(st.nextToken());
            works[i][1] = Integer.parseInt(st.nextToken());
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
