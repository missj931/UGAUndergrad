/**
 * .AssignmentBTester. 
 * This is a test class to be used when you have completed
 * (or are working on) Assignment B. You should not make 
 * any changes to it, in order that you get a good test of 
 * your program.
 * 
 * To test your program, ask BlueJ to create a "NEW" AssignmentBTester.
 * You should get the output indicated on the eLC AssignmentB page.
 * 
 * @author Dr. Janine E. Aronson for her MIST 4600 class
 * @version 20140112
 * 
 * .AssignmentBTester. Wrapper.
 */
public class AssignmentBTester
{
    // instance variable (object, actually). Define/Declare it:
    private TwoDestinationMachine x; // x is the name of the TwoDestinationMachine object instantiated.

    /**
     * .AssignmentBTester. Constructor.
     */
    public AssignmentBTester()
    {
        // initialise instance variables (construct a TwoDestinationMachine x and invoke 5 of its methods):
        x = new TwoDestinationMachine("Atlanta", 500, "Stone Mountain", 400);
        x.insertMoney(1000);
        x.printTicket(2);
        x.printTicket(1);
        x.printTicket(2);
        x.printTicket(1);
    }

    
}
