//Matthew vollkommer csci 1730 project 1 cotterell
//MineSweeper Class
#include <string> // class MineSweeper uses C++ standard string class
#include <iostream> //class MineSweeper uses C++ standard iostream class
#include <cstdlib> //class MineSweeper uses C++ standard library
#include <vector> //class MineSweeper uses C++ standard vector class
#include <sstream> //class MineSweeper uses C++ standard sstream class
#include "MineSweeper.h" //minesweeper header file

using namespace std;


	int mineCounter; //number of adjacent mines

	int columns; //columns

	int rows; //rows

	int mines; //mines

	int score; //the score of the game

	int round; //the current round of the game

	vector<vector<bool> > mineBoard; //mine location

	vector<vector<string> > gameBoard; //holds the blanks, numbers, and other characters for each square

	bool gameOver;//is the gameover?
	
	bool nofog; //is there nofog?


	/**
	 *@Constructor
	 *@param Row  the number of rows
	 *@param Column the number of columns
	 *@param Mines the number of mines
	 *@param locateMines the location of the mines
	 */
	MineSweeper::MineSweeper(int row, int column, int mine,
		
		 vector< vector<int> > locateMines) {

		 //are there less than 10 rows and columns?
		if (row <= 0 || column <= 0 || row > 10 || column > 10) {

		//yes tell user and end program
			cout
					<< "\n ಠ_ಠ	says, \"Cannot create a mine field with that many rows and/or columns!\" \n"
					<< endl;

			
			exit(0);

		}

		//initialize
		columns = column;
		rows = row;
		mines = mine;	
		nofog = false;
		mineCounter = 0;
		round = 0;
		score = 0;
		gameOver = false;
		
		//debugging
		//cout<< mines << " mines" <<endl;
		//cout<< columns << " columns"<<endl;
		//cout<< rows << " rows"<<endl;
		//exit(0);
		
		mineBoard.resize(rows);
		
		//initialize the mineBoard to false at all locations
		for (int rowCounter = 0; rowCounter < rows; rowCounter++) {
	
			mineBoard[rowCounter].resize(columns);

			
			for (int columnCounter = 0; columnCounter < columns; columnCounter++) {

			
			
				mineBoard[rowCounter][columnCounter] = false;

			}
			
		}
		
	
		
		//put true in mineBoard where there are mines
		for (int counter = 0; counter < mine; counter++) {
		
			mineBoard[locateMines[counter][0]][locateMines[counter][1]] =
					true;

		}
		
		//initialize the gameBoard
		gameBoard.resize(row);

		for (int x = 0; x < row; x++) {

		gameBoard [x].resize(column);
		
			for (int y = 0; y < column; y++)

				gameBoard[x][y] = " ";

		}

	}


	/**
	*checks to see if the game has been won
	*sets the gameOver variable
	*@return void
	*
	*/
	void MineSweeper::checkGameWon(){
		
		int marks = 0;
		
		int correctGuesses = 0;
		
		//loop through the rows
		for(int rowCounter = 0; rowCounter < rows; rowCounter++){
		
			//loop through the columns
			for(int columnCounter = 0; columnCounter <columns; columnCounter++){
		
				//if there is a mark, add to marks
				if(gameBoard[rowCounter][columnCounter] == "?"){
					marks++;
					break;//cant win if there is a mark, break loop
					
					//if there is a guess on a mine, increment correct guesses
				}else if(gameBoard[rowCounter][columnCounter] == "F" && mineBoard[rowCounter][columnCounter] == true){
					correctGuesses++;
				}
			}
		}
		
		//if the there are as many correct guesses as mines and no marks, you have won
		if(correctGuesses == mines && marks == 0){
			printGameWon();
			gameOver = true;
		}
	
	}
	/**
	 *	prints the game won screen. includes the ending score
	 * sets gameOver to true
	 *	@param score the score of the game
	 *	@return	void
	 */

	void MineSweeper::printGameWon() // prints the game won info
	{
		cout << "\n" << " ░░░░░░░░░▄░░░░░░░░░░░░░░▄░░░░ \"So Doge\"			"
				<< endl;
		cout << " ░░░░░░░░▌▒█░░░░░░░░░░░▄▀▒▌░░░						" << endl;
		cout << " ░░░░░░░░▌▒▒█░░░░░░░░▄▀▒▒▒▐░░░ \"Such Score\"		"
				<< endl;
		cout << " ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐░░░						" << endl;
		cout << " ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐░░░ \"Much Minesweeping\" "
				<< endl;
		cout << " ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌░░░						" << endl;
		cout << " ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒▌░░  \"Wow\"			" << endl;
		cout << " ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐░░						" << endl;
		cout << " ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄▌░						" << endl;
		cout << " ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▌░						" << endl;
		cout << " ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒▐░						" << endl;
		cout << " ▐▒▒▐▀▐▀▒░▄▄▒▄▒▒▒▒▒▒░▒░▒░▒▒▒▒▌						" << endl;
		cout << " ▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒▒▒░▒░▒░▒▒▐░						" << endl;
		cout << " ░▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒░▒░▒░▒░▒▒▒▌░						" << endl;
		cout << " ░▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▒▄▒▒▐░░						" << endl;
		cout << " ░░▀▄▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▄▒▒▒▒▌░░						" << endl;
		cout << " ░░░░▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀░░░ CONGRATULATIONS!	"
				<< endl;
		cout << " ░░░░░░▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀░░░░░ YOU HAVE WON!		"
				<< endl;
		cout << " ░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▀▀░░░░░░░░ SCORE: " << score
				<< endl;
		cout << " 													" << "\n" << endl;

		gameOver = true;
	}

	
	/**
	*prints the same output as the display functions
	*see void display()
	*except where indicated to show mine locations
	*/
	void MineSweeper::noFog() {
			
		//print the number of rounds played
		cout << " Rounds Completed: " << round << "\n" << endl;

		//print the gameBoard
		for (int rowCounter = 0; rowCounter < rows; rowCounter++) {

			cout << " " << rowCounter;
			
			for (int columnCounter = 0; columnCounter < columns;
					columnCounter++) {
				
				//is there a mine at this location?
				if(mineBoard[rowCounter][columnCounter] == true){
				//yes, show mine with < >
					cout <<"|<" <<gameBoard[rowCounter][columnCounter]<<">";
				}else{
				
				//no, there is not a mine, prin blank spaces
					cout << "| " << gameBoard[rowCounter][columnCounter]<< " ";
				}
			}

			cout<< "|" << endl;
			
		}
			
			cout<<"   ";
			
			for (int columnCounter = 0; columnCounter < columns;
					columnCounter++) {

				cout << " " << columnCounter << "  ";
				
			}

			cout << "\n "<<endl;

		}

	/**
	 *	prints the Game Quit Screen
	 * sets gameOver to true
	 *	@return	void
	 */
	void printGameQuit() // prints the game quit info
	{

		cout << "\n" << " ლ(ಠ_ಠლ)			" << endl;
		cout << " Y U NO PLAY MORE?	" << endl;
		cout << " Bye!				" << "\n" << endl;

		//end the program
		gameOver = true;
	}

	/**
	 * prints the help screen
	 * @return void
	 *
	 */
	void MineSweeper::printHelp() {

		cout << "\n" << " Commands	Available..." << endl;
		cout << " -	Reveal:	r/reveal row col	" << endl;
		cout << " -	  Mark:	m/mark	 row col	" << endl;
		cout << " -	 Guess:	g/guess	 row col	" << endl;
		cout << " -	  Help:	h/help				" << endl;
		cout << " -	  Quit:	q/quit				" << "\n" << endl;

	}

	/**
	 * prints the command error info
	 * @return void
	 *
	 */
	void MineSweeper::printCommandError() { // prints the error info
		cout << "\n" << " ಠ_ಠ says, \"Command not recognized!\" \n" << endl;

	}

	/**
	 *displays the number of rounds completed and displays the game board.
	 *@return void
	 */

	void MineSweeper::display() {

		//print the number of rounds played
		cout << " Rounds Completed: " << round << "\n" << endl;

		//**************************************
		//print the gameBoard
		//*****************************
		
		//loop through the rows
		for (int rowCounter = 0; rowCounter < rows; rowCounter++) {

			//display the row number
			cout << " " << rowCounter;
			
			//loop through the columns
			for (int columnCounter = 0; columnCounter < columns;
					columnCounter++) {
				//display contents of the gameboard vector
				cout << "| " << gameBoard[rowCounter][columnCounter]<< " ";
				}
			
			//end with a line
			cout<< "|" << endl;
			}
			
			//start with proper indentation
			cout<<"   ";
			
			//loop through the columns
			for (int columnCounter = 0; columnCounter < columns;
					columnCounter++) {

					//output the column number
				cout << " " << columnCounter << "  ";
			}

			//end with a blank line
			cout << "\n "<<endl;

		}


	/**
	 *displays the prompt and gets user input; calls other functions accordingly.
	 * @return void
	 */

	void MineSweeper::prompt() {

		//declare local variables
		int rowGuess;
		int columnGuess;
		string input = "";
		string buff;
		string theLine;
		vector<string> tokens;
		
		//request input from user
		cout << "minesweeper$ ";
		
		//round is incremented everytime there is input
		round++;
	
		//get the line of input
		getline(cin, theLine);
		
		//parse the line of user input into the tokens vector
		stringstream theStream(theLine);
		
		while (theStream >>buff){
			tokens.push_back(buff);
		}
		
		//these commands are only valid with one token
		if(tokens.size() == 1){
		
			//did the user want help? what a baby
				if (tokens[0]=="help" || tokens[0] == "h") {
			
					printHelp();
					
					
				//did the user want to quit? what a loser
				}else if (tokens[0] == "quit" || tokens[0] == "q") {
					printGameQuit();
					
					//did the user know nofog, he deserves a monocle
				}else if(tokens[0] == "nofog"){
					nofog = true;
				
				//all other input is invalid for one token commands
				}else{
					printCommandError();
					
				}
				
	//these commands are only valid with 3 tokens
			} else if (tokens.size() == 3){
		
				//the convert the second two tokens from strings to ints
					stringstream ss(tokens[1]);
					stringstream ssTwo(tokens[2]);
					
				//was the conversion succesful?
				if( (ss>> rowGuess) && (ssTwo>> columnGuess)){
					
					//successful conversion
					//are the ints in bounds?
					if (isInBounds(rowGuess, columnGuess)) {
			
						//yes in bounds
						
						//did the user want to reveal a space? boom goes the dynamite
						if (tokens[0] == "r" || tokens[0] == "reveal") {
						
							reveal(rowGuess, columnGuess);
		
						//did the user want to mark a space???????????????????????????????
						}else if (tokens[0] == "m" || tokens[0] == "mark") {

							mark(rowGuess, columnGuess);
			
			
						//did the user guess a space FFFFFFFFFFFFFFFFFFFFFFFFFFFFF
						}else if (tokens[0] == "g" || tokens[0] == "guess") {
					
							guess(rowGuess, columnGuess);

						//all input for three token arguments is invalid
						} else {
			
						printCommandError();
						
						
						}
					
					//the guesses were not in bounds
					} else {
						printCommandError();
						
						
					}
			
				//conversion from string to ints was not succesful
				}else{
					printCommandError();
					
					
				}
			
			//only 1 and 3 token arguments are valid
			}else{
				printCommandError();

		}
	}
	/**
	 *marks a square as definitely having a mine, updating the game state accordingly.
	 *@param pRow int of the guessed row
	 *@param pColumn int of the guessed column
	 *@return void
	 **/
	void MineSweeper::mark(int pRow, int pColumn) {

		gameBoard[pRow][pColumn] = "F";
	}

	/**
	 *marks a square as potentially having a mine, updating the game state accordingly.
	 *@param pRow int of the guessed row
	 *@param pColumn int of the guessed column
	 *@return void
	 **/
	void MineSweeper::guess(int pRow, int pColumn) {

		gameBoard[pRow][pColumn] = "?";

	}

	/**
	 *reveals a square, updating the game state accordingly
	 *if a mine is revealed, the game is ended
	 *@param pRow int of the guessed row
	 *@param pColumn int of the guessed column
	 *@return void
	 **/
	void MineSweeper::reveal(int pRow, int pColumn) {

	//initialize the mine counter
		mineCounter = 0;

		//if there is a mine located there
		if (mineBoard[pRow][pColumn]) {
			//you lose
			printGameOver();

		//if not, count all of the mines located adjacent to that mine
		} else {
	
			//the guessed row, row -1, and row+1 are checked
			for (int rowCounter = pRow - 1; rowCounter <= pRow + 1; rowCounter++) {

			//the guessed column, column -1 and column +1 are checked
				for (int columnCounter = pColumn-1; columnCounter <= pColumn+1;
						columnCounter++) {

						//if the location is on the board, check  mineBoard
					if (isInBounds(rowCounter, columnCounter)) {
						//if there is a mine, increment the mine counter
						if (mineBoard[rowCounter][columnCounter]) {
							mineCounter++;
						}
					}
				}
			}
			
			//convert the int to a string
			stringstream theStream;
			theStream << mineCounter;
			string numMines = theStream.str();
		//reveal to the player the number of adjacent mines to that location
		gameBoard[pRow][pColumn] = numMines;
		}
	}
	/**
	*	returns true if the square is in the game board, false otherwise.
	* @param pRow an int of the guessed row
	* @param pColumn an int of the guessed column
	* @return bool true if it is in bounds, false if not
	*/
	bool MineSweeper::isInBounds(int pRow, int pColumn) {

		if (pRow < rows && pRow >= 0 && pColumn < columns && pColumn >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	*sets up a game loop, looping the display and prompt functions
	* increments round at the end of each iteration
	*updates score at the beginning of each round
	*ends when gameOver = true
	*@return void
	*/

	void MineSweeper::run() {

		//show the title 
		printTitle();

		//continue playing until the game is over
		while(gameOver == false ){
		
			//if there is nofog show nofog
			if(nofog == true){
				noFog();
				//reset flag
				nofog = false;
			
		//else just display thhe gameboard
			}else{
		
			display();
			}
	
		//prompt the user for a command
		prompt();
		
		//update the score
		updateScore();
		
		//check if the game has been won
		checkGameWon();
		
	}
}

	/**
	*	prints the title
	*
	*	@return	void
	*/
	void MineSweeper::printTitle() //prints the title
	{
	cout<<" \n" <<
	  "   /\\/\\ (_)_ __   ___  _____      _____  ___ _ __   ___ _ __"<<endl;
	cout<<"  /    \\| | '_ \\ / _ \\/ __\\ \\ /\\ / / _ \\/ _ \\ '_ \\ / _ \\ '__|"<<endl;
	cout<<" / /\\/\\ \\ | | | |  __/\\__ \\\\ V  V /  __/  __/ |_) |  __/ |" << endl;
	cout<<" \\/    \\/_|_| |_|\\___||___/ \\_/\\_/ \\___|\\___| .__/ \\___|_|" <<endl;
	cout<<"                                  RECURSIVE |_| EDITION \n"<<endl;
	}

	/**
	*	updates the game's score
	*
	*	@return	void
	*/
	void MineSweeper::updateScore() {

		//score calculation
		score = (rows * columns) - mines - round;
	
	}

	/**
	* prints the quit game message
	* and changes gameOver to true
	*@return void
	*/

	void MineSweeper::printGameQuit(){

		cout<<"\n" << " ლ(ಠ_ಠლ) "<<endl;
		cout<<" Y U NO PLAY MORE?" <<endl;
		cout<<" Bye!" << "\n" <<endl;

		gameOver = true;
	}

	/**
	* prints the over game message
	* and changes gameOver to true
	*@return void
	*/
	
	void MineSweeper::printGameOver(){


		cout<<"\n Oh no... You revealed a mine!" << endl;
		cout<<"   __ _  __ _ _ __ ___   ___    _____   _____ _ __ "<<endl;
		cout<<"  / _` |/ _` | '_ ` _ \\ / _ \\  / _ \\ \\ / / _ \\ '__|"<<endl;
		cout<<" | (_| | (_| | | | | | |  __/ | (_) \\ V /  __/ |   "<<endl;
		cout<<"  \\__, |\\__,_|_| |_| |_|\\___|  \\___/ \\_/ \\___|_|   "<<endl;
		cout<<"  |___/  \n "<<endl;
	
		gameOver = true;
	}
