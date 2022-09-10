import collections

EMPTY, CHEESE = 0, 1
N, M = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(N)]
next_grid = [[0 for _ in range(M)] for _ in range(N)]

visited = [[False for _ in range(M)] for _ in range(N)]
dxs = [1, 0, -1, 0]
dys = [0, 1, 0, -1]

ans = 0


def in_range(x, y):
    return 0 <= x < N and 0 <= y < M


def can_go(x, y):
    return in_range(x, y) and not visited[x][y]


def before_each():
    for i in range(N):
        for j in range(M):
            next_grid[i][j] = grid[i][j]
            visited[i][j] = False


def after_each():
    for i in range(N):
        for j in range(M):
            if grid[i][j] > 2:  # 치즈의 2변 이상이 공기와 접촉하면 녹아 없어짐
                next_grid[i][j] = EMPTY
            grid[i][j] = next_grid[i][j]


def melt_cheese():
    before_each()

    q = collections.deque()
    q.append((0, 0))
    visited[0][0] = True

    while q:
        x, y = q.popleft()
        for dx, dy in zip(dxs, dys):
            nx, ny = x + dx, y + dy
            if not can_go(nx, ny):
                continue

            if grid[nx][ny] != EMPTY:  # 치즈
                grid[nx][ny] += 1  # 공기와 닿은 횟수 증가
            else:  # 공기
                visited[nx][ny] = True
                q.append((nx, ny))

    after_each()


def is_cheese_there():
    for i in range(N):
        for j in range(M):
            if grid[i][j] == CHEESE:
                return True
    return False


def simulate():
    global ans

    while is_cheese_there():
        melt_cheese()
        ans += 1


simulate()
print(ans)
