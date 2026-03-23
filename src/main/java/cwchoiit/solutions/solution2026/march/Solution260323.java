package cwchoiit.solutions.solution2026.march;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Problem: Find the "K-th" Smallest Element Description: Given an integer array nums and an integer
 * k, return the k-th smallest element in the array. Note that it is the k-th smallest element in
 * the sorted order, not the k-th distinct element.
 *
 * <p>Constraints: Try to solve it with a time complexity better than O(n \log n) if possible.
 *
 * <p>Example 1: * Input: nums = [3, 2, 3, 1, 2, 4, 5, 5, 6], k = 4 * Output: 3 * Explanation: The
 * sorted array is [1, 2, 2, 3, 3, 4, 5, 5, 6]. The 4th smallest element is 3.
 *
 * <p>Example 2: * Input: nums = [7, 10, 4, 3, 20, 15], k = 3 * Output: 7 * Explanation: The sorted
 * array is [3, 4, 7, 10, 15, 20]. The 3rd smallest element is 7.
 */
public class Solution260323 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;

        int answer = solution(nums, k);
        System.out.println("answer = " + answer);
    }

    private static int solution(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        return pq.isEmpty() ? -1 : pq.poll();
    }
}

