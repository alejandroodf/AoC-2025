package software.aoc.day01;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class Day01Tests {

    @Test
    public void testDirectionParsing() {
        assertEquals(Direction.LEFT, Direction.fromChar('L'));
        assertEquals(Direction.LEFT, Direction.fromChar('l'));
        assertEquals(Direction.RIGHT, Direction.fromChar('R'));
        assertEquals(Direction.RIGHT, Direction.fromChar('r'));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDirectionParsingInvalid() {
        Direction.fromChar('X');
    }

    @Test
    public void testStringRotationReader() {
        RotationReader reader = new StringRotationReader();
        List<Rotation> rotations = reader.readRotations("L68 R48\tL5\nL3");
        assertEquals(4, rotations.size());
        assertEquals(new Rotation(Direction.LEFT, 68), rotations.get(0));
        assertEquals(new Rotation(Direction.RIGHT, 48), rotations.get(1));
        assertEquals(new Rotation(Direction.LEFT, 5), rotations.get(2));
        assertEquals(new Rotation(Direction.LEFT, 3), rotations.get(3));
    }

    @Test
    public void testStringRotationReaderEmpty() {
        RotationReader reader = new StringRotationReader();
        assertTrue(reader.readRotations("").isEmpty());
        assertTrue(reader.readRotations(null).isEmpty());
    }

    @Test
    public void testDialRotation() {
        Dial dial = new Dial(50);
        dial = dial.rotate(new Rotation(Direction.RIGHT, 10)); // 50 + 10 = 60
        assertEquals(60, dial.position());

        dial = dial.rotate(new Rotation(Direction.LEFT, 15)); // 60 - 15 = 45
        assertEquals(45, dial.position());

        dial = dial.rotate(new Rotation(Direction.LEFT, 50)); // 45 - 50 = -5 % 100 = 95
        assertEquals(95, dial.position());

        dial = dial.rotate(new Rotation(Direction.RIGHT, 110)); // 95 + 10 = 5
        assertEquals(5, dial.position());
    }

    @Test
    public void testEndAtZeroScorer() {
        RotationScorer scorer = new EndAtZeroScorer();
        assertEquals(1, scorer.score(90, new Rotation(Direction.RIGHT, 10)));
        assertEquals(0, scorer.score(90, new Rotation(Direction.RIGHT, 9)));
        assertEquals(1, scorer.score(10, new Rotation(Direction.LEFT, 10)));
        assertEquals(0, scorer.score(10, new Rotation(Direction.LEFT, 5)));
    }

    @Test
    public void testPassThroughZeroScorer() {
        RotationScorer scorer = new PassThroughZeroScorer();

        // 50 -> L68. Clicks a 0: 50. Distancia 68 >= 50. Puntos: 1
        assertEquals(1, scorer.score(50, new Rotation(Direction.LEFT, 68)));

        // 82 -> L30. Clicks a 0: 82. Distancia 30 < 82. Puntos: 0
        assertEquals(0, scorer.score(82, new Rotation(Direction.LEFT, 30)));

        // 52 -> R48. Clicks a 0: 48. Distancia 48 >= 48. Puntos: 1
        assertEquals(1, scorer.score(52, new Rotation(Direction.RIGHT, 48)));

        // 0 -> L5. Clicks a 0: 100. Distancia 5 < 100. Puntos: 0
        assertEquals(0, scorer.score(0, new Rotation(Direction.LEFT, 5)));

        // 0 -> L100. Clicks a 0: 100. Distancia 100 >= 100. Puntos: 1
        assertEquals(1, scorer.score(0, new Rotation(Direction.LEFT, 100)));

        // 0 -> L250. Clicks a 0: 100. Distancia 250. Puntos: 1 + (150/100) = 2
        assertEquals(2, scorer.score(0, new Rotation(Direction.LEFT, 250)));
    }
}
