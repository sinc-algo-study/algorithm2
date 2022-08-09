import collections
import heapq

INF = int(1e9)
N, M, A, B, C = map(int, input().split())

graph = collections.defaultdict(list)
for _ in range(M):
    u, v, w = map(int, input().split())
    graph[u].append((v, w))
    graph[v].append((u, w))


def check(max_shame):
    q = []
    distance = [INF] * (N + 1)
    distance[A] = 0
    heapq.heappush(q, (0, A))

    while q:
        dist, node = heapq.heappop(q)
        if distance[node] < dist:
            continue

        for next_node, weight in graph[node]:
            cost = dist + weight
            if weight <= max_shame and cost < distance[next_node]:
                distance[next_node] = cost
                heapq.heappush(q, (cost, next_node))

    return distance[B] <= C and distance[B] != INF


left, right = 0, INF
ans = -1

while left + 1 < right:
    mid = left + (right - left) // 2

    if check(mid):
        ans = mid
        right = mid
    else:
        left = mid

print(ans)
