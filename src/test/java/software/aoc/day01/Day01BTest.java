package software.aoc.day01;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day01.b.Day01BSolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day01BTest {
    private final String EXAMPLE_INPUT = "L68 L30 R48 L5 R60 L55 L1 L99 R14 L82";
    private final SafeSolver solver = new Day01BSolver();

    @Test
    public void testExampleCase_ShouldReturn6() {
        long password = solver.solve(EXAMPLE_INPUT);
        assertEquals("El ejemplo del puzzle (Parte B) debe producir un password de 6.", 6, password);
    }

    @Test
    public void testFullInput_ShouldReturn5847() {
        String fullInput = TestInputReader.readInput(1, 'a', "orders.txt");
        assertNotNull(fullInput, "El input de orders.txt no debe ser nulo.");
        long result = solver.solve(fullInput);
        System.out.println("Solución Día 1 Parte B: " + result);
        assertEquals("El password final usando el método 0x434C49434B es incorrecto.", 5847, result);
    }
}
