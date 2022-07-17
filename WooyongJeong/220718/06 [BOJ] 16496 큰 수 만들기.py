from functools import cmp_to_key


def cmp(a, b):
    if a + b > b + a:
        return -1
    elif a + b < b + a:
        return 1
    return 0


N = int(input())
arr = list(input().split())

arr.sort(key=cmp_to_key(cmp))
ans = ''.join(arr)
if ans[0] == '0':
    print('0')
else:
    print(ans)
