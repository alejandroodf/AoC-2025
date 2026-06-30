package software.aoc.day06;

import java.util.List;

public class AddOperation implements MathOperation {
    @Override
    public long apply(List<Long> numbers) {
        return numbers.stream().mapToLong(Long::longValue).sum();
    }
}
