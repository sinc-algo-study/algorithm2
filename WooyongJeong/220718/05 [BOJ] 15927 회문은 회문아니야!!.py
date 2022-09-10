s = input()


def is_palindrome(s):
    n = len(s)
    for i in range(n // 2):
        if s[i] != s[n - 1 - i]:
            return False
    return True


def is_all_same_character(s):
    tmp = set(list(s))
    return len(tmp) == 1


if is_all_same_character(s):
    print(-1)
else:
    print(len(s) - 1 if is_palindrome(s) else len(s))
