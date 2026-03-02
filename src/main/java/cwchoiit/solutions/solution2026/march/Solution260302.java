package cwchoiit.solutions.solution2026.march;

import java.util.*;

public class Solution260302 {
    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};

        int[] answer = solution(temperatures);
        System.out.println("answer = " + Arrays.toString(answer));
    }

    private static int[] solution(int[] temperatures) {
        Deque<Integer> deque = new ArrayDeque<>();

        for (int temperature : temperatures) {
            deque.push(temperature);
        }

        List<Integer> answer = new ArrayList<>();
        List<Integer> pops = new ArrayList<>();

        while (!deque.isEmpty()) {
            Integer pop = deque.pop();
            pops.add(pop);

            int minTarget = Integer.MAX_VALUE;
            for (int i = 0; i < pops.size(); i++) {
                Integer i1 = pops.get(i);
                if (!i1.equals(pop) && i1 > pop) {
                    minTarget = Math.min(minTarget, pops.size() - 1 - i);
                }
            }

            if (minTarget != Integer.MAX_VALUE) {
                answer.add(minTarget);
            } else {
                answer.add(0);
            }
        }

        return answer.reversed().stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static int[] solutionGemini(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        // 우리는 인덱스를 담을 거예요!
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // 현재 온도가 스택에 쌓인 '이전 날들'보다 높다면, 그 날들의 정답을 채워줍니다.
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                answer[prevIndex] = i - prevIndex; // 며칠 기다렸는지 계산
            }

            // 현재 날짜를 스택에 넣고 다음 날로 이동!
            stack.push(i);
        }

        return answer;
    }
}
