package software.aoc.day05.a;

import software.aoc.SafeSolver;
import software.aoc.day05.*;

public class Day05ASolver implements SafeSolver {
    private final DatabaseReader reader;
    private final FreshnessValidator validator;

    public Day05ASolver() {
        this.reader = new StringDatabaseReader();
        this.validator = IntervalFreshnessValidator.getInstance();
    }

    @Override
    public long solve(String input) {
        Database db = reader.readDatabase(input);
        return db.availableIds().stream()
                .filter(id -> validator.isFresh(id, db.freshRanges()))
                .count();
    }
}
