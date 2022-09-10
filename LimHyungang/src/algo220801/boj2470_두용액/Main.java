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
 *
 * 그럼 O(N * logN) = 10만 * 20 = 200만
 *
 * N개의 용액들의 특성값은 모두 다르다
 */

public class Main {

    static int N,   ANS1, ANS2;
    static int[] arr;

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
