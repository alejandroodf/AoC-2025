package software.aoc.day06.b;

import software.aoc.SafeSolver;
import software.aoc.day06.CephalopodWorksheetReader;
import software.aoc.day06.Worksheet;
import software.aoc.day06.WorksheetReader;

public class Day06BSolver implements SafeSolver {
    private final WorksheetReader reader;

    public Day06BSolver() {
        this.reader = new CephalopodWorksheetReader();
    }

    @Override
    public long solve(String input) {
        Worksheet worksheet = reader.readWorksheet(input);
        return worksheet.solveAll();
    }
}
