//Matthew vollkommer csci 1730 project 1 cotterell

#include <fstream> // class ReadFile uses the C++ standard file input stream class
#include <iostream>
#include "MineSweeper.h"
#include <sstream>

//function main begins program execution
using namespace std;

int main(int argc, char **args) {


	//are there two arguments? ./MineSweeper FILENAME
	if (argc != 2) {

		cout << "\n" << " ಠ_ಠ says, invalid number of arguments \n " << endl;

		return 0;
	}
	
	ifstream infile(args[1]);
	
	//declare variables
	int rows, columns, mines;
	string buff;
	string theLine;
	vector<string> tokens;

	vector< vector<int> > mineLocation;
	
	//********************************************
	//get the first line from the file
	getline(infile, theLine);
	
		stringstream theStream (theLine);
	

	//loop through, adding the strings to the vector
	while (theStream >>buff){
		tokens.push_back(buff);
	}
		
	//first line must have two tokens
	if(tokens.size() != 2){
		
		cout<< " \n" << " ಠ_ಠ says, Cannot create game with "<< args[1]<<", because it is not formatted correctly. \n  " << endl;
	
		return 0;
	}
	
	//the convert the second two tokens from strings to ints
	stringstream ss(tokens[0]);
	stringstream ssTwo(tokens[1]);
			

	if (ss >> rows && ssTwo >> columns) {
		//extracted
	}else{
	
		cout<< " \n" << "  ಠ_ಠ says, Cannot create game with "<< args[1]<<", because it is not formatted correctly. \n  " << endl;
	
		return 0;
	}

	//********************************************************
	//get the second line from the file

	
	
	//reinitialize variables
	vector<string> tokensTwo;
	
	string buffTwo;
	
	getline(infile, theLine);

	stringstream theStreamTwo (theLine);
	
	//loop through, adding the strings to the vector
	while (theStreamTwo >>buffTwo){
		
	
		tokensTwo.push_back(buffTwo);
	}
	
	stringstream ssThree(tokensTwo[0]);
		
	//second line must have only one token
	if(tokensTwo.size() != 1){
		
		cout<< " \n" <<" ಠ_ಠ says, Cannot create game with "<< args[1]<<", because it is not formatted correctly. \n  " << endl;
		
		
		return 0;
		
		
	}
	
	//must be an int
	if( ssThree >> mines){
	
	}else{
	//token was not an int
		cout<< " \n"<< " ಠ_ಠ says, Cannot create game with "<< args[1]<<", because it is not formatted correctly. \n  " << endl;
		return 0;
	}
			
	//**********************
	//import lines 3 and up. only import as many lines as there are mines

	//the vector is the size of the number of mines
	mineLocation.resize(mines);
	


	for (int counter = 0; counter < mines; counter++) {
		
		//1 row, 1 column
		mineLocation[counter].resize(2);

	//itialize variables
	vector<string> tokensThree;
	
	string buffer;

	
	getline(infile, theLine);

	stringstream theStreamThree (theLine);
	
	//loop through, adding the strings to the vector
	while (theStreamThree >>buffer){
		
		tokensThree.push_back(buffer);
	}
	
	stringstream ssFour(tokensThree[0]);
	stringstream ssFive(tokensThree[1]);
	// lines 3 and up must have only 2 tokens
	if(tokensThree.size() != 2){
		
		cout<< " \n" <<" ಠ_ಠ says, Cannot create game with "<< args[1]<<", because it is not formatted correctly. \n  " << endl;
		
		
		return 0;
		
		
	}
		
	
		//are they ints
		if(ssFour >> mineLocation[counter][0] && ssFive >> mineLocation[counter][1]){

			
		}else{
		//no print error
			cout<< " \n"<<" ಠ_ಠ says, Cannot create game with FILENAME, because it is not formatted correctly. \n  " << endl;
			return 0;
			
		}
			
	}	

	//close the file
	infile.close();
		
	
	//initialize minesweeper by calling constructor
	MineSweeper theGame(rows, columns, mines, mineLocation);

	//play the game
	theGame.run();

	//successfully end the program
	return 0;
}
