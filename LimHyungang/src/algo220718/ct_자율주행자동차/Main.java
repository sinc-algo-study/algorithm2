package algo220718.ct_자율주행자동차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, CR, CC, CD;  // car (row, col, dir)
    static int CNT, ANS;
    static boolean finish;
    static int[][] map;
    static boolean[][] check;
    static int[] rArr = {-1, 0, 1, 0};
    static int[] cArr = {0, 1, 0, -1};

    public static void do1() {
        int nr = CR + rArr[(CD + 3) % 4];
        int nc = CC + cArr[(CD + 3) % 4];

        // 테두리는 항상 인도임이 보장되므로 범위 체크 필요없음
        if(map[nr][nc] == 1 || check[nr][nc]) {
            CNT += 1;
            do2();
        }else {
            check[nr][nc] = true;
            CR = nr;
            CC = nc;
            CD = (CD + 3) % 4;
            ANS += 1;
            CNT = 0;
        }
    }

    public static void do2() {
        if(CNT >= 4) {
            CD = (CD + 3) % 4;
            CNT = 0;
            do3();
        }else {
            CD = (CD + 3) % 4;  // 방향 전환 후
            do1();
        }
    }

    public static void do3() {
        int nr = CR + rArr[(CD + 2) % 4];
        int nc = CC + cArr[(CD + 2) % 4];
        if(map[nr][nc] == 1) {
            finish = true;
        }else {
            if(!check[nr][nc]) {
                check[nr][nc] = true;
                ANS += 1;
            }
            CR = nr;
            CC = nc;
        }
    }

    public static void process() {
        check[CR][CC] = true;
        ANS += 1;
        while(!finish) {
            do1();
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        CR = Integer.parseInt(st.nextToken());
        CC = Integer.parseInt(st.nextToken());
        CD = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        check = new boolean[N][M];
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

//3 5
//        1 2 1
//        1 1 1 1 1
//        1 0 0 0 1
//        1 1 1 1 1