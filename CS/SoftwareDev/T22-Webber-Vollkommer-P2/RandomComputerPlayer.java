import java.util.*;

//RandomComputerPlayer is a child of the abstract ComputerPlayer parent class
//This computer player will guess a random letter from the validLetters
//(not in alphabetical order) that has not been guessed already. 
public class RandomComputerPlayer extends ComputerPlayer {
	Random generator = new Random();
	/**
	 * overrides the parent's method
	 * generates the move. The move is a random letter from the alphabet
	 * that has not already been guessed. 
	 * the knowledge is the valid characters sorted as an array
	 * @param guesses, all the guesses submitted
	 * @return a random valid letter
	 */
	public char generateMove() {
		char x = 0;
		boolean isNewLetter = false;
		while(isNewLetter == false){
			x = this.knowledge[this.generator.nextInt(26)]; //random position (1-26)

			//checks that generated letter is not in guessedLetters
			//if x is found in guessedLetters, for loop exits & while loop restarts.
			for(int i = 0; i < 26; i++){
				if(x == this.guessedLetters[i]){ 
					isNewLetter = false;
					i = 26;
				}else{
					isNewLetter = true;
				}

			}
		}
		//adds new generated letter to guessedLetters, increments total guesses. 
		this.guessedLetters[this.getTotalGuesses()] = x;
		this.incrementTotalGuesses();

		return x;
	}
	

		
}

