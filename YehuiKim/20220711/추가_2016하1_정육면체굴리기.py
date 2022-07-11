import sys
input = sys.stdin.readline


def rotateDice(d):
    global dice
    if d==0:
        dice[0], dice[2], dice[4], dice[5] = dice[4], dice[5], dice[2], dice[0]
    elif d==1:
        dice[0], dice[2], dice[4], dice[5] = dice[5], dice[4], dice[0], dice[2]
    elif d==2:
        dice = dice[1:4] + dice[:1] + dice[4:]
    else:
        dice = dice[3:4] + dice[:3] + dice[4:]


def moveDice(d):
    global sy, sx
    dy, dx = dirs[d]
    ny, nx = sy+dy, sx+dx
    if ny<0 or ny>=N or nx<0 or nx>=M:
        return False
    # - 칸이 0이면, 주사위 바닥 숫자가 칸으로 복사.
    # - 칸이 0 아니면, 칸 숫자가 정육면체 바닥으로 이동.
    rotateDice(d-1)
    if maps[ny][nx] == 0:
        maps[ny][nx] = dice[2]
    else:
        dice[2], maps[ny][nx] = maps[ny][nx], 0
    sy, sx = ny, nx
    return True


if __name__ == '__main__':
    N, M, sy, sx, K = map(int, input().split())
    maps = []
    for i in range(N):
        maps.append(list(map(int, input().split())))
    moves = list(map(int, input().split()))

    dice = [0, 0, 0, 0, 0, 0]
    dirs = [0, (0, 1), (0, -1), (-1, 0), (1, 0)]  # y,x 동 서 북 남
    for k in range(K):
        if moveDice(moves[k]):
            print(dice[0])
'''
n, m 
바닥 숫자가 정육면체 바닥으로 복사. 해당 칸 숫자 0.
격자판 밖으로 이동 불가.
초기 위치. 굴리는 방향 주어짐.
- 칸이 0이면, 주사위 바닥 숫자가 칸으로 복사.
- 칸이 0 아니면, 칸 숫자가 정육면체 바닥으로 이동.
=> 정육면체 상단 면에 쓰여진 숫자는?
x, y가 반대??
'''