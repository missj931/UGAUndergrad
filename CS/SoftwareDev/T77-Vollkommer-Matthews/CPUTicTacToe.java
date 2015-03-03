
/**
 * 
 * @author Matthew Vollkommer and Austin Matthews
 * 
 *
 */


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

//implemented interfaces
public class CPUTicTacToe implements MouseListener, KeyListener
{
	//constants
	private final int Z_KEY=17;
	private final int CONTROL_KEY =90;

	//class variables
	private boolean zKeyPressed=false;
	private boolean controlKeyPressed=false;
	private int theY;
	private int theX;
	private boolean theFlag;
	private boolean won;
	private boolean tie;
	//count wins
	private int xWins;
	private int oWins;

	//Frame
	private JFrame theJFrame;

	//a game board is a 3X3 2D array of X's and O's
	private char[][] charArray = new char[3][3];

	//stack to keep track of moves
	private Stack<Round> theRound = new Stack<Round>(); 


	//constructor
	public CPUTicTacToe()
	{


		int xWins = 0;
		int oWins = 0;
		won = false;
		theFlag = true;
		tie = false;

		//initialize the JFrame
		theJFrame = new JFrame("Turn: player O ||  O has "+ oWins +"  & X has " +xWins + " wins");

		theJFrame.setSize(900, 600);
		theJFrame.getBackground();
		theJFrame.setBackground(Color.white);


		theJFrame.setVisible(true);
		//add listeners
		theJFrame.addMouseListener(this);
		theJFrame.addKeyListener(this);


		//initialize the board array
		for (int counter = 0; counter < 3; counter++){
			for(int counterTwo = 0; counterTwo < 3; counterTwo++){
				//make the char array all a's
				charArray[counter][counterTwo] = '_';
			}

		}


		// ****************************************************
		// prevent resizing

		theJFrame.setResizable(false);
		// prevent window from being increased
		theJFrame.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(ComponentEvent event) {
				theJFrame.setSize(Math.max(100, theJFrame.getWidth()),
						Math.max(100, theJFrame.getHeight()));

			}
		});

		//exit on close
		theJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		drawTheBoard();
		theJFrame.setVisible(true);
	}


	public void windowClosing(WindowEvent e)
	{
		System.exit(0); 

	}

	//draw the gameboard, 9 boxes are made
	public void drawTheBoard(){

		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("tic.jpg"));
		} catch (IOException e) {
			//do nothing
		}


		JLabel picLabel = new JLabel(new ImageIcon(myPicture));

		theJFrame.add(picLabel);

	}

	//undoes a move
	private void undoMove(Round pMove){


		int x = pMove.getX();
		int y = pMove.getY();


		Graphics2D theGraphics2D;

		//draw the board
		Graphics moreGraphics = theJFrame.getGraphics();


		//tell the player it is their turn
		if(theFlag == false){
			//it is now player x's turn
			theJFrame.setTitle("Turn: player X ||  O has "+ oWins +"  & X has " +xWins + " wins");
			//swap the flag
			theFlag = true;
		}else{
			//it is now player 0's turn
			theJFrame.setTitle("Turn: player O ||  O has "+ oWins +"  & X has " +xWins + " wins");
			//swap the flag
			theFlag = false;
		}


		//reset each respective matching position
		if ((x < 300) && (y < 200)) { 
			theX = 0; theY = 0; charArray[0][0] = '_'; 
		}
		if ((x > 300) && (x < 600) && (y < 200)) { 
			theX = 300; theY= 0; charArray[0][1] = '_'; 
		}
		if ((x > 600) && (x < 900) && (y < 200)) { 
			theX = 600; theY= 0; charArray[0][2] = '_'; 
		}
		if ((x < 300) && (y > 200) && (y < 400)) { 
			theX = 0; theY= 200; charArray[1][0] = '_'; 
		}
		if ((x > 300) && (x < 600) && (y > 200) && (y < 400)) { 
			theX = 300; theY= 200; charArray[1][1] = '_'; 
		}
		if ((x > 600) && (x < 900) && (y > 200) && (y < 400)) { 
			theX = 600; theY= 200; charArray[1][2] = '_'; 
		}
		if ((x < 300) && (y > 400) && (y < 600)) { 
			theX = 0; theY= 400; charArray[2][0] = '_'; 
		}
		if ((x > 300) && (x < 600) && (y > 400) && (y < 600)) { 
			theX = 300; theY= 400; charArray[2][1] = '_'; 
		}
		if ((x > 600) && (x < 900) && (y > 400) && (y < 600)) {
			theX = 600; theY= 400; charArray[2][2] = '_'; 
		}


		//if it is the circle's turn
		if( theFlag == false){

			//draw a circle
			moreGraphics.setColor(Color.WHITE);
			theGraphics2D = (Graphics2D)moreGraphics;
			theGraphics2D.setStroke(new BasicStroke(10.0F));
			moreGraphics.drawOval(theX + 40,theY+ 40, 120, 120);

			//if it is the x's turn
		}else{


			//drawing an X
			theGraphics2D = (Graphics2D)moreGraphics;
			theGraphics2D.setStroke(new BasicStroke(10.0F));
			moreGraphics.setColor(Color.WHITE);
			theGraphics2D.drawLine(theX + 10,theY + 40,theX + 170,theY+ 130);
			theGraphics2D.drawLine(theX + 170,theY + 40,theX+ 10,theY+ 160);


		}

	}

	private void makeMove(int x, int y){

	
		Graphics2D theGraphics2D;

		//draw the board
		Graphics moreGraphics = theJFrame.getGraphics();

		char theMove = 'X';
		//if the flag is false, draw a circle
		if (theFlag == false){ 
			theMove = 'O';
		}


		if ((x < 300) && (y < 200)) {
			if (charArray[0][0] != '_'){
				theJFrame.setTitle("player "+ theMove +" that space already is taken");
				return;
			}

			theX = 0; theY = 0; charArray[0][0] = theMove; 
		}

		if ((x > 300) && (x < 600) && (y < 200)) { 
			if (charArray[0][1] != '_'){
				theJFrame.setTitle("player "+ theMove +" that space already is taken");
				return;
			}

			theX = 300; theY= 0; charArray[0][1] = theMove; 
		}

		if ((x > 600) && (x < 900) && (y < 200)) { 
			if (charArray[0][2] != '_'){
				theJFrame.setTitle("player "+ theMove +" that space already is taken");
				return;
			}

			theX = 600; theY= 0; charArray[0][2] = theMove; 

		}
		if ((x < 300) && (y > 200) && (y < 400)) { 
			if (charArray[1][0] != '_'){
				theJFrame.setTitle("player "+ theMove +" that space already is taken");
				return;
			}

			theX = 0; theY= 200; charArray[1][0] = theMove; 


		}
		if ((x > 300) && (x < 600) && (y > 200) && (y < 400)) { 
			if (charArray[1][1] != '_'){
				theJFrame.setTitle("player "+ theMove +" that space already is taken");
				return;
			}

			theX = 300; theY= 200; charArray[1][1] = theMove; 
		}

		if ((x > 600) && (x < 900) && (y > 200) && (y < 400)) { 
			if (charArray[1][2] != '_'){
				theJFrame.setTitle("player "+ theMove +" that space already is taken");
				return;
			}

			theX = 600; theY= 200; charArray[1][2] = theMove; 
		}

		if ((x < 300) && (y > 400) && (y < 600)) { 
			if (charArray[2][0] != '_'){
				theJFrame.setTitle("player "+ theMove +" that space already is taken");
				return;
			}

			theX = 0; theY= 400; charArray[2][0] = theMove; 
		}
		if ((x > 300) && (x < 600) && (y > 400) && (y < 600)) { 
			if (charArray[2][1] != '_'){
				theJFrame.setTitle("player "+ theMove +" that space already is taken");
				return;
			}
			theX = 300; theY= 400; charArray[2][1] = theMove; 

		}
		if ((x > 600) && (x < 900) && (y > 400) && (y < 600)) { 
			if (charArray[2][2] != '_'){
				theJFrame.setTitle("player "+ theMove +" that space already is taken");
				return;
			}
			theX = 600; theY= 400; charArray[2][2] = theMove; 
		}

		if( theFlag == false){

			//draw a circle
			moreGraphics.setColor(Color.BLACK);
			theGraphics2D = (Graphics2D)moreGraphics;
			theGraphics2D.setStroke(new BasicStroke(10.0F));
			moreGraphics.drawOval(theX + 40,theY+ 40, 120, 120);


		}else{


			//drawing an X
			theGraphics2D = (Graphics2D)moreGraphics;
			theGraphics2D.setStroke(new BasicStroke(10.0F));
			theGraphics2D.setColor(Color.RED);
			theGraphics2D.drawLine(theX + 10,theY + 40,theX + 170,theY+ 130);
			theGraphics2D.drawLine(theX + 170,theY + 40,theX+ 10,theY+ 160);

		}


		//tell the player it is their turn
		if(theFlag == false){
			theJFrame.setTitle("Turn: player X ||  O has "+ oWins +"  & X has " +xWins +" wins");
			theFlag = true;
		}else{
			theJFrame.setTitle("Turn: player O ||  O has "+ oWins +"  & X has " +xWins + " wins");
			theFlag = false;
		}

		//this.printArray();


		//push the turn to the stack
		theRound.push(new Round(x, y));

		//check to see if the game has ended
		this.winner();

	}


	//is the there a winner?
	private void winner(){

		//is there a winner on a row or a column?
		for (int counter = 0; counter< 3; counter++){

			if((charArray[counter][0] == charArray[counter][1]) && (charArray[counter][0] == charArray[counter][2]) && charArray[counter][0] != '_') {
				//yes, there is a win
				won = true;
				break;

			}


			if((charArray[0][counter] == charArray[1][counter]) && (charArray[1][counter] == charArray[2][counter]) && charArray[0][counter] != '_'){
				//yes, there is a win
				won = true;
				break;
			}
		}

		//is there a diagonal winner?

		if(charArray[0][0] == charArray[1][1] && charArray [0][0] == charArray[2][2] && charArray[1][1] != '_'){
			//yes there is a diagonal win
			won = true;

		}


		if(charArray[0][2] == charArray[1][1] && charArray[0][2] == charArray[2][0] && charArray[1][1] != '_'){
			//yes there is a diagonal win
			won = true;

		}

		if(won == false){
			if(charArray[0][0] != '_' && charArray[1][0] != '_' && charArray[2][0] != '_' && charArray[0][1] != '_' && charArray[1][1] != '_' && charArray[2][1] != '_' && charArray[0][2] != '_' && charArray[1][2] != '_' && charArray[2][2] != '_' ){
				tie = true;
			}
		}

		//did someone win?
		if(won == true || tie == true){

			if(theFlag == false && tie == false){
				xWins = xWins+1;

			}else if (theFlag == true && tie == false){
				oWins = oWins+1;
			}


			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(new JFrame(), "Play again?", "Player O: " + oWins + " vs Player X: " + xWins, dialogButton);

			//play again?
			if(dialogResult==0){
				//yes, reset won and tie to false
				won = false;
				tie = false;
				//who goes first is random



				//loop through the stack, undoing all moves and reseting the board
				for(int counter = theRound.size(); counter > 0; counter --){
					this.undoMove(theRound.pop());

				}


			}else{

				theJFrame.dispose();
				//return to main menu

			}


		}

	}

	//print the 2D char array for testing purposes
	/*
	 public void printArray(){

		for(int counter = 0; counter < 3; counter++){

			for(int counterTwo = 0; counterTwo < 3; counterTwo++){
				System.out.print(charArray[counter][counterTwo] + ",");
			}
			System.out.println();
		}
		System.out.println();
	}

	 */


	//*************************************************
	//the following methods are form their respective interfaces

	//*************************************************
	// following from MouseListener

	public void mousePressed(MouseEvent e) { 

		int x = 0;
		int y = 0;

		//only check the mouse for the human input
		if(theFlag == true){
			//get the location the mouse was pressed
			x = e.getX();
			y = e.getY();

			//do not count count if the press is out of the board
			if(x > 900 || x< 0 || y < 0 || y > 600 ){
				return;
			}

			this.makeMove(x, y);

			theJFrame.setTitle("Click to Make the computer Move. If computer incorrect. Click again.");
			
			//click for cpu
			
		} else {
	
			Random generator = new Random();
			boolean theBreak = false;
			
			do {
				x = generator.nextInt(3);
				y = generator.nextInt(3);
				
				if (charArray[x][y] == '_'){
						//make the x and y matching pixel coordinates
					break;
					}
				
			}while (theBreak == true);
			

			//make the x and y into pixel coordinates
			x = x*300 + 100;
			y= y*200 + 100;

			makeMove(x,y);


		}
		
	}
	
	
	//**********************************************
	//following From KeyListener

	@Override
	public void keyPressed(KeyEvent e) {


		//was the z key pressed?
		if(Z_KEY==e.getKeyCode())
		{
			//yes
			zKeyPressed=true;
		}

		//was the control key pressed?
		if(CONTROL_KEY==e.getKeyCode())
		{
			//yes
			controlKeyPressed=true;
		}

		//were both the control and the zkey pressed?
		if(controlKeyPressed==true && zKeyPressed==true)
		{

			//are there moves in the stack?
			if (theRound.isEmpty() == false){
				//yes, undo last move
				this.undoMove(theRound.pop());
			}
		}

	}



	@Override
	public void keyReleased(KeyEvent e) {

		if(Z_KEY==e.getKeyCode())
		{
			zKeyPressed=false;
		}

		if(CONTROL_KEY==e.getKeyCode())
		{
			controlKeyPressed=false;
		}
	}


	//**********************************************
	//following methods unused. override interfaces


	//*************************************************
	// following from MouseListener
	@Override
	public void mouseReleased(MouseEvent e)
	{
		//method unused
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		//method unused
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//method unused
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//method unused
	}




	//**********************************************
	//following From KeyListener

	@Override
	public void keyTyped(KeyEvent e) {
		//unused method
	}



	//main method for testing
	/*
	public static void main(String[] args)
	{

		//initialize the game, and then draw the board
		TicTacToeGame theGame = new TicTacToeGame();


	}

	 */


}
