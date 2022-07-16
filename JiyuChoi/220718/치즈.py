from collections import deque
import sys

input = sys.stdin.readline
n, m = map(int, input().split())
cheese = [list(map(int, input().split())) for _ in range(n)]
q = deque()
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]


def bfs(x, y):
    visited = [[0]*m for _ in range(n)]
    visited[x][y] = 2
    q.append((x, y))
    cnt = 0
    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < n and 0 <= ny < m and visited[nx][ny] != 2:
                if cheese[nx][ny]:
                    visited[nx][ny] += 1
                    if visited[nx][ny] == 2:
                        cheese[nx][ny] = 0
                        cnt += 1
                else:  # 치즈가 아닌 경우만 큐에 삽입 (내부로는 들어갈 수 없음)
                    visited[nx][ny] = 2
                    q.append((nx, ny))
    return cnt


time = 0
while True:
    time += 1
    res = bfs(0, 0)
    if not res:
        break

print(time - 1)