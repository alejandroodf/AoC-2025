package software.aoc.day01;

public enum Direction {
    LEFT, RIGHT;

    public static Direction fromChar(char c) {
        return switch (Character.toUpperCase(c)) {
            case 'L' -> LEFT;
            case 'R' -> RIGHT;
            default -> throw new IllegalArgumentException("Dirección desconocida: " + c);
        };
    }
}
