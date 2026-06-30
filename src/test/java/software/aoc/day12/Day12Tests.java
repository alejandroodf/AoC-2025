package software.aoc.day12;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para validar el correcto funcionamiento de las formas geométricas (Shape)
 * y la detección/deduplicación de sus variantes de rotación y reflexión.
 */
public class Day12Tests {

    @Test
    public void testSymmetricShapeOrientations() {
        // Cuadrado de 2x2 compuesto en su totalidad por '#'
        boolean[][] squareGrid = {
            {true, true},
            {true, true}
        };
        Shape square = new Shape(squareGrid);
        assertEquals(4, square.size);

        List<Shape> orientations = square.getUniqueOrientations();
        // Al ser totalmente simétrico, todas las rotaciones y flips son idénticos.
        // Debe producir exactamente 1 orientación única.
        assertEquals(1, orientations.size());
    }

    @Test
    public void testAsymmetricShapeOrientations() {
        // Forma en L de 2x2:
        // ##
        // #.
        boolean[][] lGrid = {
            {true, true},
            {true, false}
        };
        Shape lShape = new Shape(lGrid);
        assertEquals(3, lShape.size);

        List<Shape> orientations = lShape.getUniqueOrientations();
        // Una L de 2x2 tiene 4 orientaciones posibles en total en el plano 2D.
        assertEquals(4, orientations.size());
    }

    @Test
    public void testRotationsAndFlips() {
        // Forma asimétrica de 3x2:
        // ##.
        // #..
        boolean[][] grid = {
            {true, true, false},
            {true, false, false}
        };
        Shape original = new Shape(grid);

        Shape rotated = original.rotate90();
        assertEquals(3, rotated.rows);
        assertEquals(2, rotated.cols);

        Shape flippedH = original.flipHorizontal();
        assertEquals(2, flippedH.rows);
        assertEquals(3, flippedH.cols);
        // FlippedH fila 0 debe ser .##
        assertFalse(flippedH.get(0, 0));
        assertTrue(flippedH.get(0, 1));
        assertTrue(flippedH.get(0, 2));
    }
}
