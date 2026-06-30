package software.aoc.day06.a;

import software.aoc.SafeSolver;
import software.aoc.day06.StringWorksheetReader;
import software.aoc.day06.Worksheet;
import software.aoc.day06.WorksheetReader;

public class Day06ASolver implements SafeSolver {
    private final WorksheetReader reader;

    public Day06ASolver() {
        this.reader = new StringWorksheetReader();
    }

    @Override
    public long solve(String input) {
        Worksheet worksheet = reader.readWorksheet(input);
        return worksheet.solveAll();
    }
}
