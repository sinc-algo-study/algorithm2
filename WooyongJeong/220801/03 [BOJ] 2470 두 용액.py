"""
N <= 10만이므로
10만C2 = 약 50억 -> 완탐 불가능 -> O(N)이나 O(NlogN)으로
"""
N = int(input())
arr = list(map(int, input().split()))

arr.sort()

left, right = 0, N - 1
min_val = float('inf')
ans = [0, 0]

while left < right:
    sum_val = arr[left] + arr[right]
    if abs(sum_val) < min_val:
        min_val = abs(sum_val)
        ans = [arr[left], arr[right]]

    if sum_val <= 0:
        left += 1
    else:
        right -= 1

print(*ans)
