import heapq
from collections import defaultdict

INF = 1000000000
graph = defaultdict(list)

def solution(video_usados, questions):
    for connect in video_usados:
        p, q, r = connect
        graph[p].append((q, r))
        graph[q].append((p, r))

    answers = []
    for question in questions:
        k, v = question
        answers.append(bfs(k, v))
    return answers

def bfs(k,start):
    answer=0
    visited = [False for _ in range(N + 1)]
    hq = []
    heapq.heappush(hq, start)
    visited[start] = True

    while hq:
        now=heapq.heappop(hq);
        for num, u in graph[now]:
            if not visited[num] and u>=k:
                heapq.heappush(hq,num)
                visited[num]=True
                answer+=1
    return answer

if __name__ == '__main__':
    global N
    N, Q = map(int, input().split(' '))
    video_usados = []
    for _ in range(N - 1):
        p, q, r = map(int, input().split(' '))
        video_usados.append((p, q, r))

    questions = []
    for _ in range(Q):
        k, v = map(int, input().split(' '))
        questions.append((k, v))

    answer = solution(video_usados, questions)
    print(*answer, sep='\n')
