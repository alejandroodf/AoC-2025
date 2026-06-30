package software.aoc.day07;

import org.junit.Test;
import static org.junit.Assert.*;

public class Day07Tests {
    private final String EXAMPLE =
            ".......S.......\n" +
            "...............\n" +
            ".......^.......\n" +
            "...............\n" +
            "......^.^......\n" +
            "...............\n" +
            ".....^.^.^.....\n" +
            "...............\n" +
            "....^.^...^....\n" +
            "...............\n" +
            "...^.^...^.^...\n" +
            "...............\n" +
            "..^...^.....^..\n" +
            "...............\n" +
            ".^.^.^.^.^...^.\n" +
            "...............";

    @Test
    public void testReader() {
        ManifoldReader reader = new StringManifoldReader();
        Manifold manifold = reader.readManifold(EXAMPLE);
        assertEquals(15, manifold.width());
        assertEquals(16, manifold.height());
        assertEquals('S', manifold.charAt(0, 7));
        assertEquals('^', manifold.charAt(2, 7));
        assertEquals('.', manifold.charAt(1, 7));
    }

    @Test
    public void testSimulationExample() {
        ManifoldReader reader = new StringManifoldReader();
        Manifold manifold = reader.readManifold(EXAMPLE);
        TachyonSimulator simulator = new StandardTachyonSimulator();
        long splits = simulator.simulate(manifold);
        assertEquals(21L, splits);
    }

    @Test
    public void testQuantumSimulationExample() {
        ManifoldReader reader = new StringManifoldReader();
        Manifold manifold = reader.readManifold(EXAMPLE);
        TachyonSimulator simulator = new QuantumTachyonSimulator();
        long timelines = simulator.simulate(manifold);
        assertEquals(40L, timelines);
    }
}
