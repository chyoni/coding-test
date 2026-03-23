package cwchoiit.solutions.solution2026.march;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem: Longest Substring Without Repeating Characters
 * You are given a string s.
 * Your task is to find the length of the longest substring of s that contains no repeating characters.
 * <p>
 * A substring is a contiguous sequence of characters within a string.
 * <p>
 * Return an integer representing the maximum length.
 * <p>
 * Input Format
 * A single string s consisting of English letters, digits, symbols, and spaces.
 * <p>
 * Output Format
 * A single integer: the length of the longest substring without repeating characters.
 * <p>
 * Example 1
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation:
 * The longest substring without repeating characters is "abc", which has length 3.
 * Other candidates like "bca" or "cab" also have length 3, but the maximum is still 3.
 * <p>
 * Constraints
 * 0 <= s.length <= 10^5
 * s may contain:
 * - lowercase/uppercase English letters (a-z, A-Z)
 * - digits (0-9)
 * - spaces and common ASCII symbols
 * - You should design an algorithm with time complexity O(n), where n is the length of the string.
 * - Solutions with O(n^2) (e.g., checking all substrings with nested loops) will be too slow for the upper constraint.
 * - Extra space complexity around O(min(n, charset_size)) is expected (e.g., using a hash map / array to track last seen positions).
 */
public class Solution260323Two {
    public static void main(String[] args) {
        String s = "pwwkew";

        int answer = solution(s);
        System.out.println("answer = " + answer);
    }

    private static int solution(String s) {
        int left = 0;
        int max = Integer.MIN_VALUE;

        Set<Character> unique = new HashSet<>();
        for (int right = 0; right < s.length(); right++) {
            char cRight = s.charAt(right);

            while (!unique.add(cRight)) {
                char cLeft = s.charAt(left);
                unique.remove(cLeft);
                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}

