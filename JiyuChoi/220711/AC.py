from collections import deque
import sys
input = sys.stdin.readline

for _ in range(int(input())):
    p = input()
    n = int(input())
    arr = input().rstrip()[1:-1].split(",")

    d = deque(arr) if n else deque([])
    e_flag, r_flag = False, False

    for x in p:
        if x == "R":
            r_flag = not r_flag
        elif x == "D":
            if not len(d):
                e_flag = True
                break
            d.pop() if r_flag else d.popleft()

    if e_flag:
        print("error")
    else:
        if r_flag:
            d.reverse()
        print("["+','.join(d)+"]")