package software.aoc.day01.b;

import software.aoc.SafeSolver;
import software.aoc.day01.Day01Solver;
import software.aoc.day01.StringRotationReader;
import software.aoc.day01.PassThroughZeroScorer;

public class Day01BSolver implements SafeSolver {
    private final Day01Solver delegate;

    public Day01BSolver() {
        this.delegate = new Day01Solver(new StringRotationReader(), new PassThroughZeroScorer());
    }

    @Override
    public long solve(String input) {
        return delegate.solve(input);
    }
}
