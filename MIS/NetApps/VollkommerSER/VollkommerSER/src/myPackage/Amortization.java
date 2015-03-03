//include in my package
package myPackage;

//imports for converting and rounding currency
import java.text.NumberFormat;
import java.util.Locale;
import java.lang.Math;

//amortization class
public class Amortization {

	//class members
	private Loan loan;
	private double currentBalance;
	private double monthlyPayment;
	private double monthlyInterestRate;
	private double totalMonths;

	//constructor
	public Amortization(Loan loan){

		//assign this loan to the loan parameter
		this.loan = loan;
		currentBalance = loan.getPrincipal();
		monthlyInterestRate = loan.getMonthlyRate();
		totalMonths = loan.getTotalMonths();
		monthlyPayment = loan.getMonthlyPayment((monthlyInterestRate/100.00), totalMonths);
		//more to include

	}

	//*******************************************************************
	//private methods

	private double getMonthlyPaymentToInterest(){

		return (monthlyInterestRate/100) *currentBalance;

	}


	private double getMonthlyPaymentToBalance(){

		return monthlyPayment - getMonthlyPaymentToInterest();


	}

	private double getCurrentBalanceAfterPayment(){

		currentBalance -= getMonthlyPaymentToBalance();
		
		return currentBalance;

	}
	//doAmortization Method
	/**
	 * @param void, takes no inputs
	 * @return String, the table
	 */
	public String doAmortization(){

		
		//format money
	     NumberFormat mf = NumberFormat.getCurrencyInstance(Locale.US); 
	     //format percent
	    //NumberFormat pf = NumberFormat.getPercentInstance(Locale.US);
	     
	     String table = "<p>Here are the results for you loan:</p> <p>Loan Principal: "+ mf.format(currentBalance) + "</p> <p>Loan Term: "+ (int)totalMonths + " months.<p></p>Loan Rate: " + String.format("%.2f", monthlyInterestRate) +"% (monthly rate)</p>";
		

	     
		//initialize table tag
		table +="<table>";

		//header
		table += "<tr><td>Month</td><td>Payment</td><td>Interest Paid</td><td>Principal Paid</td><td>Ending Balance</td></tr>";

		for(int m = 1; m <= totalMonths; m++){
			

			//create a new table row
			table += "<tr>";

			//add data in each appropriate cell
			table += "<td>" + m + "</td>";
			table += "<td>" + mf.format(monthlyPayment) +"</td>";
			table += "<td>" + mf.format(getMonthlyPaymentToInterest()) +"</td>";
			table += "<td>" + mf.format(getMonthlyPaymentToBalance()) +"</td>";
			table += "<td>" + mf.format(getCurrentBalanceAfterPayment())+"</td>";
			table +="</tr>";

		}

		//closing table tag
		table +="</table>";

		//return the string
		return table;

	}



}