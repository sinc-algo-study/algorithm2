import sys
sys.setrecursionlimit(1000000)

def dfs(v):
    global res
    visited[v] = 1
    cycle.append(v)
    nx = link[v]
    if 0 < nx <= n and visited[nx]:
        if nx in cycle:
            res += cycle[cycle.index(nx):]
        return
    else:
        dfs(nx)

for _ in range(int(input())):
    n = int(input())
    link = [0] + list(map(int, input().split()))
    visited = [1] + [0] * n
    res = []

    for i in range(1, n+1):
        if not visited[i]:
            cycle = []
            dfs(i)

    print(n-len(res))