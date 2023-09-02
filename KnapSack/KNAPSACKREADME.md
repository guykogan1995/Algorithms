# Knapsack Problem Solvers

This repository contains three Java programs that solve the classic **Knapsack Problem** using different approaches. The Knapsack Problem is a well-known optimization problem where a thief needs to decide which items to select from a given set of items, each with a value and weight, in order to maximize the total value while not exceeding a given maximum weight capacity of a knapsack.

## Programs

### 1. **KnapsackRM.java**

This program provides a solution to the Knapsack Problem using a **dynamic programming top-down recursive approach**.

- **Author:** Guy Kogan
- **Date:** 8/2/2023
- **Teacher:** Jyh Haw Yeh
- **Description:** This program calculates the optimal value that a thief can achieve while selecting items to carry in a knapsack.

#### How to Use:

To run the program, use the following command:

```bash
java KnapsackRM <N (number of items)> <W (max weight)> <w.txt (weights text file)> <v.txt (values text file)> [<debug level>]
```

- `<N>`: Number of items
- `<W>`: Maximum weight capacity of the knapsack
- `<w.txt>`: Text file containing the weights of the items
- `<v.txt>`: Text file containing the values of the items
- `[<debug level>]`: Optional parameter for debug level (0 or 1)

### 2. **KnapsackDP.java**

This program provides a solution to the Knapsack Problem using a **dynamic programming bottom-up approach**.

- **Author:** Guy Kogan
- **Date:** 8/2/2023
- **Teacher:** Jyh Haw Yeh
- **Description:** This program calculates the optimal value that a thief can achieve while selecting items to carry in a knapsack.

#### How to Use:

To run the program, use the following command:

```bash
java KnapsackDP <n> <W> <w.txt> <v.txt> [<debug level>]
```

- `<n>`: Number of items
- `<W>`: Maximum weight capacity of the knapsack
- `<w.txt>`: Text file containing the weights of the items
- `<v.txt>`: Text file containing the values of the items
- `[<debug level>]`: Optional parameter for debug level (0 or 1)

### 3. **KnapsackDPLogic.java**

This class is used by the `KnapsackDP.java` program to implement the dynamic programming bottom-up approach.

- **Author:** Guy Kogan
- **Date:** 8/2/2023
- **Teacher:** Jyh Haw Yeh
- **Description:** This class contains the logic for solving the Knapsack Problem using a dynamic programming bottom-up approach.

## How the Programs Work:

These programs implement different algorithms to solve the Knapsack Problem:

1. `KnapsackRM.java`: Implements the top-down recursive approach with dynamic programming, storing intermediate results in a table to avoid redundant calculations.

2. `KnapsackDP.java`: Implements the bottom-up dynamic programming approach, building a table iteratively to calculate the optimal value for each subproblem.

3. `KnapsackDPLogic.java`: Implements the core logic for the dynamic programming bottom-up approach used by the `KnapsackDP.java` program.

## Running the Programs:

To run any of the programs, make sure you have Java installed on your system. Open a terminal, navigate to the directory containing the programs, and follow the usage instructions provided for each program.

## Debugging and Output:

The programs offer a debug mode (0 or 1) that controls the level of output generated. Debug mode 0 provides a summary of the experiment, including the optimal solution, optimal value, and the number of table references. Debug mode 1 additionally prints the optimal value table and decision table to output files.

**Author:** Guy Kogan  
**Class:** 421 Algorithms  
**Date:** 8/2/2023  
**Teacher:** Jyh Haw Yeh