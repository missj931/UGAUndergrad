import java.util.ArrayList;
/**
 * Name: 
 * Date: 
 * Assignment 02
 * 
 * Do your work at the bottom.
 * See the accompanying document for instructions.
 * 
 */
public class Picture7
{
    public Square wall;
    public Square window;
    public Triangle roof;
    public Circle sun;
    public House thisHouse;
    
    
    // Don't worry about the below.  It just defines a collection basket for houses.
    private ArrayList<House> houses;

    /**
     * Constructor for objects of class Picture.  All this does is set up a way to 
     * store multiple houses.  Don't worry about how it works -- that is for much later!
     */
    public Picture7()
    {
        // Don't worry about the below.  It just sets up a collection basket for houses.
        houses = new ArrayList<House>();
    }

    /**
     * A method to draw a new house.
     */
    
    public void drawSmallHouse()
    {
        wall = new Square();
        wall.moveVertical(80);
        wall.changeSize(30);
        wall.makeVisible();

        window = new Square();
        window.changeColor("black");
        window.changeSize(10);
        window.moveHorizontal(4);
        window.moveVertical(85);
        window.makeVisible();

        roof = new Triangle();
        roof.changeSize(15, 45);
        roof.moveHorizontal(25);
        roof.moveVertical(102);
        roof.makeVisible();
        
        /**
         * Since we only want one sun but might have many houses, this checks to see 
         * if there is no sun right now and only draws one if the sun is currently
         * "null" (i.e. not there).
         */
        if(sun == null)
        {
            sun = new Circle();
            sun.changeColor("yellow");
            sun.moveHorizontal(0);
            sun.moveVertical(-10);
            sun.changeSize(20);
            sun.makeVisible();
        }
        
        // don't worry about the below.  
        // Having drawn the pieces of the new house, this creates a "house" of those pieces, 
        // so we can refer to all the pieces by one name.  The "house" is now added to the collection of houses.
        thisHouse = new House(wall, window, roof);
        houses.add(thisHouse);
    }
    
    public void drawFourHouses()
    {
        drawSmallHouse();
        moveThisHouse(120,-60);
        drawSmallHouse();
        moveThisHouse(120,0);
        drawSmallHouse();
        moveThisHouse(120,60);
        drawSmallHouse();
        moveThisHouse(120,120);
    }
    
    /**
     * Don't worry about the below method.  You only need to know how to use, not how it works!
     * 
     * This program, in essence, is only "looking" at one house at a time.  
     * If you create a new house, it is "looking" at that new house.
     * If you created multiple houses, they are numbered from 0 to N-1.
     * To get the program to "look" at a different house, use the 
     * focusOnThisHouse method with the number of the house you want.  
     */
    public void focusOnThisHouse(int id)
    {
        if(id >= 0 && id < houses.size())
        {
            thisHouse = houses.get(id);
            wall = thisHouse.getWall();
            window = thisHouse.getWindow();
            roof = thisHouse.getRoof();
        }
        else
        {System.out.println("Index out of bounds.");
        }
    }
    public void moveThisHouse(int right, int down)
    {
        if(wall != null)   // only if it's painted already...
        {
            wall.moveHorizontal(right);
            wall.moveVertical(down);
            window.moveHorizontal(right);
            window.moveVertical(down);
            roof.moveHorizontal(right);
            roof.moveVertical(down);
        }
    }

    
   
    /**
     * Change this thisHouse to black/white display
     */
    public void setThisHouseBlackAndWhite()
    {
        if(wall != null)   // only if it's painted already...
        {
            wall.changeColor("black");
            window.changeColor("white");
            roof.changeColor("black");
            sun.changeColor("white");
        }
    }

    /**
     * Change thisHouse to use color display
     */
    public void setThisHouseColor()
    {
        if(wall != null)   // only if it's painted already...
        {
            wall.changeColor("red");
            window.changeColor("black");
            roof.changeColor("green");
            sun.changeColor("yellow");
        }
    }
    public void changeThisHouseWallColor(String newColor)
    {
        if(wall != null)   // only if it's painted already...
        {
            wall.changeColor(newColor);
            window.changeColor("black");
        }
    }
    public void changeThisHouseWindowColor(String newColor)
    {
        if(wall != null)   // only if it's painted already...
        {
            window.changeColor(newColor);
        }
    }
    public void example()
    {   
        drawFourHouses();
        changeThisHouseWallColor("blue");
        focusOnThisHouse(1);
        changeThisHouseWindowColor("yellow");
        focusOnThisHouse(0);
        setThisHouseBlackAndWhite();         
    }
    
    public void assign02a()
    {
        // put your solution to Assign02a here. Instructions appear in the accompanying document.
        drawFourHouses();
        drawFourHouses();
        
        focusOnThisHouse(0);
        moveThisHouse(-30,-25);
        setThisHouseBlackAndWhite();
        
        focusOnThisHouse(1);
        moveThisHouse(-30,-20);
        setThisHouseBlackAndWhite();
        
        
        focusOnThisHouse(2);
        moveThisHouse(-30,0);
        setThisHouseBlackAndWhite();
        
        focusOnThisHouse(3);
        moveThisHouse(-30,20);
        setThisHouseBlackAndWhite();
        
        focusOnThisHouse(4);
        moveThisHouse(30,-25);
        
        
        focusOnThisHouse(5);
        moveThisHouse(30,-20);
        
        
        focusOnThisHouse(6);
        moveThisHouse(30,0);
        changeThisHouseWallColor("yellow");
        
        focusOnThisHouse(7);
        moveThisHouse(30,20);
        changeThisHouseWindowColor("green");
        
    
    }

    public void assign02b()
    {
    drawFourHouses();
        
        focusOnThisHouse(0);
        moveThisHouse(-30,-30);
        setThisHouseBlackAndWhite();
        
        focusOnThisHouse(1);
        moveThisHouse(-20,-30);
        setThisHouseBlackAndWhite();
        
        
        focusOnThisHouse(2);
        moveThisHouse(0,-30);
        setThisHouseBlackAndWhite();
        
        focusOnThisHouse(3);
        moveThisHouse(20,-30);
        setThisHouseBlackAndWhite();
        
        drawFourHouses();
        
        focusOnThisHouse(4);
        moveThisHouse(-30,0);
        setThisHouseBlackAndWhite();
        
        focusOnThisHouse(5);
        moveThisHouse(-20,0);
        setThisHouseBlackAndWhite();
        
        
        focusOnThisHouse(6);
        moveThisHouse(0,0);
        setThisHouseBlackAndWhite();
        
        focusOnThisHouse(7);
        moveThisHouse(20,0);
        setThisHouseBlackAndWhite();
        
        
        drawFourHouses();
        
        
        focusOnThisHouse(8);
        moveThisHouse(-30,0);
        setThisHouseBlackAndWhite();
        
        focusOnThisHouse(9);
        moveThisHouse(-20,0);
        setThisHouseBlackAndWhite();
        
        focusOnThisHouse(10);
        moveThisHouse(-120,-60);
        moveThisHouse(-20,30);
        setThisHouseBlackAndWhite();
        
        focusOnThisHouse(11);
        moveThisHouse(-120,-120);
        moveThisHouse(-20,30);
        changeThisHouseWallColor("yellow");
         changeThisHouseWindowColor("green");
        
        

    }
}
