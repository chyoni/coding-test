package cwchoiit.solutions.solution2026.april;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Problem: Minimum Cost to Connect All Cities
 * You are given n cities labeled from 1 to n, and a list of possible bidirectional roads that can be built between them.
 * Each road is represented as roads[i] = [u, v, w], meaning building a road between city u and city v costs w.
 * <p>
 * You want to ensure that every city is reachable from every other city (i.e., the graph is connected) with minimum total cost.
 * You may choose any subset of the given roads, or determine that it's impossible to connect all cities.
 * <p>
 * Return the minimum total cost to connect all n cities.
 * If it's impossible to connect all cities using the given roads, return -1.
 * <p>
 * This is a classic optimization/graph problem suitable for a first-round interview.
 * <p>
 * Constraints
 * - 1 ≤ n ≤ 100,000
 * - 1 ≤ m ≤ 200,000
 * - 1 ≤ w ≤ 1,000,000
 * - The graph defined by roads may be disconnected.
 * - The result can be up to about 10^11, but it will fit within a 32-bit signed integer if the test data is controlled accordingly (you can use long internally to accumulate sums, then cast back to int at the end).
 * <p>
 * Time Complexity Requirement:
 * - You should aim for O(m log m) or O(m α(n)) where α is the inverse Ackermann function (essentially constant).
 * - Solutions significantly worse than O(m log m), such as O(n^2) on dense graphs, may time out on large cases.
 * <p>
 * Output
 * Return an int:
 * - The minimum total cost to connect all cities, or
 * - -1 if it's impossible.
 * <p>
 * Example 1
 * Input:
 * n = 4
 * roads = [
 * [1, 2, 3],
 * [2, 3, 4],
 * [3, 4, 5],
 * [1, 4, 10]
 * ]
 * Output:
 * 12
 * Explanation (in English):
 * - One optimal way to connect all 4 cities is:
 * - Build road (1, 2) with cost 3
 * - Build road (2, 3) with cost 4
 * - Build road (3, 4) with cost 5
 * - Total cost = 3 + 4 + 5 = 12
 * <p>
 * Another option is to use road (1, 4) with cost 10 and (2, 3) with cost 4 and one of (1,2) or (3,4), but those will be more expensive than 12.
 * So the minimum possible total cost is 12.
 * <p>
 * Example 2
 * Input:
 * n = 3
 * roads = [
 * [1, 2, 5]
 * ]
 * Output:
 * -1
 * Explanation (in English):
 * - We only have one possible road (1, 2) with cost 5.
 * - City 3 is completely isolated and cannot be connected with the given roads.
 * - Since it's impossible to connect all cities, we return -1.
 * <p>
 * Example 3
 * Input:
 * n = 5
 * roads = [
 * [1, 2, 2],
 * [1, 3, 3],
 * [2, 3, 1],
 * [2, 4, 4],
 * [3, 5, 6],
 * [4, 5, 5]
 * ]
 * Output:
 * 12
 * Explanation (in English):
 * - One minimum-cost way to connect all 5 cities:
 * - (2, 3) with cost 1
 * - (1, 2) with cost 2
 * - (2, 4) with cost 4
 * - (4, 5) with cost 5
 * Total = 1 + 2 + 4 + 5 = 12
 * All cities {1,2,3,4,5} are connected, and no cheaper way exists.
 */
public class Solution260402 {
    public static void main(String[] args) {
        int n = 5;
        int[][] roads = {
                {1, 2, 2},
                {1, 3, 3},
                {2, 3, 1},
                {2, 4, 4},
                {3, 5, 6},
                {4, 5, 5}
        };

        int answer = solution(n, roads);
        System.out.println("answer = " + answer);
    }

    private static int solution(int n, int[][] roads) {
        // [[[비용,인접도시], []], ...]
        List<List<int[]>> linked = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            linked.add(i, new ArrayList<>());
        }

        for (int[] road : roads) {
            int city = road[0];
            int linkedCity = road[1];
            int cost = road[2];

            linked.get(city).add(new int[]{cost, linkedCity});
            linked.get(linkedCity).add(new int[]{cost, city});
        }

        boolean[] visited = new boolean[n + 1];
        int visitedCount = 0;
        int minCost = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 1});

        while (!pq.isEmpty() && visitedCount < n) {
            int[] data = pq.poll();
            if (visited[data[1]]) {
                continue;
            }
            visited[data[1]] = true;
            visitedCount++;
            minCost += data[0];

            for (int[] next : linked.get(data[1])) {
                int nextCost = next[0];
                int nextCity = next[1];
                if (!visited[nextCity]) {
                    pq.offer(new int[]{nextCost, nextCity});
                }
            }
        }

        return visitedCount == n ? minCost : -1;
    }
}

