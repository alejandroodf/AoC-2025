package software.aoc.day09;

public record RedTile(long x, long y) {
    public long rectangleAreaWith(RedTile other) {
        return (Math.abs(this.x - other.x) + 1) * (Math.abs(this.y - other.y) + 1);
    }
}
