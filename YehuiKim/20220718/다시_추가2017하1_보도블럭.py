import sys
input = sys.stdin.readline


def checkHeight(arr):
    now = arr[0]
    cnt = 0
    i = 0
    while i<N:
        new = arr[i]
        if now==new:
            cnt+=1
            i += 1
        elif now==new+1:
            for j in range(L):
                if i+j>=N:
                    return False
                if new!=arr[i+j]:
                    return False
            cnt = 0
            i += L
            now = new
        elif now==new-1:
            if cnt<L:
                return False
            cnt = 1
            now = new
            i += 1
        else:
            return False
    return True


if __name__=='__main__':
    N, L = map(int, input().split())
    maps = [[0 for _ in range(N)] for _ in range(N)]
    vmaps = [[0 for _ in range(N)] for _ in range(N)]
    for i in range(N):
        temp = list(map(int, input().split()))
        for j in range(N):
            maps[i][j] = temp[j]
            vmaps[j][i] = temp[j]

    ans = 0
    for i in range(N):
        if checkHeight(maps[i]):
            ans += 1
        if checkHeight(vmaps[i]):
            ans += 1
    print(ans)
'''

'''