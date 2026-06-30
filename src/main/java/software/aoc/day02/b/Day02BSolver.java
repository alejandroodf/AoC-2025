package software.aoc.day02.b;

import software.aoc.SafeSolver;
import software.aoc.day02.Day02Solver;
import software.aoc.day02.StringIdRangeReader;
import software.aoc.day02.MultipleRepeatedIdValidator;

public class Day02BSolver implements SafeSolver {
    private final Day02Solver delegate;

    public Day02BSolver() {
        this.delegate = new Day02Solver(new StringIdRangeReader(), MultipleRepeatedIdValidator.getInstance());
    }

    @Override
    public long solve(String input) {
        return delegate.solve(input);
    }
}
