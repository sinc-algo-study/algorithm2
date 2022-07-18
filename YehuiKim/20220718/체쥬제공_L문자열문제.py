import sys
input = sys.stdin.readline


def check(org_l, arr):
    # 원소가 12개 이상
    if len(arr) != 12:
        return False
    visit = [0, 0, 0, 0]
    for l in range(12):
        if l % 3 == 0:
            # 로그 형식이 다를 경우
            if arr[l] not in keylog:
                return False
            idx = keylog.index(arr[l])
            if visit[idx]:
                return False
            visit[idx] = 1

        elif l % 3 == 1:
            if arr[l] != ":":
                return False

        else:
            # 원소 길이 100 넘으면 pass
            if len(arr[l]) > 20:
                return False
            # 97~122, 65~90만 알파벳인 점 바탕으로 걸러냄
            for i in arr[l]:
                alpha = ord(i)
                if alpha < 65 or (alpha > 90 and alpha < 97) or alpha > 122:
                    return False

    # 빈칸 포함 해당 로그 전체 길이>100이거나 중간에 다른 공백 존재
    subTotal = sum([len(i) for i in arr])
    if subTotal+11>100 or subTotal+11!=org_l:
        return False
    return True

if __name__=='__main__':
    string = input().rstrip()
    logs = eval(string)
    keylog = ["team_name", "application_name", "error_level", "message"]
    ans = 0
    for log in logs:
        logSeparated = log.split()
        if not check(len(log), logSeparated):
            ans += 1
    print(ans)

'''
200*100
0, 3, 6, 9 => 키워드
1, 4, 7,10 => :
2, 5, 8,11 => 상세

입력 1
["team_name : db application_name : dbtest error_level : info message : test", "team_name : test application_name : I DONT CARE error_level : error message : x", "team_name : ThisIsJustForTest application_name : TestAndTestAndTestAndTest error_level : test message : IAlwaysTestingAndIWillTestForever", "team_name : oberervability application_name : LogViewer error_level : error"]
입력 2
["team_name : MyTeam application_name : YourApp error_level : info messag : IndexOutOfRange", "no such file or directory", "team_name : recommend application_name : recommend error_level : info message : RecommendSucces11", "team_name : recommend application_name : recommend error_level : info message : Success!", "   team_name : db application_name : dbtest error_level : info message : test", "team_name     : db application_name : dbtest error_level : info message : test", "team_name : TeamTest application_name : TestApplication error_level : info message : ThereIsNoError"]

'''
