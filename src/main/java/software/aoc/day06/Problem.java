package software.aoc.day06;

import java.util.List;

public record Problem(List<Long> numbers, MathOperation operation) {
    public long solve() {
        return operation.apply(numbers);
    }
}
