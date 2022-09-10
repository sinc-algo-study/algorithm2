from collections import deque
import sys

dx = [1,0,-1,0]
dy = [0,1,0,-1]

n, m = map(int, sys.stdin.readline().split())
tomato = [list(map(int, sys.stdin.readline().split())) for _ in range(m)]
q = deque()

for i in range(m):
    for j in range(n):
        if tomato[i][j] == 1:
            q.append((i, j, 0))

while q:
    x, y, cnt = q.popleft()
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0<=nx<m and 0<=ny<n and not tomato[nx][ny]:
            tomato[nx][ny] = 1
            q.append((nx, ny, cnt + 1))

for box in tomato:
    if 0 in box:
        print(-1)
        exit()
else:
    print(cnt)