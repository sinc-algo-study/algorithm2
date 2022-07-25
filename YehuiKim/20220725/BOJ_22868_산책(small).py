import sys
input = sys.stdin.readline


if __name__=='__main__':
    N, M = map(int,input().split())
    graph = []
    for i in range(M):
        a, b = map(int,input().split())
    S, E = map(int,input().split())

'''
S에서 E찍고 다시 S로
숫자 같으면 사전순으로
=>이긴한데, 그냥 최단경로만 구하면 되기 때문에, 제일 짧은 2개 구하면 될듯
N<10000, M<50000
'''