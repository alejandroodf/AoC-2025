package software.aoc.day07;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day07.a.Day07ASolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day07ATest {
    private final String EXAMPLE =
            ".......S.......\n" +
            "...............\n" +
            ".......^.......\n" +
            "...............\n" +
            "......^.^......\n" +
            "...............\n" +
            ".....^.^.^.....\n" +
            "...............\n" +
            "....^.^...^....\n" +
            "...............\n" +
            "...^.^...^.^...\n" +
            "...............\n" +
            "..^...^.....^..\n" +
            "...............\n" +
            ".^.^.^.^.^...^.\n" +
            "...............";

    private final SafeSolver solver = new Day07ASolver();

    @Test
    public void testExampleCase() {
        long result = solver.solve(EXAMPLE);
        assertEquals("El número de splits en el ejemplo es incorrecto.", 21L, result);
    }

    @Test
    public void testFullInput() {
        String fullInput = TestInputReader.readInput(7, 'a', "input.txt");
        assertNotNull("El input de input.txt no debe ser nulo.", fullInput);
        long result = solver.solve(fullInput);
        System.out.println(">>> Solución Día 7 Parte A: " + result);
    }
}
