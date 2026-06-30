package software.aoc.day05;

import java.util.List;

public interface FreshnessValidator {
    boolean isFresh(long id, List<IngredientRange> ranges);
}
