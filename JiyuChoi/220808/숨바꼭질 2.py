from collections import deque, defaultdict

n, k = map(int, input().split())
max_v = 100001
visited = [False] * max_v
counter = defaultdict(int)

q = deque()
q.append([n, 0])

while q:
    x, count = q.popleft()
    visited[x] = True

    if x == k:
        counter[count] += 1

    for nx in (x-1, x+1, 2*x):
        if 0 <= nx < max_v and not visited[nx]:
            q.append((nx, count+1))

print(list(counter.keys())[0])
print(list(counter.values())[0])


# 시간이 조금 더 빠름 (800)
n, k = map(int, input().split())
max_v = 100001
visited = [-1] * max_v

q = deque()
q.append(n)

visited[n] = 0
cnt = 0

while q:
    x = q.popleft()
    if x == k:
        cnt += 1

    for nx in (x-1, x+1, 2*x):
        if 0 <= nx < max_v:
            if visited[nx] == -1 or visited[nx] >= visited[x] + 1: # 방문하지 않았거나 한번 이상 방문한 경우
                visited[nx] = visited[x] + 1
                q.append(nx)

print(visited[k])
print(cnt)