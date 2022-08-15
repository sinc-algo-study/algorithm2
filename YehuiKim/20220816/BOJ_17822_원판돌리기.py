from collections import deque


def delAdjNum():
    toDel = set()
    for i in range(N):
        for j in range(M):
            now = plates[i][j]
            if now:
                for di, dj in dirs:
                    ni, nj = i + di, (j + dj)%M
                    if ni<0 or ni>=N:
                        continue
                    if now == plates[ni][nj]:
                        toDel.add((i,j))
                        toDel.add((ni,nj))
    if toDel:
        for i, j in toDel:
            plates[i][j] = 0
        return True
    else:
        return False


def getCountSum():
    global ans, cnt
    subCnt, subSum = 0, 0
    for i in range(N):
        for j in range(M):
            if plates[i][j]:
                subCnt += 1
                subSum += plates[i][j]
    ans, cnt = subSum, subCnt
    if cnt and ans:
        return ans/cnt
    else:
        return False


def avgAdoption():
    global plates
    avg = getCountSum()
    if avg:
        for i in range(N):
            for j in range(M):
                if 0<plates[i][j]<avg:
                    plates[i][j] += 1
                elif plates[i][j]>avg:
                    plates[i][j] -= 1
        return True
    else:
        print(0)
        return False


if __name__ == '__main__':
    N, M, T = map(int, input().split())
    plates = []
    ans, cnt = 0, N*M
    for _ in range(N):
        tmp = list(map(int, input().split()))
        plates.append(deque(tmp))
        ans += sum(tmp)
    dirs = [(0,1), (0,-1), (-1,0), (1,0)]
    for _ in range(T):
        x, d, k = map(int, input().split())
        if d==0:
            d=1
        else:
            d=-1
        # 원판 회전시키기
        for i in range(x-1, N, x):
            plates[i].rotate(d*k)
        if not delAdjNum():
            if not avgAdoption():
                exit()
    getCountSum()
    print(ans)

'''
N, M <= 50
T <= 50
원판에 적힌 수 <= 1000

i번째 원판의 j번째 수
원판 회전은 독립적
수의 위치를 기준으로 회전
T번 회전

1. x의 배수인 원판, d방향(0시계&1반시계), k칸 회전
2. 원판에 남아있으면, 그 중 인접한 수 찾기
    => 인접하면서 같은 수 지우기
    => 같은 수 없으면 원판에 적힌 수의 평균보다 큰수는 -1, 작은 수는 +1
    => ?? 선택한 원반만 돌리는 것인지?
=> T번 회전 후 원판에 적힌 수의 합

## 실수 포인트
1. 원판끼리는 0에서 N으로 이어지지 않지만, 원판 내에서는 0에서 M으로 이어진다는 점!
2. 원판끼리 연속적으로 숫자가 같은 경우 for문을 돌면서 바로 0으로 만들어버리면, 다음 지점에서 비교할 대상이 없어서 삭제가 안될 수 있음
    => 지울 항목을 따로 보관해뒀다가 한번에 삭제하는게 깔끔하고 그래야 정답 나오더라
'''