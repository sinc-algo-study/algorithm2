package boj2174;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main{
    static Map<Integer,String> robotDirections=new HashMap<>();
    static Map<Integer,int[]> robotPlaces=new HashMap<>();
    static int A;
    static int B;
    static int N;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        A=Integer.parseInt(st.nextToken());
        B=Integer.parseInt(st.nextToken());
        board=new int[B][A];

        st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            String[] arr=br.readLine().split(" ");
            int x=Integer.parseInt(arr[0]);
            int y=Integer.parseInt(arr[1]);
            String d=arr[2];

            robotDirections.put(i,d);
            robotPlaces.put(i,new int[]{B-y,x-1});
        }

        String[] orders=new String[M];
        for (int i = 0; i < M; i++) {
            orders[i]= br.readLine();
        }

        System.out.println(solution(orders));
    }

    public static String solution(String[] orders){
        for (String order : orders) {
            String[] tmp=order.split(" ");
            int robot=Integer.parseInt(tmp[0]);
            String orderKind=tmp[1];
            int count=Integer.parseInt(tmp[2]);

            String result=executOrder(robot,orderKind,count);
            if(result!="OK"){
                return result;
            }
        }
        return "OK";
    }

    public static String executOrder(int robot,String orderKind,int count){
        String originDirection=robotDirections.get(robot);
        if(orderKind.equals("F")){
            return executeF(robot,orderKind,count,originDirection);
        }
        return  executeElse(robot,orderKind,count,originDirection);
    }

    public static String executeElse(int robot, String orderKind, int count, String originDirection){
        String changedDirection=originDirection;
        for (int i = 0; i < count; i++) {
            changedDirection=rotateDirection(orderKind,changedDirection);
        }
        robotDirections.put(robot,changedDirection);
        return "OK";
    }

    public static String executeF(int robot, String orderKind, int count, String originDirection){
        String changedDirection=rotateDirection(orderKind,originDirection);
        int[] change=getDirectionChange(changedDirection);
        int[] place=robotPlaces.get(robot);
        for(int i=0;i<count;i++){
            place[0]+=change[0];
            place[1]+=change[1];

            //주어진 땅 밖으로 벗어날 경우
            if(place[0]<0 || place[0]>=B || place[1]<0 || place[1]>=A){
                return "Robot "+robot+" crashes into the wall";
            }

            //다른 로봇과 충돌
            int crashRobot=isCrashOtherRobots(robot,place[0],place[1]);
            if(crashRobot!=0){
                return "Robot "+robot+" crashes into robot "+crashRobot;
            }
        }
        robotDirections.put(robot,changedDirection);
        robotPlaces.put(robot,place);
        return "OK";
    }

    public static int isCrashOtherRobots(int robot, int r, int c){
        for (int num = 1; num <= N; num++) {
            if(num==robot){ //자기 자신
                continue;
            }

            int[] place=robotPlaces.get(num);
            if(place[0]==r && place[1]==c){
                return num;
            }
        }
        return 0;
    }

    public static String rotateDirection(String orderKind,String originDirection){
        String direction=originDirection;
        if(orderKind.equals("L")){
            if(originDirection.equals("N")){
                direction="W";
            }else if(originDirection.equals("E")){
                direction="N";
            }else if(originDirection.equals("S")){
                direction="E";
            }else{
                direction="S";
            }
        }else if(orderKind.equals("R")){
            if(originDirection.equals("N")){
                direction="E";
            }else if(originDirection.equals("E")){
                direction="S";
            }else if(originDirection.equals("S")){
                direction="W";
            }else{
                direction="N";
            }
        }
        return direction;
    }

    public static int[] getDirectionChange(String direction){
        int[] ret={0,0};
        switch (direction){
            case "N":
                ret[0]=-1;
                break;
            case "E":
                ret[1]=1;
                break;
            case "S":
                ret[0]=1;
                break;
            case "W":
                ret[1]=-1;
                break;
        }
        return ret;
    }
}