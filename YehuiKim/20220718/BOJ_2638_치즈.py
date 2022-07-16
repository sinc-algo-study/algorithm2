import sys
input = sys.stdin.readline
from collections import deque


def meltingCheese(allcheese, tmpMaps):
    global cheese
    newMaps = [tmpMaps[i][:] for i in range(N)]
    while allcheese:
        cy, cx = allcheese.pop()
        side = 0
        for dy, dx in dirs:
            ncy, ncx = cy + dy, cx + dx
            if ncy < 0 or ncy >= N or ncx < 0 or ncx >= M:
                continue
            if tmpMaps[ncy][ncx] == 2:
                side += 1
        if side >= 2:
            newMaps[cy][cx] = 2
            cheese -= 1
    return newMaps


def main():
    global cheese, maps
    ans = 0
    while cheese>0:
        ans += 1
        que = deque()
        que.append((0,0))
        allcheese = []
        visit = [[0 for _ in range(M)] for _ in range(N)]
        while que:
            y,x = que.popleft()
            maps[y][x]=2
            for dy, dx in dirs:
                ny, nx = y+dy, x+dx
                if ny<0 or ny>=N or nx<0 or nx>=M:
                    continue
                if visit[ny][nx]:
                    continue
                if maps[ny][nx]==1:
                    allcheese.append((ny,nx))
                else:
                    que.append((ny,nx))
                visit[ny][nx]=1
        maps = meltingCheese(allcheese, maps)
    return ans


if __name__=='__main__':
    N, M = map(int, input().split())
    maps = []
    cheese = 0
    for _ in range(N):
        temp = list(map(int, input().split()))
        cheese += sum(temp)
        maps.append(temp)
    dirs = [(0,1),(1,0),(0,-1),(-1,0)]
    print(main())
'''
2변 이상 닿으면 녹아버림
모눈종이 맨 가장자리에는 치즈 안놓임.
=> 치즈 모두 녹는데 걸리는 시간?
100*100*(48/2)

1. 바깥쪽 2로 표시
2. 안으로 들어가기
'''