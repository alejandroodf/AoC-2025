package software.aoc.day07;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day07.b.Day07BSolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day07BTest {
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

    private final SafeSolver solver = new Day07BSolver();

    @Test
    public void testExampleCase() {
        long result = solver.solve(EXAMPLE);
        assertEquals("El número de líneas temporales activas en el ejemplo es incorrecto.", 40L, result);
    }

    @Test
    public void testFullInput() {
        String fullInput = TestInputReader.readInput(7, 'a', "input.txt");
        assertNotNull("El input de input.txt no debe ser nulo.", fullInput);
        long result = solver.solve(fullInput);
        System.out.println(">>> Solución Día 7 Parte B: " + result);
    }
}
