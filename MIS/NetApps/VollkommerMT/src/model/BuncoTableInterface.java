/**
 * Defines what a table is for Bunco
 * @author Matthew Vollkommer
 *
 */


//part of bunco package
package model;

public interface BuncoTableInterface {

	
	public abstract void roll();
	
	
	//*********************************
	//Getters
	public abstract int getValueOne();
	public abstract int getValueTwo();
	public abstract int getValueThree();
	//*********************************
}
