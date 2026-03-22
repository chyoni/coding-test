package cwchoiit.solutions.solution2026.march;

/**
 * Given a string 's' consisting of words and spaces, return the length of the word in the string.
 * A word is a maximal substring consisting of non-space characters only.
 * <p>
 * Example:
 * Input: s = "Hello World"
 * Output: 5
 */
public class Solution260322 {
    public static void main(String[] args) {
        String s = "Hello World";
        int answer = solution(s);

        System.out.println("answer = " + answer);
    }

    private static int solution(String s) {
        int left = 0;
        int right = s.length() - 1;

        int leftLength = 0;
        int rightLength = 0;

        int max = Integer.MIN_VALUE;
        while (left != right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);

            if (!Character.isSpaceChar(leftChar)) {
                leftLength++;
            } else {
                max = Math.max(max, leftLength);
                leftLength = 0;
            }
            max = Math.max(max, leftLength);

            if (!Character.isSpaceChar(rightChar)) {
                rightLength++;
            } else {
                max = Math.max(max, rightLength);
                rightLength = 0;
            }

            left++;
            right--;
            max = Math.max(max, rightLength);
        }

        return max;
    }
}
