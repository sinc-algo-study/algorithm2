import sys
input = sys.stdin.readline
from collections import deque


def rotating(n, d, visit):
    visit[n]=1
    for r in [2, 6]:
        di, dj = dirs[r]
        ni, nj = n+di, r+dj
        if ni<0 or ni>=4 or nj<0 or nj>=8:
            continue
        if visit[ni]:
            continue
        if region[ni][nj]==region[n][r]:
            continue
        rotating(ni, -d, visit)
    region[n].rotate(d)


if __name__=='__main__':
    region = []
    for i in range(4):
        temp = list(map(int, list(input().rstrip())))
        region.append(deque(temp))
    K = int(input().rstrip())
    rotation = []
    for i in range(K):
        n, d = map(int,input().split())
        rotation.append((n-1, d))
    dirs = {2:(1, 4), 6:(-1, -4)}
    for n, d in rotation:
        rotating(n, d, [0 for _ in range(4)])
    ans = 0
    for i in range(4):
        ans += region[i][0]*2**(i)
    print(ans)
'''
0 북 1 남
1 시계 -1 반시계
'''