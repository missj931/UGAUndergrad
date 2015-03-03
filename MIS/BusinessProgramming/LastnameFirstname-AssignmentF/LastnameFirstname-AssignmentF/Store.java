import java.util.ArrayList;
/** 
 * Name:
 * Code comment:
 * 
 * 
 * 
 * 
 * Author:  Goodhue, D. and Janine E. Aronson
 * Version:  20140301
 * 
 * Assignment F. See the writeup for required tasks.
 * 
 */

public class Store 
{
    private ArrayList<Movie> movies;     // Declares an ArrayList object called movies
                                         // It is set up to contain the following type of object:  Movie
    private Movie thisMovie;             // Declares a Movie object caled thisMovie
    
    
    private int movieKey;
    private String movieName;
    private int renterID;
    private int dueDate;
    private double price;
    private int timesRented;
    private int currentDate = 20110918;
    
    
    private ReadFile reader;
        
    /**
     * Constructor. Create the store. This means set up the ArrayList movies.
     */
    public Store() 
    {
        movies = new ArrayList<Movie>();
    }

   
    /**
     * .readFromMovieFile.
     * Read initial movie info from file.
     */
    public void readFromMovieFile() 
    {
        // Sets up the ReadFile object to read from the text file of movie data.
        
        reader = new ReadFile("MovieData.txt");
       
        reader.setSeparator(",");
        
        // before the loop:  read the first line of the file
        reader.readInputLine();
        
        
        while(!reader.eof())        
        {
            movieKey = reader.getIntField(1);
            movieName = reader.getStringField(2);
            renterID = reader.getIntField(3);
            dueDate = reader.getIntField(4);
            price = reader.getDoubleField(5);
            timesRented = reader.getIntField(6);

            thisMovie = new Movie(movieKey, movieName, renterID, dueDate, price, timesRented);
            movies.add(thisMovie);
            
            reader.readInputLine();
        }
        System.out.println("All movies read from the file and added to the ArrayList");
    }

    /**
     *  .printAllMovieInfoForThisMovie.
     *  Given the index in the ArrayList movies for a particular movie, 
     *  print all info for this movie.
     */
     public void printAllMovieInfoForThisMovie(int index)
    {
        System.out.println("Movie ID    Movie Name                Cust ID       DueDate");
        Movie thisMovie = movies.get(index);
        thisMovie.print();
    }
      
    /*
     * .listMoviesWithWhile.
     * List all movies in the system with a while loop
     */ 
    public void listMoviesWithWhile()
    {
        System.out.println("Listing Movies with while()");
        int index = 0;
        while(index < movies.size())
        {
            Movie thisMovie = movies.get(index);
            thisMovie.print();
            
            index ++;
        }
        // Print a blank line at the bottom
        System.out.println();
    }
    
    
}
    