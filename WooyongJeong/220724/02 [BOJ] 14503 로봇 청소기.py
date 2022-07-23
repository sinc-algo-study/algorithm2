N, M = map(int, input().split())
sx, sy, sd = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(N)]
ans = 0

# 0: 빈 칸, 1: 벽
BLANK, WALL = 0, 1
# 2: 청소 완료
CLEANED = 2
# 북, 동, 남, 서
dxs = [-1, 0, 1, 0]
dys = [0, 1, 0, -1]


def in_range(x, y):
    return 0 <= x < N and 0 <= y < M


def can_go(x, y):
    return in_range(x, y) and grid[x][y] == BLANK


def simulate(x, y, d):
    global ans

    if grid[x][y] == WALL:
        # 2-4. 네 방향 모두 청소가 이미 되어있거나 벽이면서,
        #       뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
        return
    elif grid[x][y] == BLANK:
        # 1. 현재 위치를 청소한다.
        ans += 1
        grid[x][y] = CLEANED

    for _ in range(4):
        # 2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
        nd = (d + 3) % 4
        nx, ny = x + dxs[nd], y + dys[nd]

        # 2-1. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면,
        #       그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
        if can_go(nx, ny):
            simulate(nx, ny, nd)
            return
        # 2-2. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
        d = nd

    # 2-3. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는,
    #       바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
    nx, ny = x - dxs[d], y - dys[d]
    simulate(nx, ny, d)


simulate(sx, sy, sd)
print(ans)
