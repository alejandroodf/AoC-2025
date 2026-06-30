package software.aoc.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringMachineReader implements MachineReader {
    @Override
    public List<Machine> readMachines(String input) {
        List<Machine> machines = new ArrayList<>();
        if (input == null || input.isBlank()) {
            return machines;
        }

        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String trimmed = scanner.nextLine().trim();
                if (trimmed.isEmpty()) continue;

                // 1. Extraer diagrama de luces [ ... ]
                int startBracket = trimmed.indexOf('[');
                int endBracket = trimmed.indexOf(']');
                if (startBracket == -1 || endBracket == -1) continue;

                String diagram = trimmed.substring(startBracket + 1, endBracket);
                int numLights = diagram.length();
                int targetMask = 0;
                for (int i = 0; i < numLights; i++) {
                    if (diagram.charAt(i) == '#') {
                        targetMask |= (1 << i);
                    }
                }

                // 2. Extraer combinaciones de botones ( ... )
                List<Integer> buttonMasks = new ArrayList<>();
                int idx = endBracket + 1;
                while (true) {
                    int startParen = trimmed.indexOf('(', idx);
                    int endParen = trimmed.indexOf(')', idx);
                    if (startParen == -1 || endParen == -1) {
                        break;
                    }
                    String content = trimmed.substring(startParen + 1, endParen).trim();
                    int buttonMask = 0;
                    if (!content.isEmpty()) {
                        int sIdx = 0;
                        while (sIdx < content.length()) {
                            int comma = content.indexOf(',', sIdx);
                            if (comma == -1) {
                                buttonMask |= (1 << Integer.parseInt(content.substring(sIdx).trim()));
                                break;
                            } else {
                                buttonMask |= (1 << Integer.parseInt(content.substring(sIdx, comma).trim()));
                                sIdx = comma + 1;
                            }
                        }
                    }
                    buttonMasks.add(buttonMask);
                    idx = endParen + 1;
                }

                // 3. Extraer requerimientos de joltage { ... }
                List<Integer> joltageRequirements = new ArrayList<>();
                int startBrace = trimmed.indexOf('{', idx);
                int endBrace = trimmed.indexOf('}', idx);
                if (startBrace != -1 && endBrace != -1) {
                    String content = trimmed.substring(startBrace + 1, endBrace).trim();
                    if (!content.isEmpty()) {
                        int sIdx = 0;
                        while (sIdx < content.length()) {
                            int comma = content.indexOf(',', sIdx);
                            if (comma == -1) {
                                joltageRequirements.add(Integer.parseInt(content.substring(sIdx).trim()));
                                break;
                            } else {
                                joltageRequirements.add(Integer.parseInt(content.substring(sIdx, comma).trim()));
                                sIdx = comma + 1;
                            }
                        }
                    }
                }

                machines.add(new Machine(numLights, targetMask, buttonMasks, joltageRequirements));
            }
        }

        return machines;
    }
}
