package software.aoc.day09;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class StringRedTileReader implements RedTileReader {
    @Override
    public List<RedTile> readRedTiles(String input) {
        List<RedTile> list = new ArrayList<>();
        if (input == null || input.isBlank()) {
            return list;
        }
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String trimmed = scanner.nextLine().trim();
                if (!trimmed.isEmpty()) {
                    int comma = trimmed.indexOf(',');
                    if (comma != -1) {
                        long x = Long.parseLong(trimmed.substring(0, comma).trim());
                        long y = Long.parseLong(trimmed.substring(comma + 1).trim());
                        list.add(new RedTile(x, y));
                    }
                }
            }
        }
        return list;
    }
}
