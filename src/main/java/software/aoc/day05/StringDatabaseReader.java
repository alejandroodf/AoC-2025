package software.aoc.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringDatabaseReader implements DatabaseReader {
    @Override
    public Database readDatabase(String input) {
        if (input == null || input.isBlank()) {
            return new Database(List.of(), List.of());
        }

        List<IngredientRange> freshRanges = new ArrayList<>();
        List<Long> availableIds = new ArrayList<>();
        
        try (Scanner scanner = new Scanner(input)) {
            boolean readingRanges = true;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                
                if (line.isEmpty()) {
                    if (!freshRanges.isEmpty()) {
                        readingRanges = false;
                    }
                    continue;
                }
                
                if (readingRanges) {
                    int dashIndex = line.indexOf('-');
                    if (dashIndex != -1) {
                        long start = Long.parseLong(line.substring(0, dashIndex));
                        long end = Long.parseLong(line.substring(dashIndex + 1));
                        freshRanges.add(new IngredientRange(start, end));
                    }
                } else {
                    availableIds.add(Long.parseLong(line));
                }
            }
        }
        
        return new Database(freshRanges, availableIds);
    }
}
