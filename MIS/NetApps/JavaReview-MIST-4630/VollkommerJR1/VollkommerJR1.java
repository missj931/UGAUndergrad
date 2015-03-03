import java.util.*;

public class VollkommerJR1 {


	/**
	 * Main Method
	 * @param String[] args; a list of strings
	 * @return void
	 */
	public static void main(String[] args) {



		//declare variables
		double monthlyRate, totalMonths, monthlyPayment, annualRate, totalTerm, principal;
		int exitStatus = 0;
		Scanner input = new Scanner(System.in);

		try{

			//******************************************
			//prompt and retrieve input

			System.out.println("Enter annual rate: ");
			annualRate = input.nextDouble();

			System.out.println("Enter total term: ");
			totalTerm = input.nextDouble();

			System.out.println("Enter principal: ");
			principal = input.nextDouble();

			//*******************************************

			//******************************
			//calculate and output to user

			monthlyRate = annualRate/12;
			System.out.println("Monthly Rate: " + monthlyRate);

			totalMonths = totalTerm*12;
			System.out.println("Total Months: " + totalMonths);

			monthlyPayment = principal*monthlyRate/(1- Math.pow((1+monthlyRate), -totalMonths));
			System.out.println("Monthly Payment: "+ monthlyPayment);


		}catch(Exception e){

			//prompt user if an error occured
			System.err.println("An Error Occured");
			exitStatus = 1;//change exit to failure

		}//trycatch

		//***********************************
		//end the program
		//close the scanner
		input.close();
		//prompt user
		System.out.println("Program Ended");
		//exit
		System.exit(exitStatus);
		//**************************************
	}//main(string args[])


}//VollkommerJR1
