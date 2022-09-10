"""
1. 예술 점수 구하는 모듈
2. 회전 시키는 모듈
    2-1. 십자 모양 통째로 반시계 방향으로 90도 회전 시키는 모듈
    2-2. 십자 모양을 제외한 4개의 정사각형 각각 시계 방향으로 90도 회전 시키는 모듈

"""
n = int(input())
grid = [list(map(int, input().split())) for _ in range(n)]
next_grid = [[0 for _ in range(n)] for _ in range(n)]

group_cnt = 0
group = [[0 for _ in range(n)] for _ in range(n)]
group_cell_cnt = [0 for _ in range(n * n + 1)]
visited = [[False for _ in range(n)] for _ in range(n)]

dxs = [-1, 0, 1, 0]
dys = [0, -1, 0, 1]


def in_range(x, y):
    return 0 <= x < n and 0 <= y < n


def can_go(x, y, nx, ny):
    return in_range(nx, ny) and not visited[nx][ny] \
           and grid[nx][ny] == grid[x][y]


def dfs(x, y):
    for dx, dy in zip(dxs, dys):
        nx, ny = x + dx, y + dy

        if not can_go(x, y, nx, ny):
            continue

        visited[nx][ny] = True
        group[nx][ny] = group_cnt
        group_cell_cnt[group_cnt] += 1
        dfs(nx, ny)


def make_group():
    global group_cnt

    group_cnt = 0

    for i in range(n):
        for j in range(n):
            visited[i][j] = False

    for i in range(n):
        for j in range(n):
            if not visited[i][j]:
                group_cnt += 1
                visited[i][j] = True
                group[i][j] = group_cnt
                group_cell_cnt[group_cnt] = 1
                dfs(i, j)


def get_art_score():
    art_score = 0

    for i in range(n):
        for j in range(n):
            for dx, dy in zip(dxs, dys):
                nx, ny = i + dx, j + dy
                if in_range(nx, ny) and grid[i][j] != grid[nx][ny]:
                    g1, g2 = group[i][j], group[nx][ny]
                    num1, num2 = grid[i][j], grid[nx][ny]
                    cnt1, cnt2 = group_cell_cnt[g1], group_cell_cnt[g2]

                    art_score += (cnt1 + cnt2) * num1 * num2

    return art_score // 2


def get_score():
    make_group()
    return get_art_score()


def rotate_cross_shape():
    for i in range(n):
        for j in range(n):
            if j == n // 2:
                next_grid[j][i] = grid[i][j]
            elif i == n // 2:
                next_grid[n - j - 1][i] = grid[i][j]


def rotate_square(sx, sy, sn):
    for x in range(sx, sx + sn):
        for y in range(sy, sy + sn):
            ox, oy = x - sx, y - sy
            rx, ry = oy, sn - ox - 1
            next_grid[rx + sx][ry + sy] = grid[x][y]


def rotate():
    for i in range(n):
        for j in range(n):
            next_grid[i][j] = 0

    rotate_cross_shape()

    sn = n // 2
    rotate_square(0, 0, sn)
    rotate_square(0, sn + 1, sn)
    rotate_square(sn + 1, 0, sn)
    rotate_square(sn + 1, sn + 1, sn)

    for i in range(n):
        for j in range(n):
            grid[i][j] = next_grid[i][j]


ans = 0
for _ in range(4):
    ans += get_score()
    rotate()

print(ans)
