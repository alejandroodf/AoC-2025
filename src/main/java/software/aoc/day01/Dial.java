package software.aoc.day01;

public record Dial(int position) {
    private static final int LIMIT = 100;

    public Dial rotate(Rotation rotation) {
        int delta = rotation.distance() % LIMIT;
        int newPosition;
        if (rotation.direction() == Direction.RIGHT) {
            newPosition = (this.position + delta) % LIMIT;
        } else {
            newPosition = (this.position - delta + LIMIT) % LIMIT;
        }
        return new Dial(newPosition);
    }
}
