public class Experiment
{
/**
 * Creates a TowersOfHanoi puzzle and solves it.
 */
public static void main(String[] args)
 {
 this.Iterate(3); 
 }

}

//------------------------------------    
//iteration method

public int Iterate(int n){
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
//recursion method
public int Recursive(int n){
    if (n == 0){
return 0;
}
    if (n == 1){
 	return 1;
	}
        
    return Recursive(n - 1) + Recursive(n - 2);
}

