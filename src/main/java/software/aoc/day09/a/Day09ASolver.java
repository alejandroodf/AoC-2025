package software.aoc.day09.a;

import software.aoc.SafeSolver;
import software.aoc.day09.*;

import java.util.List;

public class Day09ASolver implements SafeSolver {
    private final RedTileReader reader;
    private final RectangleSolver solver;

    public Day09ASolver() {
        this.reader = new StringRedTileReader();
        this.solver = new BruteForceRectangleSolver();
    }

    @Override
    public long solve(String input) {
        List<RedTile> tiles = reader.readRedTiles(input);
        return solver.findMaxArea(tiles);
    }
}
