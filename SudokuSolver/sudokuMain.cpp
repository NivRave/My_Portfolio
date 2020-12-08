/**
	Sudoku solver using backtracking
	@file sudokuMain.cpp
	@author Niv Rave
	@version final 01/12/2020
*/

#include "Puzzle.h"

/**
	sudokuMain.cpp is the main file containing the main() method
	and executing the program.
	The program uses a backtracking algorithm to force values and
	solve any N*N sized valid sudoku puzzle.
	It accepts a formatted sudoku as an integer array and creates
	a new Puzzle object that accepts the array as an argument
	and solves the puzzle, than deleting the object and freeing
	the allocated memory.
*/

void main(){
	/**This sudoku puzzle was created in 2012
	by the Finnish mathematician Arto Inkala
	and it is claimed to be the "World's Hardest Sudoku".
	*/
	int array[N][N] =
	{ {8, 0, 0, 0, 0, 0, 0, 0, 0},
	{0, 0, 3, 6, 0, 0, 0, 0, 0},
	{0, 7, 0, 0, 9, 0, 2, 0, 0},
	{0, 5, 0, 0, 0, 7, 0, 0, 0},
	{0, 0, 0, 0, 4, 5, 7, 0, 0},
	{0, 0, 0, 1, 0, 0, 0, 3, 0},
	{0, 0, 1, 0, 0, 0, 0, 6, 8},
	{0, 0, 8, 5, 0, 0, 0, 1, 0},
	{0, 9, 0, 0, 0, 0, 4, 0, 0} };

	Puzzle *p = new Puzzle(array); //New Puzzle object with the input array as argument

	delete p;
}