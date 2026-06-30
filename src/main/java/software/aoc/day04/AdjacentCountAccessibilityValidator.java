package software.aoc.day04;

public class AdjacentCountAccessibilityValidator implements AccessibilityValidator {
    private static final AdjacentCountAccessibilityValidator INSTANCE = new AdjacentCountAccessibilityValidator();
    private static final int MAX_NEIGHBORS_LIMIT = 4;

    private AdjacentCountAccessibilityValidator() {}

    public static AdjacentCountAccessibilityValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isAccessible(PaperGrid grid, int r, int c) {
        return grid.countPaperNeighbors(r, c) < MAX_NEIGHBORS_LIMIT;
    }
}
