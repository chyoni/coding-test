package cwchoiit.solutions.solution2026.march;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem: Longest Balanced Substring of Parentheses You are given a string s consisting only of
 * the characters '(' and ')'. Your task is to find the length of the longest contiguous substring
 * that forms a valid parentheses sequence.
 *
 * <p>A valid parentheses sequence is defined as:
 *
 * <p>Every opening bracket '(' has a corresponding closing bracket ')'.
 *
 * <p>Pairs are properly nested (like in usual arithmetic expressions).
 *
 * <p>Return the length of that longest valid substring. If there is no valid substring, return 0.
 *
 * <p>Constraints - 1 <= s.length <= 100_000 - s[i] is either '(' or ')' - Time complexity should be
 * better than O(n²) (An O(n) or O(n log n) solution is expected.) - Additional space usage should
 * be reasonable (e.g., O(n) or O(1))
 *
 * <p>Example 1 Input: s = "(()" Output: 2 Explanation: The longest valid substring is "()" (from
 * index 1 to 2). Substring "(()" is not fully valid because there is one unmatched '('.
 *
 * <p>Example 2 Input: s = ")()())" Output: 4 Explanation: The longest valid substring is "()()"
 * (from index 1 to 4). There are other valid substrings like "()" (length 2), but the maximum
 * length is 4.
 *
 * <p>Example 3 Input: s = "))))((((" Output: 0 Explanation: There is no valid parentheses substring
 * at all, so the answer is 0.
 */
public class Solution260325 {
    public static void main(String[] args) {
        String s = ")()())";
        // String s = "(()";
        // String s = "))))((((";
        // String s = "((()))";
        // String s = "(()(()))";
        int answer = solution(s);
        System.out.println("answer = " + answer);
    }

    private static int solution(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0;
        int lastInvalidIndex = -1;

        for (int i = 0; i < s.length(); i++) {
            char bracket = s.charAt(i);

            if (bracket == '(') {
                stack.push(i);
            } else { // ')'

                if (stack.isEmpty()) {
                    lastInvalidIndex = i;
                } else {
                    stack.pop(); // 짝이 맞는 '(' 하나 빼기

                    if (stack.isEmpty()) {
                        // 스택이 비었다면, 현재 인덱스에서 마지막으로 체크한 유효하지 않은 인덱스 사이의 길이가 최대 길이
                        max = Math.max(max, i - lastInvalidIndex);
                    } else {
                        // 스택이 비어있지 않다면, 현재 인덱스에서 스택에 넣은 가장 맨 위에 있는 인덱스 사이의 길이가 최대 길이
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
        }

        return max;
    }
}

