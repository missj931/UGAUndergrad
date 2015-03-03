import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class ExperimentFibs {
	
	
	
	public static void main(String[] args)
	 {
		
		//output to file, Experiment1Fibs
		PrintWriter out = null;
		
		try {
			out = new PrintWriter(new FileWriter("Experiment1Fibs"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		//number of trials
		int trials = 20;

		//perform the each recursive trial
		for( int counter = trials; counter >= 0; counter--){
			
			
			long startTime = System.currentTimeMillis(); 
		
			int fib = Recurse(trials);
		
			long runTime = System.currentTimeMillis() - startTime;
		
			out.println("Recursive Fibs: " + counter + ", " +runTime);
	
		
	 }
		
		//perform each iterative trial
		
		for(int counter = trials; counter >= 0; counter--){
			
			
			long startTime = System.currentTimeMillis(); 
		
			int fib = Iterate(trials);
		
			long runTime = System.currentTimeMillis() - startTime;
		
			out.println("Iterative Fibs: " + counter + ", " +runTime);
		
		}
			
		
	
		
	 }
		
	//------------------------------------    
	//iteration method fibinocci

	public static int Iterate(int n){
	    if (n == 0) {
	return 0;
	    }
	   
	 if (n == 1){
	 return 1;
	     }

	    int x = 0;
	    int y= 1;
	    int fibs = 0;
	        
	    for (int i = 2; i <= n; i++) {
	        fibs = y + x;
	        x= y;
	        y = fibs;
	    }
	    return fibs;
	}
	    
	//------------------------------------ 
	//recursion method fibinocci
	public static int Recurse(int n){
	    if (n == 0){
	return 0;
	}
	    if (n == 1){
	 	return 1;
		}
	        
	    return Recurse(n - 1) + Recurse(n - 2);
	}
	
	//recursion, n!
	public static int Recursive(int n)
	{
	    if (n <= 1) return 1;
	    return n * Recursive(n - 1);
	}

	//iterative, loop n!
	public static int Iterative(int n)
	{
	    int sum = 1;
	    if (n <= 1) return sum;
	    while (n > 1)
	    {
	        sum *= n;
	        n--;
	    }
	    return sum;
	}


}
