package cwchoiit.solutions.solution2026.march;

import java.util.*;

/**
 * Description:
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * <p>
 * Example 1:
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0, 6]
 * Explanation: - The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * <p>
 * Example 2:
 * Input: s = "abab", p = "ab"
 * Output: [0, 1, 2]
 * <p>
 * Constraints:
 * 1 <= s.length, p.length <= 3 * 10^4
 * s and p consist of lowercase English letters.
 */
public class Solution260309 {

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        // String s = "abab";
        // String p = "ab";

        int[] answer = solution2(s, p);
        System.out.println("answer = " + Arrays.toString(answer));
    }

    private static int[] solution(String s, String p) {
        int pLen = p.length();
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < p.length(); i++) {
            set.add(p.charAt(i));
        }

        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            Set<Character> copySet = new HashSet<>(set);
            copySet.remove(s.charAt(i));

            if (i + pLen <= s.length()) {
                for (int k = i+1; k < pLen + i; k++) {
                    copySet.remove(s.charAt(k));
                }
            }

            if (copySet.isEmpty()) {
                answer.add(i);
            }
        }

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static int[] solution2(String s, String p) {
        int[] pArr = new int[26];
        int[] answer = new int[26];

        for (int i = 0; i < p.length(); i++) {
            pArr[p.charAt(i) - 'a']++;
        }

        int left = 0;
        int count = 0;

        List<Integer> answerList = new ArrayList<>();
        for (int right = 0; right < s.length(); right++) {
            answer[s.charAt(right) - 'a']++;
            count++;

            if (count == p.length()) {
                if (Arrays.equals(pArr, answer)) {
                    answerList.add(left);
                }
                answer[s.charAt(left) - 'a']--;
                left++;
                count--;
            }
        }

        return answerList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
