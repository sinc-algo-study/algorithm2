import sys
input = sys.stdin.readline


if __name__=='__main__':
    N = int(input().rstrip())
    works = []
    for _ in range(N):
        t, p = map(int, input().split())
        works.append((t,p))
    memo = [[0 for _ in range(N)]]

    for i in range(N):
        flag = True # 해당 위치에서 값 넣을 수 있는지 체크
        for mem in memo:
            if mem[i]==0:
                mem[]


'''

'''

