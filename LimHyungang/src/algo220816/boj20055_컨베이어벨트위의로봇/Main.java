package algo220816.boj20055_컨베이어벨트위의로봇;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1~N을 2번 돌려야 모든 칸의 내구도를 1씩 소모한다
 * -> 1~N을 최대 2천번 돌리면 모든 칸의 최대내구도를 전부 없앨 수 있다
 * -> 시간 넉넉할 듯?
 */

public class Main {

    static int N, K, S, E, CNT, SIZE, ANS = 1;
    static int[] A;
    static boolean finish;
    static Deque<Integer> deq, temp;

    public static void moveRobot(int idx) {
        A[idx] -= 1;
        if(A[idx] == 0)
            CNT += 1;

        if(idx != E) {
            temp.addFirst(idx);
        }
    }
    
    public static void doOne() {
        // 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
        S = (S + (SIZE - 1)) % SIZE;
        E = (E + (SIZE - 1)) % SIZE;

        if(!deq.isEmpty() && deq.peekLast() == E) {
            deq.pollLast();
        }
    }

    public static void doTwo() {
        // 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다.
        // 만약 이동할 수 없다면 가만히 있는다
        // -> 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.

        temp = new ArrayDeque<>();
        
        while(!deq.isEmpty()) {
            int now = deq.pollLast();
            int next = (now + 1) % SIZE;
            
            if(A[next] <= 0) {  // 더이상 내구도 없음
                temp.addFirst(now);
            }else {
                // 내 앞에 로봇이 있는지만 검사하면 됨
                if(temp.isEmpty()) {  // 내 앞에 로봇 없음
                    moveRobot(next);
                }else if(temp.peekFirst() != next) {  // 내 앞에 로봇 없음
                    moveRobot(next);
                }else if(temp.peekFirst() == next) {  // 내 앞에 로봇 있음
                    temp.addFirst(now);  // 그대로 제자리에 남는다.
                }
            }
        }

        deq = temp;
    }

    public static void doThree() {
        // 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
        if(A[S] > 0) {
            deq.addFirst(S);
            A[S] -= 1;
            if(A[S] == 0)
                CNT += 1;
        }
    }

    public static void doFour() {
        // 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
        if(CNT >= K)
            finish = true;
    }

    public static void process() {
        while(true) {
            doOne();
            doTwo();
            doThree();
            doFour();
            if(finish) break;
            else ANS += 1;
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        SIZE = 2 * N;
        S = 0;
        E = N - 1;
        A = new int[2 * N];
        deq = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2 * N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
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
