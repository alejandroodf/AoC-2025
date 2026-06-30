package software.aoc.day07.b;

import software.aoc.SafeSolver;
import software.aoc.day07.Manifold;
import software.aoc.day07.ManifoldReader;
import software.aoc.day07.QuantumTachyonSimulator;
import software.aoc.day07.StringManifoldReader;
import software.aoc.day07.TachyonSimulator;

public class Day07BSolver implements SafeSolver {
    private final ManifoldReader reader;
    private final TachyonSimulator simulator;

    public Day07BSolver() {
        this.reader = new StringManifoldReader();
        this.simulator = new QuantumTachyonSimulator();
    }

    @Override
    public long solve(String input) {
        Manifold manifold = reader.readManifold(input);
        return simulator.simulate(manifold);
    }
}
