package algo220808.boj13549_숨바꼭질3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 다익스트라가 아니어도 풀리는 이유?
 * ->
 * PQ를 사용하지 않더라도, check를 하지 않더라도
 * 어차피 가중치가 더 작을 때만 갱신하기 때문에 올바른 최단거리 구할 수 있음
 */

public class Main {

    static int N, K, INF = Integer.MAX_VALUE;
    static int[] dist;

    public static void bfs() {
        Queue<Integer> que = new ArrayDeque<>();
        dist[N] = 0;
        que.add(N);

        while(!que.isEmpty()) {
            int now = que.poll();

            if(now == K) return;

            if(now + 1 <= 100000 && dist[now + 1] > dist[now] + 1) {
                que.add(now + 1);
                dist[now + 1] = dist[now] + 1;
            }

            if(now - 1 >= 0 && dist[now - 1] > dist[now] + 1) {
                que.add(now - 1);
                dist[now - 1] = dist[now] + 1;
            }

            if(now * 2 <= 100000 && dist[now * 2] > dist[now]) {
                que.add(now * 2);
                dist[now * 2] = dist[now];
            }
        }
    }

    public static void process() {
        if(N >= K) {
            dist[K] = N - K;
        }else {
            bfs();
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new int[100001];
        Arrays.fill(dist, INF);
    }

    public static void output() {
        System.out.println(dist[K]);
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
        output();
    }
}
