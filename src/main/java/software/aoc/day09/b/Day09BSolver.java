package software.aoc.day09.b;

import software.aoc.SafeSolver;
import software.aoc.day09.*;

import java.util.List;

public class Day09BSolver implements SafeSolver {
    private final RedTileReader reader;
    private final RectangleSolver solver;

    public Day09BSolver() {
        this.reader = new StringRedTileReader();
        this.solver = new PolygonContainedRectangleSolver();
    }

    @Override
    public long solve(String input) {
        List<RedTile> tiles = reader.readRedTiles(input);
        return solver.findMaxArea(tiles);
    }
}
