public abstract class Player {

	   
	/**
	 * each player gives the board a letter
	 * @param guesses, the previously submitted guesses
	 * @return the player's guess, a char
	 * @see generateMove(char[] guesses)
	 */
	public char getLetter(){
		return this.generateMove();
	}
	
	/**
	 * each player generates a move
	 * @param letters to be considered when generating a move
	 * @return char, the move to be considered by the getLetter method
	 * @see getLetter(char[] guesses)
	 */
	protected abstract char generateMove(); 
	
}
