import java.util.Scanner;


//class
public class VollkommerJR2 {

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

			monthlyRate = getMonthlyRate(annualRate);
			printMonthlyRate(monthlyRate);
			
			totalMonths = getTotalMonths(totalTerm);
			printTotalMonths(totalMonths);

			monthlyPayment = getMonthlyPayment(principal, monthlyRate, totalMonths);
			printMonthlyPayment(monthlyPayment);


		}catch(Exception e){

			//prompt user if an error occured
			System.err.println("An Error Occured");
			exitStatus = 1;//change exit to failure

		}//trycatch
		
		//end program
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


	private static double getMonthlyRate(double annualRate){

		double monthlyRate = annualRate/12;
		return monthlyRate;


	}


	private static double getTotalMonths(double totalTerm){

		double totalMonths = totalTerm*12;
		return totalMonths;

	}

	
	private static double getMonthlyPayment(double principal, double monthlyRate, double totalMonths){

		double monthlyPayment = principal*monthlyRate/(1- Math.pow((1+monthlyRate), -totalMonths));
		return monthlyPayment;

	}
	
	private static void printTotalMonths(double totalMonths){
		
		System.out.println("Total Months: " + totalMonths);
		
	}
	
	private static void printMonthlyPayment(double monthlyPayment){
		
		System.out.println("Monthly Payment: "+ monthlyPayment);
		
	}
	
	private static void printMonthlyRate(double monthlyRate){
		
		System.out.println("Monthly Rate: " + monthlyRate);
	
	}
}//VollkommerJ1


