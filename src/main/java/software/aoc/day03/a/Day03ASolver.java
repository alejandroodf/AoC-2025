package software.aoc.day03.a;

import software.aoc.SafeSolver;
import software.aoc.day03.Day03Solver;
import software.aoc.day03.StringBatteryBankReader;
import software.aoc.day03.TwoBatteryJoltageCalculator;

public class Day03ASolver implements SafeSolver {
    private final Day03Solver delegate;

    public Day03ASolver() {
        this.delegate = new Day03Solver(new StringBatteryBankReader(), TwoBatteryJoltageCalculator.getInstance());
    }

    @Override
    public long solve(String input) {
        return delegate.solve(input);
    }
}
