"""
x에서 x + 1, x - 1로 가는 경우 1초, x * 2로 가는 경우 0초 소요
-> 가중치가 다름
-> 다익스트라

시작 지점 N으로부터 모든 지점의 최소 비용을 갱신해 나감
"""
import heapq

N, K = map(int, input().split())

INF = int(1e9)
SIZE = 100001


def in_range(x):
    return 0 <= x < SIZE


def dijkstra():
    distance = [INF] * SIZE
    q = []

    heapq.heappush(q, (0, N))
    distance[N] = 0

    while q:
        dist, x = heapq.heappop(q)

        if in_range(x * 2) and distance[x * 2] > dist:
            distance[x * 2] = dist
            heapq.heappush(q, (dist, x * 2))

        if in_range(x + 1) and distance[x + 1] > dist + 1:
            distance[x + 1] = dist + 1
            heapq.heappush(q, (dist + 1, x + 1))

        if in_range(x - 1) and distance[x - 1] > dist + 1:
            distance[x - 1] = dist + 1
            heapq.heappush(q, (dist + 1, x - 1))

    return distance


ans = dijkstra()
print(ans[K])
