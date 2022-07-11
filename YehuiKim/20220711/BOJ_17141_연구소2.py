import sys
input = sys.stdin.readline
from itertools import combinations
from collections import deque


def checkSec(temp):
    visit = [[0 for _ in range(N)] for _ in range(N)]
    newMap = [maps[i][:] for i in range(N)]
    que = deque()
    for i,j in temp:
        que.append((i,j,0))
    while que:
        i, j, cnt = que.popleft()
        visit[i][j]=1
        for di,dj in dirs:
            ni,nj = i+di, j+dj
            if ni<0 or ni>=N or nj<0 or nj>=N:
                continue
            if maps[ni][nj]==-1:
                continue
            if 0<newMap[ni][nj]<=(cnt+1):
                continue
            if (ni, nj) in temp:
                continue
            que.append((ni,nj,cnt+1))
            newMap[ni][nj]=cnt+1
    if sum([sum(visit[i]) for i in range(N)]) != (N**2-wallCnt):
        return -1
    else:
        return max([max(newMap[i]) for i in range(N)])


if __name__=='__main__':
    N, M = map(int, input().split())
    maps = []
    virusZones = []
    answer = []
    wallCnt = 0
    for i in range(N):
        temp = list(map(int, input().split()))
        for j in range(N):
            if temp[j] == 2:
                virusZones.append((i,j))
                temp[j] = 0
            elif temp[j] == 1:
                temp[j] = -1
                wallCnt += 1
        maps.append(temp)
    dirs = [(0,1),(1,0),(0,-1),(-1,0)]
    combis = combinations(virusZones, M)
    for combi in combis:
        res = checkSec(combi)
        if res!=-1:
            answer.append(res)
    if answer:
        print(min(answer))
    else:
        print(-1)


'''
바이러스 M개
1초만에 상하좌우 인접한 모든 빈칸으로 동시에 복제됨
0 빈칸, 1 벽 =>-, -2 바이러스 놓을 수 있는 칸
 => 모든 칸에 바이러스 퍼뜨리는 최소 시간은?
    (놓을 수 없으면 -1)
    
0.
- 바이러스 놓을 수 있는 칸으로 리스트 만들기
- 위 리스트로 M개짜리 조합 만들기
1. 위 리스트 돌면서 확인하기
50*50짜리 맵을 10Cm 가짓수로 돌기
2500 * 5*3*8 * 3

97% 틀렸습니다
5 2
1 1 1 1 1
1 1 2 1 1
1 1 2 1 1
1 1 1 1 1
1 1 1 1 1
'''