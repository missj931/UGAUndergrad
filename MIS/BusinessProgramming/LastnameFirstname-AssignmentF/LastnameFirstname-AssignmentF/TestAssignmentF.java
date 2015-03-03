/**
 * .TestAssignmentF.
 * 
 * @author Dr. Janine E. Aronson 
 * @version 20140123
 */
public class TestAssignmentF
{
    // instance variable
    private Store x;

    /**
     * Constructor
     */
    public TestAssignmentF()
    {
        x = new Store();
        x.readFromMovieFile();
        x.listAllMoviesWithIndices();
        x.removeMovie(4);
        x.addANewMovie(654, "Michael Clayton         ", 3.25);
        x.listAllMoviesWithIndices();
        x.removeMovie(40);
    }
}
