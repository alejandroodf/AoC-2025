package software.aoc.day10;

import java.util.*;

public class BfsMachineSolver implements MachineSolver {
    @Override
    public long minPresses(Machine machine) {
        int target = machine.targetMask();
        if (target == 0) return 0;

        // Eliminar duplicados para optimizar el factor de ramificación
        Set<Integer> uniqueButtons = new HashSet<>(machine.buttonMasks());
        List<Integer> buttons = new ArrayList<>(uniqueButtons);

        int L = machine.numLights();
        if (L <= 20) {
            return solveArrayBfs(target, buttons, L);
        } else {
            return solveMapBfs(target, buttons);
        }
    }

    private long solveArrayBfs(int target, List<Integer> buttons, int L) {
        int size = 1 << L;
        int[] dist = new int[size];
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        dist[0] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int d = dist[curr];
            if (curr == target) {
                return d;
            }

            for (int button : buttons) {
                int next = curr ^ button;
                if (next < size && dist[next] == -1) {
                    dist[next] = d + 1;
                    queue.add(next);
                }
            }
        }
        return -1;
    }

    private long solveMapBfs(int target, List<Integer> buttons) {
        Map<Integer, Integer> dist = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(0);
        dist.put(0, 0);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int d = dist.get(curr);
            if (curr == target) {
                return d;
            }

            for (int button : buttons) {
                int next = curr ^ button;
                if (!dist.containsKey(next)) {
                    dist.put(next, d + 1);
                    queue.add(next);
                }
            }
        }
        return -1;
    }
}
