import re


def solution(logs):
    # "team_name : t application_name : a error_level : e message : m"
    pattern = r"team_name : [a-zA-Z]+ application_name : [a-zA-Z]+ " \
              r"error_level : [a-zA-Z]+ message : [a-zA-Z]+"
    p = re.compile(pattern)

    ans = 0
    for log in logs:
        if len(log) > 100 or p.fullmatch(log) is None:
            ans += 1
    return ans


if __name__ == '__main__':
    logs_list = [
        # case 1) 정답 3
        [
            "team_name : db application_name : dbtest error_level : info message : test",
            "team_name : test application_name : I DONT CARE error_level : error message : x",
            "team_name : ThisIsJustForTest application_name : TestAndTestAndTestAndTest error_level : test message : IAlwaysTestingAndIWillTestForever",
            "team_name : oberervability application_name : LogViewer error_level : error"],
        # case 2) 정답 6
        [
            "team_name : MyTeam application_name : YourApp error_level : info messag : IndexOutOfRange",
            "no such file or directory",
            "team_name : recommend application_name : recommend error_level : info message : RecommendSucces11",
            "team_name : recommend application_name : recommend error_level : info message : Success!",
            "   team_name : db application_name : dbtest error_level : info message : test",
            "team_name     : db application_name : dbtest error_level : info message : test",
            "team_name : TeamTest application_name : TestApplication error_level : info message : ThereIsNoError"]
    ]

    for logs in logs_list:
        print(solution(logs))
