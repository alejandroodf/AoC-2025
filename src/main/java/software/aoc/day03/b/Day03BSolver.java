package software.aoc.day03.b;

import software.aoc.SafeSolver;
import software.aoc.day03.Day03Solver;
import software.aoc.day03.StringBatteryBankReader;
import software.aoc.day03.TwelveBatteryJoltageCalculator;

public class Day03BSolver implements SafeSolver {
    private final Day03Solver delegate;

    public Day03BSolver() {
        this.delegate = new Day03Solver(new StringBatteryBankReader(), TwelveBatteryJoltageCalculator.getInstance());
    }

    @Override
    public long solve(String input) {
        return delegate.solve(input);
    }
}
