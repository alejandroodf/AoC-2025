package software.aoc.day02;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class Day02Tests {

    @Test
    public void testStringIdRangeReader() {
        IdRangeReader reader = new StringIdRangeReader();
        List<IdRange> ranges = reader.readRanges("11-22,95-115");
        assertEquals(2, ranges.size());
        assertEquals(new IdRange(11, 22), ranges.get(0));
        assertEquals(new IdRange(95, 115), ranges.get(1));
    }

    @Test
    public void testStringIdRangeReaderEmpty() {
        IdRangeReader reader = new StringIdRangeReader();
        assertTrue(reader.readRanges("").isEmpty());
        assertTrue(reader.readRanges(null).isEmpty());
    }

    @Test
    public void testRepeatedIdValidator() {
        IdValidator validator = RepeatedIdValidator.getInstance();
        
        // Casos inválidos (deben retornar true en isInvalid)
        assertTrue(validator.isInvalid(55));
        assertTrue(validator.isInvalid(6464));
        assertTrue(validator.isInvalid(123123));
        assertTrue(validator.isInvalid(1188511885L));

        // Casos válidos (deben retornar false en isInvalid)
        assertFalse(validator.isInvalid(5));
        assertFalse(validator.isInvalid(123));
        assertFalse(validator.isInvalid(123124));
        assertFalse(validator.isInvalid(101));
    }

    @Test
    public void testMultipleRepeatedIdValidator() {
        IdValidator validator = MultipleRepeatedIdValidator.getInstance();

        // Casos inválidos (deben retornar true en isInvalid)
        assertTrue(validator.isInvalid(12341234));
        assertTrue(validator.isInvalid(123123123));
        assertTrue(validator.isInvalid(1212121212));
        assertTrue(validator.isInvalid(1111111));
        assertTrue(validator.isInvalid(11));
        assertTrue(validator.isInvalid(99));
        assertTrue(validator.isInvalid(111));

        // Casos válidos (deben retornar false en isInvalid)
        assertFalse(validator.isInvalid(5));
        assertFalse(validator.isInvalid(123));
        assertFalse(validator.isInvalid(101));
        assertFalse(validator.isInvalid(123124));
    }
}
