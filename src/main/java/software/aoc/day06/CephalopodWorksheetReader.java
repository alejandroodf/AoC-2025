package software.aoc.day06;

import java.util.ArrayList;
import java.util.List;

public class CephalopodWorksheetReader implements WorksheetReader {
    @Override
    public Worksheet readWorksheet(String input) {
        if (input == null || input.isBlank()) {
            return new Worksheet(List.of());
        }

        String[] lines = input.split("\\r?\\n", -1);
        List<String> validLinesList = new ArrayList<>();
        for (String line : lines) {
            if (!line.isEmpty() || !validLinesList.isEmpty()) {
                validLinesList.add(line);
            }
        }
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

        String[] paddedLines = new String[numLines];
        for (int i = 0; i < numLines; i++) {
            StringBuilder sb = new StringBuilder(validLinesList.get(i));
            while (sb.length() < maxLen) {
                sb.append(' ');
            }
            paddedLines[i] = sb.toString();
        }

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

        List<Problem> problems = new ArrayList<>();
        for (ColumnRange range : ranges) {
            List<Long> numbers = new ArrayList<>();
            // Procesamos las columnas de derecha a izquierda
            for (int c = range.endCol(); c >= range.startCol(); c--) {
                StringBuilder numBuilder = new StringBuilder();
                for (int r = 0; r < numLines - 1; r++) {
                    numBuilder.append(paddedLines[r].charAt(c));
                }
                String numStr = numBuilder.toString().trim();
                if (!numStr.isEmpty()) {
                    try {
                        numbers.add(Long.parseLong(numStr));
                    } catch (NumberFormatException e) {
                        // Ignoramos si no se puede parsear
                    }
                }
            }

            // Identificamos el operador en la última línea
            String opSub = paddedLines[numLines - 1].substring(range.startCol(), range.endCol() + 1).trim();
            MathOperation operation = new AddOperation();
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
