/**
 * Name: Matthew Vollkommer
 * Code Status: 2/5/2014
 * 
 * Assignment D - see the Assignment writeup.
 * 
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kolling and David J. Barnes And Matthew Vollkommer
 * @version 2014.05.02
 */
public class ClockDisplay
{
    private NumberDisplay seconds;  //add seconds
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60); //add seconds, set to 60
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60); //add seconds, set to 60
        int second = 60; // initialize second to defualt value of 60
        setTime(hour, minute, second);  //include seconds when setting time
    }

    //create a new constructor, including seconds
    public ClockDisplay(int hour, int minute, int second){
        //initialize variables
        hours = new NumberDisplay(24); 
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        //set the time, including seconds
        setTime(hour, minute, second);
    }
    
    
    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    /*
     * Modify the timeTick method to increment the ClockDisplay by one second. 
     * This is a bit tricky. Exercise caution.
     */
    public void timeTick()
    {
        seconds.increment();
        if(seconds.getValue() == 0)
        {  // it just rolled over!
                 minutes.increment();
                 if(minutes.getValue() == 0)
                 {  // it just rolled over!
                     hours.increment();
                    }
        }
       
        updateDisplay();
    }
        
    /**
     * First, copy method timeTick and rename the copied method timeTickMinute. 
     * This way you can tick either by a minute or by a second. Method timeTick 
     * will increment by one second; method timeTickMinute will increment by one minute. 
     * (You could write a timeTickHour method as well for better functionality.)
     */
    
    public void timeTickMinute()
    {
        minutes.increment();
        if(minutes.getValue() == 0)
        {  // it just rolled over!
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    //modify setTime method to include int seconds
    public void setTime(int hour, int minute, int second)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        //include seconds
        seconds.setValue(second);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal String that represents the display.
     */
    private void updateDisplay()
    {
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue() + ":" + seconds.getDisplayValue();  //add seconds to the display
        // added output to terminal window
        System.out.println(displayString); // This prints out the displayString with every update.
    }
}
