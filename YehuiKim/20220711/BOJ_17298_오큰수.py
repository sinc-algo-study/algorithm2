import sys
input = sys.stdin.readline
# from collections import deque

'''
if __name__=="__main__":
    N = int(input().rstrip())
    arr = list(map(int, input().split()))
    ans = deque()
    all = [0]*1000001
    while arr:
        now = arr.pop()
        if all[now]==0:
            ans.appendleft(-1)
        else:
            ans.appendleft(all[now])
        all = [now]*now+all[now:]

    print(*ans)
'''
if __name__=="__main__":
    N = int(input().rstrip())
    arr = list(map(int, input().split()))
    ans = [-1]*N
    idxList = [0]  # 인덱스를 저장할 리스트

    for i in range(1,N):
        now = arr[i]
        # now가 idxList의 맨 마지막 원소보다 크면
        while idxList and arr[idxList[-1]] < now:
            ans[idxList.pop()] = now  #정답의 현재 위치에 now를 넣는다
        idxList.append(i)  # idxList에는 항상 현재값을 넣어준다.

    print(*ans)


'''
백만 개

'''
