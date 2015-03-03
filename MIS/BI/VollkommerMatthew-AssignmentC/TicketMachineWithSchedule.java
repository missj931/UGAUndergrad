/**
 *
 * ==================================================================
 * Assignment C:
 * Your Name: Mathew Vollkommer
 * Code Comment:
 * ==================================================================
 * 
 * TicketMachineWithSchedule-Start models a ticket machine that issues
 * flat-fare tickets for the Stone Mountain Tour.
 * This starting program is basically the BetterTicketMachine, but
 * with stubs for two new methods -- enterSchedule and printTicket with a desired train.
 * 
 * 
 * @author David J. Barnes and Michael Kolling and Dale Goodhue
 * @version 2013.08.28
 */
public class TicketMachineWithSchedule
{
    // The price of a ticket from this machine.
    private int price;
    // The amount of money entered by a customer so far.
    private int balance;
    // The total amount of money collected by this machine.
    private int total;

   /** 1. Add four new attributes, all of them “Strings”: train1Time, train1Guide, train2Time, and
    * train2Guide. DO NOT MODIFY THE CONSTRUCTOR.
   **/
   private String train1Time,train1Guide,train2Time,train2Guide;
   
    /**
     * Create a machine that issues tickets of the given price.
     * Do not update this constructor's parameters.
     */
    public TicketMachineWithSchedule(int ticketCost)
    {
        price = ticketCost;
        balance = 0;
        total = 0;
    }

    /**
    2. Leaving the constructor the same, add a new method with the following header:
      * A stub for this method is already added at the end of the TicketMachingWithSchedule class. 
      * This method should take the four parameter values and put them in the appropriate attributes. Add system comments 
      * to each new method you write.
    */
    //public void enterSchedule(String pTrain1Time, String pTrain1Guide, String pTrain2Time, String pTrain2Guide)
    public void enterSchedule(String pTrain1Time, String pTrain1Guide, String pTrain2Time, String pTrain2Guide){
        train1Time = pTrain1Time;
        train1Guide = pTrain1Guide;
        train2Time = pTrain2Time;
        train2Guide = pTrain2Guide;
    }
    /**
    3. Add a new printTicket method that it requires a parameter value indicating the desired train, 
    and prints the additional information. (You can keep the original printTicket method in the code. 
    Java allows this.) A stub for this method is already added at the end of the TicketMachingWithSchedule class.
    For Example, printTicket(1) might produce: 
    ##################
    # The BlueJ Line -- Stone Mountain Tour 
    # Departure: 9:00 AM Guide: Smith.
    # Ticket
    # 550 cents.
    ##################
    You can leave the old printTicket() method in, or delete it, either one.
    */
   public void printTicket(int pDesiredTime){
       if(balance >= price){
           if (pDesiredTime == 1){
        
            // Simulate the printing of a ticket.
            System.out.println("##################");
            System.out.println("# The BlueJ Line -- Stone Mountain Tour");
            System.out.println( "Departure: " + train1Time + "\t" + "Guide: " + train1Guide);
            System.out.println("# Ticket");
            System.out.println("# " + price + " cents.");
            System.out.println("##################");
            System.out.println();

            // Update the total collected with the price.
            total = total + price;
            // Reduce the balance by the prince.
            balance = balance - price;
        }
            else if (pDesiredTime == 2){
                        // Simulate the printing of a ticket.
            System.out.println("##################");
            System.out.println("# The BlueJ Line -- Stone Mountain Tour");
            System.out.println( "Departure: " + train2Time + "\t" + "Guide: " + train2Guide);
            System.out.println("# Ticket");
            System.out.println("# " + price + " cents.");
            System.out.println("##################");
            System.out.println();

            // Update the total collected with the price.
            total = total + price;
            // Reduce the balance by the prince.
            balance = balance - price;
        }
        else { 
            System.out.println("You must enter 1 or 2");
        }
    }
    else {
            System.out.println("You must insert at least: " +
                               (price - balance) + " more cents.");
                    
    }
    }
   /**
    4. Add a PrintFullSchedule method with no parameters that will print out the total schedule. A
    stub for this method is already added at the end of the TicketMachingWithSchedule class. For example, 
    PrintFullSchedule() should produce something such as:
    Stone Mountain Railroad Full Schedule:
    Train #    Departure Time  Guide
    1          9:00 AM         Smith
    2          10:30 AM        Jones
   */  
  
  public void printFullSchedule(){
      System.out.println("Train #" + "\t" + "Departure Time" + "\t" + "Guide");
      System.out.println(" 1 " + " \t " + train1Time + " \t " + train1Guide);
      System.out.println(" 2 " + " \t " + train2Time + " \t " + train2Guide);
    }
    
   /**
     * @Return The price of a ticket.
     */
    public int getPrice()
    {
        return price;
    }

    /**
     * Return The amount of money already inserted for the
     * next ticket.
     */
    public int getBalance()
    {
        return balance;
    }

    /**
     * Receive an amount of money in cents from a customer.
     * Check that the amount is sensible.
     */
    public void insertMoney(int amount)
    {
        if(amount > 0) {
            balance = balance + amount;
        }
        else {
            System.out.println("Use a positive amount: " +
                               amount);
        }
    }

    /**
     * Print a ticket if enough money has been inserted, and
     * reduce the current balance by the ticket price. Print
     * an error message if more money is required.
     */
    public void printTicket()
    {
        if(balance >= price) {
            // Simulate the printing of a ticket.
            System.out.println("##################");
            System.out.println("# The BlueJ Line -- Stone Mountain Tour");
            System.out.println("# Ticket");
            System.out.println("# " + price + " cents.");
            System.out.println("##################");
            System.out.println();

            // Update the total collected with the price.
            total = total + price;
            // Reduce the balance by the prince.
            balance = balance - price;
        }
        else {
            System.out.println("You must insert at least: " +
                               (price - balance) + " more cents.");
                    
        }
    }

    /**
     * Return the money in the balance.
     * The balance is cleared.
     */
    public int refundBalance()
    {
        int amountToRefund;
        amountToRefund = balance;
        balance = 0;
        return amountToRefund;
    }
}