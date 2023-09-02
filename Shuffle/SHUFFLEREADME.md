# Shuffle String Algorithms

This repository contains four Java programs that implement algorithms for solving the "Shuffle-ness" problem. Given three strings X, Y, and Z, the goal is to determine whether Z is a valid shuffle of X and Y while maintaining the left-to-right order of characters from each string.

## Programs Overview

### 1. ShuffleDP (Dynamic Programming - Bottom-Up Approach)

**Description:** This program uses a bottom-up dynamic programming approach to solve the Shuffle-ness problem.

**Main Class:** `ShuffleDP`

**Usage:** `java ShuffleDP <X> <Y> <Z> [<debug level>]`

**Inputs:**
- `X`, `Y`: Strings representing input sequences.
- `Z`: String to be checked for shuffle-ness.
- `debug level`: Optional parameter (0 for summary output, 1 for detailed output).

### 2. ShuffleRM (Dynamic Programming - Top-Down Recursion with Memorization Approach)

**Description:** This program uses a top-down recursive approach with memorization to solve the Shuffle-ness problem.

**Main Class:** `ShuffleRM`

**Usage:** `java ShuffleRM <X> <Y> <Z> [<debug level>]`

**Inputs:**
- `X`, `Y`: Strings representing input sequences.
- `Z`: String to be checked for shuffle-ness.
- `debug level`: Optional parameter (0 for summary output, 1 for detailed output).

### 3. MatrixManipulation (Utility Class for Dynamic Programming)

**Description:** This utility class contains common methods used by both `ShuffleDP`.

**Main Class:** `MatrixManipulation`

**Methods:**
- `isShuffle(X, Y, Z, debug)`: Checks if Z is a shuffle of X and Y using dynamic programming.
- `printTable(X, Y, table)`: Prints the DP table to the console.
- `ConditionalAlgo(Y, Z, table, i, j)`: Helper method to eliminate code redundancy.

### 4. MatrixManipulationRM (Utility Class for Top-Down Recursion with Memorization)

**Description:** This utility class contains common methods used by `ShuffleRM`.

**Main Class:** `MatrixManipulationRM`

**Methods:**
- `ShuffleRM(X, Y, Z, debug)`: Constructor for initializing the recursive solution with memorization.
- `ShuffleRMSet(debug)`: Initiates the recursive solution and outputs the result.
- `ShuffleRMSolve(a, b)`: Recursive method to solve the Shuffle-ness problem.
- `displayMatrix()`: Generates a formatted string representation of the memoization matrix.

## How to Run

1. Make sure you have Java installed on your system.
2. Compile the Java files using the `javac` command:
   ```
   javac *.java
   ```
3. Run the desired program with appropriate command-line arguments:
   ```
   java ShuffleDP <X> <Y> <Z> [<debug level>]
   java ShuffleRM <X> <Y> <Z> [<debug level>]
   ```
   - Replace `<X>`, `<Y>`, and `<Z>` with your input strings.
   - `<debug level>` is optional and can be `0` (summary) or `1` (detailed).

## Output

The programs will output whether Z is a shuffle of X and Y and the number of table references made during the computation.

If you choose the debug mode (`debug level = 1`), the detailed output will also include the DP table (for `ShuffleDP`) or the memoization matrix (for `ShuffleRM`).

Additionally, if debug mode is enabled, the programs will generate text files named `ShuffleDP-Table.txt` and `ShuffleRM-Table.txt` that contain the debug information.

## Author

These programs were created by Guy Kogan as part of the 421 Algorithms class, taught by Jyh Haw Yeh. The programs implement different algorithms to solve the Shuffle-ness problem using dynamic programming and top-down recursion with memorization.