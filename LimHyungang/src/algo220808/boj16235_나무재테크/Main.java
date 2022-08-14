package algo220808.boj16235_나무재테크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * 1. 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
 *    각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
 *    하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
 *    만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
 *  -> 나무의 속성 : 좌표, 나이
 *     나무는 나이순 오름차순 정렬
 *
 * 2. 여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
 *    각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다.
 *    소수점 아래는 버린다.
 *  -> 특이사항 없음
 *
 * 3. 가을에는 나무가 번식한다.
 *    번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
 *    상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
 *  -> 범위 고려
 *
 * 4. 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다.
 *    각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
 *
 *
 * (이런 류의 문제에서 항상 고민하는 것)
 * -> 타겟 객체를 어떻게 관리할 것인가?
 *
 * (일반적인 선택지)
 * 1. ArrayList로 관리하며 나무의 속성으로 좌표를 보관한다
 *   -> 나무의 개수 만큼만 반복을 돌면 된다
 * 2. 이차원 배열을 따로 만들고 나무의 좌표에 나이만 보관한다.
 *   -> 나무의 좌표를 따로 관리할 필요가 없어 좀 덜 복잡하다
 *
 * (문제의 특이사항)
 * 1. 한 칸에 여러 나무 존재 가능
 * ->
 *    2. 이차원 배열 방식 : 이차원 배열 안에 또 배열을 넣어 3차원 배열이 됨
 * 두 방식 다 별로..
 *
 * Map<좌표, PriorityQueue> 사용!
 *
 *
 * 자신의 턴을 이미 마친 객체에게는 영향을 미치지 않도록 유의해야함
 *
 */

class Tree implements Comparable<Tree> {
    int r, c, age;
    Tree(int r, int c, int age) {
        this.r = r;
        this.c = c;
        this.age = age;
    }
    @Override
    public int compareTo(Tree other) {
        return this.age - other.age;
    }
}

public class Main {

    static int N, M, K;
    static int[][] A, E, tempE;
    static PriorityQueue<Tree> trees, temp;
    static int[] rArr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] cArr = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void springAndSummer() {
        temp = new PriorityQueue<>();
        tempE = new int[N][N];

        while(!trees.isEmpty()) {
            Tree t = trees.poll();

            if(E[t.r][t.c] >= t.age) {  // 양분을 먹을 수 있는 나무만 temp에 넘긴다
                E[t.r][t.c] -= t.age;
                t.age += 1;
                temp.add(t);
            }else {  // 나무의 생사가 판단되는 시점에서 summer 수행해야 하므로 spring, summer 를 한 메서드로 수행
                tempE[t.r][t.c] += t.age / 2;  // summer
//                    E[t.r][t.c] += t.age / 2;  // 다음 나무의 spring 에 영향을 미칠 수도 있다
            }
        }

        trees = temp;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                E[i][j] += tempE[i][j];
            }
        }
    }

    public static void autumn() {
        temp = new PriorityQueue<>();

        while(!trees.isEmpty()) {
            Tree t = trees.poll();

            if(t.age % 5 == 0) {  // 5의 배수만 번식 가능
                for(int i = 0; i < 8; i++) {
                    int nr = t.r + rArr[i];
                    int nc = t.c + cArr[i];
                    if(!(-1 < nr && nr < N && -1 < nc && nc < N)) continue;
                    temp.add(new Tree(nr, nc, 1));
                }
            }
            temp.add(t);  // 번식 성공 여부와 상관 없이 temp 로 옮겨줘야 함
        }

        trees = temp;
    }

    public static void winter() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                E[i][j] += A[i][j];
            }
        }
    }

    public static void process() {
        for(int i = 0; i < K; i++) {
            springAndSummer();
            autumn();
            winter();
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        E = new int[N][N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(E[i], 5);
        }

        trees = new PriorityQueue<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(r, c, age));
        }
    }

    public static void output() {
        System.out.println(trees.size());
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
        output();
    }
}
