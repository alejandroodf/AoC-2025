package software.aoc.day04;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day04.b.Day04BSolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day04BTest {
    private final String EXAMPLE_INPUT = "..@@.@@@@.\n" +
            "@@@.@.@.@@\n" +
            "@@@@@.@.@@\n" +
            "@.@@@@..@.\n" +
            "@@.@@@@.@@\n" +
            ".@@@@@@@.@\n" +
            ".@.@.@.@@@\n" +
            "@.@@@.@@@@\n" +
            ".@@@@@@@@.\n" +
            "@.@.@@@.@.";

    private final SafeSolver solver = new Day04BSolver();

    @Test
    public void testExampleCase() {
        long result = solver.solve(EXAMPLE_INPUT);
        assertEquals("El número total de rollos retirados en el ejemplo es incorrecto.", 43, result);
    }

    @Test
    public void testFullInput() {
        String fullInput = TestInputReader.readInput(4, 'a', "input.txt");
        assertNotNull("El input de input.txt no debe ser nulo.", fullInput);
        long result = solver.solve(fullInput);
        System.out.println(">>> Solución Día 4 Parte B: " + result);
    }
}
