package algo220711.boj1074_Z;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, R, C, ANS;
    static boolean FINISH;
    static int[] rArr = {0, 0, 1, 0};
    static int[] cArr = {0, 1, -1, 1};

    public static void search(int r, int c) {
        ANS += 1;
        if(r == R && c == C) {
            FINISH = true;
        }
    }

    // 시간 초과. 이거로 돌릴떈 ANS = -1 로 초기화 후 시작
//    public static void recur(int r, int c, int len) {
//        if(len == 2) {
//            for(int i = 0; i < 4; i++) {
//                r += rArr[i];
//                c += cArr[i];
//                search(r, c);
//                if(FINISH) break;
//            }
//            return;
//        }
//
//        int half = len / 2;
//        recur(r, c, half);
//        if(FINISH) return;
//        recur(r, c + half, half);
//        if(FINISH) return;
//        recur(r + half, c, half);
//        if(FINISH) return;
//        recur(r + half, c + half, half);
//    }

    public static void recur(int r, int c, int len) {
        if(len == 1) {
            return;
        }

        int half = len / 2;

        if(R < r + half && C < c + half) {  // 1사분면
            recur(r, c, half);
        }
        if(R < r + half && C >= c + half) {  // 2사분면
            ANS += half * half;
            recur(r, c + half, half);
        }
        if(R >= r + half && C < c + half) {  // 3사분면
            ANS += half * half * 2;
            recur(r + half, c, half);
        }
        if(R >= r + half && C >= c + half) {  // 4사분면
            ANS += half * half * 3;
            recur(r + half, c + half, half);
        }
    }

    public static void process() {
        recur(0, 0, (int)Math.pow(2, N));
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
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
