package software.aoc.day01;

import java.util.List;

public class Day01Solver {
    private final RotationReader reader;
    private final RotationScorer scorer;

    public Day01Solver(RotationReader reader, RotationScorer scorer) {
        this.reader = reader;
        this.scorer = scorer;
    }

    public long solve(String input) {
        List<Rotation> rotations = reader.readRotations(input);
        Dial dial = new Dial(50); // El dial inicia en 50 por especificación

        long totalPoints = 0;
        for (Rotation rotation : rotations) {
            totalPoints += scorer.score(dial.position(), rotation);
            dial = dial.rotate(rotation);
        }
        return totalPoints;
    }
}
