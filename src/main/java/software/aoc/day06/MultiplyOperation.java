package software.aoc.day06;

import java.util.List;

public class MultiplyOperation implements MathOperation {
    @Override
    public long apply(List<Long> numbers) {
        if (numbers.isEmpty()) return 0;
        return numbers.stream().reduce(1L, (a, b) -> a * b);
    }
}
