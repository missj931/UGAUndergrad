//Matthew Vollkommer 810249412
//lab03
#include <stdio.h>
 
int main() 
{
	//declare variables
    long  array[10], min, max, size, location = 1;
	int counter = 0, result = 0;

	printf("\nEnter 10 integers: ");
	
	while(counter < 10 ){
	//request input
 
	
		//take in input
  	 	result += scanf("%ld", &array[counter]);
	
	//*********************
	//validate input
	if(result != counter+1){
		printf("\ninput error\n\n");
		return 0;
	}
	if(array[counter] < -65536 || array[counter] > 65535){
		printf("\nints need to be between -65536 and 65535 inclusive\n\n");
		return 0;
		}

	counter++;
	
	}

	//start with the first value as the minimum value
	min = array[0];
 
//******************************************
//find minimum value
    for (counter = 1 ; counter < 10 ; counter++ ) 
    {
        if ( array[counter] < min) 
        {
           min = array[counter];
           location = counter+1;
        }
    } 
	
	//print the minimum value
	printf("              Min: %i \n", min);

	//*****************************************************
	//find the maximum value
	max = array[0];
 
    for (counter = 1; counter < 10 ; counter++ ) 
    {
        if ( array[counter] > max ) 
        {
           max = array[counter];
           location = counter+1;
        }
    } 
		
		printf("              Max: %i \n\n", max);
	//succesful end program	
		return 0;

}//main;
