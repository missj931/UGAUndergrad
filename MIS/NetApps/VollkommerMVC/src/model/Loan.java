//Loan Class
//copied Loan.java from previous java review assignment
/**
 * @author Matthew Vollkommer
 */
//include in my package
package model;

public class Loan {

	//private member variables
	private double principal, rate, term;

	//**********************************
	//Constructors
	//default constructor
	public Loan(){
		principal =0;
		rate = 0;
		term = 0;

	}

	//constructor
	public Loan(double principal, double rate, double term){

		this.principal = principal;
		this.rate = rate;
		this.term = term;


	}
	//*********************************************


	//********************************************
	//getters
	/**
	 * getPrincipal
	 * 
	 * @return principal
	 */
	public double getPrincipal(){
		return principal;
	}

	/**
	 * getRate
	 * @return rate
	 */
	public double getRate(){
		return rate;
	}

	/**
	 * getTerm
	 * @return term
	 */
	public double getTerm(){
		return term;
	}

	/**
	 * calculates and prints the monthly rate
	 * @param annualRate
	 * @return monthly rate
	 */
	public double getMonthlyRate(){

		double monthlyRate = rate/12;
		//printMonthlyRate(monthlyRate);
		return monthlyRate;


	}

	/**
	 * calculates and prints the total months
	 * 
	 * @return total Months
	 */
	public double getTotalMonths(){

		double totalMonths = term*12;
		//	printTotalMonths(totalMonths);
		return totalMonths;

	}


	/**
	 * calculates the and prints the monthly payment
	 * @param principal
	 * @param monthlyRate
	 * @param totalMonths
	 * @return monthlyPayment
	 */
	public double getMonthlyPayment(double monthlyRate, double totalMonths){

		double monthlyPayment = principal*monthlyRate/(1- Math.pow((1+monthlyRate), -totalMonths));
		//printMonthlyPayment(monthlyPayment);
		return monthlyPayment;

	}

	//*******************************************

	//********************************************
	//setters

	/**
	 * setPrincipal
	 * @param principal
	 * @return void
	 */
	public void setPrincipal(double principal){
		this.principal = principal;
	}

	/**
	 * 
	 * @param rate
	 * @return void
	 */

	public void setRate(double rate){
		this.rate = rate;
	}


	/**
	 * 
	 * @param term
	 * @return void
	 */
	public void setTerm(double term){
		this.term = term;
	}
	//*******************************************


	//***************************************
	//private methods
	/**
	 * printTotalMonths
	 * @param totalMonths, double-the total months to be printed
	 */
	private  void printTotalMonths(double totalMonths){

		System.out.println("Total Months: " + totalMonths);

	}

	/**
	 * printMonthlyPayment
	 * @param monthlyPayment, double-the monthlypayment to be printed
	 */
	private  void printMonthlyPayment(double monthlyPayment){

		System.out.println("Monthly Payment: "+ monthlyPayment);

	}

	/**
	 * printMonthlyRate
	 * @param monthlyRate, double- the monthlyrate to be printed
	 */
	private  void printMonthlyRate(double monthlyRate){

		System.out.println("Monthly Rate: " + monthlyRate);

	}

}
//*******************************************************