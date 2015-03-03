
/**
 * Write a description of class House here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class House
{
    // instance variables - replace the example below with your own
    private Square wall;
    private Square window;
    private Triangle roof;
    /**
     * Constructor for objects of class House
     */
    public House(Square pWall, Square pWindow, Triangle pRoof)
    {
        wall = pWall;
        window = pWindow;
        roof = pRoof;
    }

    
    public Square getWall()
    {
        // put your code here
        return wall;
    }
    public Square getWindow()
    {
        // put your code here
        return window;
    }
    public Triangle getRoof()
    {
        // put your code here
        return roof;
    }
}
