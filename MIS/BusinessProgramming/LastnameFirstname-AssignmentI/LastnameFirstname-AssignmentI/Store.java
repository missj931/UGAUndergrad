import java.util.Iterator;
import java.util.ArrayList;

/*
 * .Assignment I. This is the Store class.
 * 
 * You will be doing a lot of work in this and the Movie class,
 * and be creating new classes.
 * 
 * Be sure to put your name and a comment about what works 
 * and what does not work below:
 * 
 * Name:
 * Code Comment:
 * 
 */

public class Store 
{
    private ArrayList<Movie> movies;
    private Movie thisMovie;
    private int thisMovieIndex;

    private int thisKey;
    private String name;
    private String director;
    private int renterID;
    private int dueDate;
    private double price;
    private int currentDate;
    
    private ReadFile reader;
        
    /**
     * Create the store. This means set up the ArrayList for movies.
     */
    public Store() 
    {
        movies = new ArrayList<Movie>();
    }

    /**
     * Read initial movie info from file.
     */
    public void readFromMovieFile() 
    {
        reader = new ReadFile("MoviesData.txt");
        reader.readInputLine();
        reader.setSeparator(",");
        while(! reader.eof())
        {
            thisKey = reader.getIntField(1);
            name = reader.getStringField(2);
            director = reader.getStringField(3);
            price = reader.getDoubleField(4);
            renterID = reader.getIntField(5);
            dueDate = reader.getIntField(6);
            
            thisMovie = new Movie(thisKey, name, director, price, renterID, dueDate);
            movies.add(thisMovie);
            
            reader.readInputLine();
        }
    }
    
      
    /*
     * .ListAllItems.
     * List all movies in the system.
     */
    public void listAllItems()
    {
        int index = 0;
        System.out.println();
        System.out.println("List Of All Items");
        System.out.println("Key     Name                      Renter ID  Due Date   Price");
        while(index < movies.size())
        {
            thisMovie = (Movie) movies.get(index);
            thisMovie.print();
            index ++;
        }
    }      
}
    