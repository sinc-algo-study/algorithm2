package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R, C, K, ANS;
    static int[][] A;

    public static void doR() {
        int max = 0;
        List[] lists = new List[A.length];  // 각 행에 대해 hash map -> list 한 것들의 배열

        for(int i = 0; i < A.length; i++) {
            Map<Integer, Integer> hm = new HashMap<>();  // <num, cnt>

            for(int j = 0; j < A[i].length; j++) {
                int num = A[i][j];
                if(num == 0) continue;
                hm.merge(num, 1, Integer::sum);
            }

            // 정렬하기 위해 hash map -> list 변형
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(hm.entrySet());
            list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
                @Override
                public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                    if(o1.getValue().equals(o2.getValue())) {
                        return o1.getKey() - o2.getKey();
                    }else {
                        return o1.getValue() - o2.getValue();
                    }
                }
            });

            // 새로운 A의 사이즈를 결정하기 위한 max 갱신
            if(max < 100) {  // max 가 100 이상이면 굳이 갱신을 볼 필요 없음
                int size = list.size() * 2;
                max = Math.max(max, Math.min(size, 100));
            }
            lists[i] = list;
        }

        A = new int[A.length][max];
        for(int i = 0; i < lists.length; i++) {
            int col = 0;
            List<Map.Entry<Integer, Integer>> list = lists[i];

            for(int j = 0; j < list.size(); j++) {
                A[i][col++] = list.get(j).getKey();
                A[i][col++] = list.get(j).getValue();
                if(col == 100) {
                    break;
                }
            }
        }
    }

    public static void doC() {
        antiClockwise();
        doR();
        clockwise();
    }

    public static void clockwise() {
        int[][] temp = new int[A[0].length][A.length];
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < A[0].length; j++) {
                temp[j][A.length - i - 1] = A[i][j];
            }
        }
        A = temp;
    }

    public static void antiClockwise() {
        int[][] temp = new int[A[0].length][A.length];
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < A[0].length; j++) {
                temp[A[0].length - j - 1][i] = A[i][j];
            }
        }
        A = temp;
    }

    public static void process() {
        while(!(A.length > R && A[0].length > C && A[R][C] == K)) {
            if(A.length >= A[0].length) {
                doR();
            }else {
                doC();
            }
            ANS += 1;

            if(ANS > 100) {
                ANS = -1;
                break;
            }
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken()) - 1;
        K = Integer.parseInt(st.nextToken());

        A = new int[3][3];
        for(int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
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
