/**
 * Class Tester1Aronson here.
 * 
 * @author Dr. Janine E. Aronson 
 * @version 20140323
 */
public class Tester1Aronson
{
    // instance variable
    private Store myStore;

    /**
     * Constructor for objects of class TesterAronson
     */
    public Tester1Aronson()
    {
        // initialise instance variables
        myStore = new Store();
        myStore.readFromMovieFile();
        myStore.readFromGameFile();
        myStore.listAllItems();
        myStore.checkoutItem(234, 2244);
        myStore.checkoutItem(234, 5000);
        myStore.checkoutItem(245, 2245);
        myStore.checkoutItem(245, 1237);
        myStore.checkoutItem(245, 2464);
        myStore.listAllItems(); 
    }
}
