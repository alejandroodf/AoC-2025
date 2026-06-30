package software.aoc.day12;

import java.util.*;

/**
 * Representa la forma geométrica de un regalo mediante una cuadrícula de booleanos.
 * Proporciona métodos para generar todas sus variantes únicas (rotaciones y reflexiones)
 * utilizando colecciones nativas basadas en equals y hashCode.
 */
public class Shape {
    private final boolean[][] cells;
    public final int rows;
    public final int cols;
    public final int size;

    public Shape(boolean[][] cells) {
        if (cells == null || cells.length == 0 || cells[0].length == 0) {
            throw new IllegalArgumentException("Grid cells cannot be empty or null.");
        }
        this.cells = cells;
        this.rows = cells.length;
        this.cols = cells[0].length;
        this.size = calculateSize();
    }

    private int calculateSize() {
        int count = 0;
        for (boolean[] row : cells) {
            for (boolean cell : row) {
                if (cell) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean get(int r, int c) {
        return cells[r][c];
    }

    /**
     * Genera el conjunto de todas las orientaciones únicas posibles para este regalo.
     * Incluye rotaciones de 90, 180, 270 grados y volteos (horizontal y vertical).
     */
    public List<Shape> getUniqueOrientations() {
        Set<Shape> orientations = new LinkedHashSet<>();
        Shape current = this;
        for (int i = 0; i < 4; i++) {
            orientations.add(current);
            orientations.add(current.flipHorizontal());
            orientations.add(current.flipVertical());
            current = current.rotate90();
        }
        return new ArrayList<>(orientations);
    }

    public Shape rotate90() {
        boolean[][] rotated = new boolean[cols][rows];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rotated[c][rows - 1 - r] = cells[r][c];
            }
        }
        return new Shape(rotated);
    }

    public Shape flipHorizontal() {
        boolean[][] flipped = new boolean[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                flipped[r][cols - 1 - c] = cells[r][c];
            }
        }
        return new Shape(flipped);
    }

    public Shape flipVertical() {
        boolean[][] flipped = new boolean[rows][cols];
        for (int r = 0; r < rows; r++) {
            flipped[rows - 1 - r] = cells[r].clone();
        }
        return new Shape(flipped);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape other = (Shape) o;
        return Arrays.deepEquals(this.cells, other.cells);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.cells);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (boolean[] row : cells) {
            for (boolean cell : row) {
                sb.append(cell ? '#' : '.');
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
