from collections import defaultdict, deque

N, M = map(int, input().split())

graph = defaultdict(list)
for _ in range(M):
    A, B = map(int, input().split())
    graph[A].append(B)
    graph[B].append(A)

S, E = map(int, input().split())

visited = [False for _ in range(N + 1)]


def bfs(start, end):
    q = deque()

    q.append((start, 0, [start]))
    visited[start] = True
    visited[end] = False

    while q:
        now, dist, route = q.popleft()
        if now == end:
            return dist, route

        for next_node in graph[now]:
            if not visited[next_node]:
                visited[next_node] = True

                route.append(next_node)
                q.append((next_node, dist + 1, route[:]))
                route.pop()


# 최단 경로가 여러 개라면 사전순으로 먼저 오는 것을 선택하기 위해
# 연결 그래프들을 사전순으로 정렬
for node in graph:
    graph[node].sort()

dist1, route = bfs(S, E)
# 먼저 S -> E로 갈 때 지나왔던 정점들을 제외하고는 방문 처리 제외
tmp = set(route)
for i in range(1, N + 1):
    if i not in tmp:
        visited[i] = False

dist2, route = bfs(E, S)
print(dist1 + dist2)
