package cwchoiit.solutions.solution2026.february;

import java.util.Arrays;

/**
 * [Today's Problem: Valid Anagram]
 * Description:
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * <p>
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 * <p>
 * Constraints:
 * 1 <= s.length, t.length <= 5 * 10^4
 * s and t consist of lowercase English letters.
 */
public class Solution260219 {

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";

        boolean answer = solutionGemini2(s, t);
        System.out.println("answer = " + answer);
    }

    /**
     * 시간복잡도: O(N제곱)
     * 공간복잡도: O(N)
     */
    private static boolean solution(String s, String t) {
        for (char c : s.toCharArray()) {
            if (!t.contains(String.valueOf(c))) {
                return false;
            }
            int tIndex = t.indexOf(String.valueOf(c));
            String s1 = t.substring(0, tIndex);
            String s2 = t.substring(tIndex + 1);
            t = s1 + s2;
        }
        return true;
    }

    /**
     * 시간복잡도: O(N log N) - 정렬
     * 공간복잡도: O(N) - N의 길이의 문자열 2개를 char[]로 변경하는 과정에서 O(2N) = O(N)
     */
    private static boolean solutionGemini(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        Arrays.sort(sChars);
        Arrays.sort(tChars);

        String sortedS = Arrays.toString(sChars);
        String sortedT = Arrays.toString(tChars);

        return sortedS.equals(sortedT);
    }

    /**
     * 시간복잡도: O(N)
     * 공간복잡도: O(1) - 26개짜리의 상수로 이루어진 counter 배열
     */
    private static boolean solutionGemini2(String s, String t) {
        if (s.length() != t.length()) return false;

        char threshold = 'a';
        int[] counter = new int[26]; // 소문자 알파벳 a - z : 26개

        // 이 부분에서 'a'를 왜 빼냐? 'a' = 97을 가르키는데,
        // 만든 counter는 방이 26개고 0번부터 25번까지의 인덱스를 가진다.
        // s.charAt(i)가 만약 'b'이라면 98 인덱스에 1을 추가하려 할텐데, 98번은 없기 때문에
        // ArrayIndexOutOfBoundsException이 발생한다. 그래서 기준점인 'a' = 97을 빼서 0부터 25번까지 모든 알파벳이 들어갈 수 있게끔 맞춰주는 것
        // 'a' - 'a' = 97 - 97 = 0 (0번 인덱스)
        // 'b' - 'a' = 98 - 97 = 1 (1번 인덱스)
        // 'c' - 'a' = 99 - 97 = 2 (2번 인덱스)
        // ...
        // 'z' - 'a' = 122 - 97 = 25 (25번 인덱스)
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - threshold]++; // s에서 나오는 모든 알파벳은 1을 더하기
            counter[t.charAt(i) - threshold]--; // t에서 나오는 모든 알파벳은 1을 빼기
        }

        // 두 문자열 s, t가 재정렬했을때 완전 같은 문자로 변경될 수 있다면 counter에는 모든 인덱스가 값이 0이어야함
        for (int i : counter) {
            if (i != 0) return false;
        }

        return true;
    }
}
