from collections import deque

N, M, T = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(N)]

INF = int(1e9)
WALL, SWORD = 1, 2
sword_x, sword_y = 0, 0
for i in range(N):
    for j in range(M):
        if grid[i][j] == 2:  # 전설의 검 그람
            sword_x, sword_y = i, j

DIST_WITH_SWORD = abs(N - 1 - sword_x) + abs(M - 1 - sword_y)

visited = [[False for _ in range(M)] for _ in range(N)]
dxs = [-1, 0, 1, 0]
dys = [0, -1, 0, 1]


def in_range(x, y):
    return 0 <= x < N and 0 <= y < M


def can_go(x, y):
    return in_range(x, y) and not visited[x][y] and grid[x][y] != WALL


def init():
    for i in range(N):
        for j in range(M):
            visited[i][j] = False


def bfs(ex, ey):
    init()

    q = deque()
    q.append((0, 0, 0))
    visited[0][0] = True

    while q:
        x, y, dist = q.popleft()
        if x == ex and y == ey:
            return dist

        for dx, dy in zip(dxs, dys):
            nx, ny = x + dx, y + dy
            if can_go(nx, ny):
                q.append((nx, ny, dist + 1))
                visited[nx][ny] = True

    return INF


ans = min(bfs(N - 1, M - 1), bfs(sword_x, sword_y) + DIST_WITH_SWORD)
print(ans if ans <= T else "Fail")
