package software.aoc.day07.a;

import software.aoc.SafeSolver;
import software.aoc.day07.*;

public class Day07ASolver implements SafeSolver {
    private final ManifoldReader reader;
    private final TachyonSimulator simulator;

    public Day07ASolver() {
        this.reader = new StringManifoldReader();
        this.simulator = new StandardTachyonSimulator();
    }

    @Override
    public long solve(String input) {
        Manifold manifold = reader.readManifold(input);
        return simulator.simulate(manifold);
    }
}
