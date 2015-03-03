
//human players are required to read input
//import the appropriate packages
import java.util.*;


public class HumanPlayer extends Player {


	
	//Override parent's method
	//this method generates the move
	//and returns it to the getLetter Method
	public char generateMove(){
		
		char guess = this.stringToChar(this.getMove());
	
	return guess;
	
	}
	//----------------------------------------------------------------------------
	//gets input from the player
	//checks that the input is only a single character
	//returns the input to the generate move method
	
	private String getMove() {
	
		boolean noError = true;
		Scanner keyboard = new Scanner(System.in);
		String userGuess = "";
	
	do {
		
		//ask for input
		System.out.print("(Enter a Letter): ");

		try{
				noError = true;
				userGuess =  keyboard.next();
				userGuess = userGuess.toLowerCase();	
				
				} catch (Exception e){
					System.err.println("Input invalid, please enter a letter.");
					noError = false;
				
				}
		if (userGuess.length() >1){
			System.out.println("Only single characters please.");
			noError = false;
		}
		
	} while (noError == false);
	
	return userGuess;
	
	}

		
	//converts a string to a char array.
	//it returns the char array
	//used for converting guess input
	private char stringToChar(String userGuess){
	
		char guess = 0;
		try{
				guess = userGuess.charAt(0);
				
			}catch(StringIndexOutOfBoundsException e){
				System.err.println("No input, enter a letter.");
				
			}catch(Exception e){
				System.err.println("Not a letter, input invalid. Please enter a letter.");
				this.getMove();
			}
			
		
			return guess;
	
		
		}
	}


