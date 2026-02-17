package cwchoiit.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * [Today's Problem: Two Sum]
 * Description:
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 * <p>
 * Example 1:
 * Input: nums = [2, 7, 11, 15], target = 9
 * Output: [0, 1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * <p>
 * Example 2:
 * Input: nums = [3, 2, 4], target = 6
 * Output: [1, 2]
 * <p>
 * Constraints:
 * 2 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 */
public class Solution260217 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] answer = solutionGemini(nums, target);
        System.out.println("answer = " + Arrays.toString(answer));
    }

    private static int[] solution(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();

        // O(N)
        for (int i = 0; i < nums.length; i++) {
            numToIndex.put(nums[i], i);
        }

        // O(N)
        int[] answer = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int needNumber = target - nums[i];
            Integer number = numToIndex.getOrDefault(needNumber, null);
            if (number != null && i != number) {
                answer[0] = i;
                answer[1] = number;
                return answer;
            }
        }

        return answer;
    }

    private static int[] solutionGemini(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int needNumber = target - nums[i];

            if (numToIndex.containsKey(needNumber)) {
                return new int[]{numToIndex.get(needNumber), i};
            }

            numToIndex.put(nums[i], i);
        }

        return new int[]{};
    }
}
