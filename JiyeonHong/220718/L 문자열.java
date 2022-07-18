class Main{
    public static void main(String[] args) {
//        String[] logs= {
//                "team_name : db application_name : dbtest error_level : info message : test",
//                "team_name : test application_name : I DONT CARE error_level : error message : x",
//                "team_name : ThisIsJustForTest application_name : TestAndTestAndTestAndTest error_level : test message : IAlwaysTestingAndIWillTestForever",
//                "team_name : oberervability application_name : LogViewer error_level : error"
//        };
        String[] logs={
            "team_name : MyTeam application_name : YourApp error_level : info messag : IndexOutOfRange",
            "no such file or directory", "team_name : recommend application_name : recommend error_level : info message : RecommendSucces11",
            "team_name : recommend application_name : recommend error_level : info message : Success!",
            "   team_name : db application_name : dbtest error_level : info message : test",
            "team_name     : db application_name : dbtest error_level : info message : test",
            "team_name : TeamTest application_name : TestApplication error_level : info message : ThereIsNoError"
        };

        System.out.println(solution(logs));
    }

    public static int solution(String[] logs){
        int answer=0;
        for (int i = 0; i < logs.length; i++) {
            String log=logs[i];
            if(log.length()>100 || !isPatternNormal(log)){
                answer++;

            }
        }
        return answer;
    }

    public static boolean isPatternNormal(String log){
        String[] arr=log.split(" ");

        //공백으로 구별되는지
        if(arr.length!=12){
            return false;
        }

        //"team_name : t application_name : a error_level : e message : m" 형식인지
        String[] key={"team_name","application_name","error_level","message"};
        int[] keyIdx={0,3,6,9};
        int[] dotIdx={1,4,7,10};
        int[] valueIdx={2,5,8,11};
        for (int i = 0; i < key.length; i++) {
            if(!arr[keyIdx[i]].equals(key[i])){
                return false;
            }

            if(!arr[dotIdx[i]].equals(":")){
                return false;
            }

            if(!arr[valueIdx[i]].matches("^[a-zA-Z]*$")){
                return false;
            }
        }
        return true;
    }
}