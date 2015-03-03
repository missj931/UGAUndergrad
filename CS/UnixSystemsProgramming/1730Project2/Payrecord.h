#ifndef PAYRECORD_H
#define PAYRECORD_H

#include <iostream>
#include <iomanip>

class Payrecord {

public:

  /** Default Constructor */
  Payrecord();

  /** Overloaded Constructor */
  Payrecord(double g, double t, double n);

  /** Sets the gross amount. */
  void setGross(double g);

  /** Sets the tax amount. */
  void setTax(double t);

  /** Sets the net amount. */
  void setNet(double n);

  /** Returns the gross amount. */
  double getGross() const;

  /** Returns the tax amount. */
  double getTax() const;

  /** Returns the net amount. */
  double getNet() const;

private:

  double gross; // gross amount
  double tax;   // tax amount
  double net;   // net amount

}; // Payrecord

#endif


