package algo220920.boj17281_야구공;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, ANS;
    static int[] batters, bases;
    static int[][] records;
    static boolean[] check;

    public static int run(int cnt) {
        int homeIn = 0;
        for(int i = 3; i >= 0; i--) {
            if(bases[i] == -1) continue;
            if(i + cnt >= 4) {
                bases[i] = -1;
                homeIn += 1;
            }else {
                bases[i + cnt] = bases[i];  // i 루 주자가 i+cnt 루로 이동
                bases[i] = -1;
            }
        }
        return homeIn;
    }

    public static int getScore() {
        int score = 0, bIdx = 0;
        int inning = 0, out = 0;

        while(inning < N) {
            bases[0] = batters[bIdx];  // 타석에 새로운 타자 등장
            int record = records[inning][batters[bIdx]];
            if(record == 0) {  // 아웃
                out += 1;
            }else {
                score += run(record);
            }

            bIdx = (bIdx + 1) % 9;
            if(out == 3) {
                inning += 1;
                out = 0;
                Arrays.fill(bases, -1);  // 이닝이 넘어가면 모든 '루'를 비운다
            }
        }

        return score;
    }

    public static void dfs(int dept) {
        if(dept == 9) {
            ANS = Math.max(ANS, getScore());
            return;
        }

        if(dept == 3) {
            for(int i = 0; i < 9; i++) {
                if(check[i]) continue;
                check[i] = true;
                batters[dept + 1] = i;
                dfs(dept + 2);
                check[i] = false;
            }
        }else {
            for(int i = 0; i < 9; i++) {
                if(check[i]) continue;
                check[i] = true;
                batters[dept] = i;
                dfs(dept + 1);
                check[i] = false;
            }
        }
    }

    public static void process() {
        batters[3] = 0;
        check[0] = true;
        dfs(0);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        batters = new int[9];
        records = new int[N][9];  // records[i][j] : i 이닝에서 j 타자의 성적
        check = new boolean[9];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++) {
                records[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bases = new int[4];
        Arrays.fill(bases, -1);
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
