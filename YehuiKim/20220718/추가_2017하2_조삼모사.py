import sys
input = sys.stdin.readline
from itertools import permutations


# 조합 뽑아내기
'''
def combination(arr, k):
    lenN = len(arr)
    cnt = 0
    while cnt<k:
        for i in range(cnt, lenN-k-cnt):
            for j in range(cnt)+1, 

def getSum(arr):
    res = 0
    for i,j in arr:
        res += works[i][j]
    return res


def getSmallerst(arr):
    ans = 100000
    for ar in arr:
        before, after = ar[:N//2], ar[N//2:]
        beforeNoon = getSum(before)
        afterNoon = getSum(after)
        dist = abs(beforeNoon-afterNoon)
        ans = min(dist, ans)
    return ans
'''

def getSmall(cnt, arr):
    global ans
    for i in range(cnt, nn+cnt):
        if cnt<nn: # <2
            if i not in arr:
                newArr = arr+[i]
                getSmall(cnt+1, newArr)
            continue
        allSet.append(arr)
        break


if __name__=='__main__':
    N = int(input().rstrip())
    works = []
    for i in range(N):
        temp = list(map(int, input().split()))
        works.append(temp)
    ans = 100000
    nn = N//2
    allSet = []
    getSmall(0, [])
    for arr in allSet:
        before = 0
        afterList = [i for i in range(N)]
        for i in range(nn-1):
            for j in range(1,nn):
                before += works[arr[i]][arr[j]]
                before += works[arr[j]][arr[i]]
            afterList.remove(arr[i])
        afterList.remove(arr[nn-1])
        after = 0
        for i in range(nn-1):
            for j in range(1,nn):
                after += works[afterList[i]][afterList[j]]
                after += works[afterList[j]][afterList[i]]
        ans = min(ans, abs(before-after))
    print(ans)

# 1,3,4 => 3개

'''
n개 일 : 아침 저녁 반씩
업무강도의 최솟값

0~n//2
1~n//2+1
2~n//2+2

(20*19*18*17*16*15*14*13*12*11)/(10*9*8*7*6*5*4*3*2*1)
184756가지
1. 조합하기
2. 오전, 오후 각 조합의 업무강도 합 구하기
3. 각 업무강도의 차이 구하기 => 최솟값 갱신
'''