#include "Employee.h"
#include <iostream>


  //Matthew Vollkommer
  //CSCI 1730
  //7/24/2014


  //************************************************
  //constructors
  
  /** Default Constructor */
  Employee::Employee(){
  
	//initialize class variables to 0
	//is this correct default values?
	char n [] {'\0'};
	
	setName(n);
	setHours (0);
	setWage (0);
	
	}
	

  /** Overloaded Constructor */
  Employee::Employee(char * n, double h, double w){

		setHours(h);
		setWage(w);
		setName(n);
		
	}

//*******************************************************
//public member functions

  /** Returns the name. */
  const char * Employee::getName() const{
	
	//debug std::cout<<"Enter get Name: " << name << std::endl;
	

	return name;
	
	}

  /** Sets the name. */
  void Employee::setName(char n[]){

  	//debug std::cout<<"setName() entered" << std::endl;

  

	//loop through the n array, assigning each value to name array
	//end when you reach the sentinal value
	
	int i = 0;
		
    while (n[i] != '\0') {
	
		// debug std::cout <<"loop entered" << i  << std::endl;
	
		name[i] = n[i];
		++i;
    }
	
	//include the null terminator, the sentinal character
    name[i] = '\0';
	
     
  }

  /** Returns the number of hours worked. */
  double Employee::getHours() const{
  
		return hours;
	}

  /** Sets the number of hours worked. */
  void Employee::setHours(double h){
		
		hours = h;
		
	}

  /** Returns the wage. */
  double Employee::getWage() const{
	
		return wage;
	
	}

  /** Sets the wage. */
  void Employee::setWage(double w){

		wage = w;

	}

//******************************************************************
//private class variables
  
  char   name[80]; // name
  double hours;    // hours worked
  double wage;     // hourly wage

