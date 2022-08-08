import sys
input = sys.stdin.readline


def springSummer():
    global ans
    for ty in range(N):
        for tx in range(N):
            if maps[ty][tx]:
                now = maps[ty][tx]
                now.sort()
                newTrees = []
                death = 0
                for i in now:
                    if i<=grnd[ty][tx]:
                        grnd[ty][tx]-=i
                        newTrees.append(i+1)
                    else:
                        death += i//2
                        ans -= 1
                grnd[ty][tx] += death
                maps[ty][tx] = newTrees


def fall():
    global ans
    for i in range(N):
        for j in range(N):
            if maps[i][j]:
                for k in maps[i][j]:
                    if not k%5:
                        for dy, dx in dirs:
                            ny, nx = i+dy, j+dx
                            if ny<0 or ny>=N or nx<0 or nx>=N:
                                continue
                            maps[ny][nx].append(1)
                            ans += 1


def winter():
    for i in range(N):
        for j in range(N):
            grnd[i][j] += nutirits[i][j]


def anYearPassBy():
    springSummer()
    fall()
    winter()


if __name__=='__main__':
    N, M, K = map(int, input().split())
    nutirits = [list(map(int, input().split())) for _ in range(N)]
    maps = [[[] for _ in range(N)] for _ in range(N)]
    grnd = [[5 for _ in range(N)] for _ in range(N)]
    for i in range(M):
        ty, tx, old = map(int, input().split())
        maps[ty-1][tx-1].append(old)
    ans = M
    dirs = [(-1,-1),(-1,0),(-1,1),(0,-1),(0,1),(1,-1),(1,0),(1,1)]
    for k in range(K):
        anYearPassBy()
    print(ans)
'''
N < 10
r, c
모든 칸 5씩 양분
M개의 나무 (최대 100)
=> dict : val에 더하고, treeList에 append
봄 : 어린 나무부터 자기 나이만큼 양분 먹음. 나이+1. 양분 못먹으면 죽음
여름 : 봄에 죽은 나무가 나이//2만큼 양분 됨
=> val이 현재 양분보다 작아질때까지 pop해서 //2만큼 새 양분에 추가하기
가을 : 나이 5의 배수인 나무만 번식. 인접 8개 칸에 나이 1인 나무 생김.
겨울 : 기계가 A배열대로 양분 추가

1. trees에서 나무 있는 좌표 for문 돌기
2. maps[r][c] sum이 현재 양분보다 작아질 때까지 pop해서 
   //2만큼 새 양분 추가하고 
   => 나무 없으면 trees에서 remove (**)
   => 나무 수 -1
   그 이전 나무들은 나이 +1하고 5의 배수이면 인접칸에 나이1인 나무 번식 
   => 나무 수 +1
3. A배열대로 양분 추가
  
K년 후 살아있는 나무의 개수?
'''