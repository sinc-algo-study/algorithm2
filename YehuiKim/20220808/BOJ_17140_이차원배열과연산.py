import sys
input = sys.stdin.readline
from collections import defaultdict


def calculate_R(tmp):
    global lenC
    newLenC = lenC
    for i in range(lenR):
        res = defaultdict(int)
        for j in range(lenC):
            # 0 등장하면 멈추기
            if tmp[i][j]==0:
                continue
            res[tmp[i][j]]+=1
            tmp[i][j]=0
        res = sorted(res.items(), key=lambda x:(x[1],x[0]))
        for r in range(len(res)):
            for n in range(2):
                tmp[i][2*r+n] = res[r][n]
        newLenC = max(newLenC, 2*len(res))
    lenC = newLenC
    return tmp


def calculate_C(tmp):
    global lenR
    newLenR = lenR
    for j in range(lenC):
        res = defaultdict(int)
        for i in range(lenR):
            if tmp[i][j]==0:
                continue
            res[tmp[i][j]]+=1
            tmp[i][j]=0
        res = sorted(res.items(), key=lambda x:(x[1],x[0]))
        for r in range(len(res)):
            for n in range(2):
                tmp[2*r+n][j] = res[r][n]
        newLenR = max(newLenR, 2*len(res))
    lenR = max(lenR, newLenR)
    return tmp


if __name__=='__main__':
    R, C, K = map(int, input().split())
    arr = [[0 for _ in range(100)] for _ in range(100)]
    for i in range(3):
        tmp = list(map(int, input().split()))
        arr[i][:3] = tmp
    lenR = 3
    lenC = 3
    for s in range(101):
        if arr[R-1][C-1]==K:
            print(s)
            exit()
        if lenR>=lenC:
            arr = calculate_R(arr)
        else:
            arr = calculate_C(arr)
    print(-1)

'''
배열 A
R연산/C연산으로 정렬
- R연산 : 행 갯수 >= 열 갯수 (모든 행)
- C연산 : 행 갯수 < 열 갯수 (모든 열)
행/열 크기 100 넘어가면 버리기
A[r][c]=k가 되기 위한 최소시간?(100초 지나면 -1)

'''