import sys
input = sys.stdin.readline


def searchingToClean():
    global sr, sc, sd
    for i in range(1, 5):
        nd = (sd-i)%4
        dr, dc = dirs[nd]
        nr, nc = sr+dr, sc+dc
        if not maps[nr][nc] and not cleaned[nr][nc]:
            sr, sc, sd = nr, nc, nd
            return True
    return False


def cleaning():
    global sr, sc, sd
    cnt = 0
    while 1:
        cleaned[sr][sc]=1
        cnt += 1
        while 1:
            if not searchingToClean():
                dr, dc = dirs[(sd+2)%4]
                nr, nc = sr+dr, sc+dc
                if maps[nr][nc]:
                    return cnt
                else:
                    sr, sc = nr, nc
            else:
                break


if __name__=='__main__':
    global sr, sc, sd
    N, M = map(int, input().split())
    sr, sc, sd = map(int, input().split())
    dirs = [(-1,0),(0,1),(1,0),(0,-1)]  # 북동남서
    maps = []
    for i in range(N):
        temp = list(map(int, input().split()))
        maps.append(temp)
    cleaned = [[0 for _ in range(M)] for _ in range(N)]
    print(cleaning())

'''
(r, c) => r행 c열
1. 현재 위치 청소
2. 현재 방향 기준으로 왼쪽부터 탐색
    =>왼쪽 방향에 청소하지 않은 공간 존재시 그 방향으로 회전-전진 1번
    =>왼쪽 방향에 미존재시, 그 방향으로 회전하고 2번
    =>네 방향 모두 청소 이미 되어있거나 벽, 방향 유지한 채 한칸 후진 2번
    =>+뒤쪽도 벽이면 작동 중지
'''