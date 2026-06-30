package software.aoc.day08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreedyCircuitSimulator implements CircuitSimulator {
    @Override
    public long simulate(List<JunctionBox> boxes, int connectionLimit) {
        int n = boxes.size();
        if (n == 0) return 0;

        List<JunctionBoxPair> pairs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pairs.add(new JunctionBoxPair(i, j, boxes.get(i).squaredDistanceTo(boxes.get(j))));
            }
        }

        Collections.sort(pairs);

        DisjointSetUnion dsu = new DisjointSetUnion(n);
        int limit = Math.min(connectionLimit, pairs.size());
        for (int k = 0; k < limit; k++) {
            JunctionBoxPair pair = pairs.get(k);
            dsu.union(pair.index1(), pair.index2());
        }

        List<Integer> sizes = dsu.getSizes();
        sizes.sort(Collections.reverseOrder());

        long product = 1;
        int count = Math.min(3, sizes.size());
        for (int i = 0; i < count; i++) {
            product *= sizes.get(i);
        }

        return product;
    }
}
