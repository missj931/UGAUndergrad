/**
 * .AssignmentCTester02.
 * This will test the TicketMachineWithSchedule
 * 
 * @author Aronson/Goodhue
 * @version 20140119
 * 
 * @author Solution by Dr. Janine E. Aronson
 * @version 20130829Thu
 * 
 */
public class AssignmentCTester02
{
    // Attributes of our class; and of any object of this class
    private TicketMachineWithSchedule theTicketMachine;

    /**
     * Constructor for AssignmentCTester02
     */
    public AssignmentCTester02()
    {
        // set up a new TicketMachineWithSchedule object, calling it "theTicketMachine".
        theTicketMachine = new TicketMachineWithSchedule(500);
        theTicketMachine.enterSchedule("8:00 AM", "Smyth", "10:00 AM", "Jynes");
        testPrintingTickets();
        theTicketMachine.printFullSchedule();
        
    }   
     
        // 
    public void testPrintingTickets()
    {
        theTicketMachine.insertMoney(2900);

        theTicketMachine.printTicket(1);
        theTicketMachine.printTicket(2);
        theTicketMachine.printTicket(1);
        theTicketMachine.printTicket(1);
        theTicketMachine.printTicket(2);
        theTicketMachine.printTicket(2);
    }   
        
    public void testEnteringASchedule()   
    {    
        theTicketMachine.enterSchedule("8:00 AM", "Smyth", "10:00 AM", "Jynes");

    }
}
