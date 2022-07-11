package algo220711.ct_정육면체돌리기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, R, C, K;
    static int[][] map;
    static int[] cube, dirs;
    static int[] rArr = {0, 0, 0, -1, 1}; // 1, 2, 3, 4 == 동, 서, 북, 남
    static int[] cArr = {0, 1, -1, 0, 0};

    public static int[] rotate(int dir) {
        int[] result = new int[6];

        switch(dir) {
            case 1:  // 동
                result[0] = cube[0];
                result[1] = cube[5];
                result[2] = cube[2];
                result[3] = cube[4];
                result[4] = cube[1];
                result[5] = cube[3];
                break;
            case 2:  // 서
                result[0] = cube[0];
                result[1] = cube[4];
                result[2] = cube[2];
                result[3] = cube[5];
                result[4] = cube[3];
                result[5] = cube[1];
                break;
            case 3:  // 남
                result[0] = cube[1];
                result[1] = cube[2];
                result[2] = cube[3];
                result[3] = cube[0];
                result[4] = cube[4];
                result[5] = cube[5];
                break;
            case 4:  // 북
                result[0] = cube[3];
                result[1] = cube[0];
                result[2] = cube[1];
                result[3] = cube[2];
                result[4] = cube[4];
                result[5] = cube[5];
                break;
            default:
                break;
        }

        if(map[R][C] == 0) {
            map[R][C] = result[1];
        }else {
            result[1] = map[R][C];
            map[R][C] = 0;
        }

        return result;
    }

    public static void process() {
        for(int i = 0; i < K; i++) {
            int dir = dirs[i];
            int nr = R + rArr[dir];
            int nc = C + cArr[dir];

            if(!(-1 < nr && nr < N && -1 < nc && nc < M)) continue;
            R = nr;
            C = nc;

            cube = rotate(dir);
            System.out.println(cube[3]);
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cube = new int[6];
        dirs = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            dirs[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        process();
    }
}
