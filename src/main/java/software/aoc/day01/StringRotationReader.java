package software.aoc.day01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringRotationReader implements RotationReader {
    @Override
    public List<Rotation> readRotations(String input) {
        if (input == null || input.isBlank()) {
            return List.of();
        }
        return Arrays.stream(input.trim().split("\\s+"))
            .filter(token -> !token.isBlank())
            .map(token -> {
                Direction dir = Direction.fromChar(token.charAt(0));
                int distance = Integer.parseInt(token.substring(1));
                return new Rotation(dir, distance);
            })
            .collect(Collectors.toList());
    }
}
