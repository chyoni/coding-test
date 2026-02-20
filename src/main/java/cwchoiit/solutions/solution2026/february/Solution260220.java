package cwchoiit.solutions.solution2026.february;

import java.util.*;

/**
 * Description:
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * <p>
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 * <p>
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 * <p>
 * Constraints:
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
public class Solution260220 {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        // String[] strs = {"a"};
        // String[] strs = {""};

        String[][] answer = solution(strs);
        System.out.println("answer = " + Arrays.deepToString(answer));
    }

    private static String[][] solution(String[] strs) {
        Map<String, List<String>> sortedStringToOriginString = new HashMap<>();
        for (String str : strs) {
            char[] strArray = str.toCharArray();
            Arrays.sort(strArray);
            String sortedString = Arrays.toString(strArray);

            /*List<String> origins = sortedStringToOriginString.getOrDefault(sortedString, new ArrayList<>());
            origins.add(str);
            sortedStringToOriginString.put(sortedString, origins);*/
            sortedStringToOriginString.computeIfAbsent(sortedString, k -> new ArrayList<>()).add(str);
        }

        return sortedStringToOriginString.values().stream()
                .map(list -> list.toArray(String[]::new))
                .toArray(String[][]::new);
    }
}
