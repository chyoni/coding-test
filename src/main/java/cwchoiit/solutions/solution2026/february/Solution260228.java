package cwchoiit.solutions.solution2026.february;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * <p>
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * <p>
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * Constraints:
 * 0 <= s.length <= 5 * 10^4
 * s consists of English letters, digits, symbols and spaces.
 */
public class Solution260228 {

    public static void main(String[] args) {
        String s = "abcabcbb";
        // String s = "bbbbb";
        // String s = "pwwkew";
        int answer = solution(s);

        System.out.println("answer = " + answer);
    }

    /**
     * 내가 푼 문제인데 논리적 오류가 발생할 수 있는 케이스가 있음
     * 문제는 left = charToIndex.get(c) + 1;과 charToIndex.remove(c); 부분입니다.
     * 예시: s = "abba"
     * right = 2일 때, 'b'가 중복됩니다. left는 2가 되고, Map에서 'b'를 지웁니다. (여기까진 OK)
     * right = 3일 때, 'a'가 중복됩니다.
     * 이때 Map에는 'a'가 인덱스 0으로 들어있죠.
     * left = charToIndex.get('a') + 1을 하면 left가 1이 됩니다.
     * 앗! left는 이미 2인데, 과거에 있던 'a' 때문에 다시 뒤(1)로 돌아가 버립니다. 윈도우가 다시 넓어지는 대참사가 발생하죠.
     */
    private static int solution(String s) {
        int answer = Integer.MIN_VALUE;
        int left = 0;
        char[] charArray = s.toCharArray();

        Map<Character, Integer> charToIndex = new HashMap<>();

        for (int right = 0; right < charArray.length; right++) {
            char c = charArray[right];

            // 이 4라인이 논리적 오류가 생길수도 있는 케이스
            if (charToIndex.containsKey(c)) {
                left = charToIndex.get(c) + 1;
                charToIndex.remove(c);
            }

            charToIndex.put(c, right);
            answer = Math.max(answer, right - left + 1);
        }

        return answer;
    }

    /**
     * 개선 포인트
     * 1. toCharArray()를 사용하면 배열을 만들면서 메모리를 문자열의 길이만큼 O(N) 사용. 그래서 charAt(...)을 사용하면 더 효율적
     * 2. 내가 푼 문제에서는 left가 다시 후진을 하는 논리적 오류가 발생할 수 있는 케이스가 있음 left가 다시 후진하면 윈도우가 다시 커지는 대참사
     * 따라서, Map에서 뽑아낸 인덱스가 현재 left 보다 크거나 같은 경우에만 left를 이동 Math.max를 사용하면 매우 간결해지긴 하는데, 직관적인건 내 코드가 좀 더 직관적인듯
     * 3. remove()는 연산이 큰 비용. 대신 이후에 또 찾은 중복 char의 인덱스 값을 덮어씌우면 됨
     * <p>
     * 슬라이딩 윈도우 습득 포인트
     * 점프형 슬라이딩 윈도우: 중복 발생 시 하나씩 미는 게 아니라, Map을 이용해 left를 한 번에 점프시킬 수 있다.
     * 후진 금지: 포인터를 점프시킬 때는 반드시 현재 위치보다 앞인지 확인해야 한다 (Math.max).
     */
    private static int solutionGemini(String s) {
        if (s.isEmpty()) return 0; // 빈 문자열 처리

        int answer = Integer.MIN_VALUE;
        int left = 0;
        Map<Character, Integer> charToIndex = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (charToIndex.containsKey(c)) {
                // 중복된 문자의 '다음 위치'와 '현재 left' 중 더 큰 값을 선택! (후진 방지)
                left = Math.max(left, charToIndex.get(c) + 1);
            }

            charToIndex.put(c, right); // 현재 문자의 최신 위치 업데이트
            answer = Math.max(answer, right - left + 1);
        }

        return answer;
    }
}
