from collections import deque

N, M, K = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(N)]

trees = [[deque() for _ in range(N)] for _ in range(N)]  # 나무들의 나이
dead_trees = [[[] for _ in range(N)] for _ in range(N)]  # 죽은 나무들의 나이
for _ in range(M):
    x, y, age = map(int, input().split())
    trees[x - 1][y - 1].append(age)

nutrients = [[5 for _ in range(N)] for _ in range(N)]  # 해당 땅의 양분
dxs = [-1, -1, -1, 0, 0, 1, 1, 1]
dys = [-1, 0, 1, -1, 1, -1, 0, 1]


def kill_trees(x, y, k):
    for _ in range(k, len(trees[x][y])):  # k~마지막 index의 나무들이 즉시 죽음
        dead_trees[x][y].append(trees[x][y].pop())  # 죽은 나무들의 나이에 표시


def dead_or_alive(x, y):
    for k in range(len(trees[x][y])):  # 각 나무들에 대해
        tree_age = trees[x][y][k]  # 나무의 나이
        if nutrients[x][y] < tree_age:  # 해당 칸에 양분이 부족하여 죽음
            kill_trees(x, y, k)  # k~마지막 index의 나무들이 즉시 죽음
            break

        nutrients[x][y] -= tree_age  # 해당 칸의 양분을 나무 나이만큼 소비
        trees[x][y][k] += 1  # 나무는 나이가 1 증가


def spring_and_summer():
    # spring
    for i in range(N):
        for j in range(N):
            # 각 칸의 나무들에 대해 죽을지 살지 판단 -> 상태에 따라 로직 실행
            dead_or_alive(i, j)

    # summer
    for i in range(N):
        for j in range(N):
            # 각 칸의 죽은 나무들이 양분으로 변함
            while dead_trees[i][j]:
                dead_tree_age = dead_trees[i][j].pop()
                # 죽은 나무의 나이를 2로 나눈 몫이 해당 칸에 양분으로 추가됨
                nutrients[i][j] += dead_tree_age // 2


def in_range(x, y):
    return 0 <= x < N and 0 <= y < N


def breed_trees(x, y):
    for k in range(len(trees[x][y])):  # 각 나무들에 대해
        tree_age = trees[x][y][k]  # 나무의 나이
        if tree_age % 5 != 0:  # 나무의 나이가 5의 배수가 아니라면 번식하지 않음
            continue

        for dx, dy in zip(dxs, dys):
            nx, ny = x + dx, y + dy
            # 땅을 벗어나지 않으면서 인접한 8개의 칸에 나무가 생길 거임
            if not in_range(nx, ny):
                continue
            # 인접한 칸에 나이가 1인 나무가 생김
            trees[nx][ny].appendleft(1)


def autumn():
    for i in range(N):
        for j in range(N):
            breed_trees(i, j)  # 나무가 번식함


def winter():
    # S2D2가 땅을 돌아다니며 땅에 양분을 추가함
    for i in range(N):
        for j in range(N):
            nutrients[i][j] += A[i][j]


def simulate():
    spring_and_summer()
    autumn()
    winter()


def get_alive_trees_cnt():
    cnt = 0
    for i in range(N):
        for j in range(N):
            cnt += len(trees[i][j])  # 각 칸마다 살아있는 나무의 개수를 구함
    return cnt


for _ in range(K):
    simulate()

print(get_alive_trees_cnt())
