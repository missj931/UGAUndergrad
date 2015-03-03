//NaiveComputerPlayer is a child of the abstract ComputerPlayer parent class
//This computer player will guess the first letter, alphabetically, that has
//not been already guessed or played. 


public class NaiveComputerPlayer extends ComputerPlayer {




	/**
	 * 
	 * generates the move. The move is the next letter in the alphabet.
	 * takes the next letter in the alphabet array based on the number of guesses
	 * for example. an array with a length of zero has zero guess,
	 * so it gets the letter in the alphabet at 0, a
	 * 
	 * @param guesses, relevant guesses
	 * @return a char
	 */
	//the knowledge is the array sorted as an alphabet
	public char generateMove() {
		char x = this.knowledge[this.getTotalGuesses()];
		this.incrementTotalGuesses();
		return x;
	}

}
