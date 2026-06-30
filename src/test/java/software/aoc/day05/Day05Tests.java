package software.aoc.day05;

import org.junit.Test;
import static org.junit.Assert.*;

public class Day05Tests {

    @Test
    public void testRangeContains() {
        IngredientRange range = new IngredientRange(3, 5);
        assertTrue(range.contains(3));
        assertTrue(range.contains(4));
        assertTrue(range.contains(5));
        assertFalse(range.contains(2));
        assertFalse(range.contains(6));
        assertEquals(3, range.length());
    }

    @Test
    public void testDatabaseReader() {
        String input = "3-5\n10-14\n\n1\n5\n8";
        DatabaseReader reader = new StringDatabaseReader();
        Database db = reader.readDatabase(input);
        assertEquals(2, db.freshRanges().size());
        assertEquals(3, db.availableIds().size());
        assertEquals(new IngredientRange(3, 5), db.freshRanges().get(0));
        assertEquals(Long.valueOf(1), db.availableIds().get(0));
    }

    @Test
    public void testIngredientRangeSorting() {
        java.util.List<IngredientRange> ranges = new java.util.ArrayList<>(java.util.List.of(
            new IngredientRange(10, 14),
            new IngredientRange(3, 5),
            new IngredientRange(12, 18)
        ));
        java.util.Collections.sort(ranges);
        assertEquals(new IngredientRange(3, 5), ranges.get(0));
        assertEquals(new IngredientRange(10, 14), ranges.get(1));
        assertEquals(new IngredientRange(12, 18), ranges.get(2));
    }
}
