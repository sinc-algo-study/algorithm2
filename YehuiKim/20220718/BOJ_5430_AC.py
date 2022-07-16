import sys
input = sys.stdin.readline
from collections import deque

def doTest(p, arr):
    reverseFlag = 0
    for f in p:
        if f=='R':
            reverseFlag += 1
            reverseFlag %= 2
        else:
            if arr:
                if reverseFlag:
                    arr.pop()
                else:
                    arr.popleft()
            else:
                return False
    if arr:
        ans = '['
        if reverseFlag:
            while len(arr)>1:
                ans += str(arr.pop()) + ','
        else:
            while len(arr)>1:
                ans += str(arr.popleft()) + ','
        ans += str(arr.pop()) + ']'
        return ans
    else:
        return '[]'


if __name__=='__main__':
    T = int(input().rstrip())
    ans = []
    for _ in range(T):
        p = input().rstrip()
        n = int(input().rstrip())
        arr = deque(eval(input().rstrip()))
        res = doTest(p, arr)
        if res:
            ans.append(res)
        else:
            ans.append('error')
    for a in ans:
        print(a)


'''
R(뒤집기), D(버리기)
- R : 배열에 있는 수의 순서 뒤집기
- D : 첫번째 수 버리기
 => 배열 비어있는데 사용하면 에러남

T 최대 100개
p 최대 100000개
n 최대 100000개
=> 전체 테케의 p합+n합 < 70만
'''