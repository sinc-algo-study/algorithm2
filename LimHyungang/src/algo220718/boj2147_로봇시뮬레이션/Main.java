package algo220718.boj2147_로봇시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Inst {
    int target, cnt;
    char ch;
    Inst(int target, char ch, int cnt) {
        this.target = target;
        this.ch = ch;
        this.cnt = cnt;
    }
}

class Robot {
    int r, c, d;
    Robot(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}

public class Main {

    static int A, B, N, M;  // 열, 행, 로봇수, 명령어수
    static String ANS = "OK";
    static ArrayList<Robot> robots;
    static ArrayList<Inst> insts;
    static int[][] map;
    static int[] rArr = {-1, 0, 1, 0};  // 북 서 남 동 (상 좌 하 우)
    static int[] cArr = {0, -1, 0, 1};

    public static void doL(int target) {
        Robot robot = robots.get(target);
        robot.d = (robot.d + 1) % 4;  // 좌회전
    }

    public static void doR(int target) {
        Robot robot = robots.get(target);
        robot.d = (robot.d + 3) % 4;  // 우회전
    }

    public static int doF(int target) {
        // return 0 : 문제 없음
        // return -1 : 범위 초과
        // return n : 부딫힌 로봇 번호

        Robot robot = robots.get(target);
        int nr = robot.r + rArr[robot.d];
        int nc = robot.c + cArr[robot.d];
        if(!(1 <= nr && nr <= B && 1 <= nc && nc <= A)) return -1;
        else if(map[nr][nc] != 0) return map[nr][nc];
        else {  // 충돌 없을 경우에만 실제 이동 발생
            map[robot.r][robot.c] = 0;
            robot.r = nr;
            robot.c = nc;
            map[nr][nc] = target;
            return 0;
        }
    }

    public static void process() {
        for(Inst inst : insts) {
            int target = inst.target;
            char ch = inst.ch;
            int cnt = inst.cnt;

            for(int i = 0; i < cnt; i++) {
                if(ch == 'L') {
                    doL(target);
                }else if(ch == 'R') {
                    doR(target);
                }else if(ch == 'F') {
                    int crash = doF(target);
                    if(crash == -1) {  // 범위 초과
                        ANS = "Robot " + target + " crashes into the wall";
                        return;
                    }else if(crash != 0) {  // 로봇 충돌
                        ANS = "Robot " + target + " crashes into robot " + crash;
                        return;
                    }
                }
            }
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());  // 열
        B = Integer.parseInt(st.nextToken());  // 행
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        robots = new ArrayList<>();
        robots.add(null);
        map = new int[B+1][A+1];
        for(int i = 1; i <= N; i++) {  // 로봇 N 마리
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());          // 열 (열은 그냥 받으면 됨)
            int r = B - Integer.parseInt(st.nextToken()) + 1;  // 행 (new r = ( N - r + 1 ))
            char ch = st.nextToken().charAt(0);
            int d = (ch == 'N' ? 0 : (ch == 'W' ? 1 : (ch == 'S' ? 2 : 3)));
            map[r][c] = i;
            robots.add(new Robot(r, c, d));
        }

        insts = new ArrayList<>();
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            char ch = st.nextToken().charAt(0);
            int cnt = Integer.parseInt(st.nextToken());
            insts.add(new Inst(target, ch, cnt));
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