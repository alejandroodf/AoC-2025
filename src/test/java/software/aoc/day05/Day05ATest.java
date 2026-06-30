package software.aoc.day05;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day05.a.Day05ASolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day05ATest {
    private final String EXAMPLE_INPUT = "3-5\n" +
            "10-14\n" +
            "16-20\n" +
            "12-18\n" +
            "\n" +
            "1\n" +
            "5\n" +
            "8\n" +
            "11\n" +
            "17\n" +
            "32";

    private final SafeSolver solver = new Day05ASolver();

    @Test
    public void testExampleCase() {
        long result = solver.solve(EXAMPLE_INPUT);
        assertEquals("La cantidad de ingredientes frescos en el ejemplo es incorrecta.", 3, result);
    }

    @Test
    public void testFullInput() {
        String fullInput = TestInputReader.readInput(5, 'a', "input.txt");
        assertNotNull("El input de input.txt no debe ser nulo.", fullInput);
        long result = solver.solve(fullInput);
        System.out.println(">>> Solución Día 5 Parte A: " + result);
    }
}
