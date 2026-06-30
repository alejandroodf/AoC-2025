package software.aoc.day12.a;

import software.aoc.SafeSolver;
import software.aoc.day12.BacktrackSolver;
import software.aoc.day12.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Resolvedor para la Parte A del Día 12.
 * Adapta la entrada del archivo de texto leyendo primero los moldes (shapes)
 * y luego procesando cada región bajo el árbol para verificar si caben los regalos.
 */
public class Day12ASolver implements SafeSolver {

    @Override
    public long solve(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }

        List<Shape> shapes = new ArrayList<>();
        List<String> currentShapeLines = new ArrayList<>();
        long validRegionsCount = 0;

        String[] lines = input.split("\\R");
        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                continue;
            }

            if (trimmed.contains("x") && trimmed.contains(":")) {
                // Es una línea de región: por ejemplo, "12x5: 1 0 1 0 2 2"
                // Si había un molde pendiente de procesar, lo finalizamos primero
                if (!currentShapeLines.isEmpty()) {
                    shapes.add(parseShape(currentShapeLines));
                    currentShapeLines.clear();
                }

                if (solveRegion(trimmed, shapes)) {
                    validRegionsCount++;
                }
            } else if (trimmed.endsWith(":")) {
                // Es el inicio de un molde: por ejemplo, "0:"
                // Finalizamos el molde anterior si existe
                if (!currentShapeLines.isEmpty()) {
                    shapes.add(parseShape(currentShapeLines));
                    currentShapeLines.clear();
                }
            } else {
                // Línea de dibujo del molde (contiene '#' y/o '.')
                currentShapeLines.add(trimmed);
            }
        }

        // Caso límite: molde pendiente al final de la lectura
        if (!currentShapeLines.isEmpty()) {
            shapes.add(parseShape(currentShapeLines));
        }

        return validRegionsCount;
    }

    private Shape parseShape(List<String> lines) {
        int h = lines.size();
        int w = 0;
        for (String l : lines) {
            w = Math.max(w, l.length());
        }

        boolean[][] grid = new boolean[h][w];
        for (int r = 0; r < h; r++) {
            String row = lines.get(r);
            for (int c = 0; c < row.length(); c++) {
                if (row.charAt(c) == '#') {
                    grid[r][c] = true;
                }
            }
        }
        return new Shape(grid);
    }

    private boolean solveRegion(String regionLine, List<Shape> shapes) {
        try {
            String[] mainParts = regionLine.split(":");
            String[] dims = mainParts[0].trim().split("x");
            int width = Integer.parseInt(dims[0]);
            int height = Integer.parseInt(dims[1]);

            String[] quantityTokens = mainParts[1].trim().split("\\s+");
            int[] quantities = new int[quantityTokens.length];
            for (int i = 0; i < quantityTokens.length; i++) {
                quantities[i] = Integer.parseInt(quantityTokens[i]);
            }

            BacktrackSolver solver = new BacktrackSolver();
            return solver.canFit(width, height, shapes, quantities);
        } catch (Exception e) {
            // Si la línea no tiene el formato esperado, se ignora
            return false;
        }
    }
}
