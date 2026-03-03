package cwchoiit.solutions.solution2026.march;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Description:
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
 * <p>
 * Example 1:
 * Input: heights = [2, 1, 5, 6, 2, 3]
 * Output: 10
 * Explanation: The largest rectangle is formed by bars [5, 6] with a height of 5 and width of 2 (Area = 10).
 * <p>
 * Example 2:
 * Input: heights = [2, 4]
 * Output: 4
 * <p>
 * Constraints:
 * 1 <= heights.length <= 10^5
 * 0 <= heights[i] <= 10^4
 */
public class Solution260303 {
    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        int answer = solutionGemini(heights);
        System.out.println("answer = " + answer);
    }

    private static int solution(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];

            if (!stack.isEmpty() && height > heights[stack.peek()]) {
                if (i - stack.peek() != 1) {
                    int minHeight = Integer.MAX_VALUE;
                    int size = stack.size();
                    while (!stack.isEmpty()) {
                        minHeight = Math.min(minHeight, heights[stack.pop()]);
                    }
                    answer = Math.max(answer, minHeight * size);
                }
                stack.push(i);
            } else if (stack.isEmpty()) {
                stack.push(i);
            }
        }

        if (!stack.isEmpty()) {
            int minHeight = Integer.MAX_VALUE;
            int size = stack.size();
            while (!stack.isEmpty()) {
                minHeight = Math.min(minHeight, heights[stack.pop()]);
            }
            return Math.max(answer, minHeight * size);
        } else {
            return answer;
        }
    }

    public static int solutionGemini(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;

        // 1. 배열 끝에 높이 0짜리 막대를 추가한다고 생각하면
        // 스택에 남은 모든 막대가 마지막에 깔끔하게 처리됩니다.
        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            // 2. 현재 막대가 스택 top보다 작으면 '경계선'을 만난 것!
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()]; // 사각형의 높이

                // 3. 너비 계산:
                // 현재 i는 오른쪽 경계, stack.peek()은 왼쪽 경계
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;

                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
