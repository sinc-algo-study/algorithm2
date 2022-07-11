package algo220711.boj17298_오큰수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] A, NGE;
    static Stack<Integer> stk;

    public static void process() {
        for(int i = 0; i < N; i++) {
            // stk.peek()가 오큰수를 정하는 대상임
            while(!stk.isEmpty() && A[stk.peek()] < A[i]) {
                NGE[stk.pop()] = A[i];
            }
            stk.push(i);
        }

        while(!stk.isEmpty()) {
            NGE[stk.pop()] = -1;
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        NGE = new int[N];
        stk = new Stack<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void output() {
        StringBuilder sb = new StringBuilder();
        for(int ans : NGE) {
            sb.append(ans).append(" ");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
        output();
    }
}
