package algo220816.ct_바이러스실험;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Virus implements Comparable<Virus> {
    int r, c, age;
    Virus(int r, int c, int age) {
        this.r = r;
        this.c = c;
        this.age = age;
    }
    @Override
    public int compareTo(Virus other) {
        return this.age - other.age;
    }
}

public class Main {

    static int N, M, K;
    static int[][] A, E, tempE;
    static PriorityQueue<Virus> pq, tempPq;
    static int[] rArr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] cArr = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void doOneAndTwo() {
        tempPq = new PriorityQueue<>();
        tempE = new int[N][N];

        while(!pq.isEmpty()) {
            Virus v = pq.poll();

            if(E[v.r][v.c] >= v.age) {  // 양분 섭취 가능
                E[v.r][v.c] -= v.age;
                v.age += 1;
                tempPq.add(v);
            }else {  // 양분 섭취 불가능
                tempE[v.r][v.c] += v.age / 2;
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                E[i][j] += tempE[i][j];
            }
        }
        pq = tempPq;
    }

    public static void doThree() {
        tempPq = new PriorityQueue<>();

        while(!pq.isEmpty()) {
            Virus v = pq.poll();
            tempPq.add(v);
            if(v.age % 5 != 0) continue;

            for(int i = 0; i < 8; i++) {
                int nr = v.r + rArr[i];
                int nc = v.c + cArr[i];
                if(!(-1 < nr && nr < N && -1 < nc && nc < N)) continue;
                tempPq.add(new Virus(nr, nc, 1));
            }
        }

        pq = tempPq;
    }

    public static void doFour() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                E[i][j] += A[i][j];
            }
        }
    }

    public static void process() {
        for(int i = 0; i < K; i++) {
            doOneAndTwo();
            doThree();
            doFour();
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

        pq = new PriorityQueue<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            pq.add(new Virus(r, c, age));
        }

        E = new int[N][N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(E[i], 5);
        }

    }

    public static void output() {
        System.out.println(pq.size());
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
        output();
    }
}
