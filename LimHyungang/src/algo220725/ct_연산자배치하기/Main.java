package algo220725.ct_연산자배치하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, MAX = Integer.MIN_VALUE, MIN = Integer.MAX_VALUE;
    static int[] arr, ops;

    public static void dfs(int dept, int calc) {
        if(dept == N) {
            MIN = Math.min(MIN, calc);
            MAX = Math.max(MAX, calc);
            return;
        }

        if(ops[0] > 0) {
            ops[0] -= 1;
            dfs(dept + 1, calc + arr[dept]);
            ops[0] += 1;
        }
        if(ops[1] > 0) {
            ops[1] -= 1;
            dfs(dept + 1, calc - arr[dept]);
            ops[1] += 1;
        }
        if(ops[2] > 0) {
            ops[2] -= 1;
            dfs(dept + 1, calc * arr[dept]);
            ops[2] += 1;
        }
    }

    public static void process() {
        dfs(1, arr[0]);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ops = new int[3];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 3; i++) {
            ops[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void output() {
        System.out.println(MIN + " " + MAX);
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
        output();
    }
}
