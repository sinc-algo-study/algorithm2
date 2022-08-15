## -*- coding: utf-8 -*-
import sys
input = sys.stdin.readline
from bisect import bisect_left


if __name__=='__main__':
    N = int(input().rstrip())
    liquids = list(map(int, input().split()))
    ans = 2*10**10
    ansList = []
    tmpList = []
    answer = []
    for i in range(N):
        num = abs(liquids[i])
        idx = bisect_left(tmpList, num)
        tmpList.insert(idx, num)
        ansList.insert(idx, liquids[i])
    print(ansList)
    for i in range(N-1):
        tmp = ansList[i]+ansList[i+1]
        #print(abs(tmp))
        if abs(tmp)<ans:
            ans = abs(tmp)
            answer = [ansList[i], ansList[i+1]]
            print("ans",str(ans))
    answer.sort()
    print(answer[0], answer[1])


'''

두 용액 특성값 오름차순 출력. 답 여러 개면 아무거나.
'''