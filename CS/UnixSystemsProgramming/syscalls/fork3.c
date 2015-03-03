#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdarg.h>
#include <sys/types.h>
#include <sys/wait.h>

#define ITERATIONS 100

int main() {

  printf("Before the fork!\n");

  pid_t pid = fork();

  if (pid < 0) {
    printf("failed to fork...\n");
    exit(1);
  } else if (pid == 0) {
    printf("I'm a child with PID = %d and PPID = %d\n", getpid(), getppid());
    for (int i = 0; i < ITERATIONS; ++i) printf("c%d\n", i);
    exit(1);
  } else {
    printf("I'm the parent with PID = %d and child PID = %d\n", getpid(), pid);
    int status;
    waitpid(pid, &status, 0);
    if (WIFEXITED(status)) {
      printf("The child exited with status = %d\n", WEXITSTATUS(status));
    }
  }

  return EXIT_SUCCESS;

}
