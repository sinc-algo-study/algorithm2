from collections import deque

n, k = map(int, input().split())

max_v = 100001
visited = [0] * max_v
time = [-1] * max_v
q = deque()
q.append(n)

visited[n] = 1
time[n] = 0

while q:
    x = q.popleft()

    if x == k:
        print(time[k])
        break

    if x*2 < max_v and not visited[x * 2]:
        nx = x * 2
        q.appendleft(nx)
        visited[nx] = 1
        time[nx] = time[x]

    for nx in (x+1, x-1):
        if 0 <= nx < max_v and not visited[nx]:
            q.append(nx)
            visited[nx] = 1
            time[nx] = time[x] + 1