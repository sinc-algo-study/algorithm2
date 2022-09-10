package algo220718.lotte_문자열기출;

/**
 * 1. 로그는 "team_name : t application_name : a error_level : e message : m" 형식이어야 합니다.
 * 2. t, a, e, m은 알파벳 소문자 혹은 알파벳 대문자로만 이루어진 길이 1 이상의 문자열입니다.
 * 3. team_name, application_name, error_level, message, :, t, a, e, m는 한 칸의 공백으로 구분되어 있어야 합니다.
 * 4. 로그의 길이는 100 이하여야 합니다.
 */

class Solution {

    public boolean checkFirst(String[] split) {
        // 1. 로그는 "team_name : t application_name : a error_level : e message : m" 형식이어야 합니다.

        if(split.length != 5) return false;
        if(!split[0].equals("team_name")) return false;
        if(!split[1].split(" ")[1].equals("application_name")) return false;
        if(!split[2].split(" ")[1].equals("error_level")) return false;
        if(!split[3].split(" ")[1].equals("message")) return false;

        return true;
    }

    public boolean checkSecond(String[] split) {
        // 2. t, a, e, m은 알파벳 소문자 혹은 알파벳 대문자로만 이루어진 길이 1 이상의 문자열입니다.

        String t = split[1].split(" ")[0];
        String a = split[2].split(" ")[0];
        String e = split[3].split(" ")[0];
        String m = split[4];

        for(int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if(!('a' <= ch && ch <= 'z') && !('A' <= ch && ch <= 'Z')) {
                return false;
            }
        }

        for(int i = 0; i < a.length(); i++) {
            char ch = a.charAt(i);
            if(!('a' <= ch && ch <= 'z') && !('A' <= ch && ch <= 'Z')) {
                return false;
            }
        }

        for(int i = 0; i < e.length(); i++) {
            char ch = e.charAt(i);
            if(!('a' <= ch && ch <= 'z') && !('A' <= ch && ch <= 'Z')) {
                return false;
            }
        }

        for(int i = 0; i < m.length(); i++) {
            char ch = m.charAt(i);
            if(!('a' <= ch && ch <= 'z') && !('A' <= ch && ch <= 'Z')) {
                return false;
            }
        }

        return true;
    }

    public boolean checkThird(String[] split) {
        // 3. team_name, application_name, error_level, message, :, t, a, e, m는 한 칸의 공백으로 구분되어 있어야 합니다.

        // team_name : t application_name : a error_level : e message : m
        if(split.length != 12) {
            return false;
        }

        return true;
    }

    public int solution(String[] logs) {
        int answer = 0;  // 수집하지 않는 로그의 수 반환
        for(String log : logs) {
            String[] split1 = log.split(" : ");
            String[] split2 = log.split(" ");
            if(log.length() >= 100 ||
                    !checkFirst(split1) ||
                    !checkSecond(split1) ||
                    !checkThird(split2)) {
                answer += 1;
            }
        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 히든 케이스까지 고려된 게 맞는지를 모르겠네..

//        # case1
//        logs: ["team_name : db application_name : dbtest error_level : info message : test", "team_name : test application_name : I DONT CARE error_level : error message : x", "team_name : ThisIsJustForTest application_name : TestAndTestAndTestAndTest error_level : test message : IAlwaysTestingAndIWillTestForever", "team_name : oberervability application_name : LogViewer error_level : error"]
//        result: 3

//        # case2
//        logs: ["team_name : MyTeam application_name : YourApp error_level : info messag : IndexOutOfRange", "no such file or directory", "team_name : recommend application_name : recommend error_level : info message : RecommendSucces11", "team_name : recommend application_name : recommend error_level : info message : Success!", "   team_name : db application_name : dbtest error_level : info message : test", "team_name     : db application_name : dbtest error_level : info message : test", "team_name : TeamTest application_name : TestApplication error_level : info message : ThereIsNoError"]
//        result: 6

        String[] logs = {"team_name : MyTeam application_name : YourApp error_level : info messag : IndexOutOfRange", "no such file or directory", "team_name : recommend application_name : recommend error_level : info message : RecommendSucces11", "team_name : recommend application_name : recommend error_level : info message : Success!", "   team_name : db application_name : dbtest error_level : info message : test", "team_name     : db application_name : dbtest error_level : info message : test", "team_name : TeamTest application_name : TestApplication error_level : info message : ThereIsNoError"};
        int ans = sol.solution(logs);
        System.out.println(ans);
    }
}
