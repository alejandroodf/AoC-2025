package software.aoc.day06;

import java.util.ArrayList;
import java.util.List;

public class StringWorksheetReader implements WorksheetReader {
    @Override
    public Worksheet readWorksheet(String input) {
        if (input == null || input.isBlank()) {
            return new Worksheet(List.of());
        }

        String[] lines = input.split("\\r?\\n", -1);
        // Filtramos líneas completamente vacías al final si las hubiera
        List<String> validLinesList = new ArrayList<>();
        for (String line : lines) {
            if (!line.isEmpty() || !validLinesList.isEmpty()) {
                validLinesList.add(line);
            }
        }
        // Eliminar líneas vacías al final
        while (!validLinesList.isEmpty() && validLinesList.get(validLinesList.size() - 1).isEmpty()) {
            validLinesList.remove(validLinesList.size() - 1);
        }

        int numLines = validLinesList.size();
        if (numLines < 2) {
            return new Worksheet(List.of());
        }

        int maxLen = 0;
        for (String line : validLinesList) {
            maxLen = Math.max(maxLen, line.length());
        }

        // Normalizamos todas las líneas al mismo ancho rellenando con espacios
        String[] paddedLines = new String[numLines];
        for (int i = 0; i < numLines; i++) {
            StringBuilder sb = new StringBuilder(validLinesList.get(i));
            while (sb.length() < maxLen) {
                sb.append(' ');
            }
            paddedLines[i] = sb.toString();
        }

        // Identificamos las columnas que contienen solo espacios
        boolean[] spaceCols = new boolean[maxLen];
        for (int c = 0; c < maxLen; c++) {
            boolean allSpaces = true;
            for (int r = 0; r < numLines; r++) {
                if (paddedLines[r].charAt(c) != ' ') {
                    allSpaces = false;
                    break;
                }
            }
            spaceCols[c] = allSpaces;
        }

        // Encontramos los rangos contiguos de columnas de problemas
        List<ColumnRange> ranges = new ArrayList<>();
        int start = -1;
        for (int c = 0; c < maxLen; c++) {
            if (!spaceCols[c]) {
                if (start == -1) {
                    start = c;
                }
            } else {
                if (start != -1) {
                    ranges.add(new ColumnRange(start, c - 1));
                    start = -1;
                }
            }
        }
        if (start != -1) {
            ranges.add(new ColumnRange(start, maxLen - 1));
        }

        // Construimos los problemas a partir de los rangos
        List<Problem> problems = new ArrayList<>();
        for (ColumnRange range : ranges) {
            List<Long> numbers = new ArrayList<>();
            // Las primeras (numLines - 1) líneas contienen los números
            for (int r = 0; r < numLines - 1; r++) {
                String sub = paddedLines[r].substring(range.startCol(), range.endCol() + 1).trim();
                if (!sub.isEmpty()) {
                    try {
                        numbers.add(Long.parseLong(sub));
                    } catch (NumberFormatException e) {
                        // Ignoramos si no se puede parsear
                    }
                }
            }

            // La última línea contiene el operador
            String opSub = paddedLines[numLines - 1].substring(range.startCol(), range.endCol() + 1).trim();
            MathOperation operation = new AddOperation(); // por defecto suma si no se encuentra operador válido
            if (opSub.contains("*")) {
                operation = new MultiplyOperation();
            } else if (opSub.contains("+")) {
                operation = new AddOperation();
            }

            problems.add(new Problem(numbers, operation));
        }

        return new Worksheet(problems);
    }
}
