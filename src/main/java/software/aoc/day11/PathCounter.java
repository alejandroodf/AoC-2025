package software.aoc.day11;

import java.util.List;
import java.util.Map;

public interface PathCounter {
    long countPaths(Map<String, List<String>> graph, String start, String end);
}
