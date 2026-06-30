package software.aoc.day02;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day02.b.Day02BSolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day02BTest {
    private final String EXAMPLE_INPUT = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
            "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
            "824824821-824824827,2121212118-2121212124";

    private final SafeSolver solver = new Day02BSolver();

    @Test
    public void testExampleCase() {
        long result = solver.solve(EXAMPLE_INPUT);
        assertEquals("La suma de IDs inválidos del ejemplo es incorrecta (Parte B).", 4174379265L, result);
    }

    @Test
    public void testFullInput() {
        String fullInput = TestInputReader.readInput(2, 'a', "id.txt");
        assertNotNull("El input del archivo id.txt no debe ser nulo.", fullInput);
        long result = solver.solve(fullInput);
        System.out.println(">>> Solución Día 2 Parte B: " + result);
        assertEquals("El resultado del puzzle real es incorrecto.", 38262920235L, result);
    }
}
