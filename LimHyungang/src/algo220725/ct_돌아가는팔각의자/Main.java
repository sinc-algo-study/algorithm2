package algo220725.ct_돌아가는팔각의자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 타겟을 기준으로 양쪽으로 회전 여부를 각각 판별한다.
 * -> 인접한 두 인덱스를 비교! (left[2] == right[6])
 * 판별하는 중 회전하지 않는 식탁을 만나면 그 방향에 대한 판별은 중단한다.
 * 회전 방향에 맞게 각 식탁을 회전시킨다.
 */

public class Main {

    static int K;
    static int[][] gears, insts;

    public static void rotate(int target, int dir) {
        int[] temp = new int[8];
        for(int i = 0; i < 8; i++) {
            if(dir == 1) {  // 시계 방향
                temp[i] = gears[target][(i + 7) % 8];
            }else {         // 반시계 방향
                temp[i] = gears[target][(i + 1) % 8];
            }
        }
        gears[target] = temp;
    }

    public static int[][] getFlags(int target, int dir) {
        int[][] flags = new int[4][2];  // flags[n][0] == 회전 여부
                                        // flags[n][1] == 회전할 경우 회전 방향

        flags[target][0] = 1;
        flags[target][1] = dir;

        int idx = target - 1;
        while(idx >= 0) {  // target 왼쪽 검사
            int[] left = gears[idx];
            int[] right = gears[idx + 1];
            if(left[2] != right[6]) {  // 회전
                flags[idx][0] = 1;
                flags[idx][1] = flags[idx + 1][1] * -1;
                idx -= 1;
            }else {                    // 회전 종료
                break;
            }
        }

        idx = target + 1;
        while(idx < 4) {  // target 오른쪽 검사
            int[] left = gears[idx - 1];
            int[] right = gears[idx];
            if(left[2] != right[6]) {
                flags[idx][0] = 1;
                flags[idx][1] = flags[idx - 1][1] * -1;
                idx += 1;
            }else {
                break;
            }
        }

        return flags;
    }

    public static void process() {
        for(int[] inst : insts) {
            int target = inst[0];
            int dir = inst[1];

            int[][] flags = getFlags(target, dir);

            for(int i = 0; i < 4; i++) {
                if(flags[i][0] == 1) {
                    rotate(i, flags[i][1]);
                }
            }
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        gears = new int[4][8];
        for(int i = 0; i < 4; i++) {
            char[] chs = br.readLine().toCharArray();
            for(int j = 0; j < 8; j++) {
                gears[i][j] = chs[j] - '0';
            }
        }

        K = Integer.parseInt(br.readLine());
        insts = new int[K][2];
        for(int i = 0; i < K; i++) {
            String[] split = br.readLine().split(" ");
            insts[i][0] = Integer.parseInt(split[0]) - 1;
            insts[i][1] = Integer.parseInt(split[1]);
        }
    }

    public static void output() {
        int ans = 0;
        for(int i = 0; i < 4; i++) {
            ans += Math.pow(2, i) * gears[i][0];
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
        output();
    }
}
