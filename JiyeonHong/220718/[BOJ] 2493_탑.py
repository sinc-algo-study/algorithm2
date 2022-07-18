def solution(towers):
    answer=[0 for _ in range(len(towers))]
    tower_stack = [0]
    idx_stack=[0]

    # 우선순위 : 거리 가까운 순 > 더 긴 막대 순
    for idx,tower in enumerate(towers):
        while tower_stack:
            top = tower_stack[-1]
            #긴 막대 순
            if top<=tower:
                tower_stack.pop()
                idx_stack.pop()
            else:
                break

        #answer 값 지정
        if tower_stack:
            answer[idx]=idx_stack[-1]

        #거리 가까운 순
        tower_stack.append(tower)
        idx_stack.append(idx+1)
    return answer

if __name__ == '__main__':
    n = int(input())
    towers = list(map(int,input().split(' ')))

    answer=solution(towers)
    print(*answer,sep=' ')
