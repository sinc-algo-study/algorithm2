package algo220816.ct_병원거리최소화하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, ANS = Integer.MAX_VALUE;
    static int[][] map;
    static ArrayList<int[]> hospitals;

    public static int getDist(int r, int c) {
        int dist = Integer.MAX_VALUE;
        for (int[] h : hospitals) {
            if(map[h[0]][h[1]] == 0) continue;  // 사용되지 않은 병원
            dist = Math.min(dist, Math.abs(h[0] - r) + Math.abs(h[1] - c));
        }
        return dist;
    }

    public static int calculate() {
        int ans = 0;
        for(int i = 0; i < N; i++) {  // map 은 최대 50 * 50
            for(int j = 0; j < N; j++) {
                if(map[i][j] != 1) continue;
                ans += getDist(i, j);
            }
        }
        return ans;
    }

    public static void dfs(int dept, int idx) {
        if(dept == M) {
            ANS = Math.min(ANS, calculate());
            return;
        }

        for(int i = idx; i < hospitals.size(); i++) {
            int[] h = hospitals.get(i);
            map[h[0]][h[1]] = 2;
            dfs(dept + 1, i + 1);
            map[h[0]][h[1]] = 0;
        }

    }

    public static void process() {
        dfs(0, 0);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        hospitals = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    hospitals.add(new int[]{i, j});
                    map[i][j] = 0;
                }
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
