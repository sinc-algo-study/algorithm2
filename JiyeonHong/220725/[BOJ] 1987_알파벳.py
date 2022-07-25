board = []
dir = [(-1, 0), (1, 0), (0, -1), (0, 1)]
visited = [False for _ in range(26)]
answer = 0


def solution():
    visited[ord(board[0][0]) - ord('A')] = True
    dfs(0, 0, 1)
    return answer


def dfs(r, c, count):
    global answer
    answer = max(answer, count)
    for d in dir:
        nr = r + d[0]
        nc = c + d[1]

        if nr < 0 or nr >= R or nc < 0 or nc >= C:
            continue

        alphabet = board[nr][nc]
        if not visited[ord(alphabet) - ord('A')]:
            visited[ord(alphabet) - ord('A')] = True
            dfs(nr, nc, count + 1)
            visited[ord(alphabet) - ord('A')] = False


if __name__ == '__main__':
    global R, C
    R, C = map(int, input().split())
    board = []
    for _ in range(R):
        board.append(input())
    print(solution())
