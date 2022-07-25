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
        if(check[turn][row]) {
            return false;
        }else {
            check[turn][row] = true;
        }

        boolean down = false;
        int cnt = 1;
        int current = arr[0];

        for(int i = 1; i < arr.length; i++) {
            if(Math.abs(current - arr[i]) > 1) {
                return false;
            }

            /**
             * 세 가지 경우로 나눈다
             *
             * 1. current > arr[i]
             * 2. current == arr[i]
             * 3. current < arr[i]
             *
             * when 1 -> down 가능 여부 검사 시작 (현재 down 검사 중인지 판단할 boolean 변수 필요)
             *        -> L을 채우기 전에 또다시 케이스1 나오면 return false
             *
             * when 2 -> flat cnt
             *        -> 이번 칸이 합해지면서 케이스1 조건 검사 완료 가능한지 여부 확인
             *
             * when 3 -> up 가능 여부 검사 (지금까지 쌓아온 flat cnt 사용)
             */

            if(current > arr[i]) {
                if(down) {  // 아직 down 못 끝냈는데 또 down 만남 -> false
                    return false;
                }else {
                    down = true;
                    current = arr[i];
                    cnt = 1;
                }
                if(cnt == L) {  // L == 1 일 떄, down 바로 판별 가능
                    current = arr[i];
                    down = false;
                    cnt = 0;  // 현재 칸을 소비했으므로 cnt == 0으로 초기화
                }

            }else if(current < arr[i]) {  // 오르막은 항상 바로 판별 가능
                if(cnt < L) {
                    return false;
                }else {
                    current = arr[i];
                    cnt = 1;
                }

            }else if(current == arr[i]) {
                cnt += 1;
                if(down && cnt == L) {
                    current = arr[i];
                    down = false;
                    cnt = 0;
                }
            }
        }

        if(down) {  // 더이상 검사할 칸 없는데도 down 검사중 -> false
            return false;
        }else {
            return true;
        }
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
