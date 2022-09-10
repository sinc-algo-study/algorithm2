from collections import deque
import sys
si = sys.stdin.readline

N, M = map(int, si().split())
grid = [list(map(int, si().split())) for _ in range(N)]
sx, sy, sd = map(lambda x: int(x) - 1, si().split())
ex, ey, ed = map(lambda x: int(x) - 1, si().split())

# visited[i][j][k]: (i, j)에 k 방향으로 방문 여부
visited = [
    [
        [False for _ in range(4)]
        for _ in range(M)
    ]
    for _ in range(N)
]
# 동 서 남 북
dxs = [0, 0, 1, -1]
dys = [1, -1, 0, 0]


def in_range(x, y):
    return 0 <= x < N and 0 <= y < M


def is_arrived(x, y, d):
    return x == ex and y == ey and d == ed


def is_turn_twice(d, nd):
    return (d == 0 and nd == 1) or (d == 1 and nd == 0) or \
           (d == 2 and nd == 3) or (d == 3 and nd == 2)


def simulate():
    q = deque()
    q.append((sx, sy, sd, 0))  # x, y, d, order_cnt
    visited[sx][sy][sd] = True

    while q:
        x, y, d, cnt = q.popleft()
        if is_arrived(x, y, d):
            return cnt

        # 명령 1. Go k
        for k in range(1, 3 + 1):
            nx, ny = x + dxs[d] * k, y + dys[d] * k
            if not in_range(nx, ny) or grid[nx][ny] != 0:
                break
            if visited[nx][ny][d]:
                continue
            visited[nx][ny][d] = True
            q.append((nx, ny, d, cnt + 1))

        # 명령 2. Turn dir
        for nd in range(4):
            if nd == d or visited[x][y][nd]:
                continue

            visited[x][y][nd] = True
            if is_turn_twice(d, nd):
                q.append((x, y, nd, cnt + 2))
            else:
                q.append((x, y, nd, cnt + 1))


print(simulate())
