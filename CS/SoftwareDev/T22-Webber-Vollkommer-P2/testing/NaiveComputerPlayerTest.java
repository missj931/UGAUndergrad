import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class NaiveComputerPlayerTest {

	@Test
	public void testGenerateMove() {
		NaiveComputerPlayer test = new NaiveComputerPlayer();
		assert test.generateMove() != '8';
	}


	@Test
	public void testGetLetter() {
		NaiveComputerPlayer test = new NaiveComputerPlayer();
		assert test.getLetter() != 8;
	}


}
