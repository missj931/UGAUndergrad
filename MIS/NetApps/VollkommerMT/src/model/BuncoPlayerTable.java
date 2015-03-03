/**
 * The BuncoPlayerTable class is a child of the BuncoTable class.
 * It implements the BuncoPlayerTableInterface
 * @author Matthew Vollkommer
 *
 */

//part of bunco package
package model;

public class BuncoPlayerTable extends BuncoTable implements BuncoPlayerTableInterface{

	private int score;

	BuncoPlayerTable(){
		score = 0;//score starts at 0
	}
	@Override
	public int getScore() {
		
		return score;
	}
	@Override
	public void addPoints(int points) {
		
		score += points;
	}
	
	
	
}
