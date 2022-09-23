package algo220920.boj17825_주사위윷놀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 1. 조합을 먼저 다 구하고 점수 계산하기
 * ->
 * "말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다." 
 * 위 조건 때문에 말들의 위치를 그때그떄 기억해야 함.. 고로 부적절.
 *
 * 2. 그때그떄 점수를 추가하며 조합 구하기
 * ->
 * ㄱㄱ
 *
 * (말의 속성)
 * 1. 위치.. 만 있으면 되나..?
 * -> ㅇㅇ
 *
 * (map 재구성)
 * 칸에 표시된 점수가 동일한 칸이 존재하기 떄문에 점수를 인덱스로 사용할 수 없다
 *
 * <key, value>
 * key : index
 * value : {score, red, blue}
 *
 *
 */

public class Main {

    static int ANS;
    static int[] dices, horses;
//    static boolean[] check;  // check[i] == i번 좌표에 말이 존재하는가? -> 말들이 계속 머물러 있는 게 아니기 때문에 무의미
    static Map<Integer, int[]> map;

    public static boolean isPossible(int num) {
        // 이동 대상 말에 대한 예외처리 불필요 (이도알 칸은 1~5로 고정이기 때문에 자기 자신과 만나는 경우 없음)
        if(num == 13) return true;  // 도착점은 예외
        for(int i = 0; i < 4; i++) {
            if(horses[i] == num)  // 말이 존재하는 곳으로는 이동할 수 없다
                return false;
        }
        return true;
    }

    public static boolean isBlue(int now) {
        return now == 5 || now == 18 || now == 25;
    }

    public static int getNext(int now, int move, int cnt) {
        // cnt : 현재가 몇번째 이동인지
        // move : 남은 이동 횟수
        if(move == 0 || now == 13) {  // 이동 완료 or 도착지점 도착
            return now;
        }

        if(cnt == 0 && isBlue(now)) {
            return getNext(map.get(now)[2], move - 1, cnt + 1);
        }else {
            return getNext(map.get(now)[1], move - 1, cnt + 1);
        }
    }

    public static void dfs(int dept, int score) {
        if(dept == 10) {
            ANS = Math.max(ANS, score);
            return;
        }

        int move = dices[dept];  // 이번 턴에 move 칸 이동 가능
        for(int i = 0; i < 4; i++) {
            if(horses[i] == 13) continue;
            int next = getNext(horses[i], move, 0);
            if(!isPossible(next)) continue;
            int now = horses[i];

            horses[i] = next;
            dfs(dept + 1, score + map.get(next)[0]);
            horses[i] = now;
        }
    }

    public static void process() {
        dfs(0, 0);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dices = new int[10];
        for(int i = 0; i < 10; i++) {
            dices[i] = Integer.parseInt(st.nextToken());
        }

        horses = new int[4];
//        check = new boolean[41];
        map = new HashMap<>();
        map.put(0, new int[]{0, 1, 1});
        map.put(1, new int[]{2, 2, 2});
        map.put(2, new int[]{4, 3, 3});
        map.put(3, new int[]{6, 4, 4});
        map.put(4, new int[]{8, 5, 5});
        map.put(5, new int[]{10, 14, 6});
        map.put(6, new int[]{13, 7, 7});
        map.put(7, new int[]{16, 8, 8});
        map.put(8, new int[]{19, 9, 9});
        map.put(9, new int[]{25, 10, 10});
        map.put(10, new int[]{30, 11, 11});
        map.put(11, new int[]{35, 12, 12});
        map.put(12, new int[]{40, 13, 13});
        map.put(13, new int[]{0, -1, -1});
        map.put(14, new int[]{12, 15, 15});
        map.put(15, new int[]{14, 16, 16});
        map.put(16, new int[]{16, 17, 17});
        map.put(17, new int[]{18, 18, 18});
        map.put(18, new int[]{20, 21, 19});
        map.put(19, new int[]{22, 20, 20});
        map.put(20, new int[]{24, 9, 9});
        map.put(21, new int[]{22, 22, 22});
        map.put(22, new int[]{24, 23, 23});
        map.put(23, new int[]{26, 24, 24});
        map.put(24, new int[]{28, 25, 25});
        map.put(25, new int[]{30, 29, 26});
        map.put(26, new int[]{28, 27, 27});
        map.put(27, new int[]{27, 28, 28});
        map.put(28, new int[]{26, 9, 9});
        map.put(29, new int[]{32, 30, 30});
        map.put(30, new int[]{34, 31, 31});
        map.put(31, new int[]{36, 32, 32});
        map.put(32, new int[]{38, 12, 12});
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
