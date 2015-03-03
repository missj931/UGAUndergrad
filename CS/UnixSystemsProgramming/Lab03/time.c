//Matthew Vollkommer 810249412
//lab03

#include <stdio.h>


int main(){
//declare local variables
long time, seconds, minutes, hours;

	
	printf("\nEnter in a number of seconds as an integer: ");
	const int result = scanf("%ld", &time);
	//*********************************************
	//validate input
	
	if(result != 1){
		printf("\ninput error\n\n");
		return 0;
	}
	if(time < -65536 || time > 65535){
	printf("\nints are between -65536 and 65535 inclusive\n\n");
		return 0;
		}

		//conversion
		seconds = time %60;
		minutes = (time/60) % 60;
		hours = (time/3600);
		
		
		printf("%i seconds is equivalent to %i hours %i minutes %i seconds.\n\n", time, hours, minutes, seconds);
	

	return(0);
}
