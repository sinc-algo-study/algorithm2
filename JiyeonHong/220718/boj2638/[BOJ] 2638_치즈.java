package boj2638;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N,M;
    static int[][] board;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int cheeseCnt=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < s.length; j++) {
                board[i][j] = Integer.parseInt(s[j]);
                if(board[i][j]==1){
                    cheeseCnt++;
                }
            }
        }

        System.out.println(solution());
    }

    public static int solution() {
        int turn=0;
        while(cheeseCnt>0){
            //1. 치즈 내부에 있는 공간 판별
            findInnerAir();
            //2. 녹을 치즈 판별
            //3. 치즈 녹이기
            meltCheese();
            turn++;
        }
        return turn;
    }

    public static void meltCheese(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(board[i][j]==1 && isOuterAirOver2(i,j)){
                    board[i][j]=0;
                    cheeseCnt--;
                }
            }
        }
    }

    public static boolean isOuterAirOver2(int r,int c){
        int outerAirCnt=0;
        for (int d = 0; d < 4; d++) {
            int nr=r+dir[d][0];
            int nc=c+dir[d][1];

            if(nr<0 || nr>=N || nc<0 || nc>=M){
                continue;
            }

            if(board[nr][nc]==2){
                outerAirCnt++;
            }
        }

        if(outerAirCnt>=2){
            return true;
        }
        return false;
    }

    public static void findInnerAir() {
        // 0: 빈 공간, 1: 치즈
        // -> 0: 치즈 내부 공간, 1: 치즈, 2: 치즈 외부 공간
        boolean[][] visited=new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0]=true;

        while (!queue.isEmpty()) { //bfs
            int[] now= queue.poll();
            board[now[0]][now[1]]=2;

            for(int d=0;d<4;d++){
                int nr=now[0]+dir[d][0];
                int nc=now[1]+dir[d][1];

                if(nr<0 || nr>= N || nc<0 || nc>=M){
                    continue;
                }

                if(visited[nr][nc]){
                    continue;
                }

                if(board[nr][nc]!=1){ //치즈가 아니면
                    queue.add(new int[]{nr,nc});
                    visited[nr][nc]=true;
                }

            }
        }
    }

}