/**
 *
 * ==================================================================
 * Assignment C:
 * Your Name:
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
    
    public void enterSchedule(String pTrain1Time, String pTrain1Guide, 
        String pTrain2Time, String pTrain2Guide)
    {
        // put your new code here
    }
    
    public void printFullSchedule()
    {
        // put your new code here
    }
    
    public void printTicket(int pDesiredTrain)
    {
        // put your new code here
    }
}
