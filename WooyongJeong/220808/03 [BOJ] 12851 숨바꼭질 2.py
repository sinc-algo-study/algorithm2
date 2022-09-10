"""
수빈이가 있을 수 있는 위치는.. 무한함
X에서 X - 1, X + 1, 2 * X의 위치로 이동할 때 소요하는 시간은 1초로 동일함
즉, 간선의 가중치가 1로 동일한 그래프로 생각하면 됨
-> BFS 이용
"""
from collections import deque

N, K = map(int, input().split())
SIZE = 100001

q = deque()
visited = [False for _ in range(SIZE)]

q.append((N, 0))
visited[N] = True

cnt = 0
min_sec = 0


def in_range(x):
    return 0 <= x < SIZE


def can_go(x):
    return in_range(x) and not visited[x]


while q:
    x, sec = q.popleft()
    visited[x] = True

    if min_sec > 0 and min_sec == sec and x == K:
        # K에 한 번 이상 도달한 경우. 방법의 수만 1 증가
        cnt += 1

    if min_sec == 0 and x == K:
        # K에 최초로 도달한 경우. 방법의 수을 1 증가시킬 뿐 아니라 최소 시간도 기록
        cnt += 1
        min_sec = sec

    for nx in [x * 2, x + 1, x - 1]:
        if not can_go(nx):
            continue
        q.append((nx, sec + 1))

print(min_sec)
print(cnt)
