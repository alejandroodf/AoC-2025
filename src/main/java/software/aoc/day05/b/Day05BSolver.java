package software.aoc.day05.b;

import software.aoc.SafeSolver;
import software.aoc.day05.Database;
import software.aoc.day05.DatabaseReader;
import software.aoc.day05.IngredientRange;
import software.aoc.day05.StringDatabaseReader;

import java.util.Collections;
import java.util.List;

public class Day05BSolver implements SafeSolver {
    private final DatabaseReader reader;

    public Day05BSolver() {
        this.reader = new StringDatabaseReader();
    }

    @Override
    public long solve(String input) {
        Database db = reader.readDatabase(input);
        List<IngredientRange> ranges = db.freshRanges();
        if (ranges.isEmpty()) {
            return 0;
        }

        // Ordenamos los rangos por su extremo inferior (compareTo en IngredientRange)
        Collections.sort(ranges);

        long totalCoverage = 0;
        long currentMin = ranges.get(0).start();
        long currentMax = ranges.get(0).end();

        for (int i = 1; i < ranges.size(); i++) {
            IngredientRange next = ranges.get(i);
            if (next.start() <= currentMax + 1) {
                currentMax = Math.max(currentMax, next.end());
            } else {
                totalCoverage += (currentMax - currentMin + 1);
                currentMin = next.start();
                currentMax = next.end();
            }
        }
        totalCoverage += (currentMax - currentMin + 1);

        return totalCoverage;
    }
}
