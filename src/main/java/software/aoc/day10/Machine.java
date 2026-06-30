package software.aoc.day10;

import java.util.List;

public record Machine(int numLights, int targetMask, List<Integer> buttonMasks, List<Integer> joltageRequirements) {}
