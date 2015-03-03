//Matthew Vollkommer 810249412
//lab03
#include <stdio.h>
#include <stdlib.h>

int main (){

	//declare local variables
	long  sum, intOne, intTwo;
	float favg;
	int iavg;
	
	//prompt user
	printf("\nEnter two integers: ");

	//get input
	const int result = scanf ("%ld %ld", &intOne, &intTwo);
	
	//**********************
	//validate input
	if(result != 2){
		printf("\nincorrect input\n\n");
		return 0;
	}
	
	//an int must be between 
	if(intOne>65535 || intTwo > 65535||intOne<-65536 || intTwo<-65536){
		printf("\nints must be between -65536 and 65535 inclusive\n\n");
		return 0;
	}

	//calculate the sum
	sum = intOne + intTwo;

	//***********************************
	//output
	
	printf("               SUM: ");
	printf("%i", sum);
	
	printf("\n           Average: ");
	//prints after rounding
	favg = sum/2.00;
	iavg = sum/2;
	//is the float arithmetic equal to the int aritmetic?
	if(iavg == favg){
		//yes. print an int
		printf("%i", iavg);
	}else{
	//no, print a float
		printf("%.1f", favg);
	}
		//calculate sum of squares
	sum = (intOne*intOne) + (intTwo*intTwo);

	printf("\n    Sum of Squares: ");
	printf("%ld \n\n", sum);
	

	return(0);


}
