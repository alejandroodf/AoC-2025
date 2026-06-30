package software.aoc.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringBatteryBankReader implements BatteryBankReader {
    @Override
    public List<BatteryBank> readBanks(String input) {
        if (input == null || input.isBlank()) {
            return List.of();
        }
        List<BatteryBank> banks = new ArrayList<>();
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNext()) {
                banks.add(new BatteryBank(scanner.next()));
            }
        }
        return banks;
    }
}
