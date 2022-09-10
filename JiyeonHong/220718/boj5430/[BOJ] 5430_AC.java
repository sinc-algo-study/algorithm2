package boj5430;

import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < T; i++) {
            String p=br.readLine();
            int n=Integer.parseInt(br.readLine());
            String s=br.readLine();
            sb.append(solution(p,s)+"\n");
        }

        System.out.println(sb.toString());
    }

    public static String solution(String p,String s){
        //Array -> List
        List<Integer> list=makeList(s);

        int reverse=0;
        for(int i=0;i<p.length();i++){
            char func=p.charAt(i);
            if(func == 'R'){  //함수 R : 뒤집기
                reverse++;
            }else{ //함수 D : 첫 번째 수 버리기
                if(list.size()==0){
                    return "error";
                }

                if(reverse%2==0){ //뒤집기 횟수가 짝수
                    list.remove(0);
                }else{ //뒤집기 횟수가 홀수
                    list.remove(list.size()-1);
                }
            }
        }

        if(reverse%2==1){
            Collections.reverse(list);
        }

        return makeListToString(list);
    }

    public static String makeListToString(List<Integer> list){
        StringBuilder sb=new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            if(i==list.size()-1){
                sb.append(list.get(i));
            }else{
                sb.append(list.get(i)+",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static List<Integer> makeList(String s){
        List<Integer> list=new ArrayList<>();

        if(s.equals("[]")){
            return list;
        }

        String[] arr=s.substring(1,s.length()-1).split(",");
        for(int i=0;i<arr.length;i++){
            list.add(Integer.parseInt(arr[i]));
        }
        return list;
    }


}