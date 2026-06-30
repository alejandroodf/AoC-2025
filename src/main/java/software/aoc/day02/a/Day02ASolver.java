package software.aoc.day02.a;

import software.aoc.SafeSolver;
import software.aoc.day02.Day02Solver;
import software.aoc.day02.StringIdRangeReader;
import software.aoc.day02.RepeatedIdValidator;

public class Day02ASolver implements SafeSolver {
    private final Day02Solver delegate;

    public Day02ASolver() {
        this.delegate = new Day02Solver(new StringIdRangeReader(), RepeatedIdValidator.getInstance());
    }

    @Override
    public long solve(String input) {
        return delegate.solve(input);
    }
}
