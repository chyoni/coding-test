package cwchoiit.solutions.solution2026.march;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * <p>
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * <p>
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * Constraints:
 * 0 <= s.length <= 5 * 10^4
 * s consists of English letters, digits, symbols and spaces.
 */
public class Solution260308 {
    public static void main(String[] args) {
        // String s = "abcabcbb";
        // String s = "bbbbb";
        String s = "pwwkew";

        int answer = solution(s);
        System.out.println("answer = " + answer);
    }

    private static int solution(String s) {
        int left = 0;
        int answer = Integer.MIN_VALUE;

        // 인덱스를 저장하지 않는다면 Set이 베스트
        Set<Character> set = new HashSet<>();
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            while (set.contains(c)) {
                set.remove(s.charAt(left++));
            }

            set.add(c);
            answer = Math.max(answer, set.size());
        }

        return answer == Integer.MIN_VALUE ? 0 : answer;
    }
}
