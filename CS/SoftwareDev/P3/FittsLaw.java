//import packages

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

//*******************
//Fitts Law class

public class FittsLaw extends JPanel {

	public int x;
	public int y;
	private JButton Yes, No;
	private JLabel label;
	private JPanel buttonPanel;
	private final int TICK_SPACING = 10;
	private JLabel theLabel;
	private JSlider theSlider;
	private int value = 50;
	public File fileToSave;

	// -----------------------------------------------------------------
	// Constructor: Sets up the setttings GUI.
	// -----------------------------------------------------------------
	public FittsLaw() {
		
		//set up the button panel and slider
		
		Yes = new JButton("Yes");
		No = new JButton("No");
		ButtonListener listener = new ButtonListener();
		Yes.addActionListener(listener);
		No.addActionListener(listener);

		label = new JLabel("Do you want to play?");
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(1000, 40));
		buttonPanel.setBackground(Color.blue);
		buttonPanel.add(Yes);
		buttonPanel.add(No);
		theSlider = new JSlider(JSlider.HORIZONTAL, 50, 100, 50);
		theSlider.setMajorTickSpacing(TICK_SPACING);
		theSlider.setPaintTicks(true);

		theSlider.addChangeListener(listener);

		buttonPanel.add(theSlider);
		setPreferredSize(new Dimension(1000, 100));
		setBackground(Color.cyan);
		add(label);
		add(buttonPanel);
		theLabel = new JLabel("Trials: 50");

		add(theLabel);

	}

	/*****************************************************************
	 * // Represents a listener for both buttons. and has a method to listen for
	 * the slider
	 */
	// *****************************************************************

	class ButtonListener implements ActionListener, ChangeListener {

		public void stateChanged(ChangeEvent e) {
			value = theSlider.getValue();
			theLabel.setText("Trials: " + value);

		}

		// --------------------------------------------------------------
		// Determines which button was pressed and sets the label
		// text accordingly.
		// also takes the appropriate action
		// --------------------------------------------------------------
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == Yes) {

				this.startTheGame();

			} else {

				System.exit(0);

			}

		}

		// Start playing the game
		public void startTheGame() {
			// hide the buttons on the first panel
			buttonPanel.setVisible(false);

			// parent component of the dialog
			JFrame parentFrame = new JFrame();
			
			//ask the user to select a file
			//and location to save to
			JFileChooser fileChooser = new JFileChooser();
			//filter out csv files
			FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "CSV");
				fileChooser.setFileFilter(filter);
				
			fileChooser.setDialogTitle("Specify a file to save");

			int userSelection = fileChooser.showSaveDialog(parentFrame);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				fileToSave = fileChooser.getSelectedFile();

			}
			
			//if the a file location was not properly selected, alert the user
			//of the defualt location and name for ouput file
			if(fileToSave == null){
				JOptionPane.showMessageDialog(new JFrame(), "The Data will be saved as Fitts in the current Directory");
				
			}
			
			// create the panel which the game will be played in
			final JFrame theFrame = new JFrame("PlayGame");
			theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			theFrame.setSize(800, 800); // make frame 800x800
			theFrame.setResizable(false);

			// ****************************************************
			// prevent resizing

			// prevent window from being increased
			theFrame.addComponentListener(new java.awt.event.ComponentAdapter() {
				public void componentResized(ComponentEvent event) {
					theFrame.setSize(Math.max(100, theFrame.getWidth()),
							Math.max(100, theFrame.getHeight()));
				}

			});

			//alert the user that if he/she starts before the
			//countdown ends, they are cheating and will throw the results
			JOptionPane.showMessageDialog(new JFrame(), "Do Not Start until the Counter Reaches 0 and dissappears or you are cheating! and will skew results");
			
			
			// prevent window from being decreased
			theFrame.setBounds(0, 0, 800, 800);

			// create a canvas object
			MyCanvas theCanvas = new MyCanvas();
			theCanvas.setFile(fileToSave);
			theCanvas.setTrials(value);
			theFrame.add(theCanvas);
			theFrame.setVisible(true);
			
			//create countdown object
			new CountDown();

		}

	}

}
