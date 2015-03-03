# Project 3 - Simplush

Matthew Vollkommer(412)

## Description

Simplush is job-controlling shell that supports
	*changing the current working directory
	*printing the current working directory
	*killing processes
	*exiting the shell
	*foreground tasks
	*background tasks
	*changing foreground and background tasks
	*removing environmental variables passed down to children
	*printing Michael Jackson's thriller
	*naming child processes
	*Removing environmental variables
	
## Additional Features

In addition to the basic features listed above, my implementation includes
the following additional features:

 * String Literals in Command-Line Arguments
 * Jobs with Command Pipelines
 * redirection of standard input and output
 *foreground jobs and background jobs using &
 *handling quoted command line arguments

## Compile, Run, and Clean

To compile and run my shell, you can use the following commands:

//Compile//
 $ make
 -compiles all of the source files and links them into an executable
 
//run//
 $ ./simplush
 -executes the simplush program
 
 //clean//
If you need to clean the build environment, then you can use the following
command:
 $ make clean
 
 For more supported make commands, open the Makefile

 
 ## Supported Commands
 		
		"cd [dir]" - change directory
		
		"continue n" - Send SIGCONT to job with job number n. This brings the job into the foreground and does 
						any cleanup that might be necessary so that your shell knows the job was continued.
						
		"delenv name" - remove any instance of an environmental variable called name in the environment listed
						that is passed to child processes.
		"exit[n]" - Exit the shell, returning a status of n to the shell's parent. If n is omitted, the exit status is that
					of the last command executed
					
		"jobs" - lists the jobs running in the background in the order in which they were executed, giving the job number, 
					and incrementing integer value. The oldest job in the list always have a job number of 1. The output
					of this command should look something like the following:
						[1] RUNNING $ ls -a &
						[2] STOPPED $ ls -alh &
						[3] STOPPED $ ls -lh > lsout.txt &
						
					The first value is the job's number. The second value indicates the current state of the job's process.
					The rest of the line show the command the user typed into the shell to execute the job
		
		"kill n" - sends SIGTERM to job with job number n.  This also does any cleanup that might be
					neccesary so that your shell knows the job was killed.
					
		"pwd" - prints current working director
		
		"setenv [name[=value]]" - Set a name to be passed to child processes in the environment list. If no names are supplied,
								the current state of the environment list is displayed. By default the initial
								name=value strings in the environment list should be inherited from you shell's parent
								
		"thriller" - prints the Lyrics to Michael Jackson's Thriller