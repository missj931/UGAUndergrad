#Makefile
#Matthew Vollkommer
#CSCI 1730 project 3

CC = gcc
COMPILE = $(CC) $(CFLAGS)
DEBUG = -g -O0
CFLAGS = $(DEBUG) -std=c11 -c
LFLAGS = $(DEBUG) -std=c11 -o
LINK = $(CC) $(LFLAGS)
RM =rm -f

all: simplush.o thriller.o
	$(LINK) p3 simplush.o thriller.o
	
simplush.o: simplush.c simplush.h thriller.h
	$(COMPILE) simplush.c
	
thriller.o: thriller.c thriller.h
	$(COMPILE) thriller.c
	
.PHONY: clean cleancore

clean:
	$(RM) *.o
	$(RM) p3
	
cleancore:
	$(RM) core.*
