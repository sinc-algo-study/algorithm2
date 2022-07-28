from collections import deque
from itertools import combinations

board = []
group = []  # 그룹번호 - 그룹에 속한 칸의 위치들
group_board=[] #[i][j]=그룹번호
dir = [(-1, 0), (1, 0), (0, -1), (0, 1)]


def solution():
    art_score_sum=0
    for turn in range(4):
        if turn!=0:
            #회전
            rotate()

        # 그룹 찾기
        group.clear()
        global group_board
        group_board=[[0 for _ in range(N)] for _ in range(N)]
        find_group()

        # 초기 예술 점수 구하기
        group_nums = [i for i in range(len(group))]
        group_combs = list(combinations(group_nums, 2))
        art_score = 0
        for group_comb in group_combs:
            g1, g2 = group_comb
            harmony_score=find_harmony_score(g1, g2)
            if harmony_score>0:
                art_score += harmony_score
        art_score_sum+=art_score
    return art_score_sum


def rotate():
    rotate_ten()
    rotate_outside()


def rotate_ten():
    tmp_board = [arr[:] for arr in board]
    middle = N // 2
    for i in range(N):
        board[middle][i] = tmp_board[i][middle]
        board[(N - 1) - i][middle] = tmp_board[middle][i]


def rotate_outside():
    # 4개 정사각형 90도 회전
    rotate_outside_clockwise(0, 0)
    rotate_outside_clockwise(0, N // 2 + 1)
    rotate_outside_clockwise(N // 2 + 1, 0)
    rotate_outside_clockwise(N // 2 + 1, N // 2 + 1)


def rotate_outside_clockwise(sr, sc):
    tmp_board = [arr[:] for arr in board]
    for i in range(sr, sr + N // 2):
        for j in range(sc, sc + N // 2):
            board[j-sc+sr][sc+N//2-1-i+sr] = tmp_board[i][j]


def find_harmony_score(g1, g2):
    g1_r, g1_c = group[g1][0]
    g2_r, g2_c = group[g2][0]
    g1_num = board[g1_r][g1_c]
    g2_num = board[g2_r][g2_c]
    score = (len(group[g1]) + len(group[g2])) * g1_num * g2_num
    score *= count_adjacent(g1, g2)
    return score


def count_adjacent(g1, g2):
    count = 0
    for place in group[g1]:
        r, c = place
        for d in dir:
            nr = r + d[0]
            nc = c + d[1]
            if nr < 0 or nr >= N or nc < 0 or nc >= N:  # 범위 밖
                continue
            if group_board[nr][nc]==g2:
                count += 1
    return count


def find_group():
    visited = [[False for _ in range(N)] for _ in range(N)]
    group_num = 0
    for i in range(N):
        for j in range(N):
            if not visited[i][j]:
                visited = bfs(i, j, visited, group_num)
                group_num += 1


def bfs(r, c, visited, group_num):
    group.append([])
    dq = deque([])
    dq.append((r, c))
    visited[r][c] = True

    while dq:
        nowr, nowc = dq.popleft()
        group[group_num].append((nowr, nowc))
        group_board[nowr][nowc]=group_num
        for d in dir:
            nr = nowr + d[0]
            nc = nowc + d[1]
            if nr < 0 or nr >= N or nc < 0 or nc >= N:
                continue
            if not visited[nr][nc] and board[nowr][nowc] == board[nr][nc]:
                visited[nr][nc] = True
                dq.append((nr, nc))
    return visited

if __name__ == '__main__':
    global N
    N = int(input().strip())
    for i in range(N):
        board.append(list(map(int, input().split())))
    print(solution())