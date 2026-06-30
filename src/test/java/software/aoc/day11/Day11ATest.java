package software.aoc.day11;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day11.a.Day11ASolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day11ATest {
    private final String EXAMPLE =
            "aaa: you hhh\n" +
            "you: bbb ccc\n" +
            "bbb: ddd eee\n" +
            "ccc: ddd eee fff\n" +
            "ddd: ggg\n" +
            "eee: out\n" +
            "fff: out\n" +
            "ggg: out\n" +
            "hhh: ccc fff iii\n" +
            "iii: out";

    private final SafeSolver solver = new Day11ASolver();

    @Test
    public void testExampleCase() {
        long result = solver.solve(EXAMPLE);
        assertEquals("El número total de caminos en el ejemplo es incorrecto.", 5L, result);
    }

    @Test
    public void testFullInput() {
        String fullInput = TestInputReader.readInput(11, 'a', "input.txt");
        assertNotNull("El input de input.txt no debe ser nulo.", fullInput);
        long result = solver.solve(fullInput);
        System.out.println(">>> Solución Día 11 Parte A: " + result);
    }
}
