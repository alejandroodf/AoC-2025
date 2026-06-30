package software.aoc.day11.b;

import software.aoc.SafeSolver;
import software.aoc.day11.*;

import java.util.List;
import java.util.Map;

public class Day11BSolver implements SafeSolver {
    private final GraphReader reader;
    private final PathCounter counter;

    public Day11BSolver() {
        this.reader = new StringGraphReader();
        this.counter = new DfsPathCounter();
    }

    @Override
    public long solve(String input) {
        Map<String, List<String>> graph = reader.readGraph(input);

        // Caso 1: svr -> fft -> dac -> out
        long svrToFft = counter.countPaths(graph, "svr", "fft");
        long fftToDac = counter.countPaths(graph, "fft", "dac");
        long dacToOut = counter.countPaths(graph, "dac", "out");
        long case1 = svrToFft * fftToDac * dacToOut;

        // Caso 2: svr -> dac -> fft -> out
        long svrToDac = counter.countPaths(graph, "svr", "dac");
        long dacToFft = counter.countPaths(graph, "dac", "fft");
        long fftToOut = counter.countPaths(graph, "fft", "out");
        long case2 = svrToDac * dacToFft * fftToOut;

        return case1 + case2;
    }
}
