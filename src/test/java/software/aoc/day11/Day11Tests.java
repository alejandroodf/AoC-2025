package software.aoc.day11;

import org.junit.Test;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

public class Day11Tests {
    private final String EXAMPLE =
            "aaa: you hhh\n" +
            "you: bbb ccc\n" +
            "bbb: ddd eee\n" +
            "ccc: ddd eee fff\n" +
            "ddd: ggg\n" +
            "eee: out\n" +
            "fff: out\n" +
            "ggg: out\n" +
            "hhh: ccc fff iii\n" +
            "iii: out";

    @Test
    public void testParser() {
        Map<String, List<String>> graph = new StringGraphReader().readGraph(EXAMPLE);
        assertEquals(10, graph.size());
        assertEquals(List.of("bbb", "ccc"), graph.get("you"));
        assertEquals(List.of("ddd", "eee", "fff"), graph.get("ccc"));
    }

    @Test
    public void testPathCounterExample() {
        Map<String, List<String>> graph = new StringGraphReader().readGraph(EXAMPLE);
        PathCounter counter = new DfsPathCounter();
        long result = counter.countPaths(graph, "you", "out");
        assertEquals(5L, result);
    }
}
