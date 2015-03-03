
/**
 * this interface determines how to interact with a BuncoPlayerTable
 * @author Matthew Vollkommer
 */


//part of bunco package
package model;

public interface BuncoPlayerTableInterface {

	
	public abstract void roll();
	
	
	
	//*********************************
	//Getters
	public abstract int getValueOne();
	public abstract int getValueTwo();
	public abstract int getValueThree();
	public abstract int getScore();
	//*********************************
	
	//************************************
	//Setters
	public abstract void addPoints(int points);
	//*********************************
	
}
