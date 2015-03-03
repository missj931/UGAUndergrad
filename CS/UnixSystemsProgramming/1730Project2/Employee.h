#ifndef EMPLOYEE_H
#define EMPLOYEE_H
#include <iostream>

class Employee {

public:
  
  /** Default Constructor */
  Employee();

  /** Overloaded Constructor */
  Employee(char * n, double h, double w);

  /** Returns the name. */
  const char * getName() const;

  /** Sets the name. */
  void setName(char n[]);

  /** Returns the number of hours worked. */
  double getHours() const;

  /** Sets the number of hours worked. */
  void setHours(double h);

  /** Returns the wage. */
  double getWage() const;

  /** Sets the wage. */
  void setWage(double w);

 private:

  char   name[80]; // name
  double hours;    // hours worked
  double wage;     // hourly wage

}; // Employee

#endif
