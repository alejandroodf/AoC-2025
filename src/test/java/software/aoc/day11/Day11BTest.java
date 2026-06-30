package software.aoc.day11;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day11.b.Day11BSolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day11BTest {
    private final String EXAMPLE =
            "svr: aaa bbb\n" +
            "aaa: fft\n" +
            "fft: ccc\n" +
            "bbb: tty\n" +
            "tty: ccc\n" +
            "ccc: ddd eee\n" +
            "ddd: hub\n" +
            "hub: fff\n" +
            "eee: dac\n" +
            "dac: fff\n" +
            "fff: ggg hhh\n" +
            "ggg: out\n" +
            "hhh: out";

    private final SafeSolver solver = new Day11BSolver();

    @Test
    public void testExampleCase() {
        long result = solver.solve(EXAMPLE);
        assertEquals("El número total de caminos válidos en el ejemplo es incorrecto.", 2L, result);
    }

    @Test
    public void testFullInput() {
        String fullInput = TestInputReader.readInput(11, 'a', "input.txt");
        assertNotNull("El input de input.txt no debe ser nulo.", fullInput);
        long result = solver.solve(fullInput);
        System.out.println(">>> Solución Día 11 Parte B: " + result);
    }
}
