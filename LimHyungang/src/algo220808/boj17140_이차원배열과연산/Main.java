package algo220808.boj17140_이차원배열과연산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R, C, K, ANS;
    static int[][] A;

    // 각 행들에 대해 숫자가 몇개인지 <key, value> 형태로 담아 반환
    public static Map<Integer, Integer> getHashMap(int row) {
        Map<Integer, Integer> hm = new HashMap<>();

        // 각 숫자가 몇개씩 있는지 센다
        for(int col = 0; col < A[0].length; col++) {
            int num = A[row][col];
            if(num == 0) continue;
            hm.merge(num, 1, Integer::sum);
        }

        return hm;
    }

    // 기준에 맞춰 정렬하기 위해 HashMap -> List 변환
    // 변환된 List 를 정렬하여 반환
    public static List<Map.Entry<Integer, Integer>> getOrderedList(Set<Map.Entry<Integer, Integer>> entrySet) {
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(entrySet);  // <num, cnt>

        // 기준에 맞춰 정렬 (cnt asc, num asc)
        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                if(!o1.getValue().equals(o2.getValue())) {
                    return o1.getValue() - o2.getValue();
                }else {
                    return o1.getKey() - o2.getKey();
                }
            }
        });

        return list;
    }

    // 정렬된 List 를 사용해 새로운 int[] row 를 만들어 반환
    public static int[] getNewRow(List<Map.Entry<Integer, Integer>> list, int size) {
        int col = 0;
        int[] row = new int[size];

        for(int i = 0; i < list.size(); i++) {
            row[col++] = list.get(i).getKey();
            row[col++] = list.get(i).getValue();
            if(col == 100) {
                break;
            }
        }

        return row;
    }

    public static void doR() {
        int max = 0;
        List[] listArr = new List[A.length];

        for(int i = 0; i < A.length; i++) {
            Map<Integer, Integer> hm = getHashMap(i);
            List<Map.Entry<Integer, Integer>> list = getOrderedList(hm.entrySet());

            if(max < 100) {
                max = Math.max(max, list.size() * 2);
                if(max > 100) {
                    max = 100;
                }
            }

            listArr[i] = list;
        }


        A = new int[A.length][max];
        for(int i = 0; i < A.length; i++) {
            A[i] = getNewRow(listArr[i], max);
        }
    }

    public static void doC() {
        // 시계 -> R -> 반시계 순서는 안 됨
        // 이건 반시계로 돌린 후 당겨오는 로직이 추가로 필요함

        antiClockwise();
        doR();
        clockwise();
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

    public static void clockwise() {
        int[][] temp = new int[A[0].length][A.length];
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < A[0].length; j++) {
                temp[j][A.length - i - 1] = A[i][j];
            }
        }
        A = temp;
    }

    public static void process() {
        while(!(A.length >= R+1 && A[0].length >= C+1 && A[R][C] == K)) {
            if(A.length >= A[0].length) {
                doR();
            }else {
                doC();
            }
            ANS += 1;

            if(ANS == 101) {
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
