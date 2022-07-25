import sys
input = sys.stdin.readline
from collections import deque


def checkSafetyZone(tempMap):
    newMaps = [tempMap[i][:] for i in range(N)]
    visit = [[0 for _ in range(M)] for _ in range(N)]
    q = deque(virus)
    while q:
        i,j = q.popleft()
        visit[i][j] = 1
        for di,dj in dirs:
            ni,nj = i+di,j+dj
            if ni<0 or ni>=N or nj<0 or nj>=M:
                continue
            if visit[ni][nj]:
                continue
            if newMaps[ni][nj]==1:
                continue
            q.append((ni,nj))
            newMaps[ni][nj]=2
    res = 0
    for i in range(N):
        for j in range(M):
            if newMaps[i][j]==0:
                res += 1
    return res


def checkVirusZone():
    ans = 0
    lenE = len(empty)
    for w1 in range(lenE-2):
        wi1, wj1 = empty[w1]
        maps[wi1][wj1] = 1
        for w2 in range(w1+1, lenE-1):
            wi2, wj2 = empty[w2]
            maps[wi2][wj2] = 1
            for w3 in range(w2+1, lenE):
                wi3, wj3 = empty[w3]
                maps[wi3][wj3] = 1
                ans = max(ans, checkSafetyZone(maps))
                maps[wi3][wj3] = 0
            maps[wi2][wj2] = 0
        maps[wi1][wj1] = 0
    return ans


if __name__=='__main__':
    N, M = map(int,input().split())
    maps = []
    empty = []
    virus = []
    for i in range(N):
        temp = list(map(int,input().split()))
        maps.append(temp)
        for j in range(M):
            if temp[j]==0:
                empty.append((i,j))
            elif temp[j]==2:
                virus.append((i,j))
    dirs = [(0,1),(0,-1),(1,0),(-1,0)]
    print(checkVirusZone())
'''
'''