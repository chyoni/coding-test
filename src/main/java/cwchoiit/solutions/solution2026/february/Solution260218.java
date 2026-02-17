package cwchoiit.solutions.solution2026.february;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * [Today's Problem: Contains Duplicate]
 * Description:
 * Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
 * <p>
 * Example 1:
 * Input: nums = [1, 2, 3, 1]
 * Output: true
 * Explanation: The element 1 occurs at indices 0 and 3.
 * <p>
 * Example 2:
 * Input: nums = [1, 2, 3, 4]
 * Output: false
 * <p>
 * Example 3:
 * Input: nums = [1, 1, 1, 3, 3, 4, 3, 2, 4, 2]
 * Output: true
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution260218 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};

        boolean answer = solutionGemini2(nums);
        System.out.println("answer = " + answer);
    }

    private static boolean solution(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    private static boolean solutionGemini(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (!set.add(num)) { // 넣으려는데 실패했다는 건 이미 있다는 뜻
                return true;
            }
        }
        return false;
    }

    /**
     * 만약 면접관이 "메모리 사용량을 줄여야 한다면 어떻게 할까요?"라고 묻는다면?
     * 아래와 같은 방식을 사용해서, Set과 같은 추가적인 자료구조를 만드는게 아니라,
     * 주어진 배열을 정렬한 뒤, 현재 인덱스와 바로 다음 인덱스값이 같은지를 비교해서 중복을 체크한다.
     * 시간 복잡도는 위 방식보다 더 느린 O(N log N)이 되지만, 공간 복잡도는 O(1)이 된다.
     * <p>
     * 다만, 정렬을 하면 원본 배열의 순서가 망가지므로, 만약 원본 순서를 유지해야 하는 상황이라면 정렬 전에 배열 복사를 해야한다.
     * 이 경우, 사실상 O(N)의 메모리를 다시 쓰게 된다는 점을 알고 있어야 한다. (아래 경우는 해당X)
     */
    private static boolean solutionGemini2(int[] nums) {
        Arrays.sort(nums); // O(N log N)

        for (int i = 0; i < nums.length - 1; i++) { // O(N)
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
