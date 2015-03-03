import java.util.Scanner;


public class VollkommerJR3 {

	public static void main(String[] args) {

		double annualRate, totalTerm, principal;
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

			Loan theLoan = new Loan(principal, annualRate, totalTerm);
			//calculate monthly payment
			theLoan.getMonthlyPayment(theLoan.getMonthlyRate(), theLoan.getTotalMonths());

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


}//VollkommerJR3
