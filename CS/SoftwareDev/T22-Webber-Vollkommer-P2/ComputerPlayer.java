//it is a parent of the Naive and random computer classes
public abstract class ComputerPlayer extends Player{

	protected final char knowledge[] = { //list of valid guesses that can be made. 
			//This may not be necessary to have in Player if we provide get methods in HangmanBoard
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
			'u', 'v', 'w', 'x', 'y', 'z'};
	protected char[] guessedLetters = new char[26];
	private int totalGuesses = 0;


	//----------------------------------------------------------------------------
	//Getter method for totalGuesses: returns the total guesses the player has made.
	public int getTotalGuesses(){
		int x = this.totalGuesses;
		return x;
	}
	//----------------------------------------------------------------------------
	//Setter method for totalGuesses: Increments the total guesses the player has made.
	//This method should be implemented EVERY time a computer player generates a letter.
	protected void incrementTotalGuesses(){
		int x = this.totalGuesses;
		x++;
		this.totalGuesses = x;
	}
}
