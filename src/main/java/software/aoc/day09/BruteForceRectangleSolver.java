package software.aoc.day09;

import java.util.List;

public class BruteForceRectangleSolver implements RectangleSolver {
    @Override
    public long findMaxArea(List<RedTile> tiles) {
        int n = tiles.size();
        if (n < 2) return 0;

        long maxArea = 0;
        for (int i = 0; i < n; i++) {
            RedTile a = tiles.get(i);
            for (int j = i + 1; j < n; j++) {
                RedTile b = tiles.get(j);
                long area = a.rectangleAreaWith(b);
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }
}
