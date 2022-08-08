import sys
from itertools import permutations
from collections import deque
import copy

input = sys.stdin.readline


def spread():
    global cnt
    q = deque(virus)
    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < n and 0 <= ny < m and not lab[nx][ny]:
                q.append((nx, ny))
                lab[nx][ny] = 2
    for l in lab:
        cnt += l.count(0)
    return cnt


n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
blank = []
virus = []

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]
answer = 0

for i in range(n):
    for j in range(m):
        if not graph[i][j]:
            blank.append((i, j))
        if graph[i][j] == 2:
            virus.append((i, j))

for pm in permutations(blank, 3):
    lab = copy.deepcopy(graph)

    cnt = 0
    for x, y in pm:
        lab[x][y] = 1
    cnt = spread()

    if cnt > answer:
        answer = cnt

print(answer)