package cwchoiit.solutions.solution2026.march;

/**
 * [이진 탐색]
 * Description: Koko loves to eat bananas. There are n piles of bananas, the i-th pile has piles[i] bananas.
 * The guards have gone and will come back in h hours.Koko can decide her bananas-per-hour eating speed of k.
 * Each hour, she chooses some pile of bananas and eats k bananas from that pile.
 * If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 * <p>
 * Example 1:
 * Input: piles = [3,6,7,11],
 * h = 8
 * Output: 4
 * Explanation: At speed k=4, she can finish in 1+2+2+3 = 8 hours.
 * (3개짜리는 1시간, 6개는 2시간, 7개는 2시간, 11개는 3시간)
 * <p>
 * Example 2:
 * Input: piles = [30,11,23,4,20],
 * h = 5
 * Output: 30
 * <p>
 * Constraints:1 <= piles.length <= 10^4
 * piles.length <= h <= 10^9
 * 1 <= piles[i] <= 10^9
 */
public class Solution260315 {
    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int h = 8;

        long answer = solution(piles, h);
        System.out.println("answer = " + answer);
    }

    private static long solution(int[] piles, int h) {
        long low = 1;
        long high = getMaxElement(piles);

        long minEatPerHour = Long.MAX_VALUE;
        while (low <= high) {
            long mid = low + (high - low) / 2;

            // mid만큼 각 더미의 바나나를 처먹는데 걸린 총 시간 (단위: hour)
            long totalEatHour = getTotalEatHour(piles, mid);

            if (totalEatHour > h) { // 경비병이 왔음에도 다 못먹는 경우
                low = mid + 1;
            } else if (totalEatHour < h) { // 경비병이 오기전에 다 먹는 경우
                minEatPerHour = Math.min(minEatPerHour, mid);
                high = mid - 1;
            } else { // 경비병이 돌아오는 시간이랑 같은 시간 동안 다 먹을 수 있는 경우
                minEatPerHour = Math.min(minEatPerHour, mid);
                high = mid - 1;
            }
        }
        return minEatPerHour;
    }

    private static long getTotalEatHour(int[] piles, long mid) {
        long totalHour = 0;
        for (int pile : piles) {
            long estimateHourByPile = (pile + mid - 1) / mid;
            totalHour = totalHour + estimateHourByPile;
        }
        return totalHour;
    }

    private static int getMaxElement(int[] piles) {
        int max = Integer.MIN_VALUE;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }
        return max;
    }
}
