import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class MyCanvas extends JPanel {

	//set the class variables
	private final int HEIGHT_MIN = 50;
	private final int WIDTH_MIN = 50;
	private final int WIDTH_MAX = 800 / 2;
	private final int HEIGHT_MAX = 800 / 2;
	private int height;
	private int width;
	private int x;
	private int y;
	private int theColor;
	private int shape;
	private int xCoor = 99999;
	private int yCoor = 999999;
	private int trials = 5000000;
	private int counter = 0;
	public long startTime;
	public int priorX = 0;
	public int priorY = 0;
	public BufferedWriter output;
	public File file;
	public int writeCounter = 0;

	//*********
	//paint method
	public void paint(Graphics g) {

		// set writer counter and startime
		writeCounter = 0;
		startTime = System.currentTimeMillis();
		
		//subtract time added by the counter on initial click
		if(counter == 0){
			startTime = startTime - 5000;
		}
			
	
		//the start time is the current time in milliseconds
		startTime = System.currentTimeMillis();

		//if the file was not set earlier, output to Fitts.csv
		if (counter == 0) {
			try {
				if (file == null) {
					file = new File("Fitts");
				}
				//set output to file
				output = new BufferedWriter(new FileWriter(file));

			} catch (Exception e) {
				System.err.println("No file found");
			}
		}
		
		//increment counter
		counter++;
		
		// paint the object
		super.paintComponent(g);

		//listen for mouse presses, all other methods are ignored
		this.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {

				xCoor = e.getX();
				yCoor = e.getY();

				//

				if (xCoor >= x && xCoor <= x + width && yCoor >= y
						&& yCoor <= y + height) {
					// testing System.out.println("hit");
					writeCounter++;

					// only write first accurate mouse press(prevents duplicate
					// records)
					if (writeCounter == 1) {
						long timeTaken = System.currentTimeMillis() - startTime;

						if (counter > trials) {
							// close the output file, prevents corruption
							try {
								//close output to prevent corruption
								output.close();

							} catch (Exception e1) {

								System.err.println("could not close writer");
							}
							// notify the player that the game has ended and the
							// data has been saved
							JOptionPane.showMessageDialog(new JFrame(), "The Experiment ended. Achievement Unlocked!!!!!!! You won!!!11!!");
							
							//end the program without error
							System.exit(0);

						}
						
						//calculate distance
						int distance = (int) Math.sqrt((priorX - x)
								* (priorX - x) + (priorY - y) * (priorY - y));
						
						//output to file in correct order
						try {
							output.write(counter + ", " + width * height + ", "+ distance + ", " + timeTaken + "\n");
						} catch (Exception eTwo) {
							System.err
									.println("Error Writing to file on turn: "
											+ counter);
						}
						//set the prior coordinates to the coordinates just used
						priorX = xCoor;
						priorY = yCoor;
						
						//go back to painting all over again
						repaint();

					}
				}

			}
		});

		// make a random number generator
		Random gen = new Random();

		// *****************************************************
		// generate the height

		do {
			height = gen.nextInt(HEIGHT_MAX);
			// System.err.println(height +" height"); testing height generation
		} while (height < HEIGHT_MIN);

		// *************************************************
		// generate the width

		do {
			width = gen.nextInt(WIDTH_MAX);
			// System.err.println(width +" width"); testing width generation
		} while (width < WIDTH_MIN);

		// **********************************************
		// generate the x coordinate

		do {

			x = gen.nextInt(800 - HEIGHT_MIN);
			// System.err.println(x +" x"); testing x coordinate generation
		} while (x + width > 800);

		// ****************************************************
		// generate y coordinate

		do {
			y = gen.nextInt(800 - WIDTH_MIN);
			// System.err.println(y +" y"); testing y coordinate generation
		} while (y + height > 800);

		// ********************************************
		// /generate the color
		theColor = gen.nextInt(3);

		if (theColor == 1) {
			g.setColor(Color.RED);
		} else if (theColor == 2) {
			g.setColor(Color.BLUE);
		} else {
			g.setColor(Color.YELLOW);
		}

		// ***************************************
		// generate the shape

		shape = gen.nextInt(3);

		if (shape == 1) {
			// make a rectangle
			g.fillRect(x, y, width, height);

		} else if (shape == 2) {
			// make an oval
			g.fillOval(x, y, width, height);

		} else {
			// make a square
			height = width;
			g.fillRect(x, y, width, height);
		}

	}

	//***************************
	//set the number of trials to complete
	public void setTrials(int pTrials) {
		trials = pTrials;
	}

	
	//***************************
	//set the output file
	public void setFile(File pFile) {
		file = pFile;
	}

}
				
	    	