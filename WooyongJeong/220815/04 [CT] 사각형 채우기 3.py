MOD = 1000000007
SIZE = 1001
n = int(input())

dp = [0] * SIZE

dp[0] = 1
dp[1] = 2

for i in range(2, n + 1):
    dp[i] = (dp[i - 1] * 2 + dp[i - 2] * 3) % MOD
    for j in range(i - 3, -1, -1):
        dp[i] = (dp[i] + dp[j] * 2) % MOD

print(dp[n])
