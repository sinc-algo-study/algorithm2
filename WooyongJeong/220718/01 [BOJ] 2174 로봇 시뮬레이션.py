def crash_into_wall(x):
    return f"Robot {x} crashes into the wall"


def crash_into_robot(x, y):
    return f"Robot {x} crashes into robot {y}"


A, B = map(int, input().split())

# N, E, S, W
dxs = [-1, 0, 1, 0]
dys = [0, 1, 0, -1]

dirs = {
    'N': 0,
    'E': 1,
    'S': 2,
    'W': 3
}
robots = {}
land = {}
N, M = map(int, input().split())
for robot in range(N):
    x, y, d = input().split()
    x, y = int(x) - 1, int(y)  # 여기서 y에도 1을 빼주면 좌표가 이상해짐
    robots[robot + 1] = [B - y, x, dirs[d]]
    land[(B - y, x)] = robot + 1

order_info = []
for _ in range(M):
    robot, order, repeat_cnt = input().split()
    order_info.append((int(robot), order, int(repeat_cnt)))


def in_range(x, y):
    return 0 <= x < B and 0 <= y < A


def rotate_clockwise(d):
    return (d + 1) % 4


def rotate_counter_clockwise(d):
    return (d + 3) % 4


def simulate(i, order, repeat_cnt):
    x, y, d = robots[i]

    if order == 'F':  # 전진
        del land[(x, y)]

        for _ in range(repeat_cnt):
            nx, ny = x + dxs[d], y + dys[d]
            if not in_range(nx, ny):
                return crash_into_wall(i)
            if (nx, ny) in land:
                return crash_into_robot(i, land[(nx, ny)])
            x, y = nx, ny

        land[(x, y)] = i
    elif order == 'L':  # 반시계 방향으로 90도 회전
        for _ in range(repeat_cnt):
            d = rotate_counter_clockwise(d)
    elif order == 'R':  # 시계 방향으로 90도 회전
        for _ in range(repeat_cnt):
            d = rotate_clockwise(d)

    robots[i] = [x, y, d]
    return None


for robot, order, repeat_cnt in order_info:
    result = simulate(robot, order, repeat_cnt)
    if result is not None:
        print(result)
        break
else:
    print("OK")
