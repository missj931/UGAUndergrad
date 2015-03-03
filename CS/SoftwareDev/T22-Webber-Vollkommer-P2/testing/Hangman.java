//TODO pass along switch
//TODO finish JUNIT test cases
import java.io.*;

//the hangman class contains the main method. It runs the program
//the Hangman class transfers data between the players and the HangmanBoard
public class Hangman {



	/**
	 * main method, runs the program
	 * @param args the arguments entered in the makefile runs command
	 */
	public static void main(String[] args)
	{

		//--------------------------------------------------------------------------
		//Testing command-line input parameters...
		//the argument must contain a certain number of inputs, these inputs must be valid.

		try{
			//loop through the array looking for -L
			for(int counter=0; counter<args.length; counter++){
				
				//if the argument conains -L, output to file
				if(args[counter].equals("-L")){
					    FileOutputStream file = new FileOutputStream("logged-gameplay.output");
					    DualPrintStream dualPrinting = new DualPrintStream(file, System.out);
					    System.setOut(dualPrinting);
					    System.out.println("overwrite output to file logged-gameplay.output activated");
				}

			}
			//catch the error if no output file is found and end the game
		}catch(Exception e){
			System.err.println("output file not found");
			System.exit(1);
		}

		//--------------------------------------------------------------------------
		//Testing command-line input parameters...
		//the argument must contain a certain number of inputs, these inputs must be valid.
		checkNumberOfArgs(args);

		//returns the location in the array of the player
		int lPlayer = checkArgOrder(args);
		
		//instantiate the player
		//is it a the PlayFaster computer player?
		if(args[lPlayer].equalsIgnoreCase("run_faster")){
			//yes, create a faster computer player
			HangmanBoard board = new HangmanBoard();
			board.generateWord();
			PlayFasterComputerPlayer fasterPlayer = new PlayFasterComputerPlayer(board.returnInWord().length());
			playGameSmartAI(fasterPlayer, board);
			//is it a human player?
		}else if(args[lPlayer].equalsIgnoreCase("human")){
			//yes, create a human player
			HumanPlayer thePlayer = new HumanPlayer();
			//play the game
			playGame(thePlayer);

			//is it a naive computer player?
		}else if (args[lPlayer].equalsIgnoreCase("naive")){
			//yes, create a NaiveComputerPlayer
			NaiveComputerPlayer thePlayer = new NaiveComputerPlayer();
			//play the game
			playGame(thePlayer);

			//is it a random computer player?
		}else if (args[lPlayer].equalsIgnoreCase("random")){
			//yes, create a RandomComputerplayer
			RandomComputerPlayer thePlayer = new RandomComputerPlayer();
			//play the game
			playGame(thePlayer);

			//is it a cutthroat computer player?
		}else if (args[lPlayer].equalsIgnoreCase("cutthroat")){
			//yes Create a HangmanAI player
			HangmanBoard board = new HangmanBoard();
			board.generateWord();
			CutThroatComputerPlayer cutThroatPlayer = new CutThroatComputerPlayer();
			playGameSmartAI(cutThroatPlayer, board);

			//if a player was not selected, there was an error in the input
		}else{
			//print error end game
			System.err.println("Error: input for player must be 'faster', 'human', 'random', 'naive', 'cutthroat' or 'run_faster'.");
			System.err.println("Place the desired player after the 'computer' board operator in args.");
			System.exit(1);
		}
	}
	//end of main method


	//-------------------------------------------------------------------------------
	//Methods called in main


	/**
	 * keeps asking the player for a new guess until an acceptable character
	 * is presented,
	 * an acceptable guess is a guess that is not a repeat and is not a character
	 * returns the player's guess
	 *
	 * @param hal, the player
	 * @param theBoard being played on
	 * @return the character guessed
	 */
	public static char getGuess(Player hal, HangmanBoard theBoard){
		//initialize varaibles
		boolean valid = false;
		char guess = 0;
		//loop until an acceptable guess is submitted
		do{
			System.out.print("Guess: ");
			valid =	theBoard.isGuessAccepted(guess = hal.getLetter());
		}while(valid == false);
		return guess;
	}

	/**
	 * submits a guess to the gameboard
	 * the main method runs this after making sure the guess is valid or not repeated
	 * @param guess the player's guess to be submitted
	 * @param theBoard the board the game is being played on
	 */
	public static void submitGuess(char guess, HangmanBoard theBoard){
		theBoard.submitGuess(guess);
	}

	/**
	 * checks if the correct number of arguments entered?
	 * if an error occurs, ends the program
	 * @param theArgs the array of arguments entered in the command line
	 *
	 */
	public static void checkNumberOfArgs(String [] theArgs){
		try
		{
			for(int i = 0; i < 2; i++){
				if (theArgs[i].equals("") || theArgs[i].equals(null)){
					System.err.println("Error: No input for args[" + i + "].");
					System.err.println("Please specify the board operator (computer), the player, and log option (-L) if desired.");
					System.exit(1);
				}else if(theArgs.length == 0){
					System.err.println("Error: No input arguments.");
					System.err.println("Please specify the board operator (computer), the player, and log option (-L) if desired.");
					System.exit(1);
				}
			}

		}
		catch(ArrayIndexOutOfBoundsException e){
			//make sure that only 2 or 3 arguments are entered
			//there cannot be more than three arguments
			if(theArgs.length > 3){
				System.err.println("Error: Too many arguments entered.");
				System.err.println("Please specify the board operator (computer), the player, and log option (-L) if desired.");
				System.exit(1);
			}

			//there cannot be less than two arguments
			if(theArgs.length <2){
				System.err.println("not enough arguments entered");
				System.exit(1);

			}
		}
	}



	//----------------------------------------------------------
	/**
	 * if an error occurs, ends the program
	 * checks to make sure the args are entered in the ana acceptable order order
	 * -L anywhere, but Computer must come before a player argument
	 * and reveals the location of the player argument
	 * @param theArgs the array of arguments entered in the command line
	 * @return the location in the arg array that contains the player
	 */
	public static int checkArgOrder(String theArgs[]){
		//make sure the args are in the correct order
		//computer must come before the player type
		//-L can be located anywhere
		int playerLocation = 666;
		int computerLocation = 666;
		int checkPlayer = 666;
		//find the location of the computer and player arguments

		//look for computer
		for(int cCounter = 0; cCounter < theArgs.length; cCounter++){
			//if the arg contains computer, assign location,
			if(theArgs[cCounter].equalsIgnoreCase("Computer")){
				computerLocation = cCounter;
				break;
			}
		}
		//look for player
		for(int pCounter = 0; pCounter < theArgs.length; pCounter++){
			//if the arg contains the player, assign location
			if(theArgs[pCounter].equalsIgnoreCase("random") || theArgs[pCounter].equalsIgnoreCase("faster") || !theArgs[pCounter].equalsIgnoreCase("computer") || theArgs[pCounter].equalsIgnoreCase("human") || theArgs[pCounter].equalsIgnoreCase("naive") || theArgs[pCounter].equalsIgnoreCase("cutthroat") || theArgs[pCounter].equalsIgnoreCase("run_faster")){
				playerLocation = pCounter;
				checkPlayer = pCounter;
				break;
			}
		}
		
		System.out.println(playerLocation);
		//computer argument must come before player location
		if(computerLocation > checkPlayer){
			System.err.println("Error: The computer argument (board operator) must come before the player argument");
			System.exit(1);
		}
		System.out.println(playerLocation);
		return playerLocation;
	}

	//----------------------------------------------------------
	/**
	 * playGame plays the game for human and regular computer players.
	 * It starts the board.
	 * It also loops until the game ends and exits.
	 * The game ends and exits when the player loses
	 * at 6 guesses or wins when completing the word
	 * @param thePlayer, the player playing the game
	 */
	public static void playGame(Player thePlayer){
		//instantiate the HangmanBoard
		HangmanBoard board = new HangmanBoard();
		board.generateWord();
		System.out.println("Hangman Game");
		board.printBoard();
		System.out.println(board.returnWordBoard());

		//game loop ends when the word is found or max guesses is reached.
		while(board.getIncorrectGuesses() < 6 && board.isWordFound() == false){
			submitGuess(getGuess(thePlayer, board), board); //get and submit input
			System.out.print(board.returnWordBoard()); //print wordboard
			System.out.println(" " + board.returnGuessedLetters());

		}
		//determining how to exit the game (won or lost).
		if(board.getIncorrectGuesses() >= 6){
			System.out.println("Game Over! You died :( The word was " + board.returnWord());
			System.exit(0);
		}else if(board.isWordFound() == true){
			System.out.println("Game Over! You guessed the word! You had " + board.getIncorrectGuesses() + " incorrect guesses.");
			System.exit(0);
		}	
	}

	//----------------------------------------------------------
	/**
	 * playGameSmartAI plays the game specifically tailored for
	 * hangmanAI (cutthroat) and run_faster computer players.
	 * It loops until the game ends and exits.
	 * The game ends and exits when the AI loses
	 * at 6 incorrect guesses or wins when completing the word
	 * @param thePlayer, the SmartAI that is to be used.
	 */
	public static void playGameSmartAI(SmartAIComputerPlayer thePlayer, HangmanBoard board){
		//Hangmanboard is instantiated and random word is generated when one of the
		//SmartAI computer players are specified and passed to playGame method.
		System.out.println("Hangman Game");
		board.printBoard();
		System.out.println(board.returnWordBoard());
		//game loop ends when the word is found or max guesses is reached.
		while(board.getIncorrectGuesses() < 6 && board.isWordFound() == false){
			System.out.print("Guess: ");
			char smartGuess = thePlayer.generateMove(board.returnInWord(), board.returnAlreadyGuessed());
			board.submitGuess(smartGuess); //get and submit input

			System.out.print(board.returnWordBoard()); //print wordboard.
			System.out.println(" " + board.returnGuessedLetters());

		}
		//determining how to exit the game (won or lost).
		if(board.getIncorrectGuesses() >= 6){
			System.out.println("Game Over! You died :( The word was " + board.returnWord());
			System.exit(0);
		}else if(board.isWordFound() == true){
			System.out.println("Game Over! You guessed the word! You had " + board.getIncorrectGuesses() + " incorrect guesses.");
			System.exit(0);
		}
	}
}//end of Hangman class
