from collections import Counter
r, c, k = map(int, input().split())

A = [list(map(int, input().split())) for _ in range(3)]


def R(A):
    max_len = 0
    for i in range(len(A)):
        tmp = []
        count = Counter(A[i])
        del count[0]  # 0은 제거해야함
        count = list(count.items())
        count.sort(key=lambda x: (x[1], x[0]))
        for a, b in count:
            tmp.append(a)
            tmp.append(b)
        A[i] = tmp
        max_len = max(max_len, len(A[i]))

    for i in range(len(A)):
        if len(A[i]) < max_len:
            A[i] += [0] * (max_len - len(A[i]))


cnt = 0
while True:
    if r-1 < len(A) and c-1 < len(A[0]):  # 해당 부분 check 안하면 인덱스 오류남
        if A[r-1][c-1] == k:
            print(cnt)
            break
    if cnt > 100:
        print(-1)
        break
    if len(A) >= len(A[0]):
        R(A)
    else:
        A = list(map(list, zip(*A)))
        R(A)
        A = list(map(list, zip(*A)))
    cnt += 1

