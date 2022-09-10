def dfs(x, y, d):
    global cnt
    for _ in range(4):
        nd = (d+3) % 4
        nx = x + dx[nd]
        ny = y + dy[nd]
        if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 0:
            cnt += 1
            graph[nx][ny] = 2
            dfs(nx, ny, nd)
            return
        d = nd # 해당 안되는 경우도 방향 돌리는 것 잊지않기!

    nx = x - dx[nd]
    ny = y - dy[nd]
    if graph[nx][ny] == 1:
        return
    dfs(nx, ny, nd)


n, m = map(int, input().split())
x, y, d = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

cnt = 1
graph[x][y] = 2
dfs(x, y, d)

print(cnt)