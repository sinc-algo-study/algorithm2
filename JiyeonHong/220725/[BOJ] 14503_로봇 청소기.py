board = []
directions = [(-1, 0), (0, 1), (1, 0), (0, -1)]


def solution(r, c, d):
    # board 상태 : 0-빈 칸, 1-벽, 2-청소완료
    board[r][c] = 2
    count = 1
    turn_time = 0
    while True:
        d = rotate_left(d)
        nr = r + directions[d][0]
        nc = c + directions[d][1]

        if board[nr][nc] == 0:  # 빈 공간인 경우
            board[nr][nc] = 2
            r = nr  # 위치 이동
            c = nc  # 위치 이동
            count += 1
            turn_time = 0  # 왼쪽 방향 회전 횟수 0으로 초기화
            continue
        else:
            turn_time += 1

        if turn_time == 4:  # 네 방향 모두 청소가 되어 있거나 벽이 있는 경우
            nr = r - directions[d][0]  # 후진
            nc = c - directions[d][1]  # 후진
            if board[nr][nc] != 1:  # 이동한 위치가 벽이 아니라면
                r = nr
                c = nc
            else:
                break
            turn_time = 0
    return count


def rotate_left(d):
    # 0->3, 1->0, 2->1, 3->2
    return (d + 3) % 4


if __name__ == '__main__':
    global N, M
    N, M = map(int, input().split(' '))
    # d : 0-북, 1-동, 2-남, 3-서
    r, c, d = map(int, input().split(' '))
    for _ in range(N):
        board.append(list(map(int, input().split(' '))))
    print(solution(r, c, d))
