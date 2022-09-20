import sys
input = sys.stdin.readline


def rotate():
    global belts, strengths
    belts = [0]+belts[:N-1]+[0 for _ in range(N)]
    strengths = [strengths[-1]]+strengths[:-1]


def checkStrength(idx):
    global cnt
    if strengths[idx]==0:
        cnt += 1
    if cnt >= K:
        return True
    return False


def movingRobots():
    global belts, strengths
    belts[N-1]=0
    for i in range(N-2,-1,-1):
        if belts[i] and not belts[i+1] and strengths[i+1]:
            belts[i], belts[i+1] = belts[i+1], belts[i]
            strengths[i+1] -= 1
            if checkStrength(i+1):
                return True
    return False


def newRobots():
    global belts, strengths
    if strengths[0]:
        belts[0] = 1
        strengths[0] -= 1
        if checkStrength(0):
            return True
    return False


if __name__ == '__main__':
    N, K = map(int, input().split())
    strengths = list(map(int, input().split()))
    belts = [0 for _ in range(2*N)]
    ans = 0
    cnt = 0
    while 1:
        ans += 1
        rotate()
        if movingRobots():
            break
        if newRobots():
            break
    print(ans)

'''
길이 N인 컨베이어 벨트
2N 벨트 위아래로 감싸며
1 간격으로 2N
=>1번 : 올리는 위치, N번: 내리는 위치 => 로봇이 올리고 내림

로봇 내구도 1만큼 감소
0. while문(무한 루트)
1. 벨트 회전 (그 위 로봇도 회전)
=> 내구도랑 벨트 둘다 (2N이 1로 가고 1이 2로)
2. 먼저 올라간 로봇부터 이동 : 벨트 회전하는 방향으로 1칸 이동. 이동 못하면 가만히
    => 이동하려는 칸에 로봇 없고, 내구도 1 이상이어야 함
=> N-1부터 0까지 체크하면서 로봇 있으면 앞으로 이동(내구도 -1&내구도 0이면 ans+1)
=> N은 제거하기
3. 로봇 올리기(내구도 0 아닐때에만)
=> 0번째에 올리기 (내구도 -1&내구도 0이면 ans+1)
4. 내구도 0인 칸 개수 K개 이상되면 종료.
=> 몇 번째에서 종료되는지 찾기
'''
