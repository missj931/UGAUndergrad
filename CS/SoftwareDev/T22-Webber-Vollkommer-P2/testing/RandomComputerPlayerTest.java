import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class RandomComputerPlayerTest {


	@Test
	public void testGenerateMove() {
		RandomComputerPlayer test = new RandomComputerPlayer();
		assert test.generateMove() != '8';
	}


	@Test
	public void testGetLetter() {
		RandomComputerPlayer test = new RandomComputerPlayer();
		assert test.getLetter() != 8;

	}
}
