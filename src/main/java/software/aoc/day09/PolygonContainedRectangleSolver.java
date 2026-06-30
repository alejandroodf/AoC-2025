package software.aoc.day09;

import java.util.*;

public class PolygonContainedRectangleSolver implements RectangleSolver {
    @Override
    public long findMaxArea(List<RedTile> tiles) {
        int n = tiles.size();
        if (n < 2) return 0;

        // 1. Obtener coordenadas únicas ordenadas
        Set<Long> uniqueXSet = new TreeSet<>();
        Set<Long> uniqueYSet = new TreeSet<>();
        for (RedTile tile : tiles) {
            uniqueXSet.add(tile.x());
            uniqueYSet.add(tile.y());
        }

        List<Long> X = new ArrayList<>(uniqueXSet);
        List<Long> Y = new ArrayList<>(uniqueYSet);
        int mx = X.size();
        int my = Y.size();

        Map<Long, Integer> xToIndex = new HashMap<>();
        for (int i = 0; i < mx; i++) xToIndex.put(X.get(i), i);

        Map<Long, Integer> yToIndex = new HashMap<>();
        for (int i = 0; i < my; i++) yToIndex.put(Y.get(i), i);

        // 2. Determinar celdas interiores usando Ray Casting/Scanline
        boolean[][] inside = new boolean[mx - 1][my - 1];
        for (int v = 0; v < my - 1; v++) {
            double yMid = (Y.get(v) + Y.get(v + 1)) / 2.0;

            // Encontrar cortes con aristas verticales del polígono
            List<Integer> crossings = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                RedTile curr = tiles.get(i);
                RedTile next = tiles.get((i + 1) % n);
                if (curr.x() == next.x()) { // Arista vertical
                    long yMin = Math.min(curr.y(), next.y());
                    long yMax = Math.max(curr.y(), next.y());
                    if (yMin <= Y.get(v) && yMax >= Y.get(v + 1)) {
                        crossings.add(xToIndex.get(curr.x()));
                    }
                }
            }
            Collections.sort(crossings);

            for (int i = 0; i < crossings.size(); i += 2) {
                int uStart = crossings.get(i);
                int uEnd = crossings.get(i + 1);
                for (int u = uStart; u < uEnd; u++) {
                    inside[u][v] = true;
                }
            }
        }

        // 3. Inicializar Prefix Sums
        // 3.1 Celdas 2D
        int[][] prefInside = new int[mx][my];
        for (int u = 0; u < mx - 1; u++) {
            for (int v = 0; v < my - 1; v++) {
                prefInside[u + 1][v + 1] = (inside[u][v] ? 1 : 0)
                        + prefInside[u][v + 1]
                        + prefInside[u + 1][v]
                        - prefInside[u][v];
            }
        }

        // 3.2 Segmentos Verticales (1D)
        boolean[][] isValidV = new boolean[mx][my - 1];
        for (int u = 0; u < mx; u++) {
            for (int v = 0; v < my - 1; v++) {
                isValidV[u][v] = (u > 0 && inside[u - 1][v]) || (u < mx - 1 && inside[u][v]);
            }
        }
        int[][] prefV = new int[mx][my];
        for (int u = 0; u < mx; u++) {
            for (int v = 0; v < my - 1; v++) {
                prefV[u][v + 1] = prefV[u][v] + (isValidV[u][v] ? 1 : 0);
            }
        }

        // 3.3 Segmentos Horizontales (1D)
        boolean[][] isValidH = new boolean[mx - 1][my];
        for (int u = 0; u < mx - 1; u++) {
            for (int v = 0; v < my; v++) {
                isValidH[u][v] = (v > 0 && inside[u][v - 1]) || (v < my - 1 && inside[u][v]);
            }
        }
        int[][] prefH = new int[my][mx];
        for (int v = 0; v < my; v++) {
            for (int u = 0; u < mx - 1; u++) {
                prefH[v][u + 1] = prefH[v][u] + (isValidH[u][v] ? 1 : 0);
            }
        }

        // 4. Búsqueda del área máxima
        long maxArea = 0;
        for (int i = 0; i < n; i++) {
            RedTile a = tiles.get(i);
            int au = xToIndex.get(a.x());
            int av = yToIndex.get(a.y());

            for (int j = i + 1; j < n; j++) {
                RedTile b = tiles.get(j);
                int bu = xToIndex.get(b.x());
                int bv = yToIndex.get(b.y());

                int u1 = Math.min(au, bu);
                int u2 = Math.max(au, bu);
                int v1 = Math.min(av, bv);
                int v2 = Math.max(av, bv);

                boolean valid = false;
                if (u1 < u2 && v1 < v2) {
                    // Rectángulo 2D
                    int count = prefInside[u2][v2] - prefInside[u1][v2] - prefInside[u2][v1] + prefInside[u1][v1];
                    int totalCells = (u2 - u1) * (v2 - v1);
                    valid = (count == totalCells);
                } else if (u1 == u2 && v1 < v2) {
                    // Segmento Vertical 1D
                    int count = prefV[u1][v2] - prefV[u1][v1];
                    valid = (count == (v2 - v1));
                } else if (v1 == v2 && u1 < u2) {
                    // Segmento Horizontal 1D
                    int count = prefH[v1][u2] - prefH[v1][u1];
                    valid = (count == (u2 - u1));
                } else {
                    // Mismo punto
                    valid = true;
                }

                if (valid) {
                    long area = (X.get(u2) - X.get(u1) + 1) * (Y.get(v2) - Y.get(v1) + 1);
                    if (area > maxArea) {
                        maxArea = area;
                    }
                }
            }
        }

        return maxArea;
    }
}
