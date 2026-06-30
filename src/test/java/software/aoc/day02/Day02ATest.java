package software.aoc.day02;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day02.a.Day02ASolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day02ATest {
    private final String EXAMPLE_INPUT = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
            "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
            "824824821-824824827,2121212118-2121212124";

    private final SafeSolver solver = new Day02ASolver();

    @Test
    public void testExampleCase() {
        long result = solver.solve(EXAMPLE_INPUT);
        assertEquals("La suma de IDs inválidos del ejemplo es incorrecta.", 1227775554L, result);
    }

    @Test
    public void testFullInput() {
        String fullInput = TestInputReader.readInput(2, 'a', "id.txt");
        assertNotNull("El input del archivo id.txt no debe ser nulo.", fullInput);
        long result = solver.solve(fullInput);
        System.out.println(">>> Solución Día 2 Parte A: " + result);
        assertEquals("El resultado del puzzle real es incorrecto.", 24043483400L, result);
    }
}
