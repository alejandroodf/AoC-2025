package software.aoc.day10;

import org.junit.Test;
import software.aoc.SafeSolver;
import software.aoc.day10.b.Day10BSolver;
import util.TestInputReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Day10BTest {
    private final String EXAMPLE =
            "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}\n" +
            "[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}\n" +
            "[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}";

    private final SafeSolver solver = new Day10BSolver();

    @Test
    public void testExampleCase() {
        long result = solver.solve(EXAMPLE);
        assertEquals("El mínimo total de pulsaciones en modo joltage en el ejemplo es incorrecto.", 33L, result);
    }

    @Test
    public void testFullInput() {
        String fullInput = TestInputReader.readInput(10, 'a', "input.txt");
        assertNotNull("El input de input.txt no debe ser nulo.", fullInput);
        long result = solver.solve(fullInput);
        System.out.println(">>> Solución Día 10: " + result);
    }

    @Test
    public void testAnalyzeInput() {
        String fullInput = TestInputReader.readInput(10, 'a', "input.txt");
        List<Machine> machines = new StringMachineReader().readMachines(fullInput);
        System.out.println(">>> Cantidad de máquinas: " + machines.size());
        int maxB = 0, maxC = 0, maxT = 0;
        for (int i = 0; i < machines.size(); i++) {
            Machine m = machines.get(i);
            int B = m.buttonMasks().size();
            int C = m.joltageRequirements().size();
            int maxTi = m.joltageRequirements().stream().max(Integer::compareTo).orElse(0);
            maxB = Math.max(maxB, B);
            maxC = Math.max(maxC, C);
            maxT = Math.max(maxT, maxTi);
            if (i < 10) {
                System.out.println(String.format("Máquina %d: B=%d, C=%d, maxT=%d, targets=%s", i, B, C, maxTi, m.joltageRequirements()));
            }
        }
        System.out.println(String.format("Máximos globales: B=%d, C=%d, maxT=%d", maxB, maxC, maxT));
    }
}
