from itertools import combinations
from collections import deque


def spread(virus):
    cnt = 0
    q = deque(virus)
    visited = [[-1]*n for _ in range(n)]
    for x, y in virus:
        visited[x][y] = 0
    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < n and 0 <= ny < n:
                if lab[nx][ny] != 1 and visited[nx][ny] == -1:
                    q.append((nx, ny))
                    visited[nx][ny] = visited[x][y] + 1
                    cnt = max(cnt, visited[nx][ny])
    for i in range(n):
        for j in range(n):
            if visited[i][j] == -1 and lab[i][j] != 1:
                return float("inf")
    return cnt


n, m = map(int, input().split())
lab = [list(map(int, input().split())) for _ in range(n)]
answer = float("inf")

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

virus = []
for i in range(n):
    for j in range(n):
        if lab[i][j] == 2:
            virus.append((i, j))

for cb in combinations(virus, m):
    answer = min(spread(cb), answer)

if answer == float("inf"):
    print(-1)
else:
    print(answer)


