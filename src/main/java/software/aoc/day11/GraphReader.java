package software.aoc.day11;

import java.util.List;
import java.util.Map;

public interface GraphReader {
    Map<String, List<String>> readGraph(String input);
}
