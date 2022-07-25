package algo220725.boj22868_산책small;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.StringTokenizer;
//
//public class Main {
//
//    static int N, M, S, E, MIN = Integer.MAX_VALUE;
//    static ArrayList<Integer>[] map;
//    static ArrayList<Integer> SE, ES, ANS;
//    static boolean[] check, temp;
//
//    public static boolean compareList(ArrayList<Integer> l1, ArrayList<Integer> l2) {
//        // true : l1 > l2
//        // false : l2 <= l2
//
//        if(l1 == null) {  // ANS is null
//            return true;
//        }else {
//            int len = Math.min(l1.size(), l2.size());
//            for(int i = 0; i < len; i++) {
//                if(l1.get(i) < l2.get(i)) {
//                    return true;
//                }else if(l1.get(i) > l2.get(i)) {
//                    return false;
//                }
//
//                if(l2.size() - 1 == i) return true;
//            }
//        }
//
//        return false;  // 의미 없음. 컴파일 에러 방지.
//    }
//
//    public static void dfs(int node, int dest, ArrayList<Integer> path) {
//        if(path.size() > MIN) {
//            return;
//        }
//
//        if(node == dest) {
//            if(path.size() < MIN) {
//                MIN = path.size();
//                ANS = new ArrayList<>(path);  // deep copy of arraylist
//                temp = check.clone();  // 정답 경로의 check 를 기억
//            }else if(compareList(ANS, path)) {
//                ANS = new ArrayList<>(path);  // deep copy of arraylist
//                temp = check.clone();  // 정답 경로의 check 를 기억
//            }
//            return;
//        }
//
//        for(int i = 0; i < map[node].size(); i++) {
//            int next = map[node].get(i);
//            if(check[next]) continue;
//
//            check[next] = true;
//            path.add(next);
//            dfs(next, dest, path);
//            path.remove(path.size() - 1);
//            check[next] = false;
//        }
//    }
//
//    public static void process() {
//        check[S] = true;
//        dfs(S, E, new ArrayList<>(Arrays.asList(S)));  // get S to E
//        SE = ANS;
//        ANS = new ArrayList<>();
//
//        check = temp;
//        check[S] = false;
//        ANS = null;
//        dfs(E, S, new ArrayList<>(Arrays.asList(E)));
//        ES = ANS;
//    }
//
//    public static void input() throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//
//        map = new ArrayList[N + 1];
//        check = new boolean[N + 1];
//        for(int i = 1; i <= N; i++) {
//            map[i] = new ArrayList<>();
//        }
//
//        for(int i = 0; i < M; i++) {
//            st = new StringTokenizer(br.readLine());
//            int A = Integer.parseInt(st.nextToken());
//            int B = Integer.parseInt(st.nextToken());
//            map[A].add(B);
//            map[B].add(A);
//        }
//
//        st = new StringTokenizer(br.readLine());
//        S = Integer.parseInt(st.nextToken());
//        E = Integer.parseInt(st.nextToken());
//    }
//
//    public static void output() {
//        System.out.println(SE.size() + ES.size() - 2);
//    }
//
//    public static void main(String[] args) throws IOException {
//        input();
//        process();
//        output();
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Status {
    int node;
    ArrayList<Integer> path;
    Status(int node, ArrayList<Integer> path) {
        this.node = node;
        this.path = path;
    }
}

public class Main {

    static int N, M, S, E;
    static ArrayList<Integer>[] map;
    static ArrayList<Integer> SE, ES;
    static boolean[] check;

    public static ArrayList<Integer> bfs(int start, int dest) {
        Queue<Status> que = new ArrayDeque<>();
        check[start] = true;
        que.add(new Status(start, new ArrayList<>(Arrays.asList(start))));

        while(!que.isEmpty()) {
            Status status = que.poll();
            int now = status.node;
            ArrayList<Integer> path = status.path;

            if(now == dest) {
                return path;
            }

            for(int i = 0; i < map[now].size(); i++) {
                int next = map[now].get(i);

                if(check[next]) continue;

                check[next] = true;
                ArrayList<Integer> nextPath = new ArrayList<>(path);
                nextPath.add(next);
                que.add(new Status(next, nextPath));
            }
        }

        return null;  // 컴파일 에러 방지 용
    }

    public static void process() {
        for(int i = 1; i <= N; i++) {  // 애초에
            Collections.sort(map[i]);
        }
        SE = bfs(S, E);

        check = new boolean[N + 1];
        for(int node : SE) {
            check[node] = true;
        }

        check[S] = false;
        ES = bfs(E, S);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new ArrayList[N + 1];
        check = new boolean[N + 1];
        for(int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            map[A].add(B);
            map[B].add(A);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
    }

    public static void output() {
        System.out.println(SE.size() + ES.size() - 2);
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
        output();
    }
}