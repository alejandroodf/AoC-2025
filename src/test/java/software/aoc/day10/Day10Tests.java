package software.aoc.day10;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class Day10Tests {
    private final String EXAMPLE =
            "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}\n" +
            "[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}\n" +
            "[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}";

    @Test
    public void testParser() {
        List<Machine> machines = new StringMachineReader().readMachines(EXAMPLE);
        assertEquals(3, machines.size());

        // Primera máquina
        Machine m1 = machines.get(0);
        assertEquals(4, m1.numLights());
        assertEquals(6, m1.targetMask()); // 0110 -> 6
        assertEquals(6, m1.buttonMasks().size());
        assertEquals(List.of(3, 5, 4, 7), m1.joltageRequirements());

        // Segunda máquina
        Machine m2 = machines.get(1);
        assertEquals(5, m2.numLights());
        assertEquals(8, m2.targetMask()); // 00010 -> 8
        assertEquals(5, m2.buttonMasks().size());
        assertEquals(List.of(7, 5, 12, 7, 2), m2.joltageRequirements());
    }

    @Test
    public void testSolverIndividual() {
        List<Machine> machines = new StringMachineReader().readMachines(EXAMPLE);
        MachineSolver solver = new BfsMachineSolver();

        assertEquals(2, solver.minPresses(machines.get(0)));
        assertEquals(3, solver.minPresses(machines.get(1)));
        assertEquals(2, solver.minPresses(machines.get(2)));
    }
}
