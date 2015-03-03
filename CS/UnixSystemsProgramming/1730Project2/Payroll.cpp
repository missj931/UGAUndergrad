#include <iostream>
#include <fstream>
#include "Employee.h"
#include "Payrecord.h"
#include "Payroll.h"
#include <string>
#include <sstream>
#include <cstdlib>
#include <cstring>
#define MAX_EMPLOYEES 80



  //Matthew Vollkommer
  //CSCI 1730
  //7/24/2014


//*************************************************************************
//nonmember functions

  
  //overload the << operator for Employee
std::ostream& operator<< (std::ostream & os, const Employee & e){
	os 	<< e.getName() 
		<< " worked "
		<< std::fixed <<std::setprecision(2)
		<<e.getHours() 
		<< " hours at $" 
		<< e.getWage() 
		<< " per hour";
		
		return os;
	}
	  //overload the << operator for Payrecord
std::ostream& operator<< (std::ostream & os, const Payrecord & p){
	
		os<< std::fixed <<std::setprecision(2)
		 << "\nGross:\t $  " <<p.getGross() 
		  <<"\nTaxes:\t $  " <<p.getTax()
		  <<"\nNet:  \t $  " <<p.getNet();

		return os;//return the output stream
	}//os<<

//**************************************************************
//constructors
std::istream & operator>>(std::istream& is, Employee & e) {


	//create local variables
  std::string s;
	double h, w;
	char name [80];          // a C-string


	//extract first arg is into s string?
  if(!(is >> s)){
	//no, error
	std::cout <<"Incorrect format on Employee name" << std::endl;
		exit (1);
	}
	
	// debug std::cout << s << std::endl;
	
	//extract second arg into h double?
	if(!(is >> h)){
	//no, error
	 std::cout <<"Incorrect format on Employee hours"<< std::endl;
		exit (1);
	}
	
	//debug std::cout <<h << std::endl;
	
	//extract third arg into w double?
	if(!(is >> w)){
	//no error
	 std::cout <<"Incorrect format on Employee wage" << std::endl;
		exit (1);
	}
	
	//debug std::cout<< w << std::endl;
	
	strcpy(name, s.c_str()); // copy the C-string from the string into the 
	 
	name[sizeof(name)/sizeof(char)] = '\0';//must be null terminated
	 
	//debug std::cout <<  "pass 0 " << name << std::endl;
	 
	e.setName(name);//set the name
	
	//debug std::cout<< "pass1" << std:: endl;
	
	e.setHours(h);//set the hours
	
	//debug std::cout<< "pass2" << std:: endl;
	
	e.setWage(w);//set the wage

	//debug std::cout<< "pass3" << std:: endl;

  return is;       // return the input stream

} // operator>>

 

  /** Default Constructor */
  Payroll::Payroll(){
    num_employees = 0;
	
  }

  /** Overloaded Constructor */
  //copy constructor
  Payroll::Payroll(const Payroll &thePayroll){

	//make the num_employees equal
	num_employees = thePayroll.num_employees;
	
	//copy the arrays into eachother
	for(int counter = 0; counter < MAX_EMPLOYEES; counter++){
		
		//***************************
		//create local variables
		char theName[80];
		int i = 0;//index for while loop
		const char * name = thePayroll.employees[counter].getName();//retrieve the name
		
	
	//*************************************************
	//convert the const char* into char[]
	//getName returns const char* but constructor needs a char array
		while (name[i] != '\0') {
	
			theName[i] = name[i];//copy char
			++i;//increment i
	
		}//while
		
			//include the sentinal character		
			theName[i] = '\0';
		
		//**************************************************
		//add each object to the appropriate array
		
		employees [counter] = Employee(theName, thePayroll.employees[counter].getHours(), thePayroll.employees[counter].getWage());
		payrecords [counter] = Payrecord(thePayroll.payrecords[counter].getGross(), thePayroll.payrecords[counter].getTax(), thePayroll.payrecords[counter].getNet());
		
	}//for
	
	
  }//Payroll const Payroll &thePayroll

  
  //*****************************************************
  //static functions
  
   /** Returns the tax amount for an Employee. */
	double Payroll::getTax(const Employee &e){
	
	return getGross(e)*.15;
	
  }

  /** Returns the net amount for an Employee. */
  double Payroll::getNet(const Employee &e){
 
	return getGross(e) - getTax(e);

  }

 
   /** Returns the gross amount for an Employee. */
  double Payroll::getGross(const Employee &e){
  
	return e.getHours()*e.getWage();
	
  }

  
  //**********************************************************
  //public member functions
  
  /** Reads in a payroll batch file. */
  void Payroll::readBatch(){
  
  
  //****************************************
	//read in the payroll.input.txt and append the employees array
	//open the payroll file for reading
	
	std::string filename;
	std::ifstream myfile;
	std::cout<<"Read from what file?: ";
	std::cin>> filename;
	std::cout<<std::endl;
	myfile.open (filename);
	std::string num;
	
	
	if(!(myfile.is_open())){
	
		std::cout<< " unable to open " << filename <<std::endl;
		return;
	
	}
	
	getline(myfile, num);
	std::stringstream is(num);
	
	//does the first line contain the number of employees?
	if(!(is >> num_employees)){
	
		std::cout<< " first line of "<<filename<< " must be an int" << std::endl;
		
		exit(1);
	}
	
	if(num_employees > MAX_EMPLOYEES){
		std::cout<<"error Max employees is: "<<MAX_EMPLOYEES<<std::endl;
		std::cout<<"Employees given to read is: "<< num_employees<<std::endl;
		exit(1);
	}
	
	//debug std::cout<<num_employees<<std::endl;
	
	int counter = 0;
	//continue reading the file until nothing left to read
	while(!(myfile.eof())  && counter < num_employees){
		//local variables
		std::string info;
		Employee temp;
		//read first line
		std::getline(myfile, info);
		//parse first line
		std::stringstream ss (info);         // a stringstream
		ss >> temp;  //extract from string stream into Employee temp
		
		//debug std::cout<<temp <<std::endl;
		//debug std::cout<<temp <<std::endl;
		//add the employee to the employees array
		employees [counter] = temp;
		//add the employees payrecord to the payrecord array at the same index
		payrecords[counter] = Payrecord (getGross(temp), getTax(temp) , getNet(temp) );
		
		//increment number of employees
		counter++;

		
		if(counter == MAX_EMPLOYEES){
			std::cout << "error Max employees reached, stopped adding at employee no. "<< counter <<": "<< temp.getName() << " from " <<filename<< std::endl;
			break;
		}

		
	}
	
	
	if(!(myfile.eof())){
		std::cout<<"warning, end of file not reached, there may be more employees not read"<<std::endl;
			return;
		}
			

		//close the stream
		myfile.close();
		
	//is the number of employees read the same as the number of employees indicated at the top of the txt?
	if(num_employees != counter){
		std::cout << " error number of employees different from number of employees read \n";
		std::cout << " Number of Employees given: " << num_employees << std::endl;
		std::cout << " Number of Employees read: " << counter << std::endl;
		std::cout << " "<<num_employees - counter <<" Employee(s) will have default values"<<std::endl;
		return;
	}
	
 }
 
  /** Generates checks. If a payroll batch file has not been read, then there 
      are no checks to generate. */
  void Payroll::generateChecks(){
	std::cout<<" Generating Checks"<<std::endl;
	
	//declare local variables
	char dir;
	std::string theFile;

	//store in a file?
	std::cout<< "\nStore output in a file? (y/n):";
	std::cin>> dir;
	std::cout<<"\n"<<std::endl;
	
	
	switch (dir){
	
		//*************************
		//store in a file if yes
		case 'y':{} 
		case 'Y':{
			std::cout<<"\nfilename: ";
			std::cin >> theFile;
			
			//does the file already exist?
			if(std::ifstream(theFile)){
			
			//yes
				std::cout<<"\nFile already exists, content will be truncated \n"<<std::endl;
					bool next = false;
				while(next == false){
					std::cout<<" Continue:y, return to menu:n (y/n):: ";
					char in;
					std::cin>>in;
					
					if(in == 'n' || in == 'N'){
						//if no, return to menu
						std::cout <<"process aborted"<<std::endl;
						return;
					}else if(in == 'Y' || in == 'y'){
						next = true;
						//exit loop
					}else{
						std::cout<< "invalid input, must be y or n" <<std::endl;
						//continue loop
					}
				}//while(next == false)
			}
			
			std::cout<<"\n"<<std::endl;
			std::ofstream myoutput;
			myoutput.open(theFile, std::ios::trunc);
			myoutput<<"Generating Checks\n"<<std::endl;
			//loop through the arrays of employees and payrecords, printing the checks
			for (int counter = 0; counter < num_employees && myoutput.is_open(); counter++){
		
				Employee tempEmployee = employees[counter] ;
				Payrecord tempPayrecord = payrecords[counter];
			
				myoutput << "\n" << tempEmployee << tempPayrecord << std::endl;
				
			}
		
			myoutput.close();//close the stream
	
		//do not store in a file
		}
		
		case 'n':{}
		case 'N':{
		
		//**************************
		//if yes or no, output to console

			//loop through the arrays of employees and payrecords, printing the checks
			for (int counter = 0; counter < num_employees; counter++){
		
				//debug std::cout<<"loop :" << counter << std::endl;
		
				Employee tempEmployee = employees[counter] ;
				Payrecord tempPayrecord = payrecords[counter];
		
				std::cout << tempEmployee << tempPayrecord << "\n" << std::endl;
				
			}
	
			break;
		}
		
		default: 

		//****************************
		//if not yes or no, tell user
		{
			std::cout << dir <<"invalid, input must be n or y"<< std::endl;
			break;
			}
			
		}

	}
	
  /** Generates reports. If a payroll batch file has not been read, then there 
      are no reports to generate. */
  void Payroll::generateReports(){
  
  std::cout<<"\n Reports"<<std::endl;
	//declare local variables
	char dir;
	std::string theFile;

	//store in a file?
	std::cout<< "\nStore output in a file? (y/n):";
	std::cin>> dir;
	std::cout<<"\n"<<std::endl;
	
	//*************************
	//store in a file if yes
	switch (dir){
		case 'y':{} 
		case 'Y':{

			std::cout<<"\nfilename: ";
			std::cin >> theFile;
			
						//does the file already exist?
			if(std::ifstream(theFile)){
			
			//yes
				std::cout<<"\nFile already exists, content will be truncated \n"<<std::endl;
					bool next = false;
				while(next == false){
					std::cout<<" Continue:y, return to menu:n (y/n):: ";
					char in;
					std::cin>>in;
					
					if(in == 'n' || in == 'N'){
						//if no, return to menu
						std::cout <<"process aborted"<<std::endl;
						return;
					}else if(in == 'Y' || in == 'y'){
						next = true;
						//exit loop
					}else{
						std::cout<< "invalid input, must be y or n" <<std::endl;
						//continue loop
					}
				}//while(next == false)
			}
			std::cout<<"\n"<<std::endl;
			std::ofstream myoutput;
			
			myoutput.open(theFile, std::ios::trunc);
			myoutput<<"\n Reports"<<std::endl;		
			myoutput<< "\nHighest Earning Employee:\n"
					<< findHigh() <<"\n\n"
					<< "Lowest Earning Employee:\n"
					<< findLow() <<"\n\n"
					<<"Totals:\n"
					<<"Total Gross Pay $ " << findTotalGross() << "\n"
					<<"Total Tax       $ " << findTotalTax() <<  "\n"
					<<"Total Net Pay   $ " << findTotalNet() << "\n"
					<<"\n"
					<<"Averages:\n"
					<<"Average Gross Pay $ " << findAvgGross()<<"\n"
					<<"Average Tax       $ " << findAvgTax()<<"\n"
					<<"Average Net Pay   $ " << findAvgNet()<< std::endl;
  
			myoutput.close();//close the stream
		}
	case 'n':{}
	case 'N':{
		//***************************
		//output to console if yes or now
		std::cout << "Highest Earning Employee:\n"
			 << findHigh() <<"\n\n"
			 << "Lowest Earning Employee:\n"
			 << findLow() <<"\n\n"
			 <<"Totals:\n"
			 <<"Total Gross Pay $ " << findTotalGross() << "\n"
			 <<"Total Tax       $ " << findTotalTax() <<  "\n"
			 <<"Total Net Pay   $ " << findTotalNet() << "\n"
			 <<"\n"
			 <<"Averages:\n"
			 <<"Average Gross Pay $ " << findAvgGross()<<"\n"
			 <<"Average Tax       $ " << findAvgTax()<<"\n"
			 <<"Average Net Pay   $ " << findAvgNet()<< std::endl;
		
		break;
	}
	//***************************
	//if not y or n, tell user
	
	default:{
		
		std::cout << dir <<"invalid, input must be n or y"<< std::endl;
			break;
	  }
	 }
  }

  

	//****************************************************************
	//private class variables
  int num_employees;
  Employee employees [MAX_EMPLOYEES];
  Payrecord payrecords [MAX_EMPLOYEES];

  //**************************************************************************
  //private member functions

  /** Returns the highest earning Employee. */
  const Employee & Payroll::findHigh() const{
	
	//loop through the array of employees, finding the max paid employee
	int i = 0;
	int max = 0;

	
	for (; i< num_employees; ++i){
		
		//is this employee paid more than the current max?
		if(employees[i].getWage() * employees[i].getHours() > employees[max].getWage()*employees[max].getHours()){
			//yes, he is now the max paid employee
			max = i;
		}
	}
	

	return employees[max];
  }

  /** Returns the lowest earning Employee. */
  const Employee & Payroll::findLow() const{
	
	//loop through the array of employees, finding the max paid employee
	int i = 0; 
	int min = 0;
	for (; i< num_employees; ++i){
		
		//is this employee paid more than the current max?
		if(employees[i].getWage() * employees[i].getHours() < employees[min].getWage()* employees[min].getHours()){
			//yes, he is now the max paid employee
			min = i;
		}
	}
	
	return employees[min];
	
  }

  /** Returns the total gross amount. */
  const double Payroll::findTotalGross() const{
  
	double gross = 0;
	//loop through the pay records, adding each individual gross to the total gross
	for(int i = 0; i < sizeof(payrecords)/sizeof(payrecords[0]); ++i){
		gross += payrecords[i].getGross();
	}
	return gross;
  }

  /** Returns the total tax amount. */
  const double Payroll::findTotalTax() const{
  
	double tax= 0;
	
	//loop through the payrecords, adding each tax to the total
	for(int i = 0; i < num_employees; ++i){
		tax += payrecords[i].getTax();
	}
	
	return tax;
	
  }

  /** Returns the total net amount. */
  const double Payroll::findTotalNet() const{
	
	double net = 0;
	for(int i = 0; i < num_employees; ++i){
		net += payrecords[i].getNet();
	}
	return net;
  }
  
  /** Returns the average gross amount. */
  const double Payroll::findAvgGross() const{
  
	return findTotalGross()/num_employees;
	
  }

  /** Returns the average tax amount. */
  const double Payroll::findAvgTax() const{
	return findTotalTax()/num_employees;
  }
  
  /** Returns the average net amount. */
  const double Payroll::findAvgNet() const{
  
	return findTotalNet()/num_employees;
  }
  

  
