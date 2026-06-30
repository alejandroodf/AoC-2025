package software.aoc.day07;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class QuantumTachyonSimulator implements TachyonSimulator {
    @Override
    public long simulate(Manifold manifold) {
        if (manifold.height() == 0 || manifold.width() == 0) {
            return 0;
        }

        int startCol = -1;
        for (int c = 0; c < manifold.width(); c++) {
            if (manifold.charAt(0, c) == 'S') {
                startCol = c;
                break;
            }
        }

        if (startCol == -1) {
            return 0;
        }

        BigInteger completedTimelines = BigInteger.ZERO;
        Map<Integer, BigInteger> activeColumns = new HashMap<>();
        activeColumns.put(startCol, BigInteger.ONE);

        for (int r = 0; r < manifold.height(); r++) {
            Map<Integer, BigInteger> nextColumns = new HashMap<>();

            for (Map.Entry<Integer, BigInteger> entry : activeColumns.entrySet()) {
                int col = entry.getKey();
                BigInteger count = entry.getValue();

                if (manifold.charAt(r, col) == '^') {
                    // Rama izquierda
                    if (col - 1 >= 0) {
                        nextColumns.put(col - 1, nextColumns.getOrDefault(col - 1, BigInteger.ZERO).add(count));
                    } else {
                        completedTimelines = completedTimelines.add(count);
                    }
                    // Rama derecha
                    if (col + 1 < manifold.width()) {
                        nextColumns.put(col + 1, nextColumns.getOrDefault(col + 1, BigInteger.ZERO).add(count));
                    } else {
                        completedTimelines = completedTimelines.add(count);
                    }
                } else {
                    // Continúa hacia abajo
                    if (r + 1 < manifold.height()) {
                        nextColumns.put(col, nextColumns.getOrDefault(col, BigInteger.ZERO).add(count));
                    } else {
                        completedTimelines = completedTimelines.add(count);
                    }
                }
            }

            activeColumns = nextColumns;
        }

        // Sumar cualquier línea temporal que haya quedado activa al final de la última fila (si hubiera)
        for (BigInteger count : activeColumns.values()) {
            completedTimelines = completedTimelines.add(count);
        }

        return completedTimelines.longValue();
    }
}
