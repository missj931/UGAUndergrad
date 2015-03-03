import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ HangmanBoardTest.class, NaiveComputerPlayerTest.class,
		RandomComputerPlayerTest.class, PlayFasterComputerPlayerTest.class })
public class AllTests {

}
