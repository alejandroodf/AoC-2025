package software.aoc.day05;

import java.util.List;

public class IntervalFreshnessValidator implements FreshnessValidator {
    private static final IntervalFreshnessValidator INSTANCE = new IntervalFreshnessValidator();

    private IntervalFreshnessValidator() {}

    public static IntervalFreshnessValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isFresh(long id, List<IngredientRange> ranges) {
        for (IngredientRange range : ranges) {
            if (range.contains(id)) {
                return true;
            }
        }
        return false;
    }
}
