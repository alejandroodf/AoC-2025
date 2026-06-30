package software.aoc.day08.b;

import software.aoc.SafeSolver;
import software.aoc.day08.*;

import java.util.List;

public class Day08BSolver implements SafeSolver {
    private final JunctionBoxReader reader;
    private final CircuitSimulator simulator;

    public Day08BSolver() {
        this.reader = new StringJunctionBoxReader();
        this.simulator = new MstCircuitSimulator();
    }

    @Override
    public long solve(String input) {
        List<JunctionBox> boxes = reader.readJunctionBoxes(input);
        return simulator.simulate(boxes, 0); // El límite es ignorado en MstCircuitSimulator
    }
}
