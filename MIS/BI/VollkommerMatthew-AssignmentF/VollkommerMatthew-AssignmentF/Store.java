import java.util.ArrayList;
/** 
 * Name: Matthew Vollkommer
 * Code comment:
 * 1 listAllMoviesWithIndices(). This is much the same as the listMoviesWithWhile() method, but you need to (1) add some headers to the printout, and (2) use a line of code such as the following before the main System.out.println statement used in listMoviesWithWhile():
System.out.print(“ “ + index + “ “)
[Note that the above is not System.out.println(“ “ + index + “ “) ]
The above statement will print out the value of the index, but then wait without spacing down a line for the next thisMovie.print(). In this way you will get, all on the same line, the index value for each movie before its info is printed out.
2 addANewMovie(int pMovieKey, String pMovieName, double pPrice). This takes the parameter values, creates a new Movie object, and then adds that new Movie object to
?1
the end of the ArrayList. To add to the end of the ArrayList, you will need to use the “add(nameOfObject)” method with the “movies” ArrayList as was done in the readFromMovieFile method. Note that renterID, dueDate and timesRented are not in the parameter list. For new movies, it is assumed they have not yet ever been checked out by anyone. You will need to provide to the Movie constructor all the needed data as parameters. Note that this includes dueDate, renterID and timesRented, even though their values will all be zero. For the other parameters, choose your favorite movie or movies and make up any data needed. Again, remember that the order of the parameters matters!
Don’t worry about duplicate movieKey values. We can consider that later.
3 removeMovie(int desiredIndex) This causes the movie that has that index to be removed (permanently) from the movies ArrayList. (Note that you will never be removing movies from the “MovieData.txt” file, so you can always get back to the starting position if you want.) You will need to use the ArrayList “remove(int index)” method to do this for ArrayList movies.
Protect your program from bombing. Make sure that if someone inters an index less than zero or bigger than the size of the ArrayList, you catch it and do not try to execute the change. Without this protection you could get an “index out of bounds” error. Try your program without this protection to see.
4 UpdateMoviePrice(int pIndex, double pNewPrice). This method allows you to change the price of a particular movie in your ArrayList. I recommend that you “get” the particular movie indicated by pIndex and put it into the holding memory location “thisMovie”, and then change the price for that movie. Hint: the Movie class has a method called updatePrice(double pNewPrice).
You’ll want similar protection against a bad index here as you put in the third new method above.

 * 
 * 
 * 
 * Author:  Goodhue, D. and Janine E. Aronson & Matthew Vollkommer
 * Version:  20140301
 * 
 * Assignment F. See the writeup for required tasks.
 * 
 * add four new methods in the Store class, as in assignment pdf
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
    private int z = 20110918;
    
    
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
    //problem 1; adds the ability to print all movies with index
    public void listAllMoviesWithIndices(){
        System.out.println("Listing MoviesWithIndices");
        System.out.println("Index" + "\t" + "Movie ID    Movie Name                Cust ID DueDate  price");
       int index = 0;
        // loop through the movies
        while(index < movies.size())
        {
            System.out.print(" " + index + "\t" );
            Movie thisMovie = movies.get(index);
            thisMovie.print();
            
            index ++;
        }
    System.out.println(); //empty line after all movies are printed
    }
            //problem 2
    public void addANewMovie(int pMovieKey, String pMovieName, double pPrice){
        
        Movie thisMovie = new Movie(pMovieKey, pMovieName, 0, 0, pPrice, 0);        // Declares a Movie object caled thisMovie
        movieKey = pMovieKey; //sets the moviekey
        movieName = pMovieName;  //sets the moviename
        price = pPrice; //sets the price

    movies.add(thisMovie); //adds the movie objiect to the arraylist
        
    }
    //3, takes in the index number of the movie to be removed
    public void removeMovie(int desiredIndex){
        try{
            movies.remove(desiredIndex);
        }catch (Exception e){
            System.out.println("error occured, no change was made");
    }
}
    //4, takes in the index of the movie to be updated and the update price
    public void updateMoviePrice(int pIndex, double pNewPrice){
   
        try{
            thisMovie = movies.get(pIndex);
            thisMovie.updatePrice(pNewPrice);
            movies.set(pIndex, thisMovie);
        }catch (Exception e){
            System.out.println("error occured, no change was made");
        }
}
}


    