"""
1. 입력 문자열을 앞에서부터 차례대로 stack에 push 합니다.
2. 현재 문자가 폭발 문자열의 마지막 글자와 일치한다면,
   스택의 top부터 폭발 문자열의 길이만큼을 확인하여 폭발 문자열이 만들어지는지 확인합니다.
3. 폭발 문자열이 만들어지는 경우, 해당 문자열의 del 합니다.
4. 1-3을 반복한 후, 스택이 비어있으면 FRULA를 출력, 문자열이 있다면 차례대로 출력합니다.
"""

string = input()
bomb = input()

last_char = bomb[-1]
l = len(bomb)
stack = []

for s in string:
    stack.append(s)
    if s == last_char and ''.join(stack[-l:]) == bomb:
        del stack[-l:]

answer = "".join(stack)

if answer:
    print(answer)
else:
    print("FRULA")