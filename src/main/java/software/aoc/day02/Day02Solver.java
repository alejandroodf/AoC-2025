package software.aoc.day02;

import java.util.List;
import java.util.stream.LongStream;

public class Day02Solver {
    private final IdRangeReader reader;
    private final IdValidator validator;

    public Day02Solver(IdRangeReader reader, IdValidator validator) {
        this.reader = reader;
        this.validator = validator;
    }

    public long solve(String input) {
        List<IdRange> ranges = reader.readRanges(input);
        return ranges.stream()
            .flatMapToLong(range -> LongStream.rangeClosed(range.start(), range.end()))
            .filter(validator::isInvalid)
            .sum();
    }
}
