import sys
input = sys.stdin.readline


def moveRobots():
    global ans
    for rnum, order, repeat in orders:
        rx, ry, rd = robots[rnum]
        if order!=0:  # 회전하는 경우 : 로봇 방향만 바꿔주기
            robots[rnum] = (rx, ry, (rd+order*(repeat%4))%4)
            continue
        # 움직이는 경우 : 한 칸씩 옮기기
        org_x, org_y = rx, ry
        for k in range(repeat):
            dy, dx = dirs[rd]
            ny, nx = ry+dy, rx+dx
            if ny<0 or ny>=B or nx<0 or nx>=A:
                ans = 'Robot '+ str(rnum+1) + ' crashes into the wall'
                return
            if maps[ny][nx]!=0:
                ans = 'Robot '+ str(rnum+1) + ' crashes into robot ' + \
                      str(maps[ny][nx])
                return
            ry, rx = ny, nx
        maps[org_y][org_x], maps[ry][rx] = 0, rnum+1
        robots[rnum] = (rx, ry, rd)
    return


if __name__ == '__main__':
    A, B = map(int, input().split()) # 가로, 세로
    N, M = map(int, input().split())
    orders = []
    robots = []
    maps = [[0 for _ in range(A)] for _ in range(B)]
    for i in range(N):
        rx, ry, rd = input().split()
        rx, ry = int(rx)-1, B-int(ry)
        if rd=='N': rd=0
        elif rd=='E': rd=1
        elif rd=='S': rd=2
        else: rd=3
        robots.append((rx, ry, rd))
        maps[ry][rx] = i+1
    for _ in range(M):
        rnum, order, repeat = input().split()
        if order=='F':order=0
        elif order=='L':order=-1
        else: order=1
        orders.append((int(rnum)-1, order, int(repeat)))
    dirs = [(-1,0),(0,1),(1,0),(0,-1)]  # y,x 북동남서
    ans = 'OK'
    moveRobots()
    print(ans)

'''
가로 A, 세로 B, N
y는 반대로

M개 명령 (L,R,F)
-벽에 충돌
-다른 로봇에 충돌

최대 100개의 명령 * 최대 100번 반복 = 10000번 이동
=> 무지성으로 돌리기

1. 로봇 배치 
 => maps에 로봇번호 놓기
2. 로봇 옮기기
-명령대로 이행하면서 확인하기
 => L, R은 %4한 값만큼 돌리기
-벽에 부딪히면 return
-로봇에 부딪히면 현로봇+1, 부딪힘당한 로봇값+1 return

5 4
2 3
1 1 E
5 4 W
2 F 4
2 L 1
2 F 3
'''

