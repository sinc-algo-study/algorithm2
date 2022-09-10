def solution(buildings):
    left = [0 for _ in range(len(buildings))]
    left, left_higher_idxes = find_higher_building_left(buildings, left)
    right = [0 for _ in range(len(buildings))]
    right, right_highter_idxes = find_higher_building_right(buildings, right)


    answer = []
    for i in range(len(buildings)):
        count = left[i] + right[i]

        closest_idx = 0
        # 거리 가까운 건물의 번호 중 작은 번호
        if abs(left_higher_idxes[i] - (i+1)) > abs(right_highter_idxes[i] - (i+1)):
            closest_idx = right_highter_idxes[i]
        else:
            closest_idx = left_higher_idxes[i]

        answer.append((count, closest_idx))
    return answer


def find_higher_building_right(buildings, right):
    higher_idxes = [200000 for _ in range(len(buildings))]  # 현재 위치에서 가장 가까운 높은 건물의 번호
    stack = [(0, 0)]
    # 우선순위 : 거리 가까운 순 > 더 긴 막대 순
    for idx, height in enumerate(buildings[::-1]):
        idx = len(buildings) - 1 - idx
        # 더 긴 막대 순
        while stack and stack[-1][1] <= height:
            stack.pop()

        if stack:
            higher_idxes[idx] = stack[-1][0]
            right[idx] = len(stack)

        # 거리 가까운 순
        stack.append((idx + 1, height))
    return right, higher_idxes


def find_higher_building_left(buildings, left):
    higher_idxes = [200000 for _ in range(len(buildings))]  # 현재 위치에서 가장 가까운 높은 건물의 번호
    stack = [(0, 0)]
    # 우선순위 : 거리 가까운 순 > 더 긴 막대 순
    for idx, height in enumerate(buildings):
        # 더 긴 막대 순
        while stack and stack[-1][1] <= height:
            stack.pop()

        if stack:
            higher_idxes[idx] = stack[-1][0]
            left[idx] = len(stack)

        # 거리 가까운 순
        stack.append((idx + 1, height))
    return left, higher_idxes


if __name__ == '__main__':
    n = int(input())
    buildings = list(map(int, input().split()))

    answer = solution(buildings)
    for tuple in answer:
        if tuple[0]==0:
            print(tuple[0])
        else:
            print(*tuple, sep=' ')
