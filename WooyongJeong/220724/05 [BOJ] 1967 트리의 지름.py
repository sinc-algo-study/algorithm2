"""
1. 임의의 노드로부터 가장 거리가 먼 노드 n1를 찾는다
2. 노드 n1에서 가장 거리가 먼 노드 n2를 찾는다
3. n1 ~ n2 의 거리가 트리의 지름
"""

from collections import defaultdict
import sys
si = sys.stdin.readline

n = int(si())
graph = defaultdict(list)
for _ in range(n - 1):
    parent, child, weight = map(int, si().split())
    graph[parent].append((child, weight))
    graph[child].append((parent, weight))

dist = [-1 for _ in range(n + 1)]


def dfs(now, now_weight):
    for i in graph[now]:
        node, node_weight = i
        if dist[node] == -1:
            dist[node] = now_weight + node_weight
            dfs(node, now_weight + node_weight)


# 1. 1번 노드로부터 가장 먼 노드 n1를 찾는다
dist[1] = 0
dfs(1, 0)

# 2. 노드 n1로부터 가장 먼 노드 n2를 찾는다
n1 = dist.index(max(dist))
for i in range(n + 1):
    dist[i] = -1
dist[n1] = 0
dfs(n1, 0)

# 3. n1과 n2의 거리가 트리의 지름
print(max(dist))
