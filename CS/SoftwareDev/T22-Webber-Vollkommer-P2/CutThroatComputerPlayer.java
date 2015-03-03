 
public class CutThroatComputerPlayer extends SmartAIComputerPlayer{

	HangmanAI computerPlayer = new HangmanAI();


	public char generateMove(String in_word, String already_guessed) {
		char x = computerPlayer.makeGuess(in_word, already_guessed);
		return x;
	}
}
