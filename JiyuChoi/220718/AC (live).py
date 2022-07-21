# R: 뒤집는 함수
# D: 첫번째 배열의 수를 버리는 함수

# n <= 100,000
# p <= 100,000
# reverse => O(n)
# 만약 p <- R  => O(p*n)
# O(n) p <- D =>

# DRRD
# R: 뒤집었는지 아닌지 check -> r_flag
# D: deque: pop O(1), popleft O(1)
# [1,2,3,4] => 문자열 split()

from collections import deque

for _ in range(int(input())):
    p = input()
    n = int(input())
    arr = input().rstrip()[1:-1].split(",")  # eval(input()) is evil... ok...
    d = deque(arr) if n else deque([])
    # r_flag False 뒤집히지않음, True 뒤집힌 경우
    e_flag, r_flag = False, False
    # RR
    for x in p:
        if x == "R":
            r_flag = not r_flag
            # if not r_flag: # fasle
            #     r_flag = True
            # else: # true
            #     r_flag = False
        elif x == "D":
            # 삭제 배열의 값이 없을 경우 error
            if not d:
                e_flag = True
                break
            # 배열의 값이 있는 경우는 삭제를 진행해야함
            d.pop() if r_flag else d.popleft()
            # r_flag = True -> d.pop()
            # r_flag = False -> d.popleft()

    if e_flag:
        print("error")
    else:
        if r_flag:
            d.reverse()
        print(f"[{','.join(d)}]")


