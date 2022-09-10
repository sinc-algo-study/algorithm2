package algo220718.ct_테트리스블럭안의합최대화하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 모든 블럭에 대해서 모든 칸을 시작점으로 잡아보며 최대의 합을 찾는다?
 *
 * 블럭의 개수 5개
 * 회전까지 고려하면 1 + 2 + 4 + 4 + 4 = 15개
 *
 * 15개 블럭을 4만칸의 시작점에서 검사 -> 60만가지 경우의 수
 * 각 경우의 수 당 최대 6칸 검사 -> 최대 360만 번
 *
 * 시간은 문제되지 않는다. 검사를 어떻게 할 것인지가 문제.
 *
 *
 *
 */

public class Main {

    static int N, M, ANS;
    static int[][] map;
    static int[] b1 = {1, 1, 1, 1};
    static int[][] b2 = {{1}, {1}, {1}, {1}};
    static int[][] b3 = {{1, 1}, {1, 1}};
    static int[][][] b4 = {
            {{0,0,1}, {1,1,1}},  // ㄴ 블럭
            {{1,1,1}, {1,0,0}},
            {{1,0,0}, {1,1,1}},
            {{1,1,1}, {0,0,1}},
            {{0,1,1}, {1,1,0}},  // ㄹ 블럭
            {{1,1,0}, {0,1,1}},
            {{0,1,0}, {1,1,1}},  // ㅗ 블럭
            {{1,1,1}, {0,1,0}}
    };  // [8][2][3]
    static int[][][] b5 = {
            {{1,0}, {1,0}, {1,1}},  // ㄴ 블럭
            {{1,1}, {1,0}, {1,0}},
            {{0,1}, {0,1}, {1,1}},
            {{1,1}, {0,1}, {0,1}},
            {{1,0}, {1,1}, {0,1}},  // ㄹ 블럭
            {{0,1}, {1,1}, {1,0}},
            {{1,0}, {1,1}, {1,0}},  // ㅗ 블럭
            {{0,1}, {1,1}, {0,1}}
    };  // [8][3][2]

    public static void block1(int r, int c) {
        int sum = 0;
        for(int i = 0; i < b1.length; i++) {
            sum += map[r][c+i];
        }
        ANS = Math.max(ANS, sum);
    }

    public static void block2(int r, int c) {
        int sum = 0;
        for(int i = 0; i < b2.length; i++) {
            for(int j = 0; j < b2[i].length; j++) {
                sum += map[r+i][c+j];
            }
        }
        ANS = Math.max(ANS, sum);
    }

    public static void block3(int r, int c) {
        int sum = 0;
        for(int i = 0; i < b3.length; i++) {
            for(int j = 0; j < b3.length; j++) {
                sum += map[r+i][c+j];
            }
        }
        ANS = Math.max(ANS, sum);
    }

    public static void block4(int r, int c) {
        for(int b = 0; b < b4.length; b++) {
            int sum = 0;
            for(int i = 0; i < b4[b].length; i++) {
                for(int j = 0; j < b4[b][i].length; j++) {
                    if(b4[b][i][j] == 0) continue;
                    sum += map[r+i][c+j];
                }
            }
            ANS = Math.max(ANS, sum);
        }
    }

    public static void block5(int r, int c) {
        for(int b = 0; b < b5.length; b++) {
            int sum = 0;
            for(int i = 0; i < b5[b].length; i++) {
                for(int j = 0; j < b5[b][i].length; j++) {
                    if(b5[b][i][j] == 0) continue;
                    sum += map[r+i][c+j];
                }
            }
            ANS = Math.max(ANS, sum);
        }
    }

    public static void process( ) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(j + 4 <= M) block1(i, j);
                if(i + 4 <= N) block2(i, j);
                if(i + 2 <= N && j + 2 <= M) block3(i, j);
                if(i + 2 <= N && j + 3 <= M) block4(i, j);
                if(i + 3 <= N && j + 2 <= M) block5(i, j);
            }
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
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
