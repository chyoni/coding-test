package cwchoiit.solutions.solution2026.february;

/**
 * Description:
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 * <p>
 * Example 1:
 * Input: target = 7, nums = [2, 3, 1, 2, 4, 3]
 * Output: 2
 * Explanation: The subarray [4, 3] has the minimal length under the problem constraint.
 * <p>
 * Example 2:
 * Input: target = 4, nums = [1, 4, 4]
 * Output: 1
 * <p>
 * Example 3:
 * Input: target = 11, nums = [1, 1, 1, 1, 1, 1, 1, 1]
 * Output: 0
 * <p>
 * Constraints:
 * 1 <= target <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 */
public class Solution260223 {

    public static void main(String[] args) {
        /*int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;*/
        int[] nums = {1, 1, 1, 1, 1, 1, 1, 1};
        int target = 11;

        int answer = solution(nums, target);
        System.out.println("answer = " + answer);
    }

    private static int solution(int[] nums, int target) {
        int answer = 0;
        int left = 0;
        int right = left + 1;
        int sum = nums[left];

        while (left < nums.length || right < nums.length) {
            if (sum >= target) {
                if (left == 0 && right == left + 1) {
                    answer = 1;
                } else {
                    answer = answer == 0 ?
                            right - left + 1 :
                            Math.min(answer, right - left + 1);
                }
                sum -= nums[left];
                left++;
            } else {
                if (right == 1) {
                    sum += nums[right];
                }
                right++;
                if (right == nums.length) {
                    break;
                }
                sum += nums[right];
            }
        }

        return answer;
    }

    private static int solutionGemini(int[] nums, int target) {
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE; // 최솟값을 구해야 하니 아주 큰 값으로 시작!

        // 1. 'right' 포인터를 한 칸씩 오른쪽으로 확장합니다.
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right]; // 창문을 넓히며 숫자를 더함

            // 2. 조건(sum >= target)을 만족하는 동안, 'left'를 최대한 당겨봅니다.
            while (sum >= target) {
                // 현재 창문의 길이(right - left + 1)와 기존 최솟값을 비교
                minLength = Math.min(minLength, right - left + 1);

                // 왼쪽을 한 칸 빼서 더 짧은 구간이 가능한지 확인
                sum -= nums[left];
                left++;
            }
        }

        // 만약 한 번도 갱신되지 않았다면(답이 없다면) 0, 아니면 최솟값 반환
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
