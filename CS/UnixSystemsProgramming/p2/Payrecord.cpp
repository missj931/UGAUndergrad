
#include "Payrecord.h"
#include <iostream>
#include <iomanip>



  //Matthew Vollkommer
  //CSCI 1730
  //7/24/2014
//******************************************************************
//constructors
  /** Default Constructor */
  Payrecord::Payrecord(){
	//set default values
	//is this correct?
	setGross (0.00);
	setTax (0.00);
	setNet(0.00);
	
  }

  /** Overloaded Constructor */
  Payrecord::Payrecord(double g, double t, double n){
	setGross(g);
	setTax(t);
	setNet(n);
  
  }

  //******************************************************************
//public member functions
  /** Sets the gross amount. */
  void Payrecord::setGross(double g){
	
	gross = g;
	
  }

  /** Sets the tax amount. */
  void Payrecord::setTax(double t){
  
	tax = t;
  }

  /** Sets the net amount. */
  void Payrecord::setNet(double n){
	net = n;
  }

  /** Returns the gross amount. */
  double Payrecord::getGross() const{
	return gross;
  }

  /** Returns the tax amount. */
  double Payrecord::getTax() const{
  
	return tax; 
  
  }

  /** Returns the net amount. */
  double Payrecord::getNet() const{
	
	return net;
	
  }

//******************************************************************
//private class variables

  double gross; // gross amount
  double tax;   // tax amount
  double net;   // net amount




