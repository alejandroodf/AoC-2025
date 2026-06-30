package software.aoc.day04.a;

import software.aoc.SafeSolver;
import software.aoc.day04.*;

import java.util.stream.IntStream;

public class Day04ASolver implements SafeSolver {
    private final PaperGridReader reader;
    private final AccessibilityValidator validator;

    public Day04ASolver() {
        this.reader = new StringPaperGridReader();
        this.validator = AdjacentCountAccessibilityValidator.getInstance();
    }

    @Override
    public long solve(String input) {
        PaperGrid grid = reader.readGrid(input);
        int rows = grid.getRows();
        int cols = grid.getCols();

        return IntStream.range(0, rows)
                .mapToLong(r -> IntStream.range(0, cols)
                        .filter(c -> grid.isPaper(r, c) && validator.isAccessible(grid, r, c))
                        .count())
                .sum();
    }
}
