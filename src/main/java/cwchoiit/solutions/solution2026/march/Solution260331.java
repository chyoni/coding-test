package cwchoiit.solutions.solution2026.march;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Longest Substring with At Most K Distinct Characters
 * You are given a string s consisting of uppercase and/or lowercase English letters, and an integer k.
 * <p>
 * Return the length of the longest substring of s that contains at most k distinct characters.
 * <p>
 * A substring is a contiguous sequence of characters within the string.
 * <p>
 * The substring can contain fewer than k distinct characters, but never more than k.
 * <p>
 * You should aim for an efficient solution.
 * <p>
 * Example 1
 * <p>
 * Input:
 * s = "eceba"
 * k = 2
 * <p>
 * Output:
 * 3
 * <p>
 * Explanation:
 * - The substring "ece" has 2 distinct characters {'e', 'c'} and length 3.
 * - "eceb" has 3 distinct characters ('e', 'c', 'b'), which is more than k = 2.
 * - "ceba" also has 3 distinct characters.
 * - Therefore the answer is 3.
 * <p>
 * Example 2
 * <p>
 * Input:
 * s = "aa"
 * k = 1
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * - The substring "aa" has only 1 distinct character 'a' and length 2, which is the longest valid substring.
 * <p>
 * Example 3
 * <p>
 * Input:
 * s = "aabbcc"
 * k = 2
 * <p>
 * Output:
 * 4
 * <p>
 * Explanation:
 * - Valid substrings with at most 2 distinct characters include "aabb", "abbc", "bbcc", each of length 4.
 * - There is no longer substring that contains at most 2 distinct characters.
 * <p>
 * Edge Cases
 * - If k == 0, no characters are allowed, so return 0.
 * - If k >= number of distinct characters in s, the whole string is valid, so return s.length().
 * <p>
 * Constraints
 * - 1 <= s.length <= 100,000
 * - 0 <= k <= 26 * 2 (since only uppercase and lowercase English letters are used)
 * - s consists of uppercase and/or lowercase English letters.
 *
 *
 */
public class Solution260331 {
    public static void main(String[] args) {
        // correct output = 3
        String s = "eceba";
        int k = 2;

        // correct output = 2;
        /*String s = "aa";
        int k = 1;*/

        // correct output = 4;
        /*String s = "aabbcc";
        int k = 2;*/

        int answer = solution(s, k);
        System.out.println("answer = " + answer);
    }

    private static int solution(String s, int k) {
        if (k == 0) {
            return 0;
        }

        int left = 0;
        int max = Integer.MIN_VALUE;

        Map<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);

            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}

