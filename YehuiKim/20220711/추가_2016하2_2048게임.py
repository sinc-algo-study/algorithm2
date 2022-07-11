import sys
input = sys.stdin.readline
from collections import deque


def chkLine(temp):
    if not temp:
        return deque([0]*N)
    new = deque()
    flag = False
    while temp:
        if not flag:
            bef = temp.popleft()
        flag = False
        if not temp:
            aft = 0
            break
        aft = temp.popleft()
        if bef == aft:
            new.append(bef*2)
        else:
            new.append(bef)
            flag = True
            bef = aft
            aft = 0
    if aft==0:
        new.append(bef)
    return new
    '''
    false, bef, aft=0
    false, 필요x
    true, bef(aft), aft=0
    '''


def moving(d, paramMap):
    tempMaps = [paramMap[i][:] for i in range(N)]
    # 위, 아래
    if d<2:
        for j in range(N):
            temp = deque()
            for i in range(N):
                now = tempMaps[i][j]
                if now>0:
                    if d==0:
                        temp.append(now)
                    else:
                        temp.appendleft(now)
            newTemp = chkLine(temp)
            for i in range(N):
                if d==0:
                    if newTemp:
                        tempMaps[i][j] = newTemp.popleft()
                    else:
                        tempMaps[i][j] = 0
                else:
                    if newTemp:
                        tempMaps[N-i-1][j] = newTemp.popleft()
                    else:
                        tempMaps[N-i-1][j] = 0
    # 좌, 우
    else:
        for i in range(N):
            temp = deque()
            for j in range(N):
                now = tempMaps[i][j]
                if now>0:
                    if d==2:
                        temp.append(now)
                    else:
                        temp.appendleft(now)
            newTemp = list(chkLine(temp))
            if d==2:
                tempMaps[i] = newTemp + [0]*(N-len(newTemp))
            else:
                tempMaps[i] = [0]*(N-len(newTemp)) + [newTemp[i] \
                                        for i in range(len(newTemp)-1, -1, -1)]
    return tempMaps


def moveGrid(cnt, nowMaps):
    if cnt>=5:
        ans.append(max([max(nowMaps[i]) for i in range(N)]))
        return
    for d in range(4):
        newMaps = moving(d, nowMaps)
        moveGrid(cnt+1, newMaps)

'''
[[8, 4, 2, 8], [4, 8, 8, 8], [0, 2, 2, 0], [0, 0, 0, 0]]
[[8, 4, 2, 16], [4, 8, 8, 0], [0, 2, 2, 0], [0, 0, 0, 0]]
[[8, 4, 2, 16], [4, 8, 8, 0], [0, 2, 2, 0], [0, 0, 0, 0]]
[[0, 0, 0, 0], [0, 4, 2, 0], [8, 8, 8, 0], [4, 2, 2, 16]]
[[0, 0, 0, 0], [0, 4, 2, 0], [8, 8, 8, 0], [4, 2, 2, 16]]

[[8, 4, 2, 16], [0, 0, 4, 16], [0, 0, 0, 4], [0, 0, 0, 0]]
'''

if __name__ == '__main__':
    N = int(input().rstrip())
    maps = []
    for i in range(N):
        maps.append(list(map(int, input().split())))
    ans = []
    moveGrid(0, maps)
    print(max(ans))

'''
4*4 격자판 주어졌을 때, 5번 움직인 이후 격자판에서 가장 큰 값의 최댓값은?

'''