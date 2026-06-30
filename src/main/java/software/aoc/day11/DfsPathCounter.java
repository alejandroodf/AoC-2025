package software.aoc.day11;

import java.util.*;

public class DfsPathCounter implements PathCounter {
    private Map<String, List<String>> graph;
    private Map<String, Long> memo;
    private Set<String> visiting;

    @Override
    public long countPaths(Map<String, List<String>> graph, String start, String end) {
        this.graph = graph;
        this.memo = new HashMap<>();
        this.visiting = new HashSet<>();
        return dfs(start, end);
    }

    private long dfs(String curr, String end) {
        if (curr.equals(end)) {
            return 1;
        }
        if (memo.containsKey(curr)) {
            return memo.get(curr);
        }
        if (visiting.contains(curr)) {
            return 0; // Evitar ciclos de vuelta
        }

        visiting.add(curr);
        long count = 0;
        List<String> neighbors = graph.getOrDefault(curr, Collections.emptyList());
        for (String next : neighbors) {
            count += dfs(next, end);
        }
        visiting.remove(curr);

        memo.put(curr, count);
        return count;
    }
}
