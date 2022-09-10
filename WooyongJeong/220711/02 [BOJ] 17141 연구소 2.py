"""
바이러스를 놓을 수 있는 칸 <= 10, M <= 10
-> 완탐 낭낭하게 가능
"""
from collections import deque
from itertools import combinations

INF = int(1e9)
WALL = 1
N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

dxs = [1, 0, -1, 0]
dys = [0, 1, 0, -1]
new_board = [[-1 for _ in range(N)] for _ in range(N)]
ans = INF


def in_range(x, y):
    return 0 <= x < N and 0 <= y < N


def can_go(x, y):
    return in_range(x, y) and board[x][y] != WALL and new_board[x][y] == -1


def init():
    for i in range(N):
        for j in range(N):
            new_board[i][j] = -1


def spread_viruses(viruses):
    q = deque()
    for x, y in viruses:
        q.append((x, y))
        new_board[x][y] = 0

    spread_time = 0
    while q:
        x, y = q.popleft()

        for dx, dy in zip(dxs, dys):
            nx, ny = x + dx, y + dy
            if can_go(nx, ny):
                q.append((nx, ny))
                new_board[nx][ny] = new_board[x][y] + 1
                spread_time = max(spread_time, new_board[nx][ny])

    return spread_time


def simulate(viruses):
    init()

    spread_time = spread_viruses(viruses)

    for i in range(N):
        for j in range(N):
            if new_board[i][j] == -1 and board[i][j] != WALL:
                return INF
    return spread_time


viruses = []
for i in range(N):
    for j in range(N):
        if board[i][j] == 2:
            viruses.append((i, j))

for candidate in combinations(viruses, M):
    ans = min(ans, simulate(candidate))
print(ans if ans != INF else -1)
