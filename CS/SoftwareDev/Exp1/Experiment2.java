import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;


public class Experiment2 {

	public static void main(String[] args) {
		
		
		//output to file, Experiment2n!
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter("Experiment2n!"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
	
	


		//number of trials
		int trials = 10;
		
		//do each trial for the recursive method
		for(int counter = trials; counter >= 0; counter--){
			
			
			long startTime = System.currentTimeMillis(); 
			
			int n = Recursive(trials);
			
			long runTime = System.currentTimeMillis() - startTime;
			
			out.println("Recursive N!: " + counter + ", " +runTime);
			
			
			}
		
		//do each trial for the iterative method
		for(int counter = trials; counter >= 0; counter--){
				
				
			long startTime = System.currentTimeMillis(); 
			
			int n = Iterative(trials);
			
			long runTime = System.currentTimeMillis() - startTime;
			
			out.println("Iterative N!: " + counter + ", " +runTime);
			}

	}


	//iterative, loop n!
	public static int Iterative(int n)
	{
        int fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
        
	}
	
	
	  public static int Recursive(int n){
		    if (n <= 1)
		        return 1;

		        else

		            return n * Recursive(n-1);
		  }
        
        
	
}
