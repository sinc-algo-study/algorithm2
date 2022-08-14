package algo220816.ct_드래곤커브;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 각 차수의 드래곤 커브에 대한 경로를 하나의 방향을 기준으로 정한다
 * 주어지는 드래곤커브의 방향에 맞춰서 해당 커브의 경로 방향들을 90도 회전 맞춰준 후 그린다.
 */

public class Main {

    static int N, MAX, ANS;
    static int[][] map, curves;
    static ArrayList<ArrayList<Integer>> paths;
    static int[] rArr = {0, -1, 0, 1};  // 우 상 좌 하
    static int[] cArr = {1, 0, -1, 0};

    public static void calculateAnswer() {
        for(int i = 0; i < map.length - 1; i++) {
            for(int j = 0; j < map[i].length - 1; j++) {
                if(map[i][j] == 1 &&
                        map[i][j+1] == 1 &&
                        map[i+1][j] == 1 &&
                        map[i+1][j+1] == 1)
                    ANS += 1;
            }
        }
    }

    public static void fillMap() {
        for(int[] curve : curves) {
            ArrayList<Integer> path = paths.get(curve[3]);  // 이번 커브의 차수에 대항하는 path
            map[curve[0]][curve[1]] = 1;
            int nr = curve[0], nc = curve[1], d = curve[2];
            for(int i = 0; i < path.size(); i++) {
                // 입력으로 주어지는 드래곤 커브의 모든 꼭지점은 좌표평면의 범위를 넘어서지 않는다고 가정해도 좋습니다.
                nr += rArr[(path.get(i) + d) % 4];
                nc += cArr[(path.get(i) + d) % 4];
                map[nr][nc] = 1;
            }
        }
    }

    public static void makePaths() {
        paths = new ArrayList<>();
        for(int i = 0; i <= MAX; i++) {
            paths.add(new ArrayList<>());
        }
        paths.get(0).add(0);  // 0차원 curve 의 경로 초기화

        for(int g = 1; g <= MAX; g++) {
            // g 차수에 대한 curve path 생성
            // 이전 차수의 path 를 거꾸로 순회하며 +1 한 path 를 갖는다
            ArrayList<Integer> pre = paths.get(g - 1);  // 이전 차수 curve 의 path
            for(int i = 0; i < pre.size(); i++) {  // clone 사용 시 배열 크기까지 바뀌기 때문에 사용 불가
                paths.get(g).add(pre.get(i));
            }
            for(int i = pre.size() - 1; i >= 0; i--) {
                paths.get(g).add((pre.get(i) + 1) % 4);
            }
        }
    }

    public static void process() {
        // 어차피 0 ~ MAX 차원 다 구할 거라 굳이 정렬할 필요도 없었네

        Arrays.sort(curves, new Comparator<int[]>() {  // 커브의 차수 기준으로 오름차순 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[3] - o2[3];
            }
        });
        MAX = curves[N-1][3];   // 최대 차수

        makePaths();            // 각 차수에 대한 path 생성
        fillMap();
        calculateAnswer();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[101][101];  // map 의 좌표 형태 어떻게 표현할지 한번 더 고려하기
        curves = new int[N][4];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            curves[i][0] = Integer.parseInt(st.nextToken());
            curves[i][1] = Integer.parseInt(st.nextToken());
            curves[i][2] = Integer.parseInt(st.nextToken());
            curves[i][3] = Integer.parseInt(st.nextToken());
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
