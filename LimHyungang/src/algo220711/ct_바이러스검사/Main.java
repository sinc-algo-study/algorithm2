package algo220711.ct_바이러스검사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] cust, ans; // 매장 별 고객 수, 매장 별 필요한 검사원 수
    static int MAST, PART;  // 팀장, 팀원 검사 가능 수

    public static boolean isPossible(int num, int mid) {
        int total = PART * mid;  // 팀원 mid 명이 처리 가능한 검사 수
        return total >= cust[num];
    }

    public static void binarySearch(int num) {
        int min = 1;
        int max = cust[num];

        while(min < max) {
            int mid = (min + max) / 2;
            if(isPossible(num, mid)) {
                max = mid - 1;
//                ans[num] = mid;
            }else {
                min = mid + 1;
            }
        }
        ans[num] = min;

        ans[num] += 1;
    }

    public static void process() {
        // 25%
        for(int i = 0; i < N; i++) {
            cust[i] -= MAST;  // 필요한 팀원만 구하기 위해
            if(cust[i] <= 0) {
                ans[i] = 1;  // 팀장 혼자서 처리 가능
                continue;
            }
            binarySearch(i);
        }

//        for(int i = 0; i < N; i++) {
//            cust[i] -= MAST;
//            if(cust[i] <= 0) {  // 팀장만으로 커버 가능
//                ans[i] = 1;
//                continue;
//            }
//
//            if(cust[i] % PART == 0) {
//                ans[i] = cust[i] / PART;
//            }else {
//                ans[i] = cust[i] / PART + 1;
//            }
//            ans[i] += 1;  // 팀장 추가
//        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        cust = new int[N];
        ans = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            cust[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        MAST = Integer.parseInt(st.nextToken());
        PART = Integer.parseInt(st.nextToken());
    }

    public static void output() {
//        long sum = Arrays.stream(ans).sum(); -> 내부에서 int로 계산해서 반환하여 overflow를 피할 수 없다
        long sum = 0;
        for(int answer : ans) {
            sum += answer;
        }
        System.out.println(sum);  // 팀장까지 더해서 출력
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
        output();
    }
}
