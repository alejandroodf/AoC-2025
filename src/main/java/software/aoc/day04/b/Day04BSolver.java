package software.aoc.day04.b;

import software.aoc.SafeSolver;
import software.aoc.day04.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day04BSolver implements SafeSolver {
    private final PaperGridReader reader;
    private final AccessibilityValidator validator;

    public Day04BSolver() {
        this.reader = new StringPaperGridReader();
        this.validator = AdjacentCountAccessibilityValidator.getInstance();
    }

    @Override
    public long solve(String input) {
        PaperGrid grid = reader.readGrid(input);
        int rows = grid.getRows();
        int cols = grid.getCols();

        long totalRemoved = 0;
        while (true) {
            List<Coordinate> toRemove = IntStream.range(0, rows)
                    .boxed()
                    .flatMap(r -> IntStream.range(0, cols)
                            .filter(c -> grid.isPaper(r, c) && validator.isAccessible(grid, r, c))
                            .mapToObj(c -> new Coordinate(r, c)))
                    .collect(Collectors.toList());

            if (toRemove.isEmpty()) {
                break;
            }

            toRemove.forEach(coord -> grid.removePaper(coord.row(), coord.col()));
            totalRemoved += toRemove.size();
        }

        return totalRemoved;
    }
}
