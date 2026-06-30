package software.aoc.day12;

import java.util.ArrayList;
import java.util.List;

/**
 * Solucionador del problema de empaquetado de cuadrícula mediante el algoritmo
 * Dancing Links (DLX) de Knuth para Cobertura Exacta con columnas secundarias.
 *
 * Las columnas primarias corresponden a los regalos que deben colocarse exactamente una vez.
 * Las columnas secundarias corresponden a las celdas de la cuadrícula, que pueden ocuparse
 * como máximo una vez (o quedar vacías).
 */
public class DancingLinksSolver {

    private static class DLXNode {
        DLXNode left, right, up, down;
        ColNode col;

        DLXNode() {
            left = right = up = down = this;
        }
    }

    private static class ColNode extends DLXNode {
        int id;
        int size;
        boolean isPrimary;

        ColNode(int id, boolean isPrimary) {
            super();
            this.id = id;
            this.size = 0;
            this.isPrimary = isPrimary;
            this.col = this;
        }
    }

    private ColNode root;
    private ColNode[] allHeaders;
    private boolean solved;

    /**
     * Determina si la lista de regalos indicada puede caber en una cuadrícula de W x H
     * sin solapamientos, permitiendo cualquier rotación o reflexión.
     */
    public boolean canFit(int W, int H, List<Shape> shapes, int[] quantities) {
        List<Integer> presentIds = new ArrayList<>();
        int totalAreaRequired = 0;

        for (int i = 0; i < quantities.length; i++) {
            if (i >= shapes.size()) break;
            for (int q = 0; q < quantities[i]; q++) {
                presentIds.add(i);
                totalAreaRequired += shapes.get(i).size;
            }
        }

        // Verificación rápida: si la suma de áreas de los regalos excede el área del tablero
        if (totalAreaRequired > W * H) {
            return false;
        }

        int numPrimary = presentIds.size();
        int numSecondary = W * H;

        buildDLXStructure(numPrimary, numSecondary);

        // Pre-generamos las orientaciones únicas
        List<List<Shape>> shapesOrientations = new ArrayList<>();
        for (Shape s : shapes) {
            shapesOrientations.add(s.getUniqueOrientations());
        }

        // Construimos las opciones (filas de la matriz)
        for (int k = 0; k < numPrimary; k++) {
            int shapeId = presentIds.get(k);
            List<Shape> orientations = shapesOrientations.get(shapeId);

            for (Shape var : orientations) {
                for (int r = 0; r <= H - var.rows; r++) {
                    for (int c = 0; c <= W - var.cols; c++) {
                        List<Integer> colIndices = new ArrayList<>();
                        // La columna primaria para el regalo 'k'
                        colIndices.add(k);

                        // Las columnas secundarias para las celdas del tablero
                        for (int i = 0; i < var.rows; i++) {
                            for (int j = 0; j < var.cols; j++) {
                                if (var.get(i, j)) {
                                    int cellIndex = (r + i) * W + (c + j);
                                    colIndices.add(numPrimary + cellIndex);
                                }
                            }
                        }
                        addRow(colIndices);
                    }
                }
            }
        }

        solved = false;
        search();
        return solved;
    }

    private void buildDLXStructure(int numPrimary, int numSecondary) {
        root = new ColNode(-1, true);
        allHeaders = new ColNode[numPrimary + numSecondary];

        // Crear columnas primarias y enlazarlas en la lista principal
        ColNode prev = root;
        for (int i = 0; i < numPrimary; i++) {
            ColNode col = new ColNode(i, true);
            allHeaders[i] = col;
            prev.right = col;
            col.left = prev;
            prev = col;
        }
        prev.right = root;
        root.left = prev;

        // Crear columnas secundarias (no enlazadas horizontalmente al root)
        for (int i = 0; i < numSecondary; i++) {
            ColNode col = new ColNode(numPrimary + i, false);
            allHeaders[numPrimary + i] = col;
            col.left = col;
            col.right = col;
        }
    }

    private void addRow(List<Integer> colIndices) {
        DLXNode first = null;
        for (int colIdx : colIndices) {
            ColNode h = allHeaders[colIdx];
            DLXNode node = new DLXNode();
            node.col = h;

            // Enlazar verticalmente al final de la columna h
            node.up = h.up;
            node.down = h;
            h.up.down = node;
            h.up = node;
            h.size++;

            // Enlazar horizontalmente en la fila
            if (first == null) {
                first = node;
            } else {
                node.left = first.left;
                node.right = first;
                first.left.right = node;
                first.left = node;
            }
        }
    }

    private void search() {
        if (solved) return;
        if (root.right == root) {
            solved = true;
            return;
        }

        ColNode c = chooseColumn();
        if (c == null || c.size == 0) {
            return;
        }

        cover(c);
        for (DLXNode r = c.down; r != c; r = r.down) {
            for (DLXNode j = r.right; j != r; j = j.right) {
                cover(j.col);
            }
            search();
            if (solved) return;
            for (DLXNode j = r.left; j != r; j = j.left) {
                uncover(j.col);
            }
        }
        uncover(c);
    }

    private ColNode chooseColumn() {
        int minSize = Integer.MAX_VALUE;
        ColNode best = null;
        for (DLXNode curr = root.right; curr != root; curr = curr.right) {
            ColNode c = (ColNode) curr;
            if (c.size < minSize) {
                minSize = c.size;
                best = c;
                if (minSize == 0) return best;
            }
        }
        return best;
    }

    private void cover(ColNode c) {
        if (c.isPrimary) {
            c.right.left = c.left;
            c.left.right = c.right;
        }
        for (DLXNode i = c.down; i != c; i = i.down) {
            for (DLXNode j = i.right; j != i; j = j.right) {
                j.down.up = j.up;
                j.up.down = j.down;
                j.col.size--;
            }
        }
    }

    private void uncover(ColNode c) {
        for (DLXNode i = c.up; i != c; i = i.up) {
            for (DLXNode j = i.left; j != i; j = j.left) {
                j.col.size++;
                j.down.up = j;
                j.up.down = j;
            }
        }
        if (c.isPrimary) {
            c.right.left = c;
            c.left.right = c;
        }
    }
}
