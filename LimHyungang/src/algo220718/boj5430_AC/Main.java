package algo220718.boj5430_AC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static int T, N;
    static boolean reverse;
    static String inst, arr;
    static Deque<Integer> deque;

    public static void init() {
        deque = new ArrayDeque<>();
        reverse = false;
        String[] split = arr.substring(1, arr.length() - 1).split(",");
        for(String str : split) {
            if("".equals(str)) return;  // 원소 없으면 split 시 "" 들어감
            deque.add(Integer.parseInt(str));
        }
    }

    public static void appendAns() {
        sb.append("[");
        while(!deque.isEmpty()) {
            if(!reverse) {
                sb.append(deque.pollFirst());
            }else {
                sb.append(deque.pollLast());
            }
            sb.append(",");
        }

        if(sb.charAt(sb.length() - 1) == ',')  // 원소 없을 경우 ',' 찍히지 않음 주의
            sb.deleteCharAt(sb.length() - 1);
        sb.append("]").append("\n");
    }

    public static void process() {
        init();
        for(int i = 0; i < inst.length(); i++) {
            char ch = inst.charAt(i);
            if(ch == 'R') {
                reverse = !reverse;
            }else if(ch == 'D') {
                if(deque.isEmpty()) {
                    sb.append("error").append("\n");
                    return;
                }else {
                    if(!reverse) deque.pollFirst();
                    else deque.pollLast();
                }
            }
        }
        appendAns();
    }

    public static void input() throws IOException {
        inst = br.readLine();
        N = Integer.parseInt(br.readLine());  // 필요 없는 듯?
        arr = br.readLine();
    }

    public static void output() {
        sb.deleteCharAt(sb.length() - 1);  // 마지막 "\n" 삭제
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            input();
            process();
        }
        output();
    }
}
