package software.aoc.day03;

import java.util.List;

public class Day03Solver {
    private final BatteryBankReader reader;
    private final JoltageCalculator calculator;

    public Day03Solver(BatteryBankReader reader, JoltageCalculator calculator) {
        this.reader = reader;
        this.calculator = calculator;
    }

    public long solve(String input) {
        List<BatteryBank> banks = reader.readBanks(input);
        return banks.stream()
            .mapToLong(calculator::calculateMaxJoltage)
            .sum();
    }
}
