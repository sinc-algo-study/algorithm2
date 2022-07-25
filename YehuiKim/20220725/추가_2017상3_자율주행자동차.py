import sys
input = sys.stdin.readline


def searchingToPark():
    global sr, sc, sd
    for i in range(1, 5):
        nd = (sd-i)%4
        dr, dc = dirs[nd]
        nr, nc = sr+dr, sc+dc
        if not maps[nr][nc] and not parked[nr][nc]:
            sr, sc, sd = nr, nc, nd
            return True
    return False


def parking():
    global sr, sc, sd
    cnt = 0
    while 1:
        parked[sr][sc]=1
        cnt += 1
        while 1:
            if not searchingToPark():
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
    parked = [[0 for _ in range(M)] for _ in range(N)]
    print(parking())