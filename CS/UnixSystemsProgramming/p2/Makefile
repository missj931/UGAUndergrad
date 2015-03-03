#Matthew Vollkommer
#CSCI 1730 project 2 7/15/2014

CC = g++
COMPILE = $(CC) $(CFLAGS)
DEBUG = -g 
CFLAGS = $(DEBUG) -O0 -c -std=c++11
LINK = $(CC) $(DEBUG) -O0

p2: main.o Employee.o Payrecord.o Payroll.o
	$(LINK) -o p2 main.o Employee.o Payrecord.o Payroll.o

main.o: main.cpp Payroll.h
	$(COMPILE) main.cpp

Employee.o: Employee.cpp Employee.h
	$(COMPILE) Employee.cpp

Payrecord.o: Payrecord.cpp Payrecord.h
	$(COMPILE) Payrecord.cpp

Payroll.o: Payroll.cpp Payroll.h Employee.h Payrecord.h
	$(COMPILE) Payroll.cpp
	
.PHONY: clean cleancore

clean:
	rm -f *.o
	rm -f p2

cleancore:
	rm -f core.*
