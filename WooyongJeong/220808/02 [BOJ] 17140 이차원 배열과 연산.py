import sys
from collections import Counter
from functools import reduce

R, C, K = map(int, input().split())
R, C = R - 1, C - 1
A = [list(map(int, input().split())) for _ in range(3)]


def r_operation():
    global A

    max_length = 0

    for i in range(len(A)):
        counter = Counter(A[i])
        del counter[0]
        counter = list(counter.items())
        counter.sort(key=lambda x: (x[1], x[0]))
        if len(counter) > 50:
            counter = counter[:50]

        A[i] = reduce(lambda x, y: list(x) + list(y),
                      counter[1:], list(counter[0]))
        max_length = max(max_length, len(A[i]))

    for i in range(len(A)):
        if len(A[i]) < max_length:
            A[i].extend([0] * (max_length - len(A[i])))


time = 0
while time <= 100:
    r, c = len(A), len(A[0])
    if r > R and c > C and A[R][C] == K:
        print(time)
        sys.exit(0)

    is_reversed = False
    if r < c:
        A = list(map(list, zip(*A)))
        is_reversed = True

    r_operation()

    if is_reversed:
        A = list(map(list, zip(*A)))

    time += 1

print(-1)
