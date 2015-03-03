//import all java io utilities
//import all java utilities
import java.io.*;
import java.util.*;

//the HangmanBoard uses the Board interface
//this is where the game action occurs
public class HangmanBoard implements Board{

	private int incorrectGuesses = 0;
	private char[] wordArray = new char[0];
	private int wordLength = 0;
	private char[] wordBoard = new char[0];
	private char[] guessedLetters = new char[26];
	private char[] In_word = new char[0];
	private int totalGuesses = 0;
	private int correctGuesses = 0;
	private final int MAX_GUESSES = 6;
	private int isGuessCorrect = 0; //0 is default. 1 is false. 2 is true.
	

		
		/**
		 *Generates a new secret word in a wordBoard array. 
		 *sets the class variable wordBoard
		 *reads a word at random from the dictionary
		 */
		public void generateWord(){
			ArrayList dictionary = new ArrayList(0);
			Scanner scanner = null;
			try{
				File file = new File("dictionary.data");
				scanner = new Scanner(file);
			}catch(FileNotFoundException filenotfound){
				System.out.println("Where's the dictionary.data file, yo?");
				System.exit(1);
			}
			for(; scanner.hasNext(); dictionary.add(scanner.next()));
			Random generator = new Random();
			String Word = (String) dictionary.get(generator.nextInt(dictionary.size()));
			this.wordArray = Word.toCharArray();
			this.wordLength = Word.length();

			//Creating word board interface
			String wordBoardString = "";
			String InWordString = "";
			for(int i = 0; i < Word.length(); i++){
				wordBoardString += "_ ";
				InWordString += "-";
			}
			this.wordBoard = wordBoardString.toCharArray();
			this.In_word = InWordString.toCharArray();
		}

		/**
		// Returns the word for the current game. This is only for testing and should 
		 * NOT be used in the final submission.
		 * @return a String hiding unguessed letters and revealing correct guesses
		 */
		public String returnWord(){
			String x = "";

			for(int i = 0; i < wordLength; i++){
				x+= wordArray[i];

			}
			return x;
		}
		
		/**
		 * Returns the letter board of positioned letters the user has guessed correctly.
		 * 
		 * @return the correctly guessed and hidden letters as an array of chars
		 */
		public String returnWordBoard(){
			String x = "Word " + "(" + wordLength + " letters): ";
			for(int i = 0; i < wordBoard.length - 1; i++){
				x += wordBoard[i];
			}
			return x;

		}
		
		/**
		// Returns the word stripped down to only correctly guessed letters in the
		 * correct positions. Letters not yet correctly guessed are replaced with "-"
		 * This is to provide the HangmanAI with the current in_word parameter.
		 * @return a String hiding unguessed letters and revealing correct guesses, 
		 * tailored specifically for HangmanAI. 
		 */
		public String returnInWord(){
			String x = "";
			for(int i = 0; i < In_word.length; i++){
				x += In_word[i];
			}
			return x;
		}
		
		/**
		 * Provides access to a String containing solely the letters that have already been guessed,
		 * with no spaces between. 
		 * This is to provide the HangmanAI with the current already_guessed parameter. 
		 * @return access the guessed letter array
		 */
		public String returnAlreadyGuessed(){
			String x = "";
			for(int i = 0; i < this.guessedLetters.length; i++)
				if(!(this.guessedLetters[i] == '\u0000')){
					x += this.guessedLetters[i];
				}
			x.trim();
			return x;
		}
		/**
		 * this method allows for access to the guessed letter array
		 * @return A string containing already guessed letters, provided in a set.
		 */
		public String returnGuessedLetters(){
			char[] guessedLettersCopy = guessedLetters;
			Arrays.sort(guessedLettersCopy);
			String x = "{";
			for(int i = 0; i < guessedLettersCopy.length; i++)
				if(!(guessedLettersCopy[i] == '\u0000')){
					x += guessedLettersCopy[i] + ", ";
				}
			if(x.length() > 3){
			x = x.substring(0, x.length() - 2);
			}
			x += "}";
			x = "Guessed: " + x;
			return x;
		}
		
		/**
		 * makes sure the guess is acceptable.
		 * an acceptable guess is not repeated and must be 
		 * a char
		 * 
		 * @param userGuess the character the player is validating
		 * @see isGuessRepeated(char userGuess)
		 * @see isGuessValid(char userGuess)
		 */
		public boolean isGuessAccepted(char userGuess){
			boolean accepted = false;
			if(!(this.isGuessRepeated(userGuess)) && this.isGuessValid(userGuess)){
				accepted = true;
			}
			
			return accepted;
			
		}
		/**
		 * Returns a boolean value determining if the user's char input	was a single letter.
		 * prints "The input is not a letter" if the player does not submit a letter
		 * 
		 * @param userGuess the user guess that is being checked
		 * @return true if if it is a letter. false if it is not a letter
		 */
		private boolean isGuessValid(char userGuess){
			boolean x = false;
			for(int i = 0; i < 26; i++){
				if(userGuess == this.getValidLetters()[i]){
					x = true;
					i = 26;
				}
			}
			//tell the user the guess is not valid
			if( x == false){
				System.out.println(userGuess + " is not a letter");
			}
			
			return x;
		}

		

		/**
		 * Returns a boolean value determining if the user's char input	a repeated guess.
		 * True if guess was repeated. False if otherwise. 
		 * 
		 * @param userGuess the users guess
		 * @return true if the guess is not a repeat. false if not already guessed
		 */
		private boolean isGuessRepeated(char userGuess){
			boolean x = false;
			for(int i = 0; i < guessedLetters.length; i++){
				if(userGuess == guessedLetters[i]){
					x = true;
				}
			}
			//tell the user the letter has already been guessed
			if( x == true){
				System.out.println(userGuess + " has already been guessed");
			}
			return x;
		}
		/**
		 * Returns a boolean value determining if the user's guess was correct. 
		 * True if the char was found in the word, false otherwise.
		 * Increments the number of correctGuesses by 1 for each letter found.
		 * Adds this userGuess to an array of guessed letters at the next available position.
		 * this method is to be called only after validated by the isGuessAccpeted method
		 * @param userGuess the guess the user is submitting
		 * @see isGuessAccepted(char userGuess)
		 */
		public void submitGuess(char userGuess){
			isGuessCorrect = 1;
			guessedLetters[totalGuesses] = userGuess;
			totalGuesses++;
			for(int i = 0; i < wordArray.length; i++){
				if(userGuess == wordArray[i]){
					isGuessCorrect = 2;
					correctGuesses++;
					wordBoard[i*2] = userGuess;
					In_word[i] = userGuess;
				}
			}
			if(isGuessCorrect == 1){
				incorrectGuesses++;
				printBoard();
				System.out.println("Sorry! That's not one of the letters.");
			}else if(isGuessCorrect == 2){
				printBoard();
				System.out.println("You got a letter!");
			}
		}
		
		/**
		 * Accesses the class variable int incorrectGuesses
		 * returns the number of incorrect guesses
		 * @return the int incorrectguesses
		 */
		public int getIncorrectGuesses(){
			return incorrectGuesses;
		}
		
		
		/**
		 * Returns a boolean value determining if the user has found the word.
		 * True if the number of correct guesses = the length of the word, false otherwise.
		 * 
		 * @return boolean true if word is found, false if word is not found
		 */
		public boolean isWordFound(){
			boolean x = false;
			if(correctGuesses == wordLength){
				x = true;
			}
			return x;
		}
		/**
		 * Prints the ASCII gallows and ASCII hangman based on the number of incorrect guesses.
		 * @see torso()
		 * @see gallows()
		 * @see head()
		 * @see torso()
		 * @see arm1()
		 * @see arm2()
		 * @see leg1()
		 * @see leg2()
		 */
		public void printBoard(){
			System.out.println();
			System.out.println("______________________________________________________________________________");	
			if(this.incorrectGuesses == 0){
				this.gallows();
			}else if(incorrectGuesses == 1){
				this.head();
			}else if(incorrectGuesses == 2){
				this.torso();
			}else if(incorrectGuesses == 3){
				this.arm1();
			}else if(incorrectGuesses == 4){
				this.arm2();
			}else if(incorrectGuesses == 5){
				this.leg1();
			}else if(incorrectGuesses == MAX_GUESSES){
				this.leg2();
			}
			System.out.println("______________________________________________________________________________");	
		}
		
		/** 
		 * prints the ASCII gallows art below.
		 * used when six mistakes are left
		 * @see printBoard()
		 */
		private void gallows(){
			System.out.println("                                         _.._");
			System.out.println("______________________                 .' .-'`");
			System.out.println(" | /                 |                |  |     *	MISTAKES LEFT: 6 ");
			System.out.println(" |/                 / \\               \\  \\");
			System.out.println(" |                  \\_/                '._'.-.               #");
			System.out.println(" |                                                          ### ");
			System.out.println(" |                                                          ###");
			System.out.println(" |                                               _______   ##### ");
			System.out.println(" |                                         ,.--''       `--..#.__");
			System.out.println("_|____                         _____.----''                  #   '''`---....");
		}
		
		/** 
		 * prints the ASCII gallows art below.
		 * used when 5 mistakes are left
		 * @see printBoard()
		 */
		private void head(){
			System.out.println("                                         _.._");
			System.out.println("______________________                 .' .-'`");
			System.out.println(" | /                _|_               |  |     *	MISTAKES LEFT: 5 ");
			System.out.println(" |/                (0_0)              \\  \\");
			System.out.println(" |                                     '._'.-.               #");
			System.out.println(" |                                                          ### ");
			System.out.println(" |                                                          ###");
			System.out.println(" |                                               _______   ##### ");
			System.out.println(" |                                         ,.--''       `--..#.__");
			System.out.println("_|____                         _____.----''                  #   '''`---....");
		}

		/** 
		 * prints the ASCII gallows art below.
		 * used when 4 mistakes are left
		 * @see printBoard()
		 */
		private void torso(){
			System.out.println("                                         _.._");
			System.out.println("______________________                 .' .-'`");
			System.out.println(" | /                _|_               |  |     *	MISTAKES LEFT: 4 ");
			System.out.println(" |/                (0_0)              \\  \\");
			System.out.println(" |                 |  |                '._'.-.               #");
			System.out.println(" |                 |  |                                     ### ");
			System.out.println(" |                                                          ###");
			System.out.println(" |                                               _______   ##### ");
			System.out.println(" |                                         ,.--''       `--..#.__");
			System.out.println("_|____                         _____.----''                  #   '''`---....");
		}

		/** 
		 * prints the ASCII gallows art below.
		 * used when 3 mistakes are left
		 * @see printBoard()
		 */
		private void arm1(){
			System.out.println("                                         _.._");
			System.out.println("______________________                 .' .-'`");
			System.out.println(" | /                _|_               |  |     *	MISTAKES LEFT: 3 ");
			System.out.println(" |/              \\ (0_0)              \\  \\");
			System.out.println(" |                \\|  |                '._'.-.               #");
			System.out.println(" |                 |  |                                     ### ");
			System.out.println(" |                                                          ###");
			System.out.println(" |                                               _______   ##### ");
			System.out.println(" |                                         ,.--''       `--..#.__");
			System.out.println("_|____                         _____.----''                  #   '''`---....");
		}

		/** 
		 * prints the ASCII gallows art below.
		 * used when 2 mistakes are left
		 * @see printBoard()
		 */
		private void arm2(){
			System.out.println("                                         _.._");
			System.out.println("______________________                 .' .-'`");
			System.out.println(" | /                _|_               |  |     *	MISTAKES LEFT: 2 ");
			System.out.println(" |/              \\ (0_0)/             \\  \\");
			System.out.println(" |                \\|  |/               '._'.-.               #");
			System.out.println(" |                 |  |                                     ### ");
			System.out.println(" |                                                          ###");
			System.out.println(" |                                               _______   ##### ");
			System.out.println(" |                                         ,.--''       `--..#.__");
			System.out.println("_|____                         _____.----''                  #   '''`---....");
		}
		/** 
		 * prints the ASCII gallows art below.
		 * used when 1 mistake is left
		 * @see printBoard()
		 */
		private void leg1(){
			System.out.println("                                         _.._");
			System.out.println("______________________                 .' .-'`");
			System.out.println(" | /                _|_               |  |     *	MISTAKES LEFT: 1 ");
			System.out.println(" |/              \\ (0_0)/             \\  \\");
			System.out.println(" |                \\|  |/               '._'.-.               #");
			System.out.println(" |                 |  |                                     ### ");
			System.out.println(" |                 //                                       ###");
			System.out.println(" |                //                             _______   ##### ");
			System.out.println(" |                                         ,.--''       `--..#.__");
			System.out.println("_|____                         _____.----''                  #   '''`---....");
		}
		/** 
		 * prints the ASCII gallows art below.
		 * used when the player loses
		 * @see printBoard()
		 */

		private void leg2(){
			System.out.println("                                         _.._");
			System.out.println("______________________                 .' .-'`");
			System.out.println(" | /                _|_               |  |     *	DEAD.... ");
			System.out.println(" |/              \\ (x_x)/             \\  \\ ");
			System.out.println(" |                \\|  |/               '._'.-.               #");
			System.out.println(" |                 |  |                                     ### ");
			System.out.println(" |                 //\\\\                                     ###");
			System.out.println(" |                //  \\\\                         _______   ##### ");
			System.out.println(" |                                         ,.--''       `--..#.__");
			System.out.println("_|____                         _____.----''                  #   '''`---....");
		}


		/**
		 * accesses the validLetters char array constant
		 * from the Board interface
		 * @see Board
		 * @return VALID_LETTERS, the letters that may be guessed
		 */
		public char[] getValidLetters() {
		
			return VALID_LETTERS;
		}
	}
