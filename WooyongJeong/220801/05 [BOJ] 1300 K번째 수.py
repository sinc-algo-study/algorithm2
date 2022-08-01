"""
그냥 B[k]를 구하면.. O(N^2 + NlogN)이 걸림
N <= 10^5이므로.. 불가능

A
  1 2 3  4
1 1 2 3  4
2 2 4 6  8
3 3 6 9  12
4 4 8 12 16

B
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
1 2 2 3 3 4 4 4 6  6  8  8  9 12 12 16


K = 11일 때, B[11] = 8
B[11] = 8의 의미는 '배열 B의 11번째 값이 8'
-> 배열 B가 오름차순으로 정렬되어 있기 때문에
이는 거꾸로 '배열 B에 8 이하인 원소 개수는 최소 11개다.'라는 뜻으로 볼 수 있음

그렇기 때문에 B[K] = x를 구하려면 배열 B에서 x 이하인 원소 개수의 최소값을 구하면 됨
-> 최적화 문제를 결정 문제로 바꿔서 해결하는 Parametric Search
=> 이분 탐색을 통해 'mid 이하의 수가 배열 전체에 몇 개인지를 묻는 질문'을 반복적으로 던짐
즉, mid - 1 이하의 수는 k개 미만이지만 mid 이하의 수가 k개 이상이라면 mid는 k번째 수가 됨
"""

N = int(input())
k = int(input())


def find_kth_number():
    left, right = 1, N * N + 1
    ans = 0
    while left + 1 < right:
        mid = left + (right - left) // 2
        cnt = 0

        for i in range(1, N + 1):
            cnt += min(mid // i, N)

        if cnt < k:
            left = mid
        else:
            ans = mid
            right = mid

    return ans


if k == 1:
    print(1)
else:
    print(find_kth_number())
