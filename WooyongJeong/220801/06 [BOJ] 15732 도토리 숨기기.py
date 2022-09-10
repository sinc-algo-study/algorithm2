"""
A번 상자부터 B번 상자까지 C개 간격으로 도토리를 하나씩 더 넣는 규칙
이러한 규칙들을 K개를 만들어 도토리를 최대한 안전하게 저장

예를 들어 100번 상자부터 150번상자까지 10개 간격으로,
110번 상자부터 150번 상자까지 15개 간격으로 넣는다면
100, 110, 120, 125, 130, 140, 150번 상자에 도토리가 있으며
110번 상자와 140번 상자에는 2개의 도토리가 들어가 있게 된다.

마지막 도토리가 들어가 있는 상자의 번호를 출력
상자 개수 N <= 100만
규칙 개수 K <= 1만
도토리 개수 D <= 10억
-> 브루트포스로 해결하기엔 너무 큼

마지막 도토리가 들어가 있는 상자 k가 정해지면,
k번 이상의 상자들에 도토리가 들어가는지 들어가지 않는지를 O(K)에 판단 가능
이러한 제일 큰 값 k를 이분 탐색으로 찾으면 됨
-> Parametric Search
"""

N, K, D = map(int, input().split())
rules = [list(map(int, input().split())) for _ in range(K)]

left, right = 0, N
while left + 1 < right:
    mid = left + (right - left) // 2
    cnt = 0
    for start, end, interval in rules:
        # 마지막 상자를 mid라고 가정했지만,
        # 각 규칙에서 도토리가 들어가는 마지막 상자가 정해져 있으므로 min(mid, end)
        tmp = min(mid, end)
        if tmp >= start:  # 해당 규칙 내에 tmp(mid)가 들어오면
            # 해당 규칙에 상자가 몇 개 인지 count
            cnt += (tmp - start) // interval + 1

    if cnt >= D:
        # 상자가 D개보다 많다면 도토리가 들어있는 마지막 상자는 더 앞에 있어야 함
        right = mid
    else:
        left = mid

print(right)
