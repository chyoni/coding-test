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

        String[][] answer = solution2(strs);
        System.out.println("answer = " + Arrays.deepToString(answer));
    }

    /**
     * 가독성을 요구한다면 아래 코드가 좀 더 적합. 다만 시간복잡도가 불리
     * 모든 단어를 순회하는 작업이 = O(N)
     * 단어를 정렬하는 작업이 = O(L log L) / L은 한 단어의 길이
     * 따라서 O(N * L log L) 만큼의 시간복잡도
     */
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

    /**
     * 성능 측면은 아래 코드가 압도적으로 유리. 특히 단어가 길어지면 길어질수록 더 심함
     *  모든 단어를 순회하는 작업이 = O(N)
     *  각 단어를 하나의 Char로 순회하는 작업이 O(L) / L은 단어의 길이
     *  카운트(26개)를 순회 O(26) = 상수값
     *  따라서, 시간 복잡도는 O(N * L)
     */
    private static String[][] solution2(String[] strs) {
        Map<String, List<String>> sortedStringToOriginString = new HashMap<>();
        for (String str : strs) {
            int[] counter = new int[26]; // 소문자 알파벳은 26개

            char[] charArray = str.toCharArray();
            for (char c : charArray) {
                counter[c - 'a']++;
            }

            StringBuilder builder = new StringBuilder();
            for (int i : counter) {
                builder.append("#").append(i);
            }

            sortedStringToOriginString.computeIfAbsent(builder.toString(), k -> new ArrayList<>()).add(str);
        }

        return sortedStringToOriginString.values().stream()
                .map(list -> list.toArray(String[]::new))
                .toArray(String[][]::new);
    }
}
