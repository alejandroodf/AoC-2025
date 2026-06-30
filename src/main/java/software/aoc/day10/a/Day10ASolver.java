package software.aoc.day10.a;

import software.aoc.SafeSolver;
import software.aoc.day10.*;

import java.util.List;

public class Day10ASolver implements SafeSolver {
    private final MachineReader reader;
    private final MachineSolver solver;

    public Day10ASolver() {
        this.reader = new StringMachineReader();
        this.solver = new BfsMachineSolver();
    }

    @Override
    public long solve(String input) {
        List<Machine> machines = reader.readMachines(input);
        long totalPresses = 0;
        for (Machine machine : machines) {
            long presses = solver.minPresses(machine);
            if (presses == -1) {
                throw new IllegalStateException("Máquina no configurable: " + machine);
            }
            totalPresses += presses;
        }
        return totalPresses;
    }
}
