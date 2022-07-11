package algo220711.boj17141_연구소2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 0은 빈 칸, 1은 벽, 2는 바이러스를 놓을 수 있는 칸이다.
 * N*N 크기 격자.
 * 바이러스 M개
 *
 * 연구소의 상태가 주어졌을 때, 모든 빈 칸에 바이러스를 퍼뜨리는 최소 시간을 구해보자.
 *
 *  N(5 ≤ N ≤ 50), M(1 ≤ M ≤ 10)
 *  2의 개수는 M보다 크거나 같고, 10보다 작거나 같은 자연수
 *  -> 최대 720가지 조합
 *  -> BFS 시간복잡도 O(V+E)
 *  -> 총 시간복잡도 720 * O(V+E)
 *  -> = ?!
 *
 *  주의 1. 답이 0초인 경우도 고려? 해야할 듯?
 *         -> 굳이 따로 고려하려고 안 해도 알아서 고려되네.
 *      2. 탐색 완료 후 검사를 위한 배열 따로 놓는 게 좋을 듯?
 *         -> dfs로 하면 백트래킹으로 다시 돌려놓으면
 *            초기 배열 원복이 가능하지만 bfs라서 까다로울 듯
 *         -> 그냥 초기화 할 필요 없이 클론 배열을 하나 새로 만들어서 거기에 놓자
 *         -> temp 가 없으면? 실제로 바이러스를 놓은 좌표와 바이러스 후보 좌표를 구분할 수 없음
 */

class Pair {
    int r, c, d;
    Pair(int r, int c) {  // viruses 용
        this.r = r;
        this.c = c;
    }
    Pair(int r, int c, int d) {  // BFS 용
        this.r = r;
        this.c = c;
        this.d = d;
    }
}

public class Main {

    static int N, M, ANS = Integer.MAX_VALUE;
    static int[][] map, temp;
    static boolean[][] pCheck;
    static boolean[] vCheck;
    static ArrayList<Pair> viruses;
    static Queue<Pair> que;
    static int[] rArr = {-1, 1, 0, 0};
    static int[] cArr = {0, 0, -1, 1};

    public static boolean isFinish() {
        // 50 * 50 밖에 안 되니까 그냥 돌려도 될 듯?
        // -> 720개 조합에 대해 다 해서 720 * 2,500 = 1,800,000 으로 큰 영향도 없을 듯
        // 0이 M개 이상 나오면 바이러스가 다 퍼지지 않은 것
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(temp[i][j] == 0) cnt += 1;
                if(cnt > M) return false;
            }
        }
        return true;
    }

    public static void initBFS() {
        pCheck = new boolean[N][N];
        temp = new int[N][N];
        for(int i = 0; i < N; i++) {
            temp[i] = map[i].clone();  // 이차원에 대한 clone 시 shallow copy 발생 -> 에러
        }

        que = new ArrayDeque<>();
        for(int i = 0; i < vCheck.length; i++) {
            if(!vCheck[i]) continue;
            Pair v = viruses.get(i);
            que.add(new Pair(v.r, v.c, 0));
            pCheck[v.r][v.c] = true;
            temp[v.r][v.c] = 0;
        }
    }

    public static int bfs() {
        // 초기화
        int max = 0;
        initBFS();

        // 1. pCheck에 걸리지 않으며,
        // 2. temp[i][j] != 1 이어야 함

        while(!que.isEmpty()) {
            Pair p = que.poll();
            max = Math.max(max, p.d);

            for(int i = 0; i < 4; i++) {
                int nr = p.r + rArr[i];
                int nc = p.c + cArr[i];

                if(!(-1 < nr && nr < N && -1 < nc && nc < N)) continue;
                if(pCheck[nr][nc] || temp[nr][nc] == 1) continue;

                pCheck[nr][nc] = true;
                temp[nr][nc] = p.d + 1;
                que.add(new Pair(nr, nc, p.d + 1));
            }
        }

        return max;
    }

    public static void comb(int dept, int idx) {  // make combination
        if(dept == M) {  // 바이러스 M개 다 놓았으면 BFS 실시
            int max = bfs();  // 이 조합을 탐색했을 때 바이러스가 가장 멀리 가는 시간
            if(isFinish()) {
                ANS = Math.min(ANS, max);  // 조합들 간의 최소 값을 찾는다
            }
            return;
        }
        for(int i = idx + 1; i < viruses.size(); i++) {
            if(vCheck[i]) continue;
            vCheck[i] = true;
            comb(dept + 1, i);
            vCheck[i] = false;
        }
    }

    public static void process() {
        comb(0, -1);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        pCheck = new boolean[N][N];
        viruses = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    viruses.add(new Pair(i, j));  // 바이러스 설치 가능 위치 따로 기억
                }
            }
        }
        vCheck = new boolean[viruses.size()];
    }

    public static void output() {
        System.out.println(ANS == Integer.MAX_VALUE ? -1 : ANS);
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
        output();
    }
}
