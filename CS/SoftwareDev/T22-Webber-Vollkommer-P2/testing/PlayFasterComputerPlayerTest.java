import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PlayFasterComputerPlayerTest {


	@Test
	public void testGenerateMove() {
		PlayFasterComputerPlayer test = new PlayFasterComputerPlayer(1);
		assert test.generateMove("-a-", "abc") != 'a';
		assert test.generateMove("-a-", "abc") != 'c';
		assert test.generateMove("-a-", "abc") != 'b';
				
	}





}
