package cwchoiit.solutions.solution2026.february;

import java.util.Arrays;

/**
 * Description:Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number.
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 * The tests are generated such that there is exactly one solution.
 * You may not use the same element twice. Your solution must use only O(1) extra space.
 * <p>
 * Example 1:
 * Input: numbers = [2, 7, 11, 15],
 * target = 9
 * Output: [1, 2]
 * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 * <p>
 * Example 2:
 * Input: numbers = [2, 3, 4],
 * target = 6
 * Output: [1, 3]
 * <p>
 * Constraints:
 * 2 <= numbers.length <= 3 * 10^4
 * -1000 <= numbers[i] <= 1000
 * numbers is sorted in non-decreasing order.
 * -1000 <= target <= 1000
 */
public class Solution260222 {

    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;

        /*int[] numbers = {2, 3, 4};
        int target = 6;*/

        int[] answer = solutionGemini(numbers, target);
        System.out.println("answer = " + Arrays.toString(answer));
    }

    private static int[] solution(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int leftNumber = numbers[left];
            int rightNumber = numbers[right];

            if (leftNumber + rightNumber == target) {
                return new int[]{left + 1, right + 1};
            }

            if (leftNumber + rightNumber > target) {
                right--;
            }

            if (leftNumber + rightNumber < target) {
                left++;
            }
        }

        throw new IllegalArgumentException("There's no answer.");
    }

    private static int[] solutionGemini(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            // sum을 long 으로 선언한 이유는, 만약에 numbers[i]값이 int의 최댓값(약 21억)에 가까우면 이 둘을 합하면 오버플로우가 발생하여
            // 이상한 값이 나올수가 있음. 따라서, 제약조건에 따라 달라지겠지만, 아주 큰 숫자를 대비해 sum을 long으로 선언
            long sum = (long) numbers[left] + (long) numbers[right];

            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                return new int[]{left + 1, right + 1};
            }
        }
        throw new IllegalArgumentException("There's no answer");
    }
}
