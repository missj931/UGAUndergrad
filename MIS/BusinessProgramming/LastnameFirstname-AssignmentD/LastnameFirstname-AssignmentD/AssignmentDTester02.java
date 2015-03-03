/**
 * .AssignmentDTester02.
 * 
 * @author Dr. Janine E. Aronson 
 * @version 20140119
 * 
 * Tests for the update to include seconds in the ClockDisplay.
 * This tester checks for timeTickMinute
 */
public class AssignmentDTester02
{
    // instance variables
    private ClockDisplay x;
    private ClockDisplay y;
    private ClockDisplay z;

    /**
     * Constructor 
     */
    public AssignmentDTester02()
    {
        x = new ClockDisplay(23, 40, 57); // Set the clock
        int index = 0; // increment minutes 20 times
        while(index < 19)
        {
            x.timeTickMinute();
            index++;
        }
        // Next increment seconds 5 times
        x.timeTick();
        x.timeTick();
        x.timeTick();
        x.timeTick();
        x.timeTick();
        System.out.println("Tester02 Done with Test 2-1. Next Test.");
        y = new ClockDisplay(11, 59, 40);
        index = 0;
        while(index < 23)
        {
            y.timeTick();
            index++;
        }
        System.out.println("Tester02 Done with Test 2-2.");
    }
}
