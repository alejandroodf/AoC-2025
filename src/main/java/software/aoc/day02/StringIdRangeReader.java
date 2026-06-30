package software.aoc.day02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringIdRangeReader implements IdRangeReader {
    @Override
    public List<IdRange> readRanges(String input) {
        if (input == null || input.isBlank()) {
            return List.of();
        }
        return Arrays.stream(input.trim().split(","))
            .filter(token -> !token.isBlank())
            .map(token -> {
                String[] parts = token.split("-");
                long start = Long.parseLong(parts[0]);
                long end = Long.parseLong(parts[1]);
                return new IdRange(start, end);
            })
            .collect(Collectors.toList());
    }
}
