package software.aoc.day12;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day12.a.Day12ASolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Pruebas de integración para verificar el resolvedor de la Parte A del Día 12.
 */
public class Day12ATest {

    private final String EXAMPLE =
            "0:\n" +
            "###\n" +
            "##.\n" +
            "##.\n" +
            "\n" +
            "1:\n" +
            "###\n" +
            "##.\n" +
            ".##\n" +
            "\n" +
            "2:\n" +
            ".##\n" +
            "###\n" +
            "##.\n" +
            "\n" +
            "3:\n" +
            "##.\n" +
            "###\n" +
            "##.\n" +
            "\n" +
            "4:\n" +
            "###\n" +
            "#..\n" +
            "###\n" +
            "\n" +
            "5:\n" +
            "###\n" +
            ".#.\n" +
            "###\n" +
            "\n" +
            "4x4: 0 0 0 0 2 0\n" +
            "12x5: 1 0 1 0 2 2\n" +
            "12x5: 1 0 1 0 3 2";

    private final SafeSolver solver = new Day12ASolver();

    @Test
    public void testExampleCase() {
        long result = solver.solve(EXAMPLE);
        assertEquals("El número de regiones válidas en el ejemplo es incorrecto.", 2L, result);
    }

    @Test
    public void testFullInput() {
        String fullInput = TestInputReader.readInput(12, 'a', "input.txt");
        assertNotNull("El archivo input.txt no debe estar vacío.", fullInput);
        long result = solver.solve(fullInput);
        System.out.println("==========================================");
        System.out.println(">>> Solución Día 12 Parte A: " + result);
        System.out.println("==========================================");
    }
}
