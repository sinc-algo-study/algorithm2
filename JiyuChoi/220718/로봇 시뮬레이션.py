a, b = map(int, input().split())
n, m = map(int, input().split())
graph = [[0 for _ in range(a)] for _ in range(b)]

direction = {'N': 0, 'E': 1, 'S': 2, 'W': 3}
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

robot = [0]

for i in range(n):
    x, y, d = input().split()
    x, y = int(x)-1, int(y)
    graph[b-y][x] = i + 1
    robot.append((b-y, x, direction[d]))

for _ in range(m):
    idx, k, repeat = input().split()
    idx, repeat = int(idx), int(repeat)
    if k == "F":
        for _ in range(repeat):
            x, y, d = robot[idx]
            nx = x + dx[d]
            ny = y + dy[d]
            if nx < 0 or nx >= b or ny < 0 or ny >= a:
                print("Robot {} crashes into the wall".format(idx))
                exit()
            if graph[nx][ny] != 0:
                print("Robot {} crashes into robot {}".format(idx, graph[nx][ny]))
                exit()
            graph[nx][ny], graph[x][y] = idx, 0
            robot[idx] = (nx, ny, d)
    elif k == "L":
        x, y, d = robot[idx]
        for _ in range(repeat):
            d = (d + 3) % 4
        robot[idx] = (x, y, d)
    elif k == "R":
        x, y, d = robot[idx]
        for _ in range(repeat):
            d = (d + 1) % 4
        robot[idx] = (x, y, d)

print("OK")
