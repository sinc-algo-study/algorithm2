import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
stack = []
answer = [0]*n

for a, b in enumerate(arr):
    while stack and stack[-1][1] < b:
        x, y = stack.pop()
        answer[x] = b
    stack.append((a, b))

for i in range(n):
    if not answer[i]:
        answer[i] = -1

print(*answer)