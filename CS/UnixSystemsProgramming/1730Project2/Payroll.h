#ifndef PAYROLL_H
#define PAYROLL_H

#include <iostream>
#include <fstream>
#include "Employee.h"
#include "Payrecord.h"

#define MAX_EMPLOYEES	80

class Payroll {

public:

  /** Default Constructor */
  Payroll();

  /** Overloaded Constructor */
  Payroll(const Payroll &);

  /** Reads in a payroll batch file. */
  void readBatch();

  /** Generates checks. If a payroll batch file has not been read, then there 
      are no checks to generate. */
  void generateChecks();

  /** Generates reports. If a payroll batch file has not been read, then there 
      are no reports to generate. */
  void generateReports();

  /** Returns the tax amount for an Employee. */
  static double getTax(const Employee &);

  /** Returns the net amount for an Employee. */
  static double getNet(const Employee &);

  /** Returns the gross amount for an Employee. */
  static double getGross(const Employee &);
 
private:

  int num_employees;
  Employee employees [MAX_EMPLOYEES];
  Payrecord payrecords [MAX_EMPLOYEES];

  /** Returns the highest earning Employee. */
  const Employee &findHigh() const;

  /** Returns the lowest earning Employee. */
  const Employee &findLow() const;

  /** Returns the total gross amount. */
  const double findTotalGross() const;

  /** Returns the total tax amount. */
  const double findTotalTax() const;

  /** Returns the total net amount. */
  const double findTotalNet() const;

  /** Returns the average gross amount. */
  const double findAvgGross() const;

  /** Returns the average tax amount. */
  const double findAvgTax() const;

  /** Returns the average net amount. */
  const double findAvgNet() const;

}; // Payroll

#endif

