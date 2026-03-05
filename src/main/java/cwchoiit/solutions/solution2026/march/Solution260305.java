package cwchoiit.solutions.solution2026.march;

/**
 * Description:
 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two endpoints of the i-th line are (i, 0) and (i, height[i]).
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 * Notice that you may not slant the container.
 * <p>
 * Example 1:
 * Input: height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1, 8, 6, 2, 5, 4, 8, 3, 7]. In this case,
 * the max area of water the container can contain is between index 1 (height 8) and index 8 (height 7).
 * The width is 8 - 1 = 7, and the height is min(8, 7) = 7.
 * Area = 7 x 7 = 49.
 * <p>
 * Example 2:
 * Input: height = [1, 1]
 * Output: 1
 * <p>
 * Constraints:
 * n == height.length
 * 2 <= n <= 10^5
 * 0 <= height[i] <= 10^4
 */
public class Solution260305 {

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        int answer = solution(height);
        System.out.println("answer = " + answer);
    }

    private static int solution(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = Integer.MIN_VALUE;

        while (left != right) {
            int width = right - left;
            int availableHeight = Math.min(height[right], height[left]);
            maxArea = Math.max(maxArea, width * availableHeight);

            if (height[right] > height[left]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
