#include "Puzzle.h"

//Puzzle object constructor
Puzzle::Puzzle(int arr[][N]) {
    for (int row = 0; row < N; row++) {
        for (int column = 0; column < N; column++) {
            puzzle[row][column] = arr[row][column];
        }
    }
    cout << "Original unsolved puzzle:\n";
    printPuzzle();
    cout << "\n\n";
    if ((solvedPuzzle=solvePuzzle())==true) printSolved();
    else cout << "No solution exists";
}

//Puzzle object destructor
Puzzle::~Puzzle()
{
    cout << "\n\nProgram has ended";
}

//Print the current puzzle method formatted as a sudoku puzzle
void Puzzle::printPuzzle()
{
    printLine();
    cout << "\n";
    for (int row = 0; row < N; row++) {
        for (int column = 0; column < N; column++) {
            if (column%3==0)
                cout << "| ";
            cout << puzzle[row][column] << " ";
        }
        cout << "| ";
        if (row == 2 || row == 5) {
            cout << "\n";
            printLine();
        }
        cout << endl;
    }
    printLine();
}

//Print a message and the solved puzzle
void Puzzle::printSolved()
{
    cout << "The puzzle has been solved! Solution below:\n\n";
    printPuzzle();
}

//Prints a line of '---' for seperation
void Puzzle::printLine()
{
    cout << "-";
    for (int i = 0; i < N - 1; i++)
        cout << "---";
}

//Solves the puzzle while checking all of
//the possible combinations until a complete
//solution is achieved
bool Puzzle::solvePuzzle()
{
    int row, column;
    if (!getEmptySquare(row, column))
        return true;
    for (int value = 1; value <= 9; value++) {     
        if (solveSquare(row, column, value)) {
            puzzle[row][column] = value;
            if (solvePuzzle())
                return true;
            puzzle[row][column] = 0;
        }
    }
    return false;
}

//Checks if a value exists in a column,
//returns true if it does
bool Puzzle::checkColumn(int column, int value)
{
    for (int row = 0; row < N; row++)
        if (puzzle[row][column] == value)
            return true;
    return false;
}

//Checks if a value exists in a row,
//returns true if it does
bool Puzzle::checkRow(int row, int value)
{
    for (int column = 0; column < N; column++)
        if (puzzle[row][column] == value)
            return true;
    return false;
}

//Checks if a value exists in a box,
//returns true if it does
bool Puzzle::checkBox(int firstRow, int firstColumn, int value)
{
    for (int row = 0; row < 3; row++)
        for (int column = 0; column < 3; column++)
            if (puzzle[row + firstRow][column + firstColumn] == value)
                return true;
    return false;
}

//Finds and assigns the coordinates
//of an the next empty square
bool Puzzle::getEmptySquare(int &row, int &column)
{
    for (row = 0; row < N; row++)
        for (column = 0; column < N; column++)
            if (puzzle[row][column] == 0)
                return true;
    return false;
}

//Checks if a value is valid for a square
bool Puzzle::solveSquare(int row, int column, int value)
{
    return (!checkRow(row, value) && !checkColumn(column, value) && !checkBox(row - row % 3, column - column % 3, value));
}