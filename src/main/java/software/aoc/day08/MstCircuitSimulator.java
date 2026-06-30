package software.aoc.day08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MstCircuitSimulator implements CircuitSimulator {
    @Override
    public long simulate(List<JunctionBox> boxes, int connectionLimit) {
        int n = boxes.size();
        if (n < 2) return 0;

        List<JunctionBoxPair> pairs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pairs.add(new JunctionBoxPair(i, j, boxes.get(i).squaredDistanceTo(boxes.get(j))));
            }
        }

        Collections.sort(pairs);

        DisjointSetUnion dsu = new DisjointSetUnion(n);
        int activeCircuits = n;

        for (JunctionBoxPair pair : pairs) {
            if (dsu.union(pair.index1(), pair.index2())) {
                activeCircuits--;
                if (activeCircuits == 1) {
                    return boxes.get(pair.index1()).x() * boxes.get(pair.index2()).x();
                }
            }
        }

        return 0;
    }
}
