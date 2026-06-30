package software.aoc.day08;

public record JunctionBoxPair(int index1, int index2, long squaredDistance) implements Comparable<JunctionBoxPair> {
    @Override
    public int compareTo(JunctionBoxPair other) {
        int cmp = Long.compare(this.squaredDistance, other.squaredDistance);
        if (cmp != 0) return cmp;
        cmp = Integer.compare(this.index1, other.index1);
        if (cmp != 0) return cmp;
        return Integer.compare(this.index2, other.index2);
    }
}
