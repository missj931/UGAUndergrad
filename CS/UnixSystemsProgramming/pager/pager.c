
/* This example program creates a child process that executes the less
 * command. A pipe is used to pipe the standard output of the parent
 * process to the standard input of the child process.
 */

#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {

  int fd [2];
  if ((pipe(fd) == -1)) {
    printf("couldn't create pipe.\n");
    exit(1);
  } // if

  pid_t pid = fork();
  if (pid < 0) {

    // couldn't fork
    printf("fork failed!");
    exit(1);

  } else if (pid == 0) {

    // child
    dup2(fd[0], STDIN_FILENO);
    close(fd[0]);
    close(fd[1]);

    // exec less
    char ** args = malloc(2 * sizeof(char *));
    args[0] = malloc(5 * sizeof(char));
    strcpy(args[0], "less");
    args[1] = NULL;

    execvp(args[0], args);

    for (int i = 0; i < 2; ++i) {
      free(args[i]);
    } // for
    free(args);

    exit(1);

  } else {

    // parent
    dup2(fd[1], STDOUT_FILENO);
    close(fd[0]);
    close(fd[1]);

    // generate some output
    for (int i = 0; i < 1000; ++i) {
      printf("line %d - blah blah\n", i);
    } // for
    
    // wait for child
    waitpid(pid, NULL, 0);

    exit(0);

  } // if

  return 0;

} // main


