from collections import deque


def moving(y, x, dd):
    dy, dx = dirs[dd]
    ny, nx = y+dy, x+dx
    return ny, nx


def curving(one):
    global maps
    y, x, d, g = one
    maps[y][x]=1
    if g == 0:
        return
    q = deque()
    for i in range(2):
        tmp = (d+i)%4
        y, x = moving(y, x, tmp)
        q.append(tmp)
        maps[y][x] = 1
    for i in range(g-1):
        nextArr = list(q)[:]
        while q:
            now = (q.pop()+1)%4
            y, x = moving(y, x, now)
            maps[y][x]=1
            nextArr.append(now)
        q = deque(nextArr)


def check(subi, subj):
    for di, dj in [(1, 0), (0, 1), (1, 1)]:
        if not maps[subi + di][subj + dj]:
            return False
    return True


def getAns():
    ans = 0
    for i in range(NUM-1):
        for j in range(NUM-1):
            if maps[i][j] and check(i, j):
                ans += 1
    return ans


if __name__=='__main__':
    NUM = 101
    maps = [[0 for _ in range(NUM)] for _ in range(NUM)]
    N = int(input().rstrip())
    dragon = []
    for _ in range(N):
        dragon.append(tuple(map(int, input().split())))
    dirs = [(0,1),(-1,0),(0,-1),(1,0)]  # y,x
    for one in dragon:
        curving(one)
    print(getAns())

'''
0 . y
.
x
방향 0~3 : 우상좌하
1. 드래곤커브 maps에 표시하기
2. 이중for문 돌면서 maps에서 4점이 1인 정사각형 +1
    -> 이중for문(0~100, 0~100)
    -> 자기가 1이면, 우,하,대각선오른쪽아래 체크
'''