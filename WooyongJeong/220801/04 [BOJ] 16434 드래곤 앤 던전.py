import sys

N, init_hero_atk = map(int, input().split())
dungeon_rooms = [list(map(int, input().split())) for _ in range(N)]


def can_slay_the_dragon(max_hp):
    hero_atk = init_hero_atk
    hero_cur_hp = max_hp
    hero_max_hp = max_hp

    for room_type, atk, hp in dungeon_rooms:
        if room_type == 1:  # 몬스터
            if (hp - 1) // hero_atk > (hero_cur_hp - 1) // atk:
                return False
            hero_cur_hp -= (hp - 1) // hero_atk * atk
        else:  # 포션
            hero_atk += atk
            hero_cur_hp = min(hero_cur_hp + hp, hero_max_hp)

    return True


ans = 0
left, right = 0, int(1e18)
while left + 1 < right:
    mid = left + (right - left) // 2

    if can_slay_the_dragon(mid):
        ans = mid
        right = mid
    else:
        left = mid

print(ans)
