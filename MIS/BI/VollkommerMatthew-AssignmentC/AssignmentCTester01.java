/**
 * .AssignmentCTester01.
 * This will test the TicketMachineWithSchedule
 * 
 * @author Aronson/Goodhue
 * @version 20140119
 * 
 * @author Solution by Dr. Janine E. Aronson
 * @version 20130829Thu
 * 
 */
public class AssignmentCTester01
{
    // Attributes of our class; and of any object of this class
    private TicketMachineWithSchedule theTicketMachine;

    /**
     * Constructor for AssignmentCTester01
     */
    public AssignmentCTester01()
    {
        // set up a new TicketMachineWithSchedule object, calling it "theTicketMachine".
        theTicketMachine = new TicketMachineWithSchedule(400);
        theTicketMachine.enterSchedule("9:00 AM", "Smith", "10:30 AM", "Jones");
        testPrintingTickets();
        theTicketMachine.printFullSchedule();
        
    }   
     
        // 
    public void testPrintingTickets()
    {
        theTicketMachine.insertMoney(1300);

        theTicketMachine.printTicket(1);
        theTicketMachine.printTicket(2);
        theTicketMachine.printTicket(1);
        theTicketMachine.printTicket(1);
    }   
        
    public void testEnteringASchedule()   
    {    
        theTicketMachine.enterSchedule("9:00 AM", "Smith", "10:30 AM", "Jones");

    }


}
