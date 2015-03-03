//*******************************
//resources
//http://ftp.gnu.org/old-gnu/Manuals/glibc-2.2.3/html_chapter/libc_27.html
//http://linux.die.net/man/3/exec

#define _GNU_SOURCE
//includes
#include <stdio.h>
#include <dirent.h>
#include <stdlib.h>
#include <signal.h>
#include <fcntl.h>
#include <string.h>
#include <unistd.h>
#include <stdio.h>
#include <dirent.h>
#include <stdlib.h>
#include <dirent.h>
#include <stdlib.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdbool.h>
 
//include project headers
#include "thriller.h"
#include "simplush.h"

//define constants
#define MAX_LINE 1024
#define MAX_COMMANDS 20
#define MAX_JOBS 1000


//*********************************
//function prototypes
char *get_current_dir_name(void);
int kill(int pid, int sig);
void thriller();

//variables
int lastExit = 0;
bool suspended = false;
char jobName [MAX_COMMANDS][MAX_LINE]; 
char theJob [MAX_LINE];
char buffer[MAX_LINE] = "initialized 3421 ";
int jobStat [MAX_JOBS];
pid_t jobPID [MAX_JOBS];



//extern variables
extern char **environ;




//print the environment list
void getEnv(){
	
	printf("\n");
    for (char **env = environ; *env; ++env){
        printf("%s\n", *env);
	}//for
	printf("\n");
}

int main(){

	//initialize variables

	//ignore job handling signals
	signal (SIGINT, SIG_IGN);
	signal (SIGQUIT, SIG_IGN);
	signal (SIGTSTP, SIG_IGN);
	signal (SIGTTIN, SIG_IGN);
	//signal (SIGTTOU, SIG_IGN);
	signal (SIGCHLD, SIG_IGN);
	
	ssize_t nread;
	
	//initialize local variables
	
	int jobIndex = 1;
	
	
	//initialize first process in jobs list
	
	jobStat [jobIndex] = 0;
	jobPID [jobIndex] = getpid();
	
	//initialze job names
	for (int counter = 0; counter < MAX_COMMANDS; ++counter){
		jobName[counter][0] = '\0';
	}
		
	strcpy(jobName [jobIndex], "./p3");

	while(true){
	
		//sleep(1);
		//prompt and get user input
		printf("\n-simplush-0.8$ ");
		
		buffer[0] = '\0';
		fgets(buffer, MAX_LINE, stdin);
		strcpy(theJob, buffer);

		//replace an enter character with a NULL character for NULL termination
		for (int counter = 0; counter < MAX_LINE; ++counter){
			if (buffer[counter] == '\n'){
				buffer[counter] = '\0';
				break;
			}//if
		}//for

		//parse the user input
		char *parse;
		parse = strtok(buffer, " ");
		char *commands[MAX_COMMANDS] = {0};//initialize commands to NULL
		char * temp [MAX_COMMANDS] = {0};//this is used if background process

				
		int words = 0;
		//place the input into the commands array
		while(parse != NULL ){
			commands[words] = parse;
			parse = strtok(NULL, " ");
			++words;
	
		}//while

		for(int counter = 0; counter < words -1; counter++){
			temp[counter] = commands [counter];
		
		}
			
		//check for tokens
		bool outputerrtrunc = false;			
		bool input = false;
		bool outputapp = false;
		bool outputerrapp =false;
		bool outputtrunc = false;
		bool foreground = true;

		if(words > 1 && strcmp(commands[words - 1], "&") == false){
				
				foreground = false;//it is a background job
				
		}
		
		
			for(int counter = 0; counter < words -1; counter++){
				temp[counter] = commands [counter];
		
			}
			
			for(int counter =0; counter < words; counter++){
			
				if(strcmp(commands[counter], "<") == false){
					input = true;
					break;
				}				
				
				if(strcmp(commands[counter], ">>") == false){
					outputapp = true;
					break;
				
				}
				
				if(strcmp(commands[counter], ">") == false){
					outputtrunc = true;
					break;
				
				}
				
				if(strcmp(commands[counter], "e>>") == false){
					outputerrapp = true;
					break;
				}
				if(strcmp(commands[counter], "e>") == false){
					outputtrunc = true;
					break;
				}
			
			}
		
		
		if(commands[0] == NULL){
		
		//do nothing for an empty command
		
		//***************************************************************************************************************************************************
		//handle builtin commands

		}else if(strcmp(buffer, "exit") == false){
			//exit [n]: Exit the shell, returning a status of n to the shell's parent. If n is omitted, the exit status is
			//that of the last job that was launched.
			
			if(commands[2] != NULL){
				fprintf(stderr, "too many arguments for command %s", commands[0]);
				continue;
			}
			
			//check command validity(looking for supported commands
			if(strcmp(commands[0], "exit") == false && commands[1] != NULL){
				printf("\n");
				return atoi(commands[1]);//exit
			
			}else{
		
				break;//exit loop
			
			}
		
		
		
		//setenv [name[=value]]: set a name to be passed to child processes in the environment list. If no
		//names are supplied, the current state of the environment list is displayed. By default the initial
		//name=value strings in the environment list should be inherited from your shell's parent.
		}else if(strcmp(commands[0], "setenv") == false){
			//setenv [name[=value]]
			//you can use unsetenv and setenv from <stdlib.h> to set and delete.
			//you may use any method you wish to list
			//the environment list when no name is supplied.

			if(commands[3] != NULL){
				fprintf(stderr,"too many arguments for command %s", commands[0]);
				continue;
			}
			
			if(commands[1] != NULL && commands[2] == NULL){
				fprintf(stderr,"too few arguments for command %s", commands[0]);
				continue;
			}
			
			if(commands[1] != NULL){
			
				int result = setenv(commands[1], commands[2], 1);
		
				if(result == -1){
					fprintf(stderr,"Unsuccessful set environmental list for: %s\n", commands[1]);
				}
				
			}else{
			
				getEnv();
			}
			
		//delenv name : Remove any instance of an environmental variable called name in the environment list
		//that is to be passed to child processes
		}else if(strcmp(commands[0], "delenv") == false){
			//you can use unsetenv and setenv from <stdlib.h> to set and delete.
			//you may use any method you wish to list
			//the environment list when no name is supplied.
			
			if(commands[2] != NULL){
				fprintf(stderr,"too many arguments for command %s", commands[0]);
				continue;
			}
			
			
			if(commands[1] != NULL){
				int result = unsetenv(commands[1]);
		
				if(result == -1){
					fprintf(stderr,"Unsuccessful delete environmental list for: %s\n", commands[1]);
				}
				
			}else{
			
				getEnv();
			}
		//jobs: Lists the jobs your shell has launched that have not yet terminated, displaying each job's status
		//as described above. To keep things simple, the first job your shell launches should have a job number of
		//1. Subsequent jobs should receive job numbers 2, 3, ..., etc. Job's should be listed in ascending order
		//the last job in the list always has the largest job number.
		}else if (strcmp(commands[0], "jobs") == false){
			//list jobs and their status
			
			if(commands[1] != NULL){
				fprintf(stderr,"too many arguments for command %s", commands[0]);
				continue;
			}
			
			for(int counter = 1; counter <= jobIndex; ++counter){
			
				printf("\n [%i] ", counter);
				
				switch (jobStat[counter]){
					
					case 0: { printf("RUNINNG\t\t$ "); break;}
				
					case 1: { printf("STOPPED\t\t$ "); break;}

					case 2: { printf("TERMINATED\t\t$ "); break;}

					default: {break;}
				}
				
				printf("%s\n", jobName[counter]);
			}

		//kill n: Sends SIGTERM to job with job number n. This also does  any cleanup that might be 
		//necessary so that your shell knows the job was killed
		//**********************************
		//kill n
		}else if (strcmp(commands[0], "kill") == false){
	
			if(commands[2] != NULL){
				fprintf(stderr,"too many arguments for command %s", commands[0]);
				continue;
			}
			
			int result = kill(jobPID[atoi(commands[1])], SIGTERM);//kill SIGTERM
			

			//error handle
			if(result != -1){
				//killed job
				printf("\n killed job no. %i\n\n", atoi(commands[1]));
				//update jobs
				jobStat[atoi(commands[1])] = 2;
				
				
	
			}else{
				//error occurred
				fprintf(stderr,"\n Could not kill job no. %i\n", atoi(commands[1]));
		
			}
	
		//continue n: Send SIGCONT to job with job number n. If the job is a foreground job, then your shell
		//should bring this job into the foreground, update any state variables needed to record that the job was
		//continued, and wait for the job to either terminate or stop. If the job is a background job, then your shell
		//need only update the state variables to record that the job was continued.
		//********************************
		//coninue (n)
		}else if(strcmp(commands[0], "continue") == false){

			if(commands[2] != NULL){
				fprintf(stderr,"too many arguments for command %s", commands[0]);
				continue;
			}
			
			if(commands[1] == NULL){
				fprintf(stderr,"too few arguments for command %s", commands[0]);
				continue;
			
			}
		
			if(atoi(commands[1]) == 1){
				continue;
			}
				int result = kill(jobPID[atoi(commands[1])], SIGCONT);//tell a job to continue
			

				//error handle
				if(result != -1){
			
				int status;
				int *statusPtr = &status;
				//continued job
				printf("\n continued job no. %i\n", atoi(commands[1]));
				setpgid(getpgid(atoi(commands[1])), getpgid(atoi(commands[1])));
				//update jobs
				jobStat[atoi(commands[1])] = 0;
				//sleep(1);
				
			
				
					
		
						//UPDATE JOB STATUS

				while(true){
				
				waitpid((pid_t) atoi(commands[1]), statusPtr, WUNTRACED|WCONTINUED);
				
				
						if (WIFEXITED(status)) {
							lastExit = WEXITSTATUS(status);
							jobStat[atoi(commands[1])] = 2;
							break;//exit loop
						}//if
					
						if (WIFSIGNALED (status)){
						
							lastExit = WTERMSIG(status);
							jobStat[atoi(commands[1])] = 2;
							break;// exit loop
						}
						
						if(WIFSTOPPED(status)){
						
							lastExit = WSTOPSIG(status);
							jobStat[atoi(commands[1])] = 1;
							break;// exit loop
						}
						
						if(WIFCONTINUED(status)){
							
							jobStat[atoi(commands[1])] = 0;
						
						}

				}
				
					jobStat[atoi(commands[1])] = 2;
				
					//debug sleep(1);
				
				tcsetpgrp(STDIN_FILENO, 0);
				
	
			}else{
				//error occurred
				fprintf(stderr,"\n Could not continue job no. %i\n", atoi(commands[1]));
		
			}
	
		
		//*********************************
		//pwd
		}else if (strcmp(commands[0], "pwd") == false){
		
		
			if(commands[1] != NULL){
				fprintf(stderr,"too many arguments for command %s", commands[0]);
				continue;
			}
			//pwd : prints out the absolute path of the current working directory.
			//declare local variable
			char cwd[MAX_LINE];
     
			//you can get the full path of the current working directory
			//using the getwd function in <inistd.h>

			//getwd
	
			if (getcwd(cwd, sizeof(cwd)) != NULL){
        
				printf("\n Current working dir: %s\n \n", cwd);
    
			}else{

				fprintf(stderr, "\n getcwd() error; could not get current working directory\n\n");
			}
		
			
		
		//*************************************
		//execute built in commands
		//cd [dir] : Change the current working directory to dir
		//*****************************
		}else if (strcmp(commands[0], "cd") == false){
	
	
			if(commands[2] != NULL){
				fprintf(stderr,"too many arguments for command %s", commands[0]);
				continue;
			}
			
			//if dir is not specified, assume the value for environmental variable HOME.
			//You can change the directory to a specified path string using chdir function
			//<inistd.h>
	
			if(commands[1] == NULL){
				
				//if the second argument is NULL, the user wants to change to the home directory
				commands[1] = getenv("HOME");
			}		
			//chdir
			int result = chdir(commands[1]);
			
			//was there an error?
			if(result == -1){
			//yes
				fprintf(stderr, "\n Could not change directory to '%s'\n\n", commands[1]);
			}
		
		//thriller: prints the lyrics to Michael Jackson's 1982 hit, "Thriller."
		}else if(strcmp(commands[0], "thriller") == false){
		
			if(commands[1] != NULL){
				fprintf(stderr,"too many arguments for command %s", commands[0]);
				continue;
			}
			thriller();
		

		//****************************************************************************************
		//not a built in command
		}else{
		
			
			pid_t pid = fork();
			
			if (pid < 0) {
				printf("\n failed to fork...\n");
				exit(1);
				
			} else if (pid == 0) {			
				//child
				
				//************************************
				//initialize processes
				//restore job control
				signal (SIGINT, SIG_DFL);
				signal (SIGQUIT, SIG_DFL);
				signal (SIGTSTP, SIG_DFL);
				signal (SIGTTIN, SIG_DFL);
				signal (SIGTTOU, SIG_DFL);
				signal (SIGCHLD, SIG_DFL);
				//make first proc a group leader
				//setpgid(0, getpgid(0));
				//setsid();
				//setup any i/o redirection
				
				//setpgid(0,0);
				//************************************
				
				//part of foreground job?

			if(foreground == true){
			
				tcsetpgrp(STDIN_FILENO, getpgid(0));
				
			}else if(foreground == false){
				raise(SIGSTOP);
			}
				

					int fd;			
					if(outputtrunc == true){
					
					int fd;
						fd = open(commands[2], O_CREAT | O_WRONLY | O_TRUNC, 0644 );
						    if (fd < 0){
							
								fprintf(stderr, "open error: %d [%s]\n", errno, strerror(errno));
								exit(1);
							}
						dup2(fd, STDOUT_FILENO);
						close(fd);
						execlp(commands[0], commands[0], NULL);
					
					
					}else if(outputapp == true){
						
						int fd;
						fd = open(commands[2], O_CREAT | O_WRONLY | O_APPEND, 0644 );
						    if (fd < 0){
							
								fprintf(stderr, "open error: %d [%s]\n", errno, strerror(errno));
								exit(1);
							}

						dup2(fd, STDOUT_FILENO);
						close(fd);
						execlp(commands[0], commands[0], NULL);
						
					
					}else if(outputerrapp == true){
						
					int fd;
						fd = open(commands[2], O_CREAT | O_WRONLY | O_APPEND, 0644 );
						    if (fd < 0){
							
								fprintf(stderr, "open error: %d [%s]\n", errno, strerror(errno));
								exit(1);
							}
						dup2(fd, STDERR_FILENO);
						close(fd);
						execlp(commands[0], commands[0], NULL);
						
	

				}else if(outputerrtrunc == true){
					int fd;
						fd = open(commands[2], O_CREAT | O_WRONLY | O_TRUNC, 0644 );
						    if (fd < 0){
							
								fprintf(stderr, "open error: %d [%s]\n", errno, strerror(errno));
								exit(1);
							}
						dup2(fd, STDERR_FILENO);
						close(fd);
						execlp(commands[0], commands[0], NULL);
						
							
				}else if(input == true){
					int fd;
						fd = open(commands[1], O_RDONLY  );
						    if (fd < 0){
							
								fprintf(stderr, "open error: %d [%s]\n", errno, strerror(errno));
								exit(1);
							}
					if (dup2(fd, STDIN_FILENO) == -1) {
			
						exit(1);
				
					}

					execlp(commands[1], commands[1]);
					close(fd);
								
				
				}else if(input != true && outputapp != true && outputtrunc != true && outputerrapp != true && outputerrtrunc != true && foreground == true){
					//**********************************************
				
				
					//exec
					if(execvp(commands[0], commands) == -1){
						//exec failed
						fprintf(stderr,"exec failed for %s", theJob);
						exit(127);
					}
					
					
				} else if(input != true && outputapp != true && outputtrunc != true && outputerrapp != true && outputerrtrunc != true && foreground == false){
					
					//raise(SIGSTOP);
					//exec
					if(execvp(temp[0], temp) == -1){
						//exec failed
						fprintf(stderr,"exec failed for %s", theJob);
						exit(127);
					}
					
				
				}

					
				
				
				exit(0);
				
			} else if (pid > 0){
				//parent 
				
				//debug sleep(1);
				
				
				
				//setpgid(0, 0);
				

							
				int status;
					int *statusPtr = &status;
					
					//did not launch foreground job
					if(foreground == false){
						jobIndex++;
						strcpy(jobName[jobIndex], theJob);
						jobStat[jobIndex] = 1;
						jobPID[jobIndex] = pid;
						continue;
					}//
					
					//kill(SIGCONT, pid);
					waitpid(-1, statusPtr, WUNTRACED|WCONTINUED );
					
		
						//UPDATE JOB STATUS


						if (WIFEXITED(status)) {
							lastExit = WEXITSTATUS(status);
							// debug printf("\n The child exited with status = %i\n", lastExit);
							jobIndex++;
							strcpy(jobName[jobIndex], theJob);
							jobStat[jobIndex] = 2;
							//degub jobPID[jobIndex] = pid;	
							continue;
						}//if
					
						if (WIFSIGNALED (status)){
						
							lastExit = WTERMSIG(status);

							// debug printf("\n The child exited with status = %i\n", lastExit);
							jobIndex++;
							strcpy(jobName[jobIndex], theJob);
							jobStat[jobIndex] = 2;
							jobPID[jobIndex] = pid;	
							continue;
						}
						
						if(WIFSTOPPED(status)){
						
							lastExit = WSTOPSIG(status);
							// debug 
							//printf("\n The child exited with status = %i\n", lastExit);
							jobIndex++;
							strcpy(jobName[jobIndex], theJob);
							jobStat[jobIndex] = 1;
							jobPID[jobIndex] = pid;	
							continue;
						}
						
					if(WIFCONTINUED(status)){
						jobIndex++;
						strcpy(jobName[jobIndex], theJob);
						jobStat[jobIndex] = 0;
						jobPID[jobIndex] = pid;	
					continue;
						}

	
	
	
	

					//******************************************************
					//cleanup

					tcsetpgrp(STDIN_FILENO, 0);
					//give your group terminal control
					//*****************************************************
					
	
				
			}//if(parent||child)
			
			
		
		} // if	
		//***************************************************************not builtin commands
/**
				int stat;
				int *statPtr = &stat;
				pid_t result = waitpid(-1, statPtr, WNOWAIT );
				
				int index = -1;
				for (int counter = 1; counter < jobIndex; counter++){
				
					
				
					if(result = jobPID[counter]){
						index = counter;
						break;
					}
					
				}
				/**
				if(index > 1){
				
				if (WIFEXITED(stat)) {
							jobStat[index] = 2;
							printf("\n %s exited \n", jobName[index]);
							continue;
						}//if
					
						if (WIFSIGNALED (stat)){
						
							jobStat[index] = 2;
							printf("\n %s terminated \n", jobName[index]);
							continue;
						}
						
						if(WIFSTOPPED(stat)){
							jobStat[index] = 2;
							printf("\n %s stopped \n", jobName[index]);	
							continue;
						}
						
						if(WIFCONTINUED(stat)){
						
							jobStat[index] = 0;
							printf("\n %s exited \n", jobName[index]);
							continue;
						
						}
						
			}			
			**/
	
	}//while(true)
		
	printf("\n");
	return jobStat[jobIndex];
}//main


