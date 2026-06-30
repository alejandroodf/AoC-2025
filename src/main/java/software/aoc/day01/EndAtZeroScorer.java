package software.aoc.day01;

public class EndAtZeroScorer implements RotationScorer {
    private static final int LIMIT = 100;

    @Override
    public long score(int startPosition, Rotation rotation) {
        int delta = rotation.distance() % LIMIT;
        int endPos;
        if (rotation.direction() == Direction.RIGHT) {
            endPos = (startPosition + delta) % LIMIT;
        } else {
            endPos = (startPosition - delta + LIMIT) % LIMIT;
        }
        return (endPos == 0) ? 1 : 0;
    }
}
