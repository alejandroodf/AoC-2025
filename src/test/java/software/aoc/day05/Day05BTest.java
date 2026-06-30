package software.aoc.day05;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day05.b.Day05BSolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day05BTest {
    private final String EXAMPLE_INPUT = "3-5\n" +
            "10-14\n" +
            "16-20\n" +
            "12-18\n" +
            "\n" +
            "1\n" +
            "5\n" +
            "8";

    private final SafeSolver solver = new Day05BSolver();

    @Test
    public void testExampleCase() {
        long result = solver.solve(EXAMPLE_INPUT);
        assertEquals("El número total de IDs frescos en el ejemplo es incorrecto.", 14, result);
    }

    @Test
    public void testFullInput() {
        String fullInput = TestInputReader.readInput(5, 'a', "input.txt");
        assertNotNull("El input de input.txt no debe ser nulo.", fullInput);
        long result = solver.solve(fullInput);
        System.out.println(">>> Solución Día 5 Parte B: " + result);
    }
}
