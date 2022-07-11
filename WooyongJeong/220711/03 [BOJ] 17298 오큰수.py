"""
매 원소마다 오른쪽 원소들을 모두 보면서 오큰수를 찾는 풀이
-> O(N^2)로 N이 100만이기 때문에 시간 초과

대충 O(N), O(NlogN) 정도로 풀어야 함
-> 스택 이용해서 O(N)에 가능
"""
N = int(input())
A = list(map(int, input().split()))

stack = []
ans = [0 for _ in range(N)]


for i in range(N - 1, -1, -1):
    while stack and stack[-1] <= A[i]:
        stack.pop()

    if not stack:
        ans[i] = -1
    else:
        ans[i] = stack[-1]

    stack.append(A[i])

print(*ans)
