package software.aoc.day01.a;

import software.aoc.SafeSolver;
import software.aoc.day01.Day01Solver;
import software.aoc.day01.StringRotationReader;
import software.aoc.day01.EndAtZeroScorer;

public class Day01ASolver implements SafeSolver {
    private final Day01Solver delegate;

    public Day01ASolver() {
        this.delegate = new Day01Solver(new StringRotationReader(), new EndAtZeroScorer());
    }

    @Override
    public long solve(String input) {
        return delegate.solve(input);
    }
}
