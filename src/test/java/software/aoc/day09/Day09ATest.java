package software.aoc.day09;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day09.a.Day09ASolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day09ATest {
    private final String EXAMPLE =
            "7,1\n" +
            "11,1\n" +
            "11,7\n" +
            "9,7\n" +
            "9,5\n" +
            "2,5\n" +
            "2,3\n" +
            "7,3";

    private final SafeSolver solver = new Day09ASolver();

    @Test
    public void testExampleCase() {
        long result = solver.solve(EXAMPLE);
        assertEquals("El área máxima en el ejemplo es incorrecta.", 50L, result);
    }

    @Test
    public void testFullInput() {
        String fullInput = TestInputReader.readInput(9, 'a', "input.txt");
        assertNotNull("El input de input.txt no debe ser nulo.", fullInput);
        long result = solver.solve(fullInput);
        System.out.println(">>> Solución Día 9 Parte A: " + result);
    }
}
