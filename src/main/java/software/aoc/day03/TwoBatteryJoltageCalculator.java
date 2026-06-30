package software.aoc.day03;

public class TwoBatteryJoltageCalculator implements JoltageCalculator {
    private static final TwoBatteryJoltageCalculator INSTANCE = new TwoBatteryJoltageCalculator();

    private TwoBatteryJoltageCalculator() {}

    public static TwoBatteryJoltageCalculator getInstance() {
        return INSTANCE;
    }

    @Override
    public long calculateMaxJoltage(BatteryBank bank) {
        String s = bank.ratings();
        int n = s.length();
        if (n < 2) {
            return 0;
        }

        long maxJoltage = 0;
        int maxDigitToRight = -1;

        for (int i = n - 1; i >= 0; i--) {
            int digit = s.charAt(i) - '0';
            if (maxDigitToRight != -1) {
                long joltage = digit * 10L + maxDigitToRight;
                if (joltage > maxJoltage) {
                    maxJoltage = joltage;
                }
            }
            if (digit > maxDigitToRight) {
                maxDigitToRight = digit;
            }
        }

        return maxJoltage;
    }
}
