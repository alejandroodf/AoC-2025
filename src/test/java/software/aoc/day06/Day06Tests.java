package software.aoc.day06;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class Day06Tests {
    @Test
    public void testAddOperation() {
        MathOperation op = new AddOperation();
        assertEquals(15L, op.apply(List.of(1L, 2L, 3L, 4L, 5L)));
        assertEquals(0L, op.apply(List.of()));
    }

    @Test
    public void testMultiplyOperation() {
        MathOperation op = new MultiplyOperation();
        assertEquals(120L, op.apply(List.of(1L, 2L, 3L, 4L, 5L)));
        assertEquals(0L, op.apply(List.of()));
    }

    @Test
    public void testReaderWithExample() {
        String input = "123 328  51 64 \n" +
                       " 45 64  387 23 \n" +
                       "  6 98  215 314\n" +
                       "*   +   *   +  ";
        WorksheetReader reader = new StringWorksheetReader();
        Worksheet ws = reader.readWorksheet(input);

        assertEquals(4, ws.problems().size());

        Problem p1 = ws.problems().get(0);
        assertEquals(List.of(123L, 45L, 6L), p1.numbers());
        assertTrue(p1.operation() instanceof MultiplyOperation);
        assertEquals(33210L, p1.solve());

        Problem p2 = ws.problems().get(1);
        assertEquals(List.of(328L, 64L, 98L), p2.numbers());
        assertTrue(p2.operation() instanceof AddOperation);
        assertEquals(490L, p2.solve());

        assertEquals(4277556L, ws.solveAll());
    }

    @Test
    public void testCephalopodReaderWithExample() {
        String input = "123 328  51 64 \n" +
                       " 45 64  387 23 \n" +
                       "  6 98  215 314\n" +
                       "*   +   *   +  ";
        WorksheetReader reader = new CephalopodWorksheetReader();
        Worksheet ws = reader.readWorksheet(input);

        assertEquals(4, ws.problems().size());

        Problem p1 = ws.problems().get(0); // El leftmost problem
        // 356 * 24 * 1 = 8544
        assertEquals(List.of(356L, 24L, 1L), p1.numbers());
        assertEquals(8544L, p1.solve());

        Problem p4 = ws.problems().get(3); // El rightmost problem
        // 4 + 431 + 623 = 1058
        assertEquals(List.of(4L, 431L, 623L), p4.numbers());
        assertEquals(1058L, p4.solve());

        assertEquals(3263827L, ws.solveAll());
    }
}
