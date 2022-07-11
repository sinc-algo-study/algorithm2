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