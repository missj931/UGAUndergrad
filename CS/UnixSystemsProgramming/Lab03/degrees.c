//Matthew Vollkommer 810249412
//lab03
#include <stdio.h>

int main(){

	//declare local variables
	float celsius, fahrenheit;
	
	//prompt user for input
	printf("\nEnter in a temperature in Celsius: ");

	//get input
	const int result = scanf("%f", &celsius);
		
	//*******************
	//validate input
		
	if(result != 1){
		printf("\ninput error\n\n");
		return 0;
	}
	
	if(celsius < -65536 || celsius > 65535){
		printf("\nints need to be between -65536 and 65535 inclusive\n\n");
		return 0;
	}

	
	
	
	//converstion
	fahrenheit = 1.8*celsius + 32;

	 //round to the second decimal
	printf("\n%.2f degrees Celsius converts to %.2f degrees Fahrenheit.\n\n", celsius, fahrenheit);
	
	//end program succesfully
	
	return(0);
}
