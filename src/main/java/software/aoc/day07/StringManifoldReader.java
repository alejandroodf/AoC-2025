package software.aoc.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringManifoldReader implements ManifoldReader {
    @Override
    public Manifold readManifold(String input) {
        if (input == null || input.isBlank()) {
            return new Manifold(new char[0][0], 0, 0);
        }

        List<char[]> validLines = new ArrayList<>();
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isBlank()) {
                    validLines.add(line.toCharArray());
                }
            }
        }

        int height = validLines.size();
        if (height == 0) {
            return new Manifold(new char[0][0], 0, 0);
        }
        int width = validLines.get(0).length;

        char[][] grid = new char[height][width];
        for (int r = 0; r < height; r++) {
            grid[r] = validLines.get(r);
        }

        return new Manifold(grid, width, height);
    }
}
