/**
 * This Class holds basic information about a Movie.  
 * It allows checking out a movie and returning a movie..
 * 
 * @author Dale Goodhue
 * @version Movie0
 */
public class Movie
{
    // instance variables - replace the example below with your own
    private int movieKey;
    private String movieName;
    private int renterID;
    private int dueDate;
    private double price;
    private int timesRented;

    
     
     /**
     * Constructor for objects of class Movie when reading from file
     */
    public Movie(int pMovieKey, String pMovieName, int pRenterID, int pDueDate, double pPrice, int pTimesRented)
    {
        // initialise instance variables
        movieKey = pMovieKey;
        movieName = pMovieName;
        timesRented = pTimesRented;
        price = pPrice;
        renterID = pRenterID;
        dueDate = pDueDate;
    }     
    
    /**
     * The getMovieName method returns the name of this movie
     * 
     * @param  void   
     * @return  movieName  
     */
    public String getMovieName()
    {
        return movieName;
    }
     /**
     * The getKey method returns the key of this movie
     * 
     * @param  void   
     * @return  movieKey  
     */
    public int getMovieKey()
    {
        return movieKey;
    }
     /**
     * The getRenterID method returns the renterID of this movie
     * 
     * @param  void   
     * @return  renterID  
     */
    public int getRenterID()
    {
        return renterID;
    }
     /**
     * The getDueDate method returns the renterID of this movie
     * 
     * @param  void   
     * @return  dueDate  
     */
    public int getDueDate()
    {
        return dueDate;
    }
    /** The getTimesRented method returns the timesRented for this movie
     * 
     * @param  void   
     * @return  timesRented;  
     */
    public int getTimesRented()
    {
        return timesRented;
    }
    /** The getPrice method returns the price for this movie
     * 
     * @param  void   
     * @return  price;  
     */
    public double getPrice()
    {
        return price;
    }
    
    
    /**
     * The print method prints out the values for most fields in Movie
     * 
     * @param  void  
     * @return  void  
     */
    
    public void print()
    {
        // The below just makes sure that the number of spaces printed for 
        // for renterID and dueDate is the same, whether the movie is rented 
        // or not.  In other words, if renterID is equal to zero (not rented)
        // this makes sure that a zero and several blanks are printed out. 
        // This keeps the formating the same for rented out and not rented out
        // movies.
        
        String printRenterID = new Integer(renterID).toString();
        if (renterID == 0)printRenterID = printRenterID + "  ";
        String printDueDate = new Integer(dueDate).toString();
        if (dueDate == 0)printDueDate = printDueDate + "       ";
        
        System.out.println(movieKey + "        " + movieName + "  " + printRenterID 
            + "    " + printDueDate + "   " + price);
        return; 
    }
    
    public void updatePrice(double pNewPrice)
    {
        if(pNewPrice > 0)
        {
            price = pNewPrice;
            System.out.println("Price for " + movieName + " updated to " + price);
        }
        else
        {
            System.out.println(pNewPrice + " is an invalid price.");
        }
    }
    
    public void updateDueDate(int pNewDueDate)
    {
        if(pNewDueDate >= 0)
        {
            dueDate = pNewDueDate;
            System.out.println("dueDate for " + movieName + " updated to " + pNewDueDate);
        }
        else
        {
            System.out.println(pNewDueDate + " is an invalid dueDate.");
        }
    }
   
    public void updateRenterID(int pRenterID)
    {
        if(pRenterID >= 0)
        {
            renterID = pRenterID;
            System.out.println("renterID for " + movieName + " updated to " + pRenterID);
        }
        else
        {
            System.out.println(pRenterID + " is an invalid renterID.");
        }
    }
}
