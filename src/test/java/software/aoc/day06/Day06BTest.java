package software.aoc.day06;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day06.b.Day06BSolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day06BTest {
    private final String EXAMPLE_INPUT = "123 328  51 64 \n" +
                                         " 45 64  387 23 \n" +
                                         "  6 98  215 314\n" +
                                         "*   +   *   +  ";

    private final SafeSolver solver = new Day06BSolver();

    @Test
    public void testExampleCase() {
        long result = solver.solve(EXAMPLE_INPUT);
        assertEquals("El gran total de la Parte B con el ejemplo es incorrecto.", 3263827L, result);
    }

    @Test
    public void testFullInput() {
        String fullInput = TestInputReader.readInput(6, 'a', "input.txt");
        assertNotNull("El input de input.txt no debe ser nulo.", fullInput);
        long result = solver.solve(fullInput);
        System.out.println(">>> Solución Día 6 Parte B: " + result);
    }
}
