package cwchoiit.solutions.solution2026.april;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You are given an array of meeting time intervals, where each interval is represented as an array of two integers [start, end] (0 ≤ start < end).
 * Each interval represents a meeting scheduled during a single day using a 24-hour clock.
 * <p>
 * Your task:
 * Determine the minimum number of conference rooms required so that all meetings can be held without overlap in the same room.
 * <p>
 * Note:
 * - If a meeting ends at time t and another meeting starts at time t, they do not overlap and can use the same room.
 * - The minimum number of conference rooms required is equal to the maximum number of meetings that are happening at the same time at any point during the day.
 * <p>
 * Example
 * Input:
 * intervals = [[1, 5], [2, 6], [8, 12]]
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * - From time 2 to 5, two meetings are happening simultaneously.
 * - At all other times, at most one meeting is happening.
 * - Therefore, the minimum number of conference rooms required is 2.
 */
public class Solution260401 {
    public static void main(String[] args) {
        // 2
        /*int[][] intervals = {
            {0, 30},
            {5, 10},
            {15, 20}
        };*/

        // 3
        int[][] intervals = {
                {1, 5},
                {2, 7},
                {3, 10},
                {8, 20}
        };

        int answer = solution(intervals);
        System.out.println("answer = " + answer);
    }

    private static int solution(int[][] intervals) {
        // 시작시간을 기준으로 오름차순 정렬
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int minNeedRoom = 0;
        for (int[] interval : intervals) {
            int startMeeting = interval[0];
            int endMeeting = interval[1];

            while (!pq.isEmpty() && pq.peek() <= startMeeting) {
                pq.poll();
            }
            pq.offer(endMeeting);
            minNeedRoom = Math.max(minNeedRoom, pq.size());
        }

        return minNeedRoom;
    }
}

