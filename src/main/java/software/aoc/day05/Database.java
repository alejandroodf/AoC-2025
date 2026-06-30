package software.aoc.day05;

import java.util.List;

public record Database(List<IngredientRange> freshRanges, List<Long> availableIds) {}
