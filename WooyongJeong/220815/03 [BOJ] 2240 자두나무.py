T, W = map(int, input().split())
a = [0] + [int(input()) for _ in range(T)]

# dp[i][j] := i초에 j번 움직여서 먹은 자두 최대 개수
dp = [[0 for _ in range(W + 1)] for _ in range(T + 1)]

for i in range(1, T + 1):
    # 한 번도 안 움직인 경우
    # 1번 나무에서 떨어질 때만 먹을 수 있음
    dp[i][0] = dp[i - 1][0] + 1 if a[i] == 1 else dp[i - 1][0]

    # W번 움직여보며 체크
    for j in range(1, W + 1):
        if j > i:
            break

        if a[i] == 1 and j % 2 == 0:
            # 1번 나무에서 떨어짐
            # 이동 횟수가 짝수번이면 1번 나무에 있음 (처음에 1번 나무에 있기 때문)
            dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - 1]) + 1
        elif a[i] == 2 and j % 2 == 1:
            # 2번 나무에서 떨어짐
            # 이동 횟수가 홀수 번이면 2번 나무에 있음 (처음에 1번 나무에 있기 때문)
            dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - 1]) + 1
        else:
            # 안 먹음
            # i초에 자두랑 엇갈림
            dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - 1])

print(max(dp[-1]))
