package software.aoc.day08;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class Day08Tests {
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

    @Test
    public void testDistance() {
        JunctionBox a = new JunctionBox(0, 0, 0);
        JunctionBox b = new JunctionBox(1, 2, 3);
        assertEquals("La distancia euclídea al cuadrado calculada es incorrecta.", 14L, a.squaredDistanceTo(b));
    }

    @Test
    public void testDisjointSetUnion() {
        DisjointSetUnion dsu = new DisjointSetUnion(5);
        assertTrue("La primera unión debería retornar verdadero (no estaban conectados).", dsu.union(0, 1));
        assertTrue("La segunda unión debería retornar verdadero (no estaban conectados).", dsu.union(2, 3));
        assertTrue("La tercera unión debería retornar verdadero (no estaban conectados).", dsu.union(1, 2));
        assertFalse("La unión de nodos ya conectados debería retornar falso.", dsu.union(0, 3)); // ya conectados
        assertEquals("La cantidad de grupos disjuntos generados no coincide.", 2, dsu.getSizes().size()); // un grupo de 4, otro de 1
    }

    @Test
    public void testSimulationExample() {
        List<JunctionBox> boxes = new StringJunctionBoxReader().readJunctionBoxes(EXAMPLE);
        CircuitSimulator simulator = new GreedyCircuitSimulator();
        long result = simulator.simulate(boxes, 10);
        assertEquals("El multiplicador de circuitos grandes en GreedyCircuitSimulator falló.", 40L, result);
    }

    @Test
    public void testMstSimulationExample() {
        List<JunctionBox> boxes = new StringJunctionBoxReader().readJunctionBoxes(EXAMPLE);
        CircuitSimulator simulator = new MstCircuitSimulator();
        long result = simulator.simulate(boxes, 0);
        assertEquals("El simulador MST calculó incorrectamente las coordenadas del último enlace.", 25272L, result);
    }
}
