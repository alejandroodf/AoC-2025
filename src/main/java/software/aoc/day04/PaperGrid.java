package software.aoc.day04;

public class PaperGrid {
    private final char[][] grid;
    private final int rows;
    private final int cols;
    private static final char PAPER = '@';
    private static final char EMPTY = '.';

    public PaperGrid(char[][] grid) {
        this.rows = grid.length;
        this.cols = grid.length > 0 ? grid[0].length : 0;
        this.grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(grid[i], 0, this.grid[i], 0, cols);
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean isPaper(int r, int c) {
        return isValidCoordinate(r, c) && grid[r][c] == PAPER;
    }

    public void removePaper(int r, int c) {
        if (isValidCoordinate(r, c)) {
            grid[r][c] = EMPTY;
        }
    }

    public boolean isValidCoordinate(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    public int countPaperNeighbors(int r, int c) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                if (isPaper(r + dr, c + dc)) {
                    count++;
                }
            }
        }
        return count;
    }
}
