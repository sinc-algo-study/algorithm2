package algo220711.boj14891_톱니바퀴;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Gear {
    int[] poles = new int[8];
    Gear(int[] poles) {
        this.poles = poles;
    }
}

class Inst {
    int target, dir;
    Inst(int target, int dir) {
        this.target = target;
        this.dir = dir;
    }
}

public class Main {

    static int K, ANS;
    static Gear[] gears;
    static Inst[] insts;

    public static void rotate(int num) {  // 시계방향 회전
        int[] poles = gears[num].poles;
        int[] temp = new int[8];
        temp[0] = poles[7];
        for(int i = 0; i < poles.length - 1; i++) {
            temp[i+1] = poles[i];
        }
        gears[num].poles = temp;
    }

    public static void reverseRotate(int num) {  // 반시계방향 회전
        int[] poles = gears[num].poles;
        int[] temp = new int[8];
        temp[7] = poles[0];
        for(int i = 1; i < poles.length; i++) {
            temp[i-1] = poles[i];
        }
        gears[num].poles = temp;
    }

    public static boolean checkDiff(int left, int right) {
        return gears[left].poles[2] != gears[right].poles[6];
    }

    public static int[] getFlags(int target, int dir) {
        int[] flags = new int[5];  // 0 : 회전X, 1 : 시계방향, 2 : 반시계방향
        flags[target] = dir;

        // 왼쪽으로 쭉쭉
        for(int i = target - 1; i >= 1; i--) {
            if(flags[i+1] != 0 && checkDiff(i, i+1)) {
                flags[i] = flags[i+1] * -1;
            }else {
                break;
            }
        }

        // 오른쪽으로 쭉쭉
        for(int i = target + 1; i <= 4; i++) {
            if(flags[i-1] != 0 && checkDiff(i-1, i)) {
                flags[i] = flags[i-1] * -1;
            }else {
                break;
            }
        }

        return flags;
    }

    public static void process() {
        for(Inst inst : insts) {
            int target = inst.target;
            int dir = inst.dir;

            // 이번 턴에 돌아가야 하는 톱니를 모두 체크
            // 연쇄 회전에 의한 2차 연쇄 회전은 고려하지 않는다

            int[] flags = getFlags(target, dir);
            for(int i = 1; i <= 4; i++) {
                if(flags[i] == 1) {         // 시계방향
                    rotate(i);
                }else if(flags[i] == -1) {  // 반시계방향
                    reverseRotate(i);
                }
            }
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        gears = new Gear[5];
        for(int i = 1; i <= 4; i++) {
            String str = br.readLine();
            int[] poles = new int[8];
            for(int j = 0; j < str.length(); j++) {
                int pole = str.charAt(j) - '0';
                poles[j] = pole;
            }
            gears[i] = new Gear(poles);
        }

        K = Integer.parseInt(br.readLine());
        insts = new Inst[K];
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            insts[i] = new Inst(target, dir);
        }
    }

    public static void output() {
        for(int i = 1; i <= 4; i++) {
            int twelve = gears[i].poles[0];
            if(twelve == 0) continue;
            if(i == 1) ANS += 1;
            else if(i == 2) ANS += 2;
            else if(i == 3) ANS += 4;
            else if(i == 4) ANS += 8;
        }
        System.out.println(ANS);
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
        output();
    }
}
