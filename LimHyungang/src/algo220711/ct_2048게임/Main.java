package algo220711.ct_2048게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Pair {
    int num;
    boolean hasSecond;
    Pair(int num, boolean hasSecond) {
        this.num = num;
        this.hasSecond = hasSecond;
    }
}

public class Main {

    static int N, ANS;
    static Pair[][] map;
//    static int[] rArr = {-1, 1, 0, 0};
//    static int[] cArr = {0, 0, -1, 1};

    public static void turnLeft() {  // 반시계
        Pair[][] temp = new Pair[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                temp[N - j - 1][i] = map[i][j];
            }
        }
        map = temp;
    }

    public static void turnRight() {  // 시계
        Pair[][] temp = new Pair[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                temp[j][N - i - 1] = map[i][j];
            }
        }
        map = temp;
    }

    public static int getMax() {
        int max = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                max = Math.max(max, map[i][j].num);
            }
        }
        return max;
    }

    public static void afterMove() {
        // second 를 가진 좌표들에 대해 값을 합쳐준다
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                Pair p = map[i][j];
                if(p.hasSecond) {
                    p.num = p.num * 2;
                    p.hasSecond = false;
                }
            }
        }
    }

    public static void move(int dir) {
        for(int row = 0; row < N; row++) {
            Pair[] arr = map[row];

            if(dir == 2) {  // 좌
                for(int i = 1; i < N; i++) {         // i == 이동시킬 대상
                    if(arr[i] == null) continue;

                    for(int j = i-1; j >= 0; j--) {  // j == 이동할 위치
                        // i를 j로 이동 가능 케이스
                        if(arr[j] == null) {  // j가 빈칸이거나
                            arr[j] = arr[i];
                            arr[i] = null;
                        }else if(arr[j].num == arr[i].num && !arr[j].hasSecond) {  // j가 i와 같은 숫자이고 second 가 없거나
                            arr[j].hasSecond = true;
                            arr[i] = null;
                        }else {
                            break;
                        }
                    }
                }


            }else if(dir == 3) {  // 우
                for(int i = N - 2; i >= 0; i--) {     // i == 이동시킬 대상
                    if(arr[i] == null) continue;

                    for(int j = i + 1; j < N; j++) {  // j == 이동할 위치
                        // i를 j로 이동 가능 케이스
                        if(arr[j] == null) {  // j가 빈칸이거나
                            arr[j] = arr[i];
                            arr[i] = null;
                        }else if(arr[j].num == arr[i].num && !arr[j].hasSecond) {  // j가 i와 같은 숫자이고 second 가 없거나
                            arr[j].hasSecond = true;
                            arr[i] = null;
                        }
                    }
                }
            }
        }
    }

    public static Pair[][] copyMap() {
        // 이렇게 해도 row 안의 Pair 는 shallow copy 만 된다

        Pair[][] temp = new Pair[N][N];
        for(int i = 0; i < N; i++) {
            temp[i] = map[i].clone();
        }
        return temp;
    }

    public static void dfs(int dept) {
        if(dept == 5) {
            ANS = Math.max(ANS, getMax());
            return;
        }

        // 네가지 방향 모두 이동 후 백트래킹
        // 상, 하 방향일 때는 turnLeft 하고 좌, 우 이동 후 turnRight

        Pair[][] temp = null;

        // 상
        temp = copyMap();
        turnLeft();
        move(2);
        afterMove();
        turnRight();
        dfs(dept + 1);
        map = temp;  // map의 원상복구. 백트래킹.

        // 하
        temp = copyMap();
        turnLeft();
        move(3);
        afterMove();
        turnRight();
        dfs(dept + 1);
        map = temp;

        // 좌
        temp = copyMap();
        move(2);
        afterMove();
        dfs(dept + 1);
        map = temp;

        // 우
        temp = copyMap();
        move(3);
        afterMove();
        dfs(dept + 1);
        map = temp;
    }

    public static void process() {
        dfs(0);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new Pair[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = new Pair(num, false);
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
