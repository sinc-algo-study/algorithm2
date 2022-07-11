from collections import Counter
import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))

stack = []
answer = [-1] * n
count = Counter(arr)

for a, b in enumerate(arr):
    while stack and count[stack[-1][1]] < count[b]:
        x, y = stack.pop()
        answer[x] = b
    stack.append((a, b))

print(answer)
