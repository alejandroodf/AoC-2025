package software.aoc.day04;

import org.junit.Test;
import static org.junit.Assert.*;

public class Day04Tests {

    private final String EXAMPLE = "..@@.@@@@.\n" +
            "@@@.@.@.@@\n" +
            "@@@@@.@.@@\n" +
            "@.@@@@..@.\n" +
            "@@.@@@@.@@\n" +
            ".@@@@@@@.@\n" +
            ".@.@.@.@@@\n" +
            "@.@@@.@@@@\n" +
            ".@@@@@@@@.\n" +
            "@.@.@@@.@.";

    @Test
    public void testParserAndNeighbors() {
        PaperGridReader reader = new StringPaperGridReader();
        PaperGrid grid = reader.readGrid(EXAMPLE);
        assertEquals(10, grid.getRows());
        assertEquals(10, grid.getCols());

        // Comprobación de rollo en (0, 2)
        assertTrue(grid.isPaper(0, 2));
        assertEquals(3, grid.countPaperNeighbors(0, 2));
        assertTrue(AdjacentCountAccessibilityValidator.getInstance().isAccessible(grid, 0, 2));

        // Comprobación de rollo en (1, 1)
        assertTrue(grid.isPaper(1, 1));
        assertEquals(6, grid.countPaperNeighbors(1, 1));
        assertFalse(AdjacentCountAccessibilityValidator.getInstance().isAccessible(grid, 1, 1));
    }
}
