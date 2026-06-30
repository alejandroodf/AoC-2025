package software.aoc.day11.a;

import software.aoc.SafeSolver;
import software.aoc.day11.*;

import java.util.List;
import java.util.Map;

public class Day11ASolver implements SafeSolver {
    private final GraphReader reader;
    private final PathCounter counter;

    public Day11ASolver() {
        this.reader = new StringGraphReader();
        this.counter = new DfsPathCounter();
    }

    @Override
    public long solve(String input) {
        Map<String, List<String>> graph = reader.readGraph(input);
        return counter.countPaths(graph, "you", "out");
    }
}
