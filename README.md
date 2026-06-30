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

## Configuración de los Inputs

Los archivos de entrada (inputs) de los puzzles no se suben al repositorio. Para poder ejecutar los tests y soluciones, debes crear y guardar tus inputs en la ruta `src/test/resources/` con los siguientes nombres:

| Día | Directorio | Nombre del Archivo |
| :--- | :--- | :--- |
| **Día 01** | `src/test/resources/d01-a/` | `orders.txt` |
| **Día 02** | `src/test/resources/d02-a/` | `id.txt` |
| **Día 03** | `src/test/resources/d03-a/` | `battery.txt` |
| **Día 04** | `src/test/resources/d04-a/` | `input.txt` |
| **Día 05** | `src/test/resources/d05-a/` | `input.txt` |
| **Día 06** | `src/test/resources/d06-a/` | `input.txt` |
| **Día 07** | `src/test/resources/d07-a/` | `input.txt` |
| **Día 08** | `src/test/resources/d08-a/` | `input.txt` |
| **Día 09** | `src/test/resources/d09-a/` | `input.txt` |
| **Día 10** | `src/test/resources/d10-a/` | `input.txt` |
| **Día 11** | `src/test/resources/d11-a/` | `input.txt` |
| **Día 12** | `src/test/resources/d12-a/` | `input.txt` |

*(Nota: Las soluciones de la parte A y parte B de cada día comparten el mismo archivo de entrada ubicado en el directorio de la parte A `dXX-a`).*
