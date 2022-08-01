'''
궁금한 점 : 보통 main 모듈 쓰나요....?
'''


# -99 -2 -1 4 98
# -99 -2 -1 4 5
def solution(liquids):
    liquids.sort()

    answer = (0, 0)
    sum = 2000000001

    left = 0
    right = len(liquids) - 1

    while left < right:
        if abs(liquids[left] + liquids[right]) < abs(sum):  # 용액의 합이 0에 가까운 조합 갱신
            sum = liquids[left] + liquids[right]
            answer = (liquids[left], liquids[right])

        if abs(liquids[left] + liquids[right]) > abs(liquids[left + 1] + liquids[right]):
            left += 1
        elif abs(liquids[left] + liquids[right]) > abs(liquids[left] + liquids[right - 1]):
            right -= 1
        else:  # (l+1,r) 용액의 합, (l,r-1) 용액의 합보다 현재 용액의 합이 작은 경우
            # 옮겼을 때 차이가 클 곳으로 이동
            if abs(liquids[left] - liquids[left + 1]) >= abs(liquids[right] - liquids[right - 1]):
                left += 1
            else:
                right -= 1

    return answer


if __name__ == '__main__':
    N = int(input().strip())
    liquids = list(map(int, input().split()))
    l1, l2 = solution(liquids)
    print(l1, l2)
