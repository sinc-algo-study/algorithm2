from collections import deque
import sys
si = sys.stdin.readline


T = int(si())
for _ in range(T):
    p = list(si())
    n = int(si())
    # 2. rstrip()으로 앞뒤 공백을 안 잘라주면 오답 (예시, 1 R 1 [12])
    arr = deque(si().rstrip()[1:-1].split(','))

    reverse_flag = False
    for func in p:
        if func == 'R':
            reverse_flag = not reverse_flag
        elif func == 'D':
            if not arr or arr[0] == '':
                print('error')
                break

            # 1. func == 'R'일 때마다 arr.reverse()를 호출하면 시간 초과
            if not reverse_flag:
                arr.popleft()
            else:
                arr.pop()
    else:
        # 3. 1번에서 reverse_flag를 이용해 func == 'D'일 때 arr 앞뒤 어디를 pop할지
        #    결정해주었지만, 정작 뒤집지 않아서 오답
        if reverse_flag:
            arr.reverse()
        print(f"[{','.join(list(map(str, arr)))}]")
