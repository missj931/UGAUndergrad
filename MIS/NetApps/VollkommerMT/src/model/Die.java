/**
 * The die class implements the DieInterface.
 * Die class : A single die can be modeled as a Java class based on the
 * following Class diagram. You must create, include and use a Die class based on this
 * design. Your class should follow the design here for full credit. The setRandom() method
 * should set the value of the die object to a random integer from between 1 and 6,
 * inclusive.
 * 
 * @author Matthew Vollkommer
 *
 */

//import random class


//part of bunco package
package model;

//import
import java.util.Random;


public class Die implements DieInterface {

	//**************************
	//private class members
	private int value;
	//******************************


	//***************************
	//Constructors
	Die(){


	}//die

	Die(int value){

	}//Die

	//********************************

	//************************************
	//public methods

	//accessor
	/**
	 * generates a new value everytime it is called
	 * @override from interface
	 * @return value, int
	 */
	public int getValue(){

		setRandom(); //generate new random value

		return value; //return value

	}//getValue

	/**
	 * @override from interface
	 * returns the die value as a sentenc in a string
	 */
	public String toString(){

		return "Die value is " + value + "."; //return string

	}//toString

	//*******************************

	//***************************************
	//private methods

	//mutator
	/**
	 * 
	 * @param value, int
	 */
	private void setValue(int value){

		this.value = value;

	}//setValue



	private void setRandom(){

		Random generator = new Random();
		int num = 0;

		while(true){

			num = generator.nextInt(7);

			//continue generating ints until num is between 1 and 6 inclusive
			if(num >= 1 && num <= 6){
				break; //end loop
			}//if

		}//while

		setValue(num);//set new value

	}//setRandom


	//***********************************************
}//Die
