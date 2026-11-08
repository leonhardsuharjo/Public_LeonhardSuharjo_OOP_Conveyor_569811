# Conveyor Rule Simulator (OOP Project 2025/2026)

A discrete-time, tick-driven Java terminal simulation of an industrial conveyor belt factory line. Packages travel through stations (weight checking and sensor detection) while subject to dynamic, user-defined rules configured at runtime.

Built as the final project for the Object Oriented Programming course (BSc in Computer Science, Università degli Studi di Messina).

- **Author:** Leonhard Satria Suharjo (ID: 569811)
- **Professor:** Dr. Salvatore Distefano

---

## Overview

The simulator models packages travelling on an 18-cell path across a 12x3 grid. As packages move tick-by-tick from the start cell (`I`) to the exit (`O`), they pass a weight station (`W`) and a stochastic sensor (`S`). The user configures factory thresholds, loads packages into a bounded waiting queue, and constructs custom rules to inspect, warn, or reject items dynamically.

### Key Features
- **7 Modular Packages (35 source files):** Strict separation between application entry, terminal I/O, domain models, belt nodes, rule engine, simulation lifecycle, and custom exceptions.
- **Rule Engine:** Build dynamic composite rules (`Condition` + `Action`) from the terminal at runtime without modifying source code.
- **Pure Java Standard Library:** Built exclusively with `java.lang` and `java.util` (no third-party dependencies).

---

## Package Structure

```text
src/
├── app/          # Main entry point and runtime setup wiring
├── console/      # Scanner input validation and ASCII grid renderer
├── exceptions/   # Checked exception hierarchy (SimulationException, OverloadException, etc.)
├── model/        # PackageItem hierarchy, PackageStatus enum, Trackable/Processable interfaces
├── nodes/        # ConveyorNode, WeightStation, SensorNode, ConveyorLoop, PathStep, GridPosition
├── rules/        # Rule engine, Condition/Action interfaces, RuleBuilder
└── sim/          # Simulation loop coordinator, SimulationContext, Statistics, LimitedQueue<T>
```

---

## Requirements

- Java Development Kit (JDK) 11 or higher
- Terminal / Command Prompt or an IDE like Eclipse / IntelliJ

---

## How to Compile & Run

### Using Terminal / CLI

1. Clone the repository:
   ```bash
   git clone <repo-url>
   cd <repo-folder>
   ```

2. Compile all source files into an `out` directory:
   ```bash
   javac -d out $(find src -name "*.java")
   ```
   *(On Windows Command Prompt or PowerShell, use `javac -d out src/app/*.java src/console/*.java src/exceptions/*.java src/model/*.java src/nodes/*.java src/rules/*.java src/sim/*.java`)*

3. Run the application:
   ```bash
   java -cp out app.Main
   ```

### Using Eclipse

1. `File` > `Import...` > `General` > `Existing Projects into Workspace`.
2. Select the repository root directory.
3. Locate `src/app/Main.java`, right-click > `Run As` > `Java Application`.

---

## OOP Concepts Demonstrated

- **Encapsulation & Information Hiding:** State bundled with domain logic; all instance fields are `private` with explicit mutability/scope constraints and read-only views for rendering.
- **Inheritance & Abstraction:** Abstract superclasses (`PackageItem`, `ConveyorNode`) and targeted interfaces (`StationHandler`, `Condition`, `Action`, `Trackable`, `Processable`).
- **Multityping & Subtyping:** Subtype references handled cleanly without manual downcasting.
- **Polymorphism:** Demonstrates inclusion (late binding in rules/nodes), parametric (generic bounded queue `LimitedQueue<T>`), overloading (constructors), and numeric coercion.
- **Exception Handling:** Custom checked exception hierarchy catching failure scenarios (overloads, sensor faults, invalid input) with dedicated recovery paths.

---

## License & Notes
Developed for academic assessment at the University of Messina, Under MIT License 
