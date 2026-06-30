package software.aoc.day09;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class Day09Tests {
    private final String EXAMPLE =
            "7,1\n" +
            "11,1\n" +
            "11,7\n" +
            "9,7\n" +
            "9,5\n" +
            "2,5\n" +
            "2,3\n" +
            "7,3";

    @Test
    public void testAreaCalculation() {
        RedTile a = new RedTile(2, 5);
        RedTile b = new RedTile(9, 7);
        assertEquals(24L, a.rectangleAreaWith(b));

        RedTile c = new RedTile(7, 1);
        RedTile d = new RedTile(11, 7);
        assertEquals(35L, c.rectangleAreaWith(d));
    }

    @Test
    public void testParserAndSolverExample() {
        List<RedTile> tiles = new StringRedTileReader().readRedTiles(EXAMPLE);
        assertEquals(8, tiles.size());
        RectangleSolver solver = new BruteForceRectangleSolver();
        long result = solver.findMaxArea(tiles);
        assertEquals(50L, result);
    }
}
