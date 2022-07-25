from itertools import combinations
from collections import deque

N, M = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(N)]
next_grid = [[0 for _ in range(M)] for _ in range(N)]

# 0은 빈 칸, 1은 벽, 2는 바이러스
BLANK = 0
WALL = 1
VIRUS = 2

blanks = []
viruses = []
for i in range(N):
    for j in range(M):
        if grid[i][j] == BLANK:
            blanks.append((i, j))
        elif grid[i][j] == VIRUS:
            viruses.append((i, j))

dxs = [-1, 0, 1, 0]
dys = [0, -1, 0, 1]


def in_range(x, y):
    return 0 <= x < N and 0 <= y < M


def can_go(x, y):
    return in_range(x, y) and next_grid[x][y] == 0


def spread_viruses():
    q = deque()
    for virus in viruses:
        q.append(virus)

    while q:
        x, y = q.popleft()
        for dx, dy in zip(dxs, dys):
            nx, ny = x + dx, y + dy
            if can_go(nx, ny):
                next_grid[nx][ny] = VIRUS
                q.append((nx, ny))


def init(walls):
    for i in range(N):
        for j in range(M):
            next_grid[i][j] = grid[i][j]

    for i, j in walls:
        next_grid[i][j] = 1


def get_safe_area():
    safe_area = 0
    for i in range(N):
        for j in range(M):
            if next_grid[i][j] == 0:
                safe_area += 1
    return safe_area


def simulate(walls):
    # 1. 빈 칸 중 3곳에 벽을 세움
    init(walls)
    # 2. 벽을 세운 후 바이러스를 퍼뜨려봄
    spread_viruses()
    # 3. 바이러스가 모두 퍼진 뒤 안전 영역의 크기를 구함
    return get_safe_area()


ans = 0
for candidate in combinations(blanks, 3):
    safe_area = simulate(candidate)
    ans = max(ans, safe_area)

print(ans)
