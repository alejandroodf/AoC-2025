package software.aoc.day08;

import java.util.ArrayList;
import java.util.List;

public class StringJunctionBoxReader implements JunctionBoxReader {
    @Override
    public List<JunctionBox> readJunctionBoxes(String input) {
        List<JunctionBox> list = new ArrayList<>();
        if (input == null || input.isBlank()) {
            return list;
        }
        try (java.util.Scanner scanner = new java.util.Scanner(input)) {
            while (scanner.hasNextLine()) {
                String trimmed = scanner.nextLine().trim();
                if (!trimmed.isEmpty()) {
                    int comma1 = trimmed.indexOf(',');
                    if (comma1 != -1) {
                        int comma2 = trimmed.indexOf(',', comma1 + 1);
                        if (comma2 != -1) {
                            long x = Long.parseLong(trimmed.substring(0, comma1).trim());
                            long y = Long.parseLong(trimmed.substring(comma1 + 1, comma2).trim());
                            long z = Long.parseLong(trimmed.substring(comma2 + 1).trim());
                            list.add(new JunctionBox(x, y, z));
                        }
                    }
                }
            }
        }
        return list;
    }
}
