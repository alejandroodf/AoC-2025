package software.aoc.day03;

public class TwelveBatteryJoltageCalculator implements JoltageCalculator {
    private static final TwelveBatteryJoltageCalculator INSTANCE = new TwelveBatteryJoltageCalculator();
    private static final int TARGET_LENGTH = 12;

    private TwelveBatteryJoltageCalculator() {}

    public static TwelveBatteryJoltageCalculator getInstance() {
        return INSTANCE;
    }

    @Override
    public long calculateMaxJoltage(BatteryBank bank) {
        String s = bank.ratings();
        int n = s.length();
        if (n < TARGET_LENGTH) {
            return 0;
        }

        int maxDrops = n - TARGET_LENGTH;
        char[] stack = new char[n];
        int top = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            while (top > 0 && stack[top - 1] < c && maxDrops > 0) {
                top--;
                maxDrops--;
            }
            stack[top++] = c;
        }

        // Si aún sobran eliminaciones (o el stack es mayor a 12), tomamos solo los primeros 12.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TARGET_LENGTH; i++) {
            sb.append(stack[i]);
        }

        return Long.parseLong(sb.toString());
    }
}
