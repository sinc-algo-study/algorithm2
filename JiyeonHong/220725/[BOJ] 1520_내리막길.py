def solution(board):
    dp=[[0 for _ in range(N)] for _ in range(M)]
    dp[0][0]=1
    for i in range(M):
        for j in range(N):
            if i-1>=0: #위쪽
                if dp[i-1][j]>dp[i][j]:
                    dp[i][j]=max(dp[i][j],dp[i-1][j]+1)
                else:
                    dp[i][j] = max(dp[i][j], dp[i - 1][j])

            if j-1>=0: #왼쪽
                if dp[i][j-1]>dp[i][j]:
                    dp[i][j]=max(dp[i][j],dp[i][j-1]+1)
                else:
                    dp[i][j] = max(dp[i][j], dp[i][j-1])

if __name__ == '__main__':
    global M,N
    M,N=map(int,input().split(' '))
    board=[]
    for _ in range(M):
        board.append(list(map(int,input().split(' '))))
        