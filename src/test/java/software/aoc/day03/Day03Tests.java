package software.aoc.day03;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class Day03Tests {

    @Test
    public void testReader() {
        BatteryBankReader reader = new StringBatteryBankReader();
        List<BatteryBank> banks = reader.readBanks("12345\n987");
        assertEquals(2, banks.size());
        assertEquals("12345", banks.get(0).ratings());
        assertEquals("987", banks.get(1).ratings());
    }

    @Test
    public void testTwoBatteryJoltageCalculator() {
        JoltageCalculator calc = TwoBatteryJoltageCalculator.getInstance();
        assertEquals(98L, calc.calculateMaxJoltage(new BatteryBank("987654321111111")));
        assertEquals(89L, calc.calculateMaxJoltage(new BatteryBank("811111111111119")));
        assertEquals(78L, calc.calculateMaxJoltage(new BatteryBank("234234234234278")));
        assertEquals(92L, calc.calculateMaxJoltage(new BatteryBank("818181911112111")));
    }

    @Test
    public void testTwelveBatteryJoltageCalculator() {
        JoltageCalculator calc = TwelveBatteryJoltageCalculator.getInstance();
        assertEquals(987654321111L, calc.calculateMaxJoltage(new BatteryBank("987654321111111")));
        assertEquals(811111111119L, calc.calculateMaxJoltage(new BatteryBank("811111111111119")));
        assertEquals(434234234278L, calc.calculateMaxJoltage(new BatteryBank("234234234234278")));
        assertEquals(888911112111L, calc.calculateMaxJoltage(new BatteryBank("818181911112111")));
    }
}
