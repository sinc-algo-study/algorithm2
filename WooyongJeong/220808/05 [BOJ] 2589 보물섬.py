from collections import deque

N, M = map(int, input().split())
grid = [list(input()) for _ in range(N)]

visited = [[False for _ in range(M)] for _ in range(N)]
dxs = [-1, 0, 1, 0]
dys = [0, -1, 0, 1]


def in_range(x, y):
    return 0 <= x < N and 0 <= y < M


def can_go(x, y):
    return in_range(x, y) and not visited[x][y] and grid[x][y] == 'L'


def init_visited():
    for i in range(N):
        for j in range(M):
            visited[i][j] = False


def bfs(sx, sy):
    init_visited()

    q = deque()
    q.append((sx, sy, 0))
    visited[sx][sy] = True

    max_dist = 0

    while q:
        x, y, dist = q.popleft()
        max_dist = max(max_dist, dist)

        for dx, dy in zip(dxs, dys):
            nx, ny = x + dx, y + dy
            if not can_go(nx, ny):
                continue

            q.append((nx, ny, dist + 1))
            visited[nx][ny] = True

    return max_dist


ans = 0

for i in range(N):
    for j in range(M):
        if grid[i][j] == 'L':
            ans = max(ans, bfs(i, j))

print(ans)
