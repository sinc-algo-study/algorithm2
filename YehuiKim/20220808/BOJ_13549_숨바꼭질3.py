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
        for k in [num+1,num-1]:
            if 0<=k<100001 and not visit[k]:
                q.append((cnt+1, k))
        if 0<=num*2<100001 and not visit[num*2]:
            q.append((cnt, num*2))
    sortedKeys = sorted(ans.keys())
    print(sortedKeys[0])
    return


if __name__=='__main__':
    N, K = map(int, input().split())
    ans = defaultdict(int)
    findSisterBFS()
