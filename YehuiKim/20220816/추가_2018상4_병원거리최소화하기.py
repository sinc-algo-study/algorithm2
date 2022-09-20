
def recursive(cnt, idx, arr):
    global ans
    if cnt>=M:
        total = 0
        for my, mx in mans:
            sub = 1000
            for hy, hx in arr:
                sub = min(sub, abs(my-hy)+abs(mx-hx))
            total += sub
        ans = min(ans, total)
        return
    for i in range(idx, lenH-(M-cnt)+1):
        recursive(cnt+1, i+1, arr+[hospitals[i]])


if __name__=='__main__':
    N, M = map(int, input().split())
    maps = []
    mans, hospitals = [], []
    ans = 10**6
    for i in range(N):
        tmp = list(map(int, input().split()))
        for j in range(N):
            if tmp[j]==1:
                mans.append((i,j))
            elif tmp[j]==2:
                hospitals.append((i,j))
        maps.append(tmp)
    lenMans, lenH = len(mans), len(hospitals)
    recursive(0, 0, [])
    print(ans)

'''
1초
N 50*50 M 13
사람 2*50
빈칸 0, 사람 1, 병원 2
=> m개 병원만 남기고 나머지 폐업. 남은 m개 병원에 대한 각 사람들 거리 합 최소 되도록
1. 병원 좌표, 사람 좌표 담기
2. 회귀문으로 병원 M개로 조합 생성
3. 각 조합에 대해 사람 for문 돌면서 거리 측정하고 최솟값 갱신하기

'''