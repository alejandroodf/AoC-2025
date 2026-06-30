package software.aoc.day10.b;

import software.aoc.SafeSolver;
import software.aoc.day10.*;

import java.util.List;

public class Day10BSolver implements SafeSolver {
    private final MachineReader reader;
    private final JoltageMachineSolver solver;

    public Day10BSolver() {
        this.reader = new StringMachineReader();
        this.solver = new JoltageMachineSolver();
    }

    @Override
    public long solve(String input) {
        List<Machine> machines = reader.readMachines(input);
        long totalPresses = 0;
        for (Machine machine : machines) {
            long presses = solver.solveJoltage(machine, machine.joltageRequirements());
            if (presses == -1) {
                throw new IllegalStateException("Máquina no configurable en joltage: " + machine);
            }
            totalPresses += presses;
        }
        return totalPresses;
    }
}
