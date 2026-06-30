package software.aoc.day06;

import java.util.List;

public record Worksheet(List<Problem> problems) {
    public long solveAll() {
        return problems.stream().mapToLong(Problem::solve).sum();
    }
}
