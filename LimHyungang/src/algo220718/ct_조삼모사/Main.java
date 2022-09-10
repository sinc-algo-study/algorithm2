package algo220718.ct_조삼모사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, ANS = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[] check;

    public static int[] getMornings() {
        int[] morning = new int[N/2];
        int idx = 0;
        for(int i = 0; i < N; i++) {
            if(check[i]) {
                morning[idx++] = i;
            }
        }
        return morning;
    }

    public static int[] getEvenings() {
        int[] evening = new int[N/2];
        int idx = 0;
        for(int i = 0; i < N; i++) {
            if(!check[i]) {
                evening[idx++] = i;
            }
        }
        return evening;
    }

    public static int getSum(int[] arr) {
        int sum = 0;
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                if(arr[i] == arr[j]) continue;
                sum += map[arr[i]][arr[j]];
            }
        }
        return sum;
    }

    public static void updateAns() {
        int[] mornings = getMornings();
        int[] evenings = getEvenings();
        int morningSum = getSum(mornings);
        int eveningSum = getSum(evenings);
        ANS = Math.min(ANS, Math.abs(morningSum - eveningSum));
    }

    public static void dfs(int dept, int idx) {
        if(dept == N / 2) {  // N개 중 아침에 할 일 N/2 개 선택 완료
            updateAns();
            return;
        }
        for(int i = idx; i < N; i++) {
            if(check[i]) continue;
            check[i] = true;
            dfs(dept + 1, i);
            check[i] = false;
        }
    }

    public static void process() {
        dfs(0, 0);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        check = new boolean[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
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
