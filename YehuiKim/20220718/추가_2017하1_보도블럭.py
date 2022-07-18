import sys
input = sys.stdin.readline


def checkVisit(i,j,t,k):
    if not t:
        for c in range(L):
            if visit[i][j-k-c]:
                return False
        for c in range(L):
            visit[i][j-k-c] = 1
        return True
    for c in range(L):
        if visit[i-k-c][j]:
            return False
    for c in range(L):
        visit[i-k-c][j] = 1
    return True


def checkHeight():
    global ans
    for i in range(n):
        # 행체크
        now = maps[i][0]
        installFlag = False
        fcnt = 1
        for j in range(1, n):
            nex = maps[i][j]
            if now==nex:
                fcnt += 1
                if installFlag and fcnt>=L:
                    installFlag = False
                    if not checkVisit(i,j,0,0):
                        break
                    fcnt = 0
            elif now-nex==1:
                if fcnt<L and installFlag:
                    break
                fcnt = 1
                if L!=1:
                    installFlag = True
            elif now-nex==-1:
                if fcnt < L: #  and installFlag
                    break
                if not checkVisit(i, j, 0,1):
                    break
                fcnt = 1
                installFlag = False
            else:
                break
            now = nex
            if j==n-1 and not installFlag:
                ans += 1

        now = maps[0][i]
        installFlag = False
        fcnt = 1
        # 열체크
        for j in range(1,n):
            nex = maps[j][i]
            if now==nex:
                fcnt += 1
                if installFlag and fcnt>=L:
                    installFlag = False
                    if not checkVisit(i,j,1,0):
                        break
                    fcnt = 0
            elif now-nex==1:
                if fcnt<L and installFlag:
                    break
                if not checkVisit(i,j,1,1):
                    break
                fcnt = 1
                if L!=1:
                    installFlag = True
            elif now-nex==-1:
                if fcnt < L: #  and installFlag
                    break
                fcnt = 1
                installFlag = False
            else:
                break
            now = nex
            if j==n-1 and not installFlag:
                ans += 1


if __name__=='__main__':
    n, L = map(int, input().split())
    maps = []
    for i in range(n):
        maps.append(list(map(int, input().split())))
    ans = 0
    visit = [[0 for _ in range(n)] for _ in range(n)]
    checkHeight()
    print(ans)
'''
보도블럭 높이
높이 차이 1 나는 보도블럭에 설치가능하되, 길이 L동안 접촉가능
- L동안 보도블럭 높이 모두 같아야 한다
=> 지나갈 수 있는 열과행의 총 개수?

n 최대 100, L 최대 100, 보도블럭 높이 최대 10
for 

'''