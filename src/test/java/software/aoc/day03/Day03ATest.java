package software.aoc.day03;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day03.a.Day03ASolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day03ATest {
    private final String EXAMPLE_INPUT = "987654321111111\n" +
            "811111111111119\n" +
            "234234234234278\n" +
            "818181911112111";

    private final SafeSolver solver = new Day03ASolver();

    @Test
    public void testExampleCase() {
        long result = solver.solve(EXAMPLE_INPUT);
        assertEquals("La suma de joltage máximo del ejemplo es incorrecta.", 357L, result);
    }

    @Test
    public void testFullInput() {
        String fullInput = TestInputReader.readInput(3, 'a', "battery.txt");
        assertNotNull("El input de battery.txt no debe ser nulo.", fullInput);
        long result = solver.solve(fullInput);
        System.out.println(">>> Solución Día 3 Parte A: " + result);
    }
}
