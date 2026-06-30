package software.aoc.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringPaperGridReader implements PaperGridReader {
    @Override
    public PaperGrid readGrid(String input) {
        if (input == null || input.isBlank()) {
            return new PaperGrid(new char[0][0]);
        }
        
        List<char[]> lineList = new ArrayList<>();
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isBlank()) {
                    lineList.add(line.toCharArray());
                }
            }
        }
        
        int rows = lineList.size();
        int cols = rows > 0 ? lineList.get(0).length : 0;
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = lineList.get(i);
        }
        return new PaperGrid(grid);
    }
}
