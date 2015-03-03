/**
 * 
 * Additional Java Class(BuncoTable)
 * The BuncoTable class implements the BuncoTableInterface.
 * 
 * @author Matthew Vollkommer
 */

//part of bunco package
package model;

public class BuncoTable {

	//*******************************
	//private class members


	//*********************************
	private int valueOne;
	private int valueTwo;
	private int valueThree;

	private int score;

	//****************************************
	// public methods
	/**
	 * generates new values using dice
	 */

	public void roll(){
		Die dieOne = new Die();
		valueOne = dieOne.getValue();
		valueTwo = dieOne.getValue();
		valueThree = dieOne.getValue();
	}

	public int getValueOne(){
		return valueOne;
	}

	public int getValueTwo(){
		return valueTwo;
	}

	public int getValueThree(){
		return valueThree;
	}
	/**
	 * calculates the score
	 * @param round, what round it is
	 */
	public void calcScore(int round){

		if(valueOne == valueTwo && valueTwo == valueThree && valueThree == round){
			score = 21;

		}else if(valueOne == round){
			score = 1;
			if(valueTwo == round || valueThree == round){
				score = 2;
			}

		}else if(valueTwo == round){

			score = 1;
			if(valueThree == round){
				score = 2;
			}
		}else if(valueThree == round){
			score = 1;

		}



	}

	/**
	 * 
	 * @return the score
	 */
	public int getScore(){
		return score;
	}

/**
 * 
 * @return Returns the score as an html string
 */
	public String getScoreHTML(){
		return "</p> Points this Roll: " + score + "</p>"; 
	}

	public String getDieHTML(int value){

		String str = "<img src='";
		switch (value){
		case 1:
			str+="Img/DieOne.png'";
			break;
		case 2:
			str+="Img/DieTwo.png'";
			break;
		case 3:
			str+="Img/DieThree.png'";
			break;
		case 4:
			str+="Img/DieFour.png'";
			break;
		case 5:
			str+="Img/DieFive.png'";
			break;
		case 6:
			str+="Img/DieSix.png'";
			break;
		}

		str+=" alt='DieImage'/>";

		return str;

	}

	/**
	 * 
	 * @param oldPlayer, the name of the current player
	 * @return, the name of the new player
	 */
	public String getNewPlayer(String oldPlayer){

		String newPlayer = "";

		if(oldPlayer.equals("West")){
			newPlayer = "North";

		}else if(oldPlayer.equals("North")){
			newPlayer = "East";

		}else if(oldPlayer.equals("South")){
			newPlayer ="West";

		}else if(oldPlayer.equals("East")){
			newPlayer ="South";
		}

		return newPlayer;
	}

	//****************************************************

}//table

