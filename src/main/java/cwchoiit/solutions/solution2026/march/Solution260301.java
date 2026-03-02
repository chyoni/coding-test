package cwchoiit.solutions.solution2026.march;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Description:
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 * <p>
 * Example 1:
 * Input: s = "()"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 * <p>
 * Example 3:
 * Input: s = "(]"
 * Output: false
 * <p>
 * Example 4:
 * Input: s = "([])"
 * Output: true
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^4
 * s consists of parentheses only '()[]{}'.
 */
public class Solution260301 {

    public static void main(String[] args) {
        String s = "()";
        // String s = "()[]{}";
        // String s = "(]";
        // String s = "([])";
        // String s = "]]]";
        // String s = "((";

        boolean answer = solution(s);
        System.out.println("answer = " + answer);
    }

    // Stack 클래스보다 훨씬 성능이 압도적으로 좋은 Deque! (내부에 synchronized 가 없음)
    // 만약, 동기화를 고려해야한다면, ConcurrentLinkedDeque 사용!
    private static boolean solution(String s) {
        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char bracket = s.charAt(i);
            if (!process(bracket, deque)) {
                return false;
            }
        }
        return deque.isEmpty();
    }

    private static boolean process(char bracket, Deque<Character> deque) {
        if (bracket == ')' && !deque.isEmpty()) {
            return deque.pop().equals('(');
        } else if (bracket == '}' && !deque.isEmpty()) {
            return deque.pop().equals('{');
        } else if (bracket == ']' && !deque.isEmpty()) {
            return deque.pop().equals('[');
        } else {
            deque.push(bracket);
            return true;
        }
    }
}
