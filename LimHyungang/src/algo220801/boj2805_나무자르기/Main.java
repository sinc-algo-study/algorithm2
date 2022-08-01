package algo220801.boj2805_나무자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, ANS;
    static int[] arr;

    public static boolean check(int mid) {
        long sum = 0;
        for(int i = 0; i < N; i++) {
            if(arr[i] <= mid) continue;  // 잘라도 얻을 수 있는 나무가 없음
            sum += arr[i] - mid;
        }
        return sum >= M;  // M미터 이상의 나무를 얻을 수 있는가?
    }

    public static int binarySearch() {
        int min = 0;
        int max = 2000000000;

        while(min + 1 < max) {
            int mid = (min + max) / 2;
            if(check(mid)) {
                min = mid;
            }else {
                max = mid;
            }
        }
        return min;
    }

    public static void process() {
        // 탐색 범위는 주어진 배열이 아니라 '범위' 이기 때문에 정렬은 필요 없다
        ANS = binarySearch();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
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
