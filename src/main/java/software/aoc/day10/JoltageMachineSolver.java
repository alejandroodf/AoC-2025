package software.aoc.day10;

import java.util.*;

public class JoltageMachineSolver implements MachineSolver {
    private static final long INF = Long.MAX_VALUE / 4;

    @Override
    public long minPresses(Machine machine) {
        return solveJoltage(machine, machine.joltageRequirements());
    }

    public long solveJoltage(Machine machine, List<Integer> joltageRequirements) {
        int numButtons = machine.buttonMasks().size();
        int numCounters = joltageRequirements.size();
        if (numCounters == 0) return 0;

        List<List<Integer>> coeffs = new ArrayList<>();
        for (int mask : machine.buttonMasks()) {
            List<Integer> coeff = new ArrayList<>();
            for (int i = 0; i < numCounters; i++) {
                coeff.add((mask & (1 << i)) != 0 ? 1 : 0);
            }
            coeffs.add(coeff);
        }

        Map<List<Integer>, Map<List<Integer>, Integer>> patternCosts = buildPatterns(coeffs, numButtons, numCounters);
        Map<List<Integer>, Long> memo = new HashMap<>();
        long result = solveAux(joltageRequirements, patternCosts, memo);
        return result == INF ? -1 : result;
    }

    private long solveAux(List<Integer> goal,
                          Map<List<Integer>, Map<List<Integer>, Integer>> patternCosts,
                          Map<List<Integer>, Long> memo) {
        boolean allZero = true;
        for (int val : goal) {
            if (val != 0) {
                allZero = false;
                break;
            }
        }
        if (allZero) return 0;

        if (memo.containsKey(goal)) {
            return memo.get(goal);
        }

        List<Integer> goalParity = new ArrayList<>(goal.size());
        for (int val : goal) {
            goalParity.add(val % 2);
        }

        long minAnswer = INF;
        Map<List<Integer>, Integer> candidates = patternCosts.get(goalParity);

        if (candidates != null) {
            for (Map.Entry<List<Integer>, Integer> entry : candidates.entrySet()) {
                List<Integer> pattern = entry.getKey();
                int cost = entry.getValue();

                if (isLessOrEqual(pattern, goal)) {
                    List<Integer> newGoal = new ArrayList<>(goal.size());
                    for (int i = 0; i < goal.size(); i++) {
                        newGoal.add((goal.get(i) - pattern.get(i)) / 2);
                    }

                    long recurse = solveAux(newGoal, patternCosts, memo);
                    if (recurse != INF) {
                        long currentScore = cost + (2 * recurse);
                        if (currentScore < minAnswer) {
                            minAnswer = currentScore;
                        }
                    }
                }
            }
        }

        memo.put(goal, minAnswer);
        return minAnswer;
    }

    private Map<List<Integer>, Map<List<Integer>, Integer>> buildPatterns(List<List<Integer>> coeffs, int numButtons, int numCounters) {
        Map<List<Integer>, Map<List<Integer>, Integer>> out = new HashMap<>();
        int limit = 1 << numButtons;
        for (int mask = 0; mask < limit; mask++) {
            List<Integer> currentPattern = new ArrayList<>(Collections.nCopies(numCounters, 0));
            int buttonsPressed = 0;

            for (int i = 0; i < numButtons; i++) {
                if ((mask & (1 << i)) != 0) {
                    buttonsPressed++;
                    List<Integer> vec = coeffs.get(i);
                    for (int k = 0; k < numCounters; k++) {
                        currentPattern.set(k, currentPattern.get(k) + vec.get(k));
                    }
                }
            }

            // Calcular paridad
            List<Integer> parity = new ArrayList<>(numCounters);
            for (int val : currentPattern) {
                parity.add(val % 2);
            }

            out.putIfAbsent(parity, new HashMap<>());
            Map<List<Integer>, Integer> innerMap = out.get(parity);

            if (!innerMap.containsKey(currentPattern)) {
                innerMap.put(currentPattern, buttonsPressed);
            } else {
                int existing = innerMap.get(currentPattern);
                if (buttonsPressed < existing) {
                    innerMap.put(currentPattern, buttonsPressed);
                }
            }
        }
        return out;
    }

    private boolean isLessOrEqual(List<Integer> pattern, List<Integer> goal) {
        for (int i = 0; i < pattern.size(); i++) {
            if (pattern.get(i) > goal.get(i)) {
                return false;
            }
        }
        return true;
    }
}
