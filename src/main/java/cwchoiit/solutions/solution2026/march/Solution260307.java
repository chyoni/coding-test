package cwchoiit.solutions.solution2026.march;

import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in $O(n)$ time.
 * <p>
 * Example 1:
 * Input: nums = [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
 * Therefore, its length is 4.
 * <p>
 * Example 2:
 * Input: nums = [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]
 * Output: 9
 * <p>
 * Constraints:
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution260307 {
    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        int answer = solution(nums);
        System.out.println("answer = " + answer);
    }

    /**
     * 이게 O(N)인 이유. for 문 안에 while이 있어서 O(N^2) 아닌가 하는 생각이 들 수도 있지만,
     * 현재 key의 -1을 한 값이 없을 때만 while문을 만나게 된다.
     * 결과적으로 배열 안의 모든 숫자는 while문에 의해 딱 한 번씩만 방문된다.
     */
    private static int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int answer = Integer.MIN_VALUE;
        for (Integer key : set) {
            if (!set.contains(key - 1)) {
                int temp = 1;
                int current = key;
                while (set.contains(current + 1)) {
                    current = current + 1;
                    temp++;
                }
                answer = Math.max(answer, temp);
            }
        }

        return answer == Integer.MIN_VALUE ? 0 : answer;
    }
}
