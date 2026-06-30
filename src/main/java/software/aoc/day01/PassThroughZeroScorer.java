package software.aoc.day01;

public class PassThroughZeroScorer implements RotationScorer {
    private static final int LIMIT = 100;

    @Override
    public long score(int startPosition, Rotation rotation) {
        int distance = rotation.distance();
        if (distance <= 0) {
            return 0;
        }

        boolean isRight = (rotation.direction() == Direction.RIGHT);
        int clicksToFirstZero;

        if (isRight) {
            clicksToFirstZero = (startPosition == 0) ? LIMIT : LIMIT - startPosition;
        } else {
            clicksToFirstZero = (startPosition == 0) ? LIMIT : startPosition;
        }

        if (distance < clicksToFirstZero) {
            return 0;
        }

        int remainingDistance = distance - clicksToFirstZero;
        return 1 + (remainingDistance / LIMIT);
    }
}
