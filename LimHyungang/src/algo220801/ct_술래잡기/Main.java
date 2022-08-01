package algo220801.ct_술래잡기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 도망자 M명, 나무 H그루
 * 도망자, 술래 순으로 움직이는 것을 K번 반복
 *
 * 도망자 이동 : 현재 술래와의 거리가 3 이하인 도망자만 움직인다
 * case 1. 범위 안
 *   다음 좌표에 술래가 없으면 이동 (나무의 유무는 관계 X)
 * case 2. 범위 밖
 *   방향 전환 후 앞 칸에 술래가 없으면 이동
 *
 * 술래 이동 : 주어진 경로에 따라 한칸씩 이동
 * do 1. 이동 후 새로운 좌표가 이동방향이 틀어지는 지점이라면 바로 방향전환까지 수행
 *       (경로의 양 끝에 도착한 경우에는 바로 180도 방향 전환 수행)
 * do 2. 바라보는 방향으로 총 3칸에 존재하는 도망자들을 잡음
 *       (이때 나무가 있는 칸에 도망자가 있다면 그 도망자는 잡히지 않는다)
 *
 * 점수 += 현재 턴 수 x 현재 턴에서 잡힌 도망자의 수
 *
 *
 *
 * 1. 도망자 이동
 *   1. 이동 대상 도망자 선정
 *   2. 도망자 이동
 * 2. 술래 이동
 *   1. 술래 이동 (필요에 따라 이동 후 90도 or 180도 방향 전환)
 *   2. 3칸 안의 도망자 잡음 (나무의 보호 여부 체크)
 *
 */

class Runner {
    int r, c, d;
    Runner(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}

public class Main {

    static int N, M, H, K, ANS, SR, SC;  // Seeker Row, Seeker Col
    static int[][] map;

    // 굳이 이렇게 관리하는 이유?
    // 술래가 이동 후 3칸을 체크할떄 모든 runner 를 검사하지 않게 하기 위함
    // 딱 3칸에 대한 것만 보도록 한다
    static Map<Integer, ArrayList<Runner>> hm;  // <좌표, Runners>
    static ArrayList<Runner> targets;  // 이번 턴에 이동할 도망자들

    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    public static void moveSeeker(int turn) {
        
    }

    public static int getDist(int key) {  // 술래와 도망자 사이의 거리를 반환
        int r = key / N;
        int c = key % N;
        return Math.abs(r - SR) + Math.abs(c - SC);
    }

    public static ArrayList<Runner> getTargets() {
        ArrayList<Runner> targets = new ArrayList<>();
        for(int key : hm.keySet()) {
            ArrayList<Runner> list = hm.get(key);
            if(getDist(key) <= 3) {
                targets.addAll(list);
            }
        }
        return targets;
    }

    public static void moveRunners() {
        targets = getTargets();

        for(Runner r : targets) {
            int nr = r.r + rArr[r.d];
            int nc = r.c + cArr[r.d];

            if(-1 < nr && nr < N && -1 < nc && nc < N) {  // 범위 안
                if(nr == SR && nc == SC) continue;  // 술래 있는 곳으론 이동 불가
                r.r = nr;  // 이동
                r.c = nc;

            }else {  // 범위 밖
                r.d = (r.d == 0 || r.d == 1) ?
                        (r.d == 0 ? 1 : 0) : (r.d == 2 ? 3 : 2);

                nr = r.r + rArr[r.d];
                nc = r.c + cArr[r.d];

                if(nr == SR && nc == SC) continue;  // 술래 있는 곳으론 이동 불가
                r.r = nr;  // 이동
                r.c = nc;
            }
        }
    }

    public static void process() {
        for(int i = 1; i <= K; i++) {  // K턴 수행
            moveRunners();
            moveSeeker(i);
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        SR = N / 2;
        SC = N / 2;

        map = new int[N][N];

        hm = new HashMap<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int key = i * N + j;  // 2차원 -> 1차원
                hm.put(key, new ArrayList<>());
            }
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) == 1 ? 3 : 1;
            int key = r * N + c;
            hm.get(key).add(new Runner(r, c, d));
        }

        for(int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 1;  // 좌표에 나무 표시
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
