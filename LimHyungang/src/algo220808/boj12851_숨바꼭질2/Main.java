package algo220808.boj12851_숨바꼭질2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 무작정 완전탐색 돌리기
 * -> 같은 시간에 대해서도 경로에 따라 도착 시간이 다를 수 있음
 * -> 1차원 boolean 배열로는 여러가지 최단경로를 전부 체크할 수 없음
 * -> 부적절
 *
 * 2. 2차원 boolean 배열 사용
 * -> 각 위치에 대해서만 check 를 두는 것이 아닌 해당 위치로 오기 위해 어떤 이동을 하였는지까지 체크
 * -> [0][n] : +1 이동하여 n 도착
 *    [1][n] : -1 이동하여 n 도착
 *    [2][n] : *2 이동하여 n 도착
 * -> 부적절한 이유를 모르겠다.
 *    어차피 BFS 라서 시간 순으로 판단하니 최단거리 보장되는 것 아닌가?
 *
 * 3.최단경로 !!!!!
 *
 *
 */

public class Main {

    static int N, K, CNT, INF = Integer.MAX_VALUE;
    static int[] dist;

    public static void bfs() {
        Queue<Integer> que = new ArrayDeque<>();
        dist[N] = 0;
        que.add(N);

        while(!que.isEmpty()) {
            int now = que.poll();

            if(now == K) return;

            // 뒤로 걷기
            if(now - 1 >= 0 && dist[now - 1] >= dist[now] + 1) {
                dist[now - 1] = dist[now] + 1;
                que.add(now - 1);
                if(now - 1 == K)
                    CNT += 1;  // 어차피 K가 now 가 되면 바로 return 할 거니까 여기서 바로바로 더해줘도 됨
            }

            // 앞으로 걷기
            if(now + 1 <= 100000 && dist[now + 1] >= dist[now] + 1) {
                dist[now + 1] = dist[now] + 1;
                que.add(now + 1);
                if(now + 1 == K)
                    CNT += 1;
            }

            // 순간이동
            if(now * 2 <= 100000 && dist[now * 2] >= dist[now] + 1) {
                dist[now * 2] = dist[now] + 1;
                que.add(now * 2);
                if(now * 2 == K)
                    CNT += 1;
            }
        }
    }

    public static void process() {
        if(N >= K) {
            dist[K] = N - K;
            CNT = 1;
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
        System.out.println(CNT);
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
        output();
    }
}
