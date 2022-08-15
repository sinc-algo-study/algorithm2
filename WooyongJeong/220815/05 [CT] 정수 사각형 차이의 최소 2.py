"""
주어지는 수의 범위가 [1, 100]으로 작기 때문에
최솟값을 1부터 100까지 일일이 가정해보는 전략

최솟값 lower_bound가 정해져있다는 가정
-> 최솟값 이상의 수들만 써서 이동한다는 가정하에
경로상에 놓여 있는 수들 중 최댓값을 최소화하는 문제로 바꾸어 해결
"""

n = int(input())
grid = [list(map(int, input().split())) for _ in range(n)]

INF = int(1e9)
MAX = 100

dp = [[0 for _ in range(n)] for _ in range(n)]


def init():
    for i in range(n):
        for j in range(n):
            dp[i][j] = INF

    dp[0][0] = grid[0][0]

    for i in range(1, n):
        dp[i][0] = max(dp[i - 1][0], grid[i][0])

    for j in range(1, n):
        dp[0][j] = max(dp[0][j - 1], grid[0][j])


def solve(lower_bound):
    for i in range(n):
        for j in range(n):
            if grid[i][j] < lower_bound:
                grid[i][j] = INF

    init()

    for i in range(1, n):
        for j in range(1, n):
            dp[i][j] = max(min(dp[i - 1][j], dp[i][j - 1]), grid[i][j])

    return dp[n - 1][n - 1]


ans = INF
for lower_bound in range(1, MAX + 1):
    upper_bound = solve(lower_bound)

    if upper_bound == INF:
        continue

    ans = min(ans, upper_bound - lower_bound)

print(ans)
