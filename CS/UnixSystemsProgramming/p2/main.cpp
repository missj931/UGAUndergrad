#include <iostream>
#include <iomanip>
#include <cstdlib>
#include "Payroll.h"

using namespace std;

/* function prototypes */

#define EXIT 4
#define MAX_CHOICE 4

void showMenu();

int main() {

  int choice;
  Payroll thePayroll;

  showMenu();
  cin >> choice;

  while (choice != EXIT){
    if ((choice > 0) || (choice < MAX_CHOICE)){
      switch (choice){
      case 1:  thePayroll.readBatch(); break;
      case 2:  thePayroll.generateChecks(); break;
      case 3:  thePayroll.generateReports(); break;
      case 4:  exit(0);
      } 
    } else {
      cout << "Please enter a valid choice ... " << endl;
    } // if
    showMenu();
    cin >> choice;
  } // while

  return EXIT_SUCCESS;

} // main

void showMenu(){
  cout << endl; 
  cout << endl; 
  cout << endl; 
  cout << endl; 
  cout << "          Employee Payroll Processing           " << endl;
  cout << "          ---------------------------           " << endl;
  cout << "                                                " << endl;
  cout << "Options:                                        " << endl;
  cout << "  1 -  Read payroll batch file                  " << endl;
  cout << "  2 -  Generate paychecks                       " << endl;
  cout << "  3 -  Generate report                          " << endl;
  cout << "  4 -  Exit                                     " << endl;
  cout << "                                                " << endl;
  cout << " Enter number of menu choice => ";
} // showMenu
