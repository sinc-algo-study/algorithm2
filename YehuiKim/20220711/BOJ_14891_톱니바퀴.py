import sys
input = sys.stdin.readline
from collections import deque


def rotatingEach(arr):
    for n, r in arr:
        #print(n, r, chains[n])
        if r==-1:
            chains[n] = chains[n][1:]+chains[n][:1]
        else:
            chains[n] = chains[n][-1:]+chains[n][:-1]
        #print(chains[n])


def rotating():
    for k in range(K):
        y, rot = rotate[k]
        y -= 1
        toRotate = [(y, rot)]  # 회전할 톱니바퀴 종류와 회전방향
        done = [(y, 2, rot), (y, 6, rot)]
        que = deque(done)
        while que:
            y, x, nowRot = que.popleft()
            dy, dx = dirs[x]
            ny, nx = y+dy, x+dx
            if ny<0 or ny>=4 or nx<0 or nx>=8:
                continue
            if chains[ny][nx] == chains[y][x]:
                continue
            toRotate.append((ny, -nowRot))
            # 다음으로 넘어갈 방향
            next = (ny, (nx+4)%8, -nowRot)
            if next not in done:
                done.append(next)
                que.append(next)
        # 회전시키기
        rotatingEach(toRotate)


if __name__=='__main__':
    chains = []
    for i in range(4):
        chains.append(list(map(int, list(input().rstrip()))))
    K = int(input())
    rotate = []
    for i in range(K):
        num, rot = map(int, input().split())
        rotate.append((num, rot))
    dirs = [0, 0, (1, 4), 0, 0, 0, (-1, -4)]
    rotating()
    ans = 0
    for i in range(4):
        ans += chains[i][0]*(2**i)
    print(ans)

'''
N(0) / S(1)
k번 회전 (1이면 시계, -1이면 반시계)

'''