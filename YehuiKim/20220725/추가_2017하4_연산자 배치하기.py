import sys
input = sys.stdin.readline


def doOperate(a, op, b):
    if op==0:
        return a+b
    elif op==1:
        return a-b
    return a*b


def dfs(now, newOperator, idx):
    global minAns, maxAns
    if sum(newOperator)==0:
        minAns = min(minAns, now)
        maxAns = max(maxAns, now)
        return
    idx += 1
    for o in range(3):
        if newOperator[o]==0:
            continue
        newOperator[o] -= 1
        dfs(doOperate(now, o, nList[idx]), newOperator, idx)
        newOperator[o] += 1


if __name__=='__main__':
    N = int(input().rstrip())
    nList = list(map(int, input().split()))
    operators = list(map(int, input().split()))
    minAns, maxAns = 100**11, -100**11
    dfs(nList[0], operators, 0)
    print(minAns, maxAns)

'''
dfs
앞에서부터 + - x 를 넣어가면서 
'''