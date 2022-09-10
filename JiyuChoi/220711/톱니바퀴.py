from collections import deque
import sys
input = sys.stdin.readline

def check_left(nx, nd):
    if nx < 1 or trail[nx][2] == trail[nx+1][-2]:
        return
    check_left(nx-1, -nd)
    trail[nx].rotate(nd)

def check_right(nx, nd):
    if nx > 4 or trail[nx][-2] == trail[nx-1][2]:
        return
    check_right(nx+1, -nd)
    trail[nx].rotate(nd)

trail = [[]]
for _ in range(4):
    trail.append(deque(map(int, input().rstrip())))

for _ in range(int(input())):
    n, d = map(int, input().split())

    check_left(n-1, -d)
    check_right(n+1, -d)
    trail[n].rotate(d)

answer = 0
for i in range(4):
    answer += trail[i+1][0]*(2**i)

print(answer)