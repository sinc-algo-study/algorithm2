import sys
input = sys.stdin.readline
from collections import deque, defaultdict


def findSisterBFS():
    global ans
    q = deque([(0, N)])
    visit = [0 for _ in range(100001)]
    while q:
        cnt, num = q.popleft()
        visit[num] = 1
        if num==K:
            ans[cnt]+=1
            continue
        for k in [num+1,num-1,num*2]:
            if 0<=k<100001 and not visit[k]:
                q.append((cnt+1, k))

    sortedKeys = sorted(ans.keys())
    print(sortedKeys[0])
    print(ans[sortedKeys[0]])
    return


if __name__=='__main__':
    N, K = map(int, input().split())
    ans = defaultdict(int)
    findSisterBFS()
