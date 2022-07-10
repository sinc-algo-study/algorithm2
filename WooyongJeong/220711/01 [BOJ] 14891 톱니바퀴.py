from collections import deque


N = 4
wheels = [deque(map(int, input())) for _ in range(N)]
K = int(input())
rotate_infos = [list(map(int, input().split())) for _ in range(K)]


def rotate_left(i, d):
    if i < 0 or wheels[i][2] == wheels[i + 1][-2]:
        return

    rotate_left(i - 1, -d)
    wheels[i].rotate(d)


def rotate_right(i, d):
    if i >= N or wheels[i - 1][2] == wheels[i][-2]:
        return

    rotate_right(i + 1, -d)
    wheels[i].rotate(d)


for i, d in rotate_infos:
    i -= 1

    rotate_left(i - 1, -d)
    rotate_right(i + 1, -d)
    wheels[i].rotate(d)

ans = 0
for i, wheel in enumerate(wheels):
    ans += wheel[0] * (2 ** i)
print(ans)
