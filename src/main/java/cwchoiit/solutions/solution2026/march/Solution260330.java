package cwchoiit.solutions.solution2026.march;

import java.util.*;

/**
 * Problem: Earliest Time to Complete All Tasks
 * You are given n independent tasks, numbered from 0 to n - 1.
 * Each task i has:
 * - a duration duration[i] (a positive integer), and
 * - may depend on the completion of some other tasks (its prerequisites).
 * <p>
 * You are also given a list of dependency relations dependencies, where each element is a pair [a, b] meaning:
 * - Task b cannot start until task a is completed.
 * <p>
 * You have infinite parallel workers, so any number of tasks can be executed in parallel, as long as all their prerequisites are finished.
 * <p>
 * Your goal is to compute the earliest time at which all tasks can be completed.
 * <p>
 * Input
 * You are given:
 * - An integer n — the number of tasks.
 * - An integer array duration of length n, where duration[i] is the time needed to complete task i.
 * - A 2D integer array dependencies, where each element is [a, b] with 0 <= a, b < n, meaning task b depends on task a.
 * <p>
 * You can assume that:
 * - There is no cyclic dependency (the graph is a DAG).
 * - If a task has no prerequisite, it can start at time 0.
 * <p>
 * Output
 * Return a single integer — the minimum total time required to complete all tasks.
 * <p>
 * Constraints
 * - 1 <= n <= 10^5
 * - 1 <= duration[i] <= 10^4
 * - 0 <= number of dependencies <= 2 * 10^5
 * - Dependencies form a Directed Acyclic Graph (DAG).
 * - Time limit and input size require an O(n + m) solution, where m is the number of dependencies. (A solution based on sorting all nodes repeatedly, or doing DFS in a way that revisits edges many times, may be too slow.)
 * <p>
 * Explanation Hint (Not the full solution)
 * - Think of tasks and dependencies as a directed acyclic graph.
 * - You need the longest path cost from a “start” to each node, where edge direction is from prerequisite to dependent.
 * - Tasks with in-degree 0 (no prerequisites) can start immediately at time 0.
 * - Use a topological order and a DP/accumulation of “earliest start/finish time”.
 * <p>
 * Example 1
 * Input:
 * n = 3
 * duration = [3, 2, 5]
 * dependencies = [[0, 1], [1, 2]]
 * Explanation:
 * - Task 0 takes 3 units, can start at 0 → finishes at 3.
 * - Task 1 depends on 0, takes 2 units:
 * - earliest start = finish time of 0 = 3 → finishes at 5.
 * - Task 2 depends on 1, takes 5 units:
 * - earliest start = finish time of 1 = 5 → finishes at 10.
 * - All tasks are done at time 10.
 * Output: 10
 */
public class Solution260330 {

    public static void main(String[] args) {
        // answer = 10
        /*int n = 3;
        int[] duration = {3, 2, 5};
        int[][] dependencies = {
            {0, 1},
            {1, 2}
        };*/

        // answer = 10
        /*int n = 5;
        int[] duration = {1, 2, 3, 4, 5};
        int[][] dependencies = {
            {0, 2},
            {1, 2},
            {2, 3},
            {2, 4}
        };*/

        // answer = 102
        int n = 6;
        int[] duration = {3, 2, 5, 4, 100, 1};
        int[][] dependencies = {
                {0, 2},
                {1, 2},
                {2, 3},
                {3, 5},
                {1, 4}
        };

        int answer = solutionRovo(n, duration, dependencies);
        System.out.println("answer = " + answer);
    }

    private static int solution(int n, int[] duration, int[][] dependencies) {
        Map<Integer, List<Integer>> taskDepends = new HashMap<>();

        for (int i = 0; i < n; i++) {
            taskDepends.computeIfAbsent(i, k -> new ArrayList<>());
        }

        for (int[] dependency : dependencies) {
            int dependsOn = dependency[0];
            int target = dependency[1];
            taskDepends.computeIfAbsent(target, k -> new ArrayList<>()).add(dependsOn);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int answer = 0;
        while (!taskDepends.isEmpty()) {
            for (Integer i : taskDepends.keySet()) {
                List<Integer> deps = taskDepends.get(i);
                if (deps.isEmpty()) {
                    queue.offer(i);
                }
            }

            int fastDoneDuration = 0;
            while (!queue.isEmpty()) {
                Integer doneTask = queue.poll();
                taskDepends.remove(doneTask);
                fastDoneDuration = Math.max(fastDoneDuration, duration[doneTask]);
                for (List<Integer> value : taskDepends.values()) {
                    value.remove(doneTask);
                }
            }
            answer += fastDoneDuration;
        }

        return answer;
    }

    private static int solutionRovo(int n, int[] duration, int[][] dependencies) {
        // 1. 네가 쓰던 구조 최대한 유지하면서, 필요한 정보만 조금 더 추가할게.

        // 각 작업이 "어떤 선행 작업들"을 가지는지 (네가 쓰던 구조 그대로)
        Map<Integer, List<Integer>> prereq = new HashMap<>();
        // 각 작업이 "끝났을 때 영향을 주는 후행 작업들" (새로 추가: 효율적으로 갱신하려고)
        Map<Integer, List<Integer>> next = new HashMap<>();
        // 각 작업의 '선행 작업 개수' (진입 차수) - deps.size()를 매번 세지 않기 위해
        int[] indegree = new int[n];
        // 각 작업이 가장 빨리 끝날 수 있는 시간
        int[] earliestFinish = new int[n];
        // 각 작업이 가장 빨리 시작할 수 있는 시간 (= 선행 작업들 중 가장 늦게 끝나는 시간)
        int[] earliestStart = new int[n];

        for (int i = 0; i < n; i++) {
            prereq.put(i, new ArrayList<>()); // i가 시작되기 전에 필요한 작업들
            next.put(i, new ArrayList<>()); // i가 끝난 후 시작 가능한 작업들
        }

        // 2. dependencies를 네 스타일대로 먼저 prereq에 넣고,
        //    동시에 next, indegree도 채운다.
        for (int[] dependency : dependencies) {
            int dependsOn = dependency[0]; // 선행 작업
            int target = dependency[1]; // 후행 작업

            prereq.get(target).add(dependsOn); // target의 선행 목록에 추가
            next.get(dependsOn).add(target); // dependsOn이 끝나면 영향을 받는 애
            indegree[target]++; // target은 선행이 하나 더 있음
        }

        Queue<Integer> queue = new ArrayDeque<>();

        // 3. 선행 작업이 없는 애들(= indegree == 0)부터 시작
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                // 바로 시작 가능 → earliestStart = 0
                earliestStart[i] = 0;
                earliestFinish[i] = duration[i]; // 0 + duration
                queue.offer(i);
            }
        }

        int answer = 0;

        // 4. 위상 정렬 방식으로, 각 작업별 earliestFinish를 계산
        while (!queue.isEmpty()) {
            int doneTask = queue.poll();

            // 이 작업이 끝나는 시각으로 정답 후보 갱신
            answer = Math.max(answer, earliestFinish[doneTask]);

            // doneTask가 끝나면서, doneTask를 선행으로 가지던 후행 작업들을 갱신
            for (int nextTask : next.get(doneTask)) {
                // nextTask는 doneTask가 끝나야 시작 가능하니,
                // "시작 가능한 가장 이른 시각" 후보를 갱신
                earliestStart[nextTask] =
                        Math.max(earliestStart[nextTask], earliestFinish[doneTask]);

                // 선행 하나 처리했으니 indegree 감소
                indegree[nextTask]--;

                // 더 이상 기다릴 선행 작업이 없으면, 이때 시작 시간이 확정된다.
                if (indegree[nextTask] == 0) {
                    earliestFinish[nextTask] = earliestStart[nextTask] + duration[nextTask];
                    queue.offer(nextTask);
                }
            }
        }

        return answer;
    }
}

