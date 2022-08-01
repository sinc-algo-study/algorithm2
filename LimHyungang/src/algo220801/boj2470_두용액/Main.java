package algo220801.boj2470_두용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 양,양 이나 음,음 조합도 가능하다 -> 굳이 양,음 구분해서 풀어나갈 필요 없다
 * 일단 이분탐색 해야하니까 정렬?
 *
 * 어떻게 결정문제로 바꿀 것인가?
 * -> 대상 용액과 조합하였을때 가장 0에 가까운 용액은 무엇인가?
 * -> 이걸 N번 반복하면 된다
 * -> N번 반복하며 두 용액을 갱신
 *
 * 그럼 O(N * logN) = 10만 * 20 = 200만
 *
 * N개의 용액들의 특성값은 모두 다르다
 */

public class Main {

    static int N,   ANS1, ANS2;
    static int[] arr;

//    public static int check(int target, int mid) {
//        return Math.abs(target + arr[mid]);
//    }
//
//    public static int binarySearch(int idx) {
//        int min = -1;
//        int max = N;
//
//        /**
//         * mid 를 갱신하는 조건은?
//         * -> T, F로 나타낼 수가 없는데 어떻게 min, max 값을 갱신해나가지..?
//         * 0이 되는 값이 있느냐? 없느냐? 가 아니라 그냥 0에 가장 가깝게 만들 수 있는 값을 찾는건데..
//         * -> 이분탐색이 아니었다 ㅜㅜ
//         */
//
//        while(min + 1 < max) {
//            int mid = (min + max) / 2;
//            if() {
//
//            }else {
//
//            }
//        }
//    }
//
//    public static void process() {
//        Arrays.sort(arr);
//        for(int i = 0; i < N; i++) {
//            // 탐색 대상에서 자신(= arr[i])은 제외해야 하는데..
//            int ansIdx = binarySearch(i);
//            int sum = arr[i] + arr[ansIdx];
//            if(ABS > Math.abs(sum)) {
//                ANS1 = arr[i];
//                ANS2 = arr[ansIdx];
//                ABS = Math.abs(sum);
//            }
//        }
//    }

    public static void twoPointer() {
        int left = 0;
        int right = arr.length - 1;
        int ABS = Integer.MAX_VALUE;

        while(left < right) {
            int sum = arr[left] + arr[right];

            if(ABS > Math.abs(sum)) {
                ANS1 = arr[left];
                ANS2 = arr[right];
                ABS = Math.abs(sum);
            }

            if(sum > 0) {
                right -= 1;
            }else {
                left += 1;
            }
        }
    }

    public static void process() {
        Arrays.sort(arr);
        twoPointer();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void output() {
        if(ANS1 < ANS2) {
            System.out.print(ANS1 + " " + ANS2);
        }else {
            System.out.println(ANS2 + " " + ANS1);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
        output();
    }
}
