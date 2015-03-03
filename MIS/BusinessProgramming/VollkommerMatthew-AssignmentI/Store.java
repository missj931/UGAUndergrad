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
 * Name:Matthew Vollkommer
 * Code Comment:I started this assignment late. So I do not have time to go through and comment as
 * thoughouly as I have been trained to do. Howeverm I folowed instructions, and it works without erro. Comments exist,
 * but as I said. I like being thourough.
 * 
 */

public class Store 
{
    private ArrayList<Item> items;
    private Movie thisMovie;
    private int thisMovieIndex;
    private String platform;
    private int thisKey;
    private String name;
    private String director;
    private int renterID;
    private int dueDate;
    private double price;
    private int currentDate;
    private Game thisGame;
    private ReadFile reader;
    private Item thisItem;    
    /**
     * Create the store. This means set up the ArrayList for movies.
     */
    public Store() 
    {
        items = new ArrayList<Item>();
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
            items.add(thisMovie);
            
            reader.readInputLine();
        }
    }
        /**
     * Read initial game info from file.
     */
    public void readFromGameFile() 
    {
        reader = new ReadFile("MoviesData.txt");
        reader.readInputLine();
        reader.setSeparator(",");
        while(! reader.eof())
        {
            thisKey = reader.getIntField(1);
            name = reader.getStringField(2);
            platform = reader.getStringField(3);
            price = reader.getDoubleField(4);
            renterID = reader.getIntField(5);
            dueDate = reader.getIntField(6);
            
            thisGame = new Game(thisKey, name, platform, price, renterID, dueDate);
            items.add(thisGame);
            
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
        while(index < items.size())
        {
            thisItem = (Item) items.get(index);
            thisItem.print();
            index ++;
        }
    }      
}
    