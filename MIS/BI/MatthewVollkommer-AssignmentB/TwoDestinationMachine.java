/**
 * TicketMachine (described below) has been modified to be the 
 * starting point for Assignment B. (D. Goodhue, 2013-08-19, Dr. Janine E. Aronson 20140112)
 * 
 * TicketMachine models a ticket machine that issues
 * flat-fare tickets.
 * The price of a ticket is specified via the constructor.
 * Instances will check to ensure that a user only enters
 * sensible amounts of money, and will only print a ticket
 * if enough money has been input.
 * 
 * @author David J. Barnes and Michael Kolling, Matthew Vollkommer
 * @version 2006.03.30
 * January 22, 2014
 * 
 * *************************************************************
 * *************************************************************
 * Your Name:  Mtthew Vollkommer
 * Explain how your program works or what went wrong:
 * This assignment was enjoyable. I could imagine much similar 
 * work being required in the near future. Most ATMs run on
 * Windows XP, new development will be required to replace
 * the outdated software. Look it up in the news. Coding is fun.
 * I few errors occured from not properly pairing { with }.
 * Easy to avoid in the future. It works by asking for two destinations
 * and their prices.
 * *************************************************************
 * *************************************************************
 * Note: You cannot use the Testers until you correctly
 * modify the TwoDestinationMachine constructor.
 * *************************************************************
 * 
 */
public class TwoDestinationMachine
{

    // The amount of money entered by a customer so far.
    private int balance;
    // The total amount of money collected by this machine.
    private int total;
    
    //destination 1 and 2 names
    private String dest1Name;
    private String dest2Name;
    
    //destination 1 and 2 prices
    private int dest1Price;
    private int dest2Price;

    /**
     * Create a machine that issues tickets of the given price.
     */
    public TwoDestinationMachine (String pDest1Name, int pDest1Price, String pDest2Name, int pDest2Price)
    {
        balance = 0;
        total = 0;
        dest1Name = pDest1Name;
        dest2Name = pDest2Name;
        dest1Price = pDest1Price;
        dest2Price = pDest2Price;
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
    public void printTicket(int pDesiredDestination)
    {
        if(pDesiredDestination == 1)
        {
            if(balance >= dest1Price) {
            // Simulate the printing of a ticket.
            System.out.println("##################");
            System.out.println("# Ticket to " + dest1Name);
            System.out.println("# The BlueJ Line");
            System.out.println("# " + dest1Price + " cents.");
            System.out.println("##################");
            System.out.println();

            // Update the total collected with the price.
            total = total + dest1Price;
            // Reduce the balance by the prince.
            balance = balance - dest1Price;
        
        } else {
            System.out.println("You must insert at least: " +
                               (dest1Price - balance) + " more cents.");
        }
                    
        }else if(pDesiredDestination == 2)
        { 
            if(balance >= dest2Price) {
            // Simulate the printing of a ticket.
            System.out.println("##################");
            System.out.println("# The BlueJ Line");
            System.out.println("# Ticket to " + dest2Name);
            System.out.println("# " + dest2Price + " cents.");
            System.out.println("##################");
            System.out.println();

            // Update the total collected with the price.
            total = total + dest2Price;
            // Reduce the balance by the prince.
            balance = balance - dest2Price;
        
        }else {
            System.out.println("You must insert at least: " +
                               (dest2Price - balance) + " more cents.");
                    
        }


    }else {
        System.out.println("Only 1 and 2 are acceptable inputs.");
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

