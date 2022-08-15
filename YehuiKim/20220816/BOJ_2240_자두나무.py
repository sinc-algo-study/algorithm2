import sys
input = sys.stdin.readline


# T=0일 때는 그냥 다 0으로 놔두기
# T=1일 때의 dp 구하기 (1초에서 여러 번 이동할 수도 있으니까)
def initial():
    global dp
    first = trees[0]%2  # 첫나무 1이면 1, 2면 0
    for j in range(W):
        if (j+1)%2 == first: # 맨 첫번째 나무==(이동횟수 홀수면 0(=2), 짝수면 1)
            dp[1][j] = 1


if __name__ == '__main__':
    T, W = map(int, input().split())
    trees = [int(input().rstrip()) for _ in range(T)]
    dp = [[0 for _ in range(W+1)] for _ in range(T+1)]
    initial()
    for t in range(2, T+1):
        for w in range(0, W+1):
            if w==0:
                num = dp[t - 1][w]
            else:
                num = max(dp[t - 1][w - 1], dp[t - 1][w])
            if trees[t-1]%2 == (w+1)%2:  # 나무2면0,1이면1 & 이동 홀수0(=2)짝수1(=1)
                dp[t][w] = num+1
            else:
                dp[t][w] = num
    print(max(dp[T]))

'''
... W
.
T
  0,1,2
0 0 0 0
1 0 1 0
2 1 1 2 
3 2 1 3
4 2 3 3
5 2 4 3
6 3 4 5
7 4 4 6
매초 마다 2개 나무 중 하나에서 열매 떨어짐
=> 열매 떨어지는 순간, 자두가 그 나무 아래에 있으면 열매 먹기 가능
두 나무 가까워서, 한 나무 아래에 있다가 다른 나무 아래로 1초 내에 이도 ㅇ가능.
체력이 딸림 근데..

T초
W번만 이동
자두가 받을 수 있는 자두의 최대 갯수?
초기에 1번 나무 아래에 있음

짝수번째 이동에서는 1번 나무, 홀수번째 이동에서는 2번 나무(=0번 나무)
(0번째, 0번) => 0, 
(0번째, 1번) => 1

이동했을때 vs 안했을때

'''