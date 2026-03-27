package cwchoiit.solutions.solution2026.march;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given two strings s and t, consisting of English letters (both lowercase and uppercase are allowed).
 * Return the smallest window (substring) of s such that every character in t (including duplicates) is included in that window with at least the same frequency.
 * - If there is no such substring, return an empty string "".
 * - If there are multiple such substrings with the same minimum length, return any one of them.
 * You must write an efficient solution that would be acceptable in a real interview (time complexity near O(|s| + |t|) is expected).
 * <p>
 * Example 1
 * Input:
 * s = "ADOBECODEBANC"
 * t = "ABC"
 * Output: "BANC"
 * Explanation (in English):
 * - All characters of t (A, B, C) must appear in the window.
 * - One valid window is "ADOBEC", but its length is 6.
 * - The minimum-length window that contains A, B, and C is "BANC" with length 4.
 * <p>
 * Example 2
 * Input:
 * s = "a"
 * t = "aa"
 * Output: ""
 * Explanation (in English):
 * - String t requires two 'a' characters.
 * - String s has only one 'a', so no substring of s can contain all characters of t.
 * - Therefore, the answer is the empty string.
 * <p>
 * Example 3
 * Input:
 * s = "aa"
 * t = "aa"
 * Output: "aa"
 * Explanation (in English):
 * - t requires two 'a' characters.
 * - The whole string s = "aa" is the minimum window that satisfies the requirement.
 * <p>
 * Constraints
 * - 1 ≤ |s| ≤ 10^5
 * - 1 ≤ |t| ≤ 10^5
 * - |t| ≤ |s|
 * - s and t consist of English letters ('a'-'z', 'A'-'Z').
 * <p>
 * Requirements
 * - Implement the solution in Java.
 * - Aim for O(n) or O(n log n) time, where n = |s|.
 * - Memory usage should be reasonable for the constraints.
 */
public class Solution260327 {
    public static void main(String[] args) {
        // answer = BANC;
        String s = "ADOBECODEBANC";
        String t = "ABC";

        // answer = "";
        /*
        String s = "a";
        String t = "aa";
        */

        // answer = "aa";
        /*
        String s = "aa";
        String t = "aa";
        */

        String answer = solution(s, t);
        System.out.println("answer = " + answer);
    }

    private static String solution(String s, String t) {
        String answer = "";
        int left = 0;
        int minLength = Integer.MAX_VALUE;

        Map<Character, Integer> countPerChar = new HashMap<>();
        for (char c : t.toCharArray()) {
            countPerChar.put(c, countPerChar.getOrDefault(c, 0) + 1);
        }

        for (int right = 0; right < s.length(); right++) {
            char rChar = s.charAt(right);
            Integer charCount = countPerChar.getOrDefault(rChar, null);
            if (charCount != null) {
                countPerChar.put(rChar, countPerChar.get(rChar) - 1);
            }

            boolean ok = true;
            for (Integer value : countPerChar.values()) {
                if (value > 0) {
                    ok = false;
                    break;
                }
            }

            if (ok) {
                if (minLength > right - left + 1) {
                    answer = s.substring(left, right + 1);
                    minLength = right - left + 1;
                }
                while (ok) {
                    char lChar = s.charAt(left);
                    for (Integer value : countPerChar.values()) {
                        if (value > 0) {
                            ok = false;
                            break;
                        }
                    }

                    if (ok && minLength > right - left + 1) {
                        answer = s.substring(left, right + 1);
                        minLength = right - left + 1;
                    }

                    if (countPerChar.get(lChar) != null) {
                        countPerChar.put(lChar, countPerChar.get(lChar) + 1);
                    }

                    left++;
                }
            }
        }

        return answer;
    }

    private static String solutionRovo(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> countPerChar = new HashMap<>();
        for (char c : t.toCharArray()) {
            countPerChar.put(c, countPerChar.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int needed = t.length(); // 아직 더 필요한 문자 개수 총합
        int minLength = Integer.MAX_VALUE;
        int minStart = 0;

        for (int right = 0; right < s.length(); right++) {
            char rChar = s.charAt(right);

            if (countPerChar.containsKey(rChar)) {
                int cnt = countPerChar.get(rChar);

                // 아직 필요한 문자였다면, 필요 개수 하나 줄어듦
                if (cnt > 0) {
                    needed--;
                }

                countPerChar.put(rChar, cnt - 1);
            }

            // 모든 필요 문자를 다 채운 상태
            while (needed == 0) {
                // 최소 길이 갱신
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minStart = left;
                }

                char lChar = s.charAt(left);

                if (countPerChar.containsKey(lChar)) {
                    int cnt = countPerChar.get(lChar);

                    // 이 문자를 빼면 다시 부족해지는 상황이면 needed++
                    if (cnt >= 0) {
                        needed++;
                    }

                    countPerChar.put(lChar, cnt + 1);
                }

                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLength);
    }
}
