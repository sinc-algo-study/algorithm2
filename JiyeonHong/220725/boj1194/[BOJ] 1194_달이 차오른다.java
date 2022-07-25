package boj1194;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
    static char[][] maze;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        maze=new char[N][M];
        int[] start=new int[2];
        for (int i = 0; i < N; i++) {
            String s=br.readLine();
            for (int j = 0; j < s.length(); j++) {
                maze[i][j]=s.charAt(j);
                if(maze[i][j]=='0'){
                    start[0]=i;
                    start[1]=j;
                }
            }
        }

        System.out.println(solution(start));
    }

    public static int solution(int[] start){
        boolean[][][] visited=new boolean[64][maze.length][maze[0].length];
        int[][] dir={{-1,0},{1,0},{0,-1},{0,1}};

        Queue<Place> queue=new LinkedList<>();
        queue.add(new Place(start[0],start[1],0,0));
        visited[0][start[0]][start[1]]=true;

        while(!queue.isEmpty()){
            Place now= queue.poll();

            //출구
            if(maze[now.r][now.c]=='1'){
                return now.count;
            }

            for (int i = 0; i < 4; i++) {
                int nr=now.r+dir[i][0];
                int nc=now.c+dir[i][1];

                //범위 밖
                if(nr<0 || nr>= maze.length || nc<0 || nc>=maze[0].length){
                    continue;
                }

                if(visited[now.keys][nr][nc]){
                    continue;
                }

                //벽이면
                if(maze[nr][nc]=='#'){
                    continue;
                }

                if(maze[nr][nc]=='.' || maze[nr][nc]=='0' || maze[nr][nc]=='1'){
                    visited[now.keys][nr][nc]=true;
                    queue.add(new Place(nr,nc, now.count+1, now.keys));
                }
                //문이면
                if('A' <= maze[nr][nc] &&  maze[nr][nc] <= 'F'){
                    int door=1<<(maze[nr][nc]-'A'); // 정수 1의 각 비트를 (maze[nr][nc]-'A')만큼 왼쪽으로 이동(빈자리는 0으로 채워짐)
                    if((now.keys & door)>0){
                        visited[now.keys][nr][nc]=true;
                        queue.add(new Place(nr,nc, now.count+1, now.keys));
                    }
                }

                //열쇠이면
                if('a' <= maze[nr][nc] &&  maze[nr][nc] <= 'f'){
                    int newKey=1<<(maze[nr][nc]-'a');
                    newKey=newKey|now.keys;
                    if(!visited[newKey][nr][nc]){
                        visited[now.keys][nr][nc]=true;
                        visited[newKey][nr][nc]=true;
                        queue.add(new Place(nr,nc, now.count+1, newKey));
                    }
                }
            }
        }
        return -1;
    }
}

class Place{
    int r;
    int c;
    int count;
    int keys;

    public Place(int r, int c, int count, int keys) {
        this.r = r;
        this.c = c;
        this.count = count;
        this.keys = keys;
    }
}