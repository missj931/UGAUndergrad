import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class HangmanBoardTest {

	@Test
	public void testIsGuessAccepted() {
		HangmanBoard test = new HangmanBoard ();
		assert test.isGuessAccepted('a') == true;
		assert test.isGuessAccepted('9') == false;
		assert test.isGuessAccepted('!') == false;
		
	}




	@Test
	public void testGetValidLetters() {
		 HangmanBoard test	= new HangmanBoard ();
		 char theLetters [] = test.getValidLetters();
			final char VALID_LETTERS[] = {
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
				'u', 'v', 'w', 'x', 'y', 'z'};
			for (int counter = 0; counter < VALID_LETTERS.length; counter++ ){
				assert theLetters [counter] == VALID_LETTERS [counter];

			}
		
	}

}
