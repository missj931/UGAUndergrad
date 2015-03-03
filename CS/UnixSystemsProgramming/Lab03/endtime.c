//Matthew Vollkommer 810249412
//lab03
#include <stdio.h>


int main (){


	int endTime, duration, durationHours, startTime, startHours, startMinutes, durationMinutes, endHours, endMinutes;
	//prompt user
	printf("\nEnter in a start time: ");


	//**********************************
	//start time input
	//get input
	int result = scanf("%i", &startTime);

	startHours = startTime / 100;
	startMinutes = startTime - startHours*100;
	//an error checking
	if(startHours>23 || startTime < 0 || startMinutes>= 60 || result != 1){
		printf("\ninput error, enter valid military time hhmm with ints\n\n");
		return 0;
	}


	//*****************************************
	//end time input
	
	//prompt user
	printf("  Enter in a duration: ");
	
	//get input
	result = scanf("%i", &duration);

		durationHours = duration / 100;
		durationMinutes = duration - durationHours*100;
	
	//an error checking
	if( durationHours < -24 || durationMinutes < -60 || durationHours > 24 || durationMinutes > 60 || result != 1){
		printf("\ninput error\n\n");
		return 0;
	}
	
	
	//********************************************
	//calculate end time


	endHours = startHours + durationHours;
	
	endMinutes = startMinutes + durationMinutes;
	
	while(endMinutes > 59){
		endMinutes -= 60;
		endHours++;
	}
	
	while(endMinutes < 0){
		endMinutes += 60;
		endHours --;
}

	while(endHours > 23){
		endHours -= 24;
	}

	while(endHours < 0){
		endHours +=24;
	}
	
	//output
	if(endMinutes >= 10 && endHours >= 10){
		printf("          End time is: %i%i\n\n", endHours, endMinutes);
	}else if(endMinutes < 10 && endHours >= 10){
		printf("          End time is: %i0%i\n\n", endHours, endMinutes);
	}else if(endMinutes >= 10 && endHours < 10){
		printf("          End time is: 0%i%i\n\n", endHours, endMinutes);
	}else if(endMinutes <10 && endHours < 10){
		printf("          End time is: 0%i0%i\n\n", endHours, endMinutes);
	}else{
		printf("calculation error\n\n");
	}

	//end program
	return(0);

}//maini
