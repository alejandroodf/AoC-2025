package software.aoc.day07;

import java.util.HashSet;
import java.util.Set;

public class StandardTachyonSimulator implements TachyonSimulator {
    @Override
    public long simulate(Manifold manifold) {
        if (manifold.height() == 0 || manifold.width() == 0) {
            return 0;
        }

        // Encontrar la posición de entrada S en la fila 0
        int startCol = -1;
        for (int c = 0; c < manifold.width(); c++) {
            if (manifold.charAt(0, c) == 'S') {
                startCol = c;
                break;
            }
        }

        if (startCol == -1) {
            return 0; // No se encuentra inicio
        }

        long splitCount = 0;
        Set<Integer> activeColumns = new HashSet<>();
        activeColumns.add(startCol);

        for (int r = 0; r < manifold.height(); r++) {
            Set<Integer> splittersHit = new HashSet<>();
            Set<Integer> continued = new HashSet<>();

            for (int col : activeColumns) {
                if (manifold.charAt(r, col) == '^') {
                    splittersHit.add(col);
                } else {
                    continued.add(col);
                }
            }

            splitCount += splittersHit.size();

            Set<Integer> emitted = new HashSet<>();
            for (int col : splittersHit) {
                if (col - 1 >= 0) {
                    emitted.add(col - 1);
                }
                if (col + 1 < manifold.width()) {
                    emitted.add(col + 1);
                }
            }

            // Para la siguiente fila, las columnas activas son la unión de las que continuaron y las emitidas
            activeColumns.clear();
            activeColumns.addAll(continued);
            activeColumns.addAll(emitted);
        }

        return splitCount;
    }
}
