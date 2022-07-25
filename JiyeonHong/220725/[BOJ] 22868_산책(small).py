import copy
import sys
from collections import defaultdict
from collections import deque

graph = defaultdict(list)
final_route = []


def solution(S, E):
    answer = 0

    #사전 순 경로를 위해 정렬
    for i in range(len(graph)):
        graph[i].sort()

    # S->E
    route_len, visited = bfs(S, E, [False for _ in range(len(graph))])
    answer += route_len-1
    # E->S
    visited[S] = False
    route_len, visited = bfs(E, S, visited)
    answer += route_len-1
    return answer


def bfs(S, E, visited):
    shortest_len = 0
    dq = deque([])
    dq.append((S, [S]))
    visited[S] = True

    while dq:
        now, route = dq.popleft()
        if now == E:
            # 경로에 해당하는 노드만 방문체크
            visited = [False for _ in range(len(graph))]
            for v_node in route:
                visited[v_node] = True
            shortest_len = len(route)
            break
        for node in graph[now]:
            if not visited[node]:
                dq.append((node, route[:] + [node]))
                visited[node] = True
    return shortest_len, visited


if __name__ == '__main__':
    sys.setrecursionlimit(10 ** 7)
    N, M = map(int, input().split(' '))
    for i in range(N + 1): #인덱스 에러 때문에 추가
        graph[i] = []
    for _ in range(M):
        A, B = map(int, input().split(' '))
        graph[A].append(B)
        graph[B].append(A)
    S, E = map(int, input().split(' '))
    print(solution(S, E))
