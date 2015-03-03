import javax.swing.JFrame;

//*************************
//Drives the Game
public class P3 {
	// -----------------------------------------------------------------
	// Creates and displays the main program frame.
	// -----------------------------------------------------------------
	public static void main(String[] args) {

		// YesNo Panel
		JFrame playFrame = new JFrame("Settings Panel");
		playFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playFrame.setSize(1000, 1000);
		
		//does the person want to play the game?
		playFrame.getContentPane().add(new FittsLaw());

		playFrame.pack();

		playFrame.setVisible(true);

	}
}
