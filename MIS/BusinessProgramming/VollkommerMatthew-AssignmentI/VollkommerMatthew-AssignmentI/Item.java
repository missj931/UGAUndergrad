import java.text.NumberFormat;
/**
 * This Class (when you complete the assignment) will extend Item, 
 * to hold unique information about a Item.  
 * 
 * 
 * @author Dale Goodhue
 * @version Item0
 */
public class Item 
{
    // instance variables - 
    private int key;
    private String name;
    private int renterID;
    private int dueDate;
    private double price;
    
    
    private NumberFormat currency = NumberFormat.getCurrencyInstance();



    /**
     * .print. will print the first part of the line, then will call the super class print method.
     * 
     * @param  void
     * @return  void  
     */
    public void print()
    {
        
        // Don't worry about the code below down to the next comment.
        // It just makes sure that the number of spaces printed for 
        // for renterID and dueDate is the same, whether the Item is rented 
        // or not.  In other words, if renterID is equal to zero (not rented)
        // this makes sure that a zero and several blanks are printed out. 
        // This keeps the formating the same for rented out and not rented out
        // Items.
        
        String printRenterID = new Integer(renterID).toString();
        if (renterID == 0)printRenterID = printRenterID + "  ";
        String printDueDate = new Integer(dueDate).toString();
        if (dueDate == 0)printDueDate = printDueDate + "       ";
        
        // above is code to neaten up the printlines, as explained in the previous comment.  
        
        System.out.println(key + "    " + name + "  " + printRenterID 
            + "        " + printDueDate + "   " + currency.format(price) + "   " );

        return;
    }
    
    /**
     * .chargeOut. To charge (check) out a Item object.
     */
    public int chargeOut(int pCustID, int currentDate)
    {
        setRenterID(pCustID);
        int dueDate = currentDate + 5;
        setDueDate(dueDate);
        return dueDate;
    }
    /**
     * .getName. returns the name of this Item item.
     * 
     * @param  void   
     * @return  Name  
     */
    public String getName()
    {
        return name;
    }
     /**
     * .getKey. returns the key of this Item item.
     * 
     * @param  void   
     * @return  Key  
     */
    public int getKey()
    {
        return key;
    }
     /**
     * .getRenterID. returns the renterID of this Item item.
     * 
     * @param  void   
     * @return  renterID  
     */
    public int getRenterID()
    {
        return renterID;
    }
         /**
     * .getDueDate. returns the renterID of this Item item.
     * 
     * @param  void   
     * @return  dueDate  
     */
    public int getDueDate()
    {
        return dueDate;
    }
    /**
     * .changeRenterID. places a new renterID in the field.
     * 
     * @param  newRenterID   
     * @return  void  
     */
    public void changeRenterID(int newRenterID)
    {
        renterID = newRenterID;
        return ;
    }
    /**
     * .changeDueDate. places a new DueDate in the field
     * 
     * @param  newDueDate   
     * @return  void  
     */
    public void changeDueDate(int newDueDate)
    {
        dueDate = newDueDate;
        return ;
    }
    
    /** 
     * 
     * .setRenterID. puts a new value in RenterID.
     * 
     */
    public void setRenterID(int newRenterID)
    {
        renterID = newRenterID;
        return;
    }
     /** 
     * 
     * .setDueDate. puts a new value in dueDate.
     * 
     */
    public void setDueDate(int newDueDate)
    {
        dueDate = newDueDate;
        return;
    }
    /** 
     * 
     * .updatePrice. puts a new value in price.
     * 
     */
    public void updatePrice(double newPrice)
    {
        price = newPrice;
        return;
    }
    /** 
     * 
     * .getPrice. returns the value of price.
     * 
     */
    public double getPrice()
    {
        return price;
    }
     
}