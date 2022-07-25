from collections import deque


def bfs(start, end):
    q = deque()

    q.append((start, 0, [start]))
    visited[start] = 1
    visited[end] = 0

    while q:
        now, dist, path = q.popleft()
        if now == end:
            return dist, path

        for nx in link[now]:
            if not visited[nx]:
                visited[nx] = 1

                path.append(nx)
                q.append((nx, dist + 1, path[:]))
                path.pop()


n, m = map(int, input().split())

link = [[] for _ in range(n+1)]

for _ in range(m):
    a, b = map(int, input().split())
    link[a].append(b)
    link[b].append(a)

s, e = map(int, input().split())

visited = [0] * (n+1)

for idx in range(1, n+1):
    link[idx].sort()

dist1, route = bfs(s, e)

tmp = set(route)
for i in range(1, n+1):
    if i not in tmp:
        visited[i] = 0

dist2, route = bfs(e, s)
print(dist1 + dist2)