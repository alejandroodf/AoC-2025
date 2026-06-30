package software.aoc.day08.a;

import software.aoc.SafeSolver;
import software.aoc.day08.*;

import java.util.List;

public class Day08ASolver implements SafeSolver {
    private final JunctionBoxReader reader;
    private final CircuitSimulator simulator;

    public Day08ASolver() {
        this.reader = new StringJunctionBoxReader();
        this.simulator = new GreedyCircuitSimulator();
    }

    @Override
    public long solve(String input) {
        List<JunctionBox> boxes = reader.readJunctionBoxes(input);
        return simulator.simulate(boxes, 1000);
    }
}
