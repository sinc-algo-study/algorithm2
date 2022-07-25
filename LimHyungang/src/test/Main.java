package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = null;
    static StringTokenizer st = null;
    static int[] arr = null;
    static ArrayList<Integer> first = null;
    static ArrayList<Integer> second = null;
    static int N, S, ans;

    public static int lowerBound(ArrayList<Integer> list, int target) {
        int min = -1;
        int max = list.size();
        while(min + 1 < max) {
            int mid = (min + max) / 2;
            if(list.get(mid) >= target) {
                max = mid;
            }else {
                min = mid;
            }
        }
        return max;
    }

    public static int upperBound(ArrayList<Integer> list, int target) {
        int min = -1;
        int max = list.size();
        while(min + 1 < max) {
            int mid = (min + max) / 2;
            if(list.get(mid) <= target) {
                min = mid;
            }else {
                max = mid;
            }
        }
        return max;
    }

    public static void dfs(int dept, int end, int sum, ArrayList<Integer> list) {
        if (dept == end) {
            list.add(sum);
            return;
        }
        dfs(dept + 1, end, sum + arr[dept], list);
        dfs(dept + 1, end, sum, list);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        first = new ArrayList<>();
        second = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 배열을 절반으로 나눠 각각 부분집합의 합의 리스트를 구한다.
        dfs(0, N / 2, 0, first);
        dfs(N / 2, N, 0, second);
        Collections.sort(first);
        Collections.sort(second);

        // 한 리스트에 대해 각 원소에 더했을 때 S를 완성할 수 있는 타겟의 수를 찾는다.
        long ans = 0;
        for(int i = 0; i < first.size(); i++) {
            int target = S - first.get(i);
            int upper = upperBound(second, target);
            int lower = lowerBound(second, target);
            ans += upper - lower;
        }
        System.out.println(S == 0 ? ans - 1 : ans);
    }
}