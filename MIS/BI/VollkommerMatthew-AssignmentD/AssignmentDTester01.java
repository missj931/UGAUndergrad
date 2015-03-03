/**
 * .AssignmentDTester01.
 * 
 * @author Dr. Janine E. Aronson 
 * @version 20140119
 * 
 * Tests for the update to include seconds in the ClockDisplay.
 * 
 */
public class AssignmentDTester01
{
    // instance variables - replace the example below with your own
    private ClockDisplay x;
    private ClockDisplay y;
    private ClockDisplay z;

    /**
     * Constructor 
     */
    public AssignmentDTester01()
    {
        x = new ClockDisplay(23, 59, 57);
        x.timeTick();
        x.timeTick();
        x.timeTick();
        x.timeTick();
        x.timeTick();
        x.timeTick();
        System.out.println("Tester01 Done with Test 1-1. Next Test.");
        y = new ClockDisplay(11, 59, 40);
        int index = 0;
        while(index < 23)
        {
            y.timeTick();
            index++;
        }
        System.out.println("Tester01 Done with Test 1-2.");
    }
}
