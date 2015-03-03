//simplush.c
//contains main method
//include

#ifndef SIMPLUSH_H
#define SIMPLUSH_H
#include "thriller.h"


/**

//continue n: Send SIGCONT to job with job number n. If the job is a foreground job, then your shell
//should bring this job into the foreground, update any state variables needed to record that the job was
//continued, and wait for the job to either terminate or stop. If the job is a background job, then your shell
//need only update the state variables to record that the job was continued.
void continueJob(int n);


void kill(int n);


void pwd();


void delenv();


void setenv();


void exitor(int n);

//cd [dir] : Change the current working directory to dir
void cd(char*dir);

void initializeShell();
**/

int main();

#endif