SIZE = 20

n = int(input())

dp = [0] * SIZE

dp[0] = 1

for i in range(1, n + 1):
    # 노드의 개수가 i인 서로 다른 BST 개수
    for j in range(1, i + 1):
        # 루트 노드가 j라면,
        # 왼쪽에는 1 ~ j - 1이 루트로 올 수 있음 -> dp[j - 1]
        # 오른쪽에는 j + 1 ~ i까지 루트로 올 수 있음 -> dp[i - j]
        dp[i] += dp[j - 1] * dp[i - j]

print(dp[n])
