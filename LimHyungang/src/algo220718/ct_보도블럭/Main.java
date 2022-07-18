package algo220718.ct_보도블럭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, L, ANS;
    static int[][] map;
    static boolean[][] check;

    public static void rotate() {
        int[][] temp = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                temp[N - j - 1][i] = map[i][j];
            }
        }
        map = temp;
    }

    public static boolean isPossible(int[] arr, int turn, int row) {
        if(check[turn][row]) return false;

        


        return true;
    }

    public static void process() {
        for(int turn = 0; turn < 4; turn++) {
            rotate();
            for(int row = 0; row < N; row++) {
                if(isPossible(map[row], turn, row)) {
                    ANS += 1;
                    check[(turn + 2) % 4][N - row - 1] = true;  // 반대편 체크
                }
            }
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        check = new boolean[4][N];
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
