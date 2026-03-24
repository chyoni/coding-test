package cwchoiit.solutions.solution2026.march;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem: Longest Consecutive Sequence
 * You are given an unsorted array of integers nums.
 * Your task is to find the length of the longest consecutive elements sequence.
 * <p>
 * A consecutive sequence is a set of numbers that can be arranged as x, x+1, x+2, ..., x+k with no gaps.
 * <p>
 * The numbers in the sequence do not need to be contiguous in the array, only consecutive in value.
 * <p>
 * You must solve it in O(n) average time.
 * <p>
 * Input
 * - An integer array nums
 * - 0 ≤ nums.length ≤ 10^5
 * - -10^9 ≤ nums[i] ≤ 10^9
 * <p>
 * Output
 * - Return an integer: the length of the longest consecutive sequence.
 * <p>
 * Example 1
 * Input: nums = [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive sequence is [1, 2, 3, 4], so the length is 4.
 * <p>
 * Example 2
 * Input: nums = [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]
 * Output: 9
 * Explanation: The longest consecutive sequence is [0, 1, 2, 3, 4, 5, 6, 7, 8], so the length is 9.
 * <p>
 * Example 3
 * Input: nums = []
 * Output: 0
 * Explanation: The array is empty, so the answer is 0.
 * <p>
 * Requirements
 * - Time complexity: O(n) on average.
 * - You are not allowed to sort the array as the main approach (sorting would be O(n log n)).
 * - Use a suitable data structure (e.g. a hash-based structure) to track numbers efficiently.
 */
public class Solution260324 {

    public static void main(String[] args) {
        // int[] nums = {100, 4, 200, 1, 3, 2};
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int answer = solution(nums);
        System.out.println("answer = " + answer);
    }

    private static int solution(int[] nums) {
        if (nums.length == 0) return 0;

        int max = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int num : set) { // set이 아닌 nums로 루프를 돌면, 0이 2번 나오면 0,1,2,... 를 두 번 체크 하게 되므로
            int sequence = 1;
            if (!set.contains(num - 1)) { // 이 조건이 빠지면, 2,3,4 등 모든 원소에서 루프를 도는데 사실 이건 0부터 도는게 가장 클테니 의미도 없고 시간 복잡도를 O(n^2)으로 만드는 행위
                while (set.contains(num + 1)) {
                    num = num + 1;
                    sequence++;
                }
            }
            max = Math.max(max, sequence);
        }

        return max;
    }
}

