package software.aoc.day01;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day01.a.Day01ASolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day01ATest {
    private final String EXAMPLE_INPUT = "L68 L30 R48 L5 R60 L55 L1 L99 R14 L82";
    private final SafeSolver solver = new Day01ASolver();

    @Test
    public void testExampleCase_ShouldReturn3() {
        long password = solver.solve(EXAMPLE_INPUT);
        assertEquals("El ejemplo del puzzle debe producir un password de 3.", 3, password);
    }

    @Test
    public void testFullInput_ShouldReturn995() {
        String fullInput = TestInputReader.readInput(1, 'a', "orders.txt");
        assertNotNull(fullInput, "El input del archivo orders.txt no debe ser nulo.");
        long password = solver.solve(fullInput);
        System.out.println(">>> Solución Día 1 Parte A: " + password);
        assertEquals("El resultado del puzzle real debe ser 995.", 995, password);
    }
}
