package cwchoiit.solutions.solution2026.march;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Problem: Minimum Refill Stops
 * You are driving a car from position 0 to position target on a number line.
 * You start with startFuel liters of fuel in your tank.
 * <p>
 * Your car has a fuel efficiency of 1 unit of distance per 1 liter of fuel.
 * <p>
 * There are gas stations along the way.
 * Each station is represented as stations[i] = [position_i, fuel_i]:
 * <p>
 * - position_i is the distance of the station from the start (0).
 * - fuel_i is how many liters of fuel you can get by stopping at that station.
 * - If you stop at a station, you refuel by fuel_i liters instantly.
 * - You can visit stations in any order as long as their position is reachable based on your current fuel.
 * <p>
 * Return the minimum number of refueling stops needed to reach exactly or beyond target.
 * If it is impossible to reach target, return -1.
 * <p>
 * Constraints
 * - 1 <= target <= 10^9
 * - 0 <= startFuel <= 10^9
 * - 0 <= stations.length <= 10^4
 * - stations[i].length == 2
 * - 0 < position_i < target
 * - 0 < fuel_i <= 10^9
 * - All position_i are strictly increasing.
 * <p>
 * Time Complexity requirement:
 * Your solution should be better than O(n²).
 * A solution around O(n log n) is expected, where n = stations.length.
 * <p>
 * Example 1
 * Input:
 * target = 100
 * startFuel = 10
 * stations = [[10,60],[20,30],[30,30],[60,40]]
 * Output: 2
 * Explanation:
 * - Start with 10 liters, can reach position 10.
 * - At position 10, refuel 60 (total fuel becomes 60).
 * - Now you can reach up to position 70.
 * - On the way, at position 30, you may skip or take; but the optimal is:
 * - At position 10: take 60 (fuel = 60, distance covered = 10).
 * - Drive to position 60 (cost 50 fuel, remaining = 10).
 * - At position 60: refuel 40 (fuel = 50).
 * - From position 60 with 50 fuel, you can reach position 110 ≥ target 100.
 * - You stopped at 2 stations (positions 10 and 60), so the answer is 2.
 * <p>
 * Example 2
 * Input:
 * target = 100
 * startFuel = 1
 * stations = [[10,100]]
 * Output: -1
 * Explanation:
 * - You start with 1 liter of fuel, so you can only reach position 1.
 * - The first station is at position 10, which is unreachable.
 * - So you can never reach the target 100; return -1.
 * <p>
 * Example 3
 * Input:
 * target = 50
 * startFuel = 50
 * stations = []
 * Output: 0
 * Explanation:
 * - You already have enough fuel to reach the target without stopping.
 * - No refuel is needed.
 */
public class Solution260326 {
    public static void main(String[] args) {
        /*int target = 50;
        int startFuel = 50;
        int[][] stations = {};*/

        /*int target = 100;
        int startFuel = 10;
        int[][] stations = {
            {10, 60},
            {20, 30},
            {30, 30},
            {60, 40}
        };*/

        /*int target = 100;
        int startFuel = 1;
        int[][] stations = {{10, 100}};*/

        int target = 100;
        int startFuel = 50;
        int[][] stations = {
                {25, 10},
                {50, 10}
        };

        int answer = solution(target, startFuel, stations);
        System.out.println("answer = " + answer);
    }

    /**
     * 이 문제 풀이의 핵심은, 기름이 남아있는 한 계속해서 전진하고 스테이션에 들린 기름을 우선순위 큐에 넣어서,
     * 가다가 기름이 부족해지는 순간에 지나친 스테이션에서 채운 기름을 그때 가서 가장 많은 기름을 우선순위 큐에서 뽑아 채워넣는 접근
     */
    private static int solution(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int refuels = 0;
        int currentFuel = startFuel;
        int prevPos = 0;

        for (int[] station : stations) {
            int pos = station[0];
            int fuel = station[1];

            int dist = pos - prevPos;
            currentFuel = currentFuel - dist;

            while (currentFuel < 0 && !pq.isEmpty()) {
                currentFuel += pq.poll();
                refuels++;
            }

            if (currentFuel < 0) {
                return -1;
            }

            pq.offer(fuel);
            prevPos = pos;
        }

        int distToTarget = target - prevPos;
        currentFuel -= distToTarget;

        while (currentFuel < 0 && !pq.isEmpty()) {
            currentFuel += pq.poll();
            refuels++;
        }

        if (currentFuel < 0) {
            return -1;
        }

        return refuels;
    }
}
