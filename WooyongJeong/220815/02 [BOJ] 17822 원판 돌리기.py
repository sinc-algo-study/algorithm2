from collections import deque

N, M, T = map(int, input().split())
wheels = [deque([0] * M)] + \
         [deque(map(int, input().split())) for _ in range(N)] + \
         [deque([0] * M)]
lengths = [0] + [M] * N
rotate_info = [list(map(int, input().split())) for _ in range(T)]

check = [[False for _ in range(M)] for _ in range(N + 2)]
exist_adjacent_same_number = False

dxs = [-1, 0, 1, 0]
dys = [0, -1, 0, 1]


def rotate(x: int, d: int, k: int) -> None:
    for i in range(x, N + 1, x):
        if d == 0:
            wheels[i].rotate(k)
        else:
            wheels[i].rotate(-k)


def in_range(x: int) -> bool:
    return 1 <= x <= N


def find_adjacent_same_number() -> None:
    global exist_adjacent_same_number

    for i in range(1, N + 1):
        for j in range(M):
            if wheels[i][j] == 0 or check[i][j]:
                continue

            for dx, dy in zip(dxs, dys):
                nx, ny = i + dx, (j + dy) % M
                if not in_range(nx):
                    continue

                if wheels[i][j] == wheels[nx][ny]:
                    check[i][j] = True
                    check[nx][ny] = True
                    if not exist_adjacent_same_number:
                        exist_adjacent_same_number = True


def delete_numbers() -> None:
    for i in range(1, N + 1):
        if lengths[i] == 0:
            continue
        for j in range(M):
            if not check[i][j]:
                continue
            wheels[i][j] = 0
            lengths[i] -= 1


def plus_or_minus_one() -> None:
    length = sum(lengths[1:])
    if length == 0:
        return

    sum_val = 0
    for i in range(1, N + 1):
        sum_val += sum(wheels[i])

    avg = sum_val / length

    for i in range(1, N + 1):
        for j in range(M):
            if wheels[i][j] == 0:
                continue
            elif wheels[i][j] > avg:
                wheels[i][j] -= 1
            elif wheels[i][j] < avg:
                wheels[i][j] += 1


def init():
    global exist_adjacent_same_number
    exist_adjacent_same_number = False

    for i in range(1, N + 1):
        for j in range(M):
            check[i][j] = False


def simulate(x: int, d: int, k: int) -> None:
    # 0. check, have_to_delete 초기화
    init()

    # 1. 번호가 x의 배수인 원판을 d 방향으로 k칸 회전
    rotate(x, d, k)

    # 2. 원판에 수가 남아 있으면, 인접하면서 수가 같은 것을 모두 찾음
    find_adjacent_same_number()

    if exist_adjacent_same_number:
        # 2-1. 그러한 수가 있으면 모두 지움
        delete_numbers()
    else:
        # 2-2. 없는 경우에는 적힌 수의 평균보다 큰 수는 1을 빼고 작은 수에는 1을 더함
        plus_or_minus_one()


def get_sum_wheel_numbers():
    result = 0
    for i in range(1, N + 1):
        result += sum(wheels[i])
    return result


for x, d, k in rotate_info:
    simulate(x, d, k)

print(get_sum_wheel_numbers())
