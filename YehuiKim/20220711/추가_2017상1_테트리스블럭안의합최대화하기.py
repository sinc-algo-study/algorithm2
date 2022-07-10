import sys
input = sys.stdin.readline


if __name__=='__main__':
    N, M = map(int, input().split())
    maps = []
    for i in range(N):
        maps.append(list(map(int, input().split())))
    spots = [(0,0)]
    for i in range(4):
        for j in range(4):
            spots.append((i,j))
    blocks = [[1,2,3,4], [1,5,9,13],
              [1,2,5,6],
              [1,2,3,5],[1,5,6,7],[3,5,6,7],[1,2,3,7],
              [1,5,9,10],[2,6,9,10],[1,2,5,9],[1,2,6,10],
              [1,5,6,10],[2,5,6,9],[2,3,5,6],[1,2,6,7],
              [1,5,6,9],[1,2,3,6],[2,5,6,10],[2,5,6,7]
             ]
    ans = 0
    for i in range(N):
        for j in range(M):
            for block in blocks:
                tempAns = 0
                for b in block:
                    di, dj = spots[b]
                    ni, nj = i+di, j+dj
                    if ni<0 or ni>=N or nj<0 or nj>=M:
                        break
                    tempAns += maps[ni][nj]
                ans = max(ans, tempAns)

    print(ans)


