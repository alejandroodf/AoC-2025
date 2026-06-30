package software.aoc.day12;

import java.util.*;

/**
 * Resolvedor de empaquetado mediante backtracking con poda de área y ruptura de simetría.
 *
 * Estrategia principal:
 *  - Poda por área: si el área restante de moldes > celdas libres, imposible.
 *  - Orden por tamaño descendente: los moldes más grandes se colocan primero,
 *    lo que genera mejores podas al inicio del árbol de búsqueda.
 *  - Ruptura de simetría para moldes idénticos: para dos copias del mismo tipo,
 *    sólo se prueba la segunda en posiciones >= que la primera. Esto evita
 *    explorar la misma configuración en distinto orden (reduce el espacio
 *    en un factor k! para k copias del mismo molde).
 */
public class BacktrackSolver {

    private boolean[][] grid;
    private int width;
    private int height;
    private int freeCells;

    /**
     * Determina si los moldes dados pueden encajar en la región especificada.
     *
     * @param width      ancho de la región
     * @param height     alto de la región
     * @param shapes     lista de moldes disponibles (por índice)
     * @param quantities cantidad de cada molde que debe caber
     * @return true si todos los moldes caben, false si es imposible
     */
    public boolean canFit(int width, int height, List<Shape> shapes, int[] quantities) {
        this.width = width;
        this.height = height;
        this.freeCells = width * height;
        this.grid = new boolean[height][width];

        // Comprobación rápida por área total
        int totalArea = 0;
        for (int i = 0; i < shapes.size(); i++) {
            totalArea += quantities[i] * shapes.get(i).size;
        }
        if (totalArea > freeCells) return false;
        if (totalArea == 0) return true;

        // Precomputar orientaciones únicas de cada molde
        List<List<Shape>> allOrientations = new ArrayList<>();
        for (Shape s : shapes) {
            allOrientations.add(s.getUniqueOrientations());
        }

        // Construir lista de instancias a colocar (índice del molde por cada unidad)
        // Ordenadas por tamaño descendente: los moldes grandes primero generan
        // podas más agresivas al inicio del árbol de búsqueda.
        List<Integer> toPlace = new ArrayList<>();
        for (int i = 0; i < shapes.size(); i++) {
            for (int j = 0; j < quantities[i]; j++) {
                toPlace.add(i);
            }
        }
        toPlace.sort((a, b) -> shapes.get(b).size - shapes.get(a).size);

        return backtrack(shapes, allOrientations, toPlace, 0, totalArea, 0);
    }

    /**
     * Backtracking recursivo.
     *
     * @param idx              índice del siguiente molde en toPlace que hay que colocar
     * @param remainingArea    suma de áreas de los moldes que quedan por colocar
     * @param minPlacementIdx  índice de colocación mínimo (ruptura de simetría entre
     *                         copias del mismo tipo)
     */
    private boolean backtrack(List<Shape> shapes, List<List<Shape>> allOrientations,
                               List<Integer> toPlace, int idx, int remainingArea,
                               int minPlacementIdx) {
        if (idx == toPlace.size()) return true;       // Todos los moldes colocados ✓
        if (remainingArea > freeCells) return false;  // Poda por área

        int si = toPlace.get(idx);
        List<Shape> orientations = allOrientations.get(si);
        int shapeArea = shapes.get(si).size;

        // Iterar sobre todas las colocaciones posibles: (orientación, fila, columna)
        int placementIdx = 0;
        for (int oi = 0; oi < orientations.size(); oi++) {
            Shape orientation = orientations.get(oi);
            int maxRow = height - orientation.rows;
            int maxCol = width - orientation.cols;

            for (int r = 0; r <= maxRow; r++) {
                for (int c = 0; c <= maxCol; c++) {
                    if (placementIdx >= minPlacementIdx && canPlace(orientation, r, c)) {
                        int placed = place(orientation, r, c, true);
                        freeCells -= placed;

                        // Ruptura de simetría: si el siguiente molde es del mismo tipo,
                        // sólo permitirle posiciones >= a la actual
                        boolean sameTypeNext = (idx + 1 < toPlace.size())
                                && toPlace.get(idx + 1).equals(si);
                        int nextMin = sameTypeNext ? placementIdx : 0;

                        if (backtrack(shapes, allOrientations, toPlace,
                                idx + 1, remainingArea - shapeArea, nextMin)) {
                            return true;
                        }

                        place(orientation, r, c, false);
                        freeCells += placed;
                    }
                    placementIdx++;
                }
            }
        }

        return false;
    }

    /** Comprueba si el molde puede colocarse en (startRow, startCol) sin salirse ni solapar. */
    private boolean canPlace(Shape shape, int startRow, int startCol) {
        for (int r = 0; r < shape.rows; r++) {
            for (int c = 0; c < shape.cols; c++) {
                if (shape.get(r, c) && grid[startRow + r][startCol + c]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Coloca o elimina el molde en la cuadrícula.
     * @return número de celdas '#' colocadas/eliminadas
     */
    private int place(Shape shape, int startRow, int startCol, boolean value) {
        int count = 0;
        for (int r = 0; r < shape.rows; r++) {
            for (int c = 0; c < shape.cols; c++) {
                if (shape.get(r, c)) {
                    grid[startRow + r][startCol + c] = value;
                    count++;
                }
            }
        }
        return count;
    }
}
