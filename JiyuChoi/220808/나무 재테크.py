N, M, K=map(int, input().split())
A = [list(map(int, input().split())) for _ in range(N)]
tree = [[[] for _ in range(N)] for _ in range(N)]

for _ in range(M):
    x, y, z = map(int, input().split())
    tree[x-1][y-1].append(z)

ground = [[5] * N for _ in range(N)]

for _ in range(K):
    # 봄, 여름
    for i in range(N):
        for j in range(N):
            if len(tree[i][j]) <= 0: continue
            tree[i][j].sort()
            idx = 0
            while idx < len(tree[i][j]):
                if tree[i][j][idx] <= ground[i][j]:
                    ground[i][j] -= tree[i][j][idx]
                    tree[i][j][idx] += 1
                    idx += 1
                else:
                    die = tree[i][j][idx:]
                    for now in die:
                        ground[i][j] += now // 2
                    tree[i][j] = tree[i][j][:idx]
                    break

    # 가을
    d = [[-1, 0], [0, -1], [1, 0], [0, 1], [1, 1], [1, -1], [-1, 1], [-1, -1]]
    for i in range(N):
        for j in range(N):
            c = 0
            if tree[i][j]:
                for now in tree[i][j]:
                    if now % 5 == 0:
                        c += 1
            if c:
                for k in range(8):
                    nx = i+d[k][0]
                    ny = j+d[k][1]
                    if 0 <= nx < N and 0 <= ny < N:
                        for _ in range(c):
                            tree[nx][ny].append(1)

    # 겨울
    for i in range(N):
        for j in range(N):
            ground[i][j] += A[i][j]

ans = 0
for i in range(N):
    for j in range(N):
        ans += len(tree[i][j])
print(ans)