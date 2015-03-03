public interface Board {

	public static final char VALID_LETTERS[] = {
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
			'u', 'v', 'w', 'x', 'y', 'z'};
	
	//General methods for a board game
	
	/** 
	 * prints the ASCII gallows art below.
	 * used when six mistakes are left
	 * 
	 */
	public void printBoard();
	
	/** 
	 *checks to see if the input is acceptable for submission
	 *@param userGuess the user's guessed letter
	 */
	public boolean isGuessAccepted(char userGuess);
	/** 
	 * submits the user guess to board.
	 * only use after verifying with isGuessAccepted
	 * @param userGuess
	 * @return true if guess is ready for submission, false if not
	 * @see isGuessAccepted(char userGuess)
	 */
	public void submitGuess(char userGuess);
	
	/**
	 * accesses the validLetters constant
	 * @return the validLetters as a char array
	 */
	public char[] getValidLetters();
	
	/** 
	 * creates the word
	 * should be called at the beginning of the game
	 */
	public void generateWord();
	/** 
	 * accesses the guessedlettes
	 * @return previously guessed letter
	 */
	public String returnGuessedLetters();
	
	
	/** 
	 * accesses the number of incorrect guesses
	 * @return number of incorrect guesses submitted
	 */
	public int getIncorrectGuesses();
}
