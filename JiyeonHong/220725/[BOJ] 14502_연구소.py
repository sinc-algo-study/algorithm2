import copy
from itertools import combinations
from collections import deque

'''
최대 보드판 크기 : 8*8=64
3개씩 조합 만들면 : 64C3=41664
'''

empty_places = []
virus_places = []


def solution(lab):
    # 1. 빈칸, 바이러스 좌표 찾기
    find_empty_and_virus(lab)

    # 2. 빈칸 조합 만들기
    empty_combinations = list(combinations(empty_places, 3))

    # 3. 조합마다 벽 세우고 안전 영역 구하기
    answer = 0
    for emptys in empty_combinations:
        answer = max(find_safezone(lab, emptys), answer)
    return answer


def find_safezone(lab, emptys):
    tmp_lab = copy.deepcopy(lab)
    # 벽 세우기
    for empty in emptys:  # emptys=((1,1),(2,2),(3,3))
        tmp_lab[empty[0]][empty[1]] = 1

    # 바이러스 퍼짐
    for virus in virus_places:
        r, c = virus
        tmp_lab = spread_virus(tmp_lab, r, c)

    # 안전 영역 크기 구하기
    count = 0
    for i in range(N):
        for j in range(M):
            if tmp_lab[i][j] == 0:
                count += 1
    return count


def spread_virus(lab, i, j):
    dir = [[-1, 0], [1, 0], [0, -1], [0, 1]]

    visited = [[False for _ in range(M)] for _ in range(N)]
    deq = deque([(i, j)])

    while deq:  # bfs
        r, c = deq.pop()
        lab[r][c] = 2
        visited[r][c] = True

        for d in dir:
            nr = r + d[0]
            nc = c + d[1]

            if nr < 0 or nr >= N or nc < 0 or nc >= M:  # 범위 밖
                continue

            if visited[nr][nc]:
                continue

            next = lab[nr][nc]
            if next == 0:
                deq.append((nr, nc))
    return lab


def find_empty_and_virus(lab):
    for i in range(len(lab)):
        for j in range(len(lab[i])):
            if lab[i][j] == 0:
                empty_places.append((i, j))
            elif lab[i][j] == 2:
                virus_places.append((i, j))


if __name__ == '__main__':
    global N, M
    N, M = map(int, input().split(' '))
    lab = []
    origin_empty = []
    for i in range(N):
        lab.append(list(map(int, input().split(' '))))
    print(solution(lab))
