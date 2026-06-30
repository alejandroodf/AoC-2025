# Advent of Code 2025 — Soluciones en Java

Repositorio de soluciones para el [Advent of Code 2025](https://adventofcode.com/2025), implementadas en Java con Maven.

## Estructura del proyecto

```
Aoc25/
├── src/
│   ├── main/java/software/aoc/
│   │   ├── SafeSolver.java          # Interfaz común para todos los resolvedores
│   │   ├── day01/                   # Un paquete por día
│   │   │   ├── a/Day01ASolver.java
│   │   │   └── b/Day01BSolver.java
│   │   └── ...
│   └── test/java/software/aoc/
│       └── dayXX/                   # Tests unitarios e integración
└── src/test/resources/
    └── dXX-a/input.txt              # Inputs de cada puzzle
```

Cada día sigue el mismo patrón:
- `DayXXASolver.java` → Parte A
- `DayXXBSolver.java` → Parte B
- Ambos implementan `SafeSolver` (`long solve(String input)`)
