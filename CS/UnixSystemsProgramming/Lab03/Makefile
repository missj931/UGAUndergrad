#Matthew Vollkommer 810249412
#lab03
CC = gcc
COMPILE = $(CC) $(CFLAGS)
DEBUG = -g
CFLAGS = -c $(DEBUG)

all: stat minmax endtime time degrees 

stat: stat.o
	$(CC) -o stat stat.o
	
stat.o: stat.c
	$(COMPILE) stat.c

minmax: minmax.o
	$(CC) -o minmax minmax.o
	
minmax.o: minmax.c
	$(COMPILE) minmax.c

endtime: endtime.o
	$(CC) -o endtime endtime.o
	
endtime.o: endtime.c
	$(COMPILE) endtime.c

time: time.o
	$(CC) -o time time.o
	
time.o: time.c
	$(COMPILE) time.c

degrees: degrees.o
	$(CC) -o degrees degrees.c
	
degrees.o: degrees.c
	$(COMPILE) degrees.c

.PHONY: clean cleancore

clean:
	rm -f *.o
	rm -f stat
	rm -f degrees
	rm -f time
	rm -f endtime
	rm -f minmax

cleancore:
	rm -f core.*
