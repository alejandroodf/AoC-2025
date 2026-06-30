package software.aoc.day08;

import java.util.List;

public interface CircuitSimulator {
    long simulate(List<JunctionBox> boxes, int connectionLimit);
}
