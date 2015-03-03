//Matthew vollkommer csci 1730 project 1 cotterell
//MineSweeper definition. This file represents the MineSweeper's public
//interface  without revealing the implementations of the MineSweeper's
//member functions, which are defined in MineSweeper.cpp

#include <string> // class MineSweeper uses C++ standard string class
#include <iostream> //class MineSweeper uses C++ standard iostream class
#include <cstdlib> //class MineSweeper uses C++ standard library
#include <vector> //class MineSweeper uses C++ standard vector class
#include <sstream> //class MineSweeper uses C++ standard sstream class

class MineSweeper
{

	bool gameOver; //is the gameOver?
	
	int columns; //columns

	int rows; //rows

	int score; //the score of the game

	int round; //the current round of the game

	int mines; //mines
	
	std::vector<std::vector<bool> > mineBoard; //mine location

	std::vector<std::vector<std::string> > gameBoard; //holds the blanks, numbers, and other characters for each square
	
	void sizeError(int row, int column); //displays the size error message

	void printGameWon();	// prints the game won info

	void noFog(); ///for bonus credit, reveals mine locations to player

	void printGameQuit(); // prints the game quit info

	void printHelp(); //prints the help info

	void printCommandError(); // prints the error info

	void printGameOver(); //prints the game over info

	void printTitle(); //prints the title

	void display(); //displays the number of rounds completed and displays the game board.

	void prompt(); //displays the prompt and gets user input; calls other functions accordingly.

	void mark(int, int); //marks a square as definitely having a mine, updating the game state accordingly.

	void guess(int, int); //marks a square as potentially having a mine, updating the game state accordingly.

	void reveal(int, int); //reveals a square, updating the game state accordingly

	bool isInBounds(int, int); //returns true if the square is in the game board, false otherwise.

	void updateScore(); //updates the score
	
	void checkGameWon(); //checks if the game is won

public:
	
	explicit MineSweeper(int row, int column, int mine, std:: vector< std::vector<int> > locateMines); // constructor to initialize the MineSweeper
	
	void run(); //sets up a game loop, looping the display and prompt functions
	
};
//end MineSweeper class


