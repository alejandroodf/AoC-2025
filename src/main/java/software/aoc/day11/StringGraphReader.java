package software.aoc.day11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Scanner;

public class StringGraphReader implements GraphReader {
    @Override
    public Map<String, List<String>> readGraph(String input) {
        Map<String, List<String>> graph = new HashMap<>();
        if (input == null || input.isBlank()) {
            return graph;
        }

        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String trimmed = scanner.nextLine().trim();
                if (trimmed.isEmpty()) continue;

                int colonIndex = trimmed.indexOf(':');
                if (colonIndex != -1) {
                    String src = trimmed.substring(0, colonIndex).trim();
                    List<String> dests = new ArrayList<>();
                    
                    String remainder = trimmed.substring(colonIndex + 1).trim();
                    if (!remainder.isEmpty()) {
                        try (Scanner tokenScanner = new Scanner(remainder)) {
                            while (tokenScanner.hasNext()) {
                                dests.add(tokenScanner.next());
                            }
                        }
                    }
                    graph.put(src, dests);
                }
            }
        }
        return graph;
    }
}
