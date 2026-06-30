package software.aoc.day07;

public record Manifold(char[][] grid, int width, int height) {
    public char charAt(int row, int col) {
        return grid[row][col];
    }
}
