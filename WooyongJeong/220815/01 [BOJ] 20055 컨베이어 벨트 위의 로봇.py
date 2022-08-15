import collections

N, K = map(int, input().split())
A = collections.deque(map(int, input().split()))

LIFT_UP_POS, LIFT_DOWN_POS = 0, N - 1
size = 2 * N
robots = collections.deque([False] * size)
pos_robots_cnt = collections.defaultdict(int)
lift_up_order = 0


def lift_up():
    if A[LIFT_UP_POS] == 0:
        return

    A[LIFT_UP_POS] -= 1
    robots[LIFT_UP_POS] = True


def can_go(pos):
    return not robots[pos] and A[pos] > 0


def move_robots():
    for pos in range(N - 2, 0, -1):
        if not robots[pos]:
            continue

        next_pos = pos + 1
        if not can_go(next_pos):
            continue

        robots[pos] = False
        robots[next_pos] = True
        A[next_pos] -= 1

        if next_pos == LIFT_DOWN_POS:
            robots[next_pos] = False


def rotate():
    # 컨베이어 벨트가 로봇과 함께 한 칸 회전
    A.rotate(1)
    robots.rotate(1)

    # 한 칸 회전했을 때 내리는 위치에 도착한 로봇 내림
    robots[LIFT_DOWN_POS] = False


def simulate():
    # 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
    rotate()

    # 2. 가장 먼저 벨트에 올라간 로봇부터 한 칸 이동
    move_robots()

    # 3. 올리는 위치에 있는 칸의 내구도가 0이 아니라면 그 위치에 로봇을 올림
    lift_up()


def is_able_to_proceed():
    return collections.Counter(A)[0] < K


step = 1
while True:
    simulate()

    if not is_able_to_proceed():
        break

    step += 1

print(step)
