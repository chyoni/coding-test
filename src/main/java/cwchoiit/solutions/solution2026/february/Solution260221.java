package cwchoiit.solutions.solution2026.february;

/**
 * Description:
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * <p>
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * <p>
 * Example 2:
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * <p>
 * Constraints:
 * 1 <= s.length <= 2 * 10^5
 * s consists only of printable ASCII characters.
 */
public class Solution260221 {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        // String s = "race a car";

        boolean answer = solutionGemini(s);
        System.out.println("answer = " + answer);
    }

    private static boolean solution(String s) {
        char[] charArray = s.toLowerCase().toCharArray();

        StringBuilder forwardBuilder = new StringBuilder();
        StringBuilder backwardBuilder = new StringBuilder();

        for (int i = 0; i < charArray.length; i++) {
            char forward = charArray[i];
            char backward = charArray[charArray.length - 1 - i];

            if (Character.isLetterOrDigit(forward)) {
                forwardBuilder.append(forward);
            }

            if (Character.isLetterOrDigit(backward)) {
                backwardBuilder.append(backward);
            }
        }

        return forwardBuilder.toString().contentEquals(backwardBuilder);
    }

    private static boolean solutionGemini(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);

            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
            } else if (!Character.isLetterOrDigit(rightChar)) {
                right--;
            } else {
                if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}
