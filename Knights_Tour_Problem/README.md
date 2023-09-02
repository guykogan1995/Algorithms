# Knight's Tour Problem Solver

This Java program provides a solution to the Knight's Tour Problem, where a knight on a chessboard must visit every cell exactly once. The program offers three different algorithms to solve the problem: exhaustive search with backtracking, heuristic approach one, and heuristic approach two (Warnsdorff's Heuristic).

## How to Use

To run the program, execute the following command:

```
java KnightTour <option> <n> <x> <y>
```

- `<option>`: Choose the search algorithm option:
  - `0`: Exhaustive Search with Backtracking.
  - `1`: Heuristic Approach One.
  - `2`: Heuristic Approach Two (Warnsdorff's Heuristic).
  
- `<n>`: Size of the chessboard (n x n).

- `<x>`: x-coordinate of the starting position.

- `<y>`: y-coordinate of the starting position.

## Classes

### KnightTour.java

This class serves as the main driver of the application. It validates the command-line arguments, creates a `KnightBoard` object, and initiates the search algorithm based on the chosen option. Finally, it displays the resulting chessboard.

### KnightBoard.java

This class represents the chessboard and contains the algorithms for moving the knight. It provides methods to calculate possible moves for each position, display the chessboard, check if the board traversal is complete, and perform different search algorithms.

#### Constructor

- `KnightBoard(int size)`: Initializes the chessboard with the specified size and sets up the necessary variables.

#### Methods

- `calculateMovesForBoard()`: Calculates all possible moves for each position on the chessboard.
- `calculateMovesForPosition()`: Recalculates possible moves after a backtracking operation.
- `showBoard()`: Prints the chessboard with the current state of visited positions.
- `isBoardDone()`: Checks if the board traversal is complete.
- `moveBoard(Position p, int option)`: Implements the search algorithm based on the chosen option. It starts from the specified position and updates the chessboard accordingly.

### Position.java

This class represents a position on the chessboard. It contains information about the coordinates, value, visit status, and possible moves for each position.

#### Constructor

- `Position(int x, int y)`: Initializes a position with the specified coordinates.

#### Methods

- `calculatePossibleMoves(int size)`: Calculates the possible moves from the current position based on the chessboard size.
- Getters and setters for the position's coordinates, value, and visit status.

## Example Usage

To solve the Knight's Tour Problem using the heuristic approach two with a chessboard of size 8x8 and starting position (2, 3), run the following command:

```
java KnightTour 2 8 2 3
```

## Results
````
java KnightTour.java 0 7 1 1
The total number of moves is 254727174
21 46 41  2 23 26  9 
40  1 22 27 10  3 24 
47 20 45 42 25  8 11 
44 39 34 19 28 15  4 
33 48 43 36  7 12 29 
38 35 18 31 14  5 16 
49 32 37  6 17 30 13 

java KnightTour.java 1 7 1 1
The total number of moves is 810
21 44 11  2 23 36 13 
10  1 22 43 12  3 24 
45 20  9 40 35 14 37 
 8 33 42 49 38 25  4 
19 46 39 34 41 28 15 
32  7 48 17 30  5 26 
47 18 31  6 27 16 29 

java KnightTour.java 2 7 1 1
The total number of moves is 172
49 44 11  2 29 42 13 
10  1 46 43 12  3 28 
45 48  9 30 41 14 35 
 8 25 40 47 36 27  4 
39 22 31 26 17 34 15 
24  7 20 37 32  5 18 
21 38 23  6 19 16 33 

java KnightTour.java 0 6 0 0
The total number of moves is 5422
 1 28 33 20  3 26
34 19  2 27  8 13 
29 32 21 12 25  4 
18 35 30  7 14  9 
31 22 11 16  5 24 
36 17  6 23 10 15 

java KnightTour.java 1 6 0 0 

The total number of moves is 47
 1 22  9 26  3 24 
10 35  2 23 16 27 
21  8 31 36 25  4 
32 11 34 17 28 15 
 7 20 13 30  5 18 
12 33  6 19 14 29

java KnightTour.java 2 6 0 0

The total number of moves is 36
 1 32  9 18  3 34 
10 19  2 33 28 17 
31  8 29 16 35  4 
20 11 36 27 24 15 
 7 30 13 22  5 26 
12 21  6 25 14 23 

java KnightTour.java 0 6 1 1

The total number of moves is 2254
25 32 21  2 23  8 
20  1 24  9 14  3 
33 26 31 22  7 10 
30 19 28 13  4 15 
27 34 17  6 11 36 
18 29 12 35 16  5 

java KnightTour.java 1 6 1 1

The total number of moves is 1529
25 22 13  2 35 32 
12  1 24 33 14  3 
23 26 21 36 31 34 
 8 11 30 17  4 15 
27 20  9  6 29 18 
10  7 28 19 16  5 

java KnightTour.java 2 6 1 1

The total number of moves is 36
25 32 11  2 19 34 
10  1 26 33 12  3 
31 24  9 18 35 20 
 8 17 36 27  4 13 
23 30 15  6 21 28 
16  7 22 29 14  5 

java KnightTour.java 0 4 0 0

The total number of moves is 2223
No solution found!
 
java KnightTour.java 1 4 0 0

The total number of moves is 2223
No solution found!

java KnightTour.java 2 4 0 0

The total number of moves is 2223
No solution found!
````

## Notes

- The program uses a modular design to separate the driver class and the chessboard class, allowing for easy extension and modification of the algorithms.

- The exhaustive search algorithm may have high time complexity for larger chessboard sizes, while the heuristic approaches provide faster solutions.

- The program utilizes the concept of object-oriented programming to represent the chessboard and its positions as objects, encapsulating related functionality.

- The program has been implemented and tested using Java version 8 or higher.

## Credits

- Author: Guy Kogan
- Instructor: Jyh Haw Yeh
- Class: CS421 - Algorithms
