package software.aoc.day10;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day10.a.Day10ASolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day10ATest {
    private final String EXAMPLE =
            "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}\n" +
            "[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}\n" +
            "[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}";

    private final SafeSolver solver = new Day10ASolver();

    @Test
    public void testExampleCase() {
        long result = solver.solve(EXAMPLE);
        assertEquals("El mínimo total de pulsaciones en el ejemplo es incorrecto.", 7L, result);
    }

    @Test
    public void testFullInput() {
        String fullInput = TestInputReader.readInput(10, 'a', "input.txt");
        assertNotNull("El input de input.txt no debe ser nulo.", fullInput);
        long result = solver.solve(fullInput);
        System.out.println(">>> Solución Día 10 Parte A: " + result);
    }
}
