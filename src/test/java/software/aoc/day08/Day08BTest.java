package software.aoc.day08;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day08.b.Day08BSolver;
import util.TestInputReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day08BTest {
    private final String EXAMPLE =
            "162,817,812\n" +
            "57,618,57\n" +
            "906,360,560\n" +
            "592,479,940\n" +
            "352,342,300\n" +
            "466,668,158\n" +
            "542,29,236\n" +
            "431,825,988\n" +
            "739,650,466\n" +
            "52,470,668\n" +
            "216,146,977\n" +
            "819,987,18\n" +
            "117,168,530\n" +
            "805,96,715\n" +
            "346,949,466\n" +
            "970,615,88\n" +
            "941,993,340\n" +
            "862,61,35\n" +
            "984,92,344\n" +
            "425,690,689";

    private final SafeSolver solver = new Day08BSolver();

    @Test
    public void testExampleCase() {
        long result = solver.solve(EXAMPLE);
        assertEquals("El producto de las coordenadas X del último enlace del ejemplo es incorrecto.", 25272L, result);
    }

    @Test
    public void testFullInput() {
        String fullInput = TestInputReader.readInput(8, 'a', "input.txt");
        assertNotNull("El input de input.txt no debe ser nulo.", fullInput);
        long result = solver.solve(fullInput);
        System.out.println(">>> Solución Día 8 Parte B: " + result);
    }
}
