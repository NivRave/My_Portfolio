/**
	Sudoku solver using backtracking 
	@file Puzzle.h 
	@author Niv Rave 
	@version final 01/12/2020 
*/

#ifndef PUZZLEH
#define PUZZLEH
#include <stdio.h>
#include <iostream>
using namespace std;

#define EMPTY_CELL 0		//Empty/Unassigned cell = 0 
#define N 9					//Puzzle size N*N 
#define BOX_SIZE sqrt(N)	//Box size sqrt(N)*sqrt(N) 


/**
	Puzzle class used to solve the sudoku 

	It stores the sudoku and the solution state - solved or unsolved. 
	The class methods use a backtracking algorithm to force values 
	and solve the puzzle. 
*/
class Puzzle {
private:
	int puzzle[N][N];		//Sudoku puzzle size N*N
	bool solvedPuzzle=false;//true when sudoku is solved
public:
	/**
		A constructor. 
		Copies arr values to the object's puzzle, than 
		uses printPuzzle() method to print the original puzzle, 
		than tries to solve the puzzle and prints the solution 
		@param arr an integer array sized N*N 
	*/	
	Puzzle(int arr[][N]);
	/**
		A destructor
	*/
	~Puzzle();
	/**
		Print method that prints the puzzle formatted as a sudoku 
	*/
	void printPuzzle();
	/**
		Print method that is activated when the puzzle is solved 
		it prints a message and the solve puzzle 
	*/
	void printSolved();
	/**
		Assisting print metheds, prints a line made of '---' 
		for ease of reading and box seperation 
	*/
	void printLine();
	/**
		The main method that contains the algorithm 
		it finds an unassigned square and uses backtracking 
		by calling the assisting methods: 
		checkColumn, checkRow, checkBox, solveSquare 
		and recursively calling itself until a solution is found 
	*/
	bool solvePuzzle();
	/**
		An assisting method that loops through the squares in a column 
		and checks if a value (1-9) is valid to be assigned to 
		another square in that column 
		@param column an int referring to the column to be searched 
		@param value an int that is the value 1-9 to be checked in the column 
		@return true if the value is assigned to a square in the column 
	*/
	bool checkColumn(int column, int value);
	/**
		An assisting method that loops through the squares in a row 
		and checks if a value (1-9) is valid to be assigned to 
		another square in that row 
		@param row an int referring to the column to be searched 
		@param value an int that is the value 1-9 to be checked in the row 
		@return true if the value is assigned to a square in the row 
	*/
	bool checkRow(int row, int value);
	/**
		An assisting method that loops through the squares in a box (sqrt(N)*sqrt(N)) 
		and checks if a value (1-9) is valid to be assigned to 
		another square in that box 
		@param firstRow an int referring to the first row in the box 
		@param firstColumn an int referring to the first column in the box 
		@param value an int that is the value 1-9 to be checked in the box 
		@return true if the value is assigned to a square in the box 
	*/
	bool checkBox(int firstRow, int firstColumn, int value);
	/**
		An assisting method that loops through the puzzle 
		and searches for the next empty square 
		when such a square is found it updates the 2 input arguments 
		int &row and int &column to be used later for solving 
		@param &row a reference to an integer, 
		updates when the method stops with the current row 
		@param &column a reference to an integer, 
		updates when the method stops with the current column 
		@return true if an empty square is found 
	*/
	bool getEmptySquare(int &row, int &column);
	/**
		An assisting method that tries to solve a square, checking 
		if the value is valid through a row and column 
		It calls the checkColumn, checkRow and checkBox methods. 
		@param row an integer to be sent to the solving methods 
		containing the square's row 
		@param column an integer to be sent to the solving methods 
		containing the square's column 
		@param value an integer to be sent to the solving methods 
		containing the value to be checked 
		@return true if the value is valid in the square 
	*/
	bool solveSquare(int row, int column, int value);
};

#endif