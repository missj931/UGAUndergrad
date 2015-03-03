#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdarg.h>
#include <sys/types.h>

#define ITERATIONS 1000

int main() {

  printf("Before the fork!\n");

  pid_t pid = fork();

  if (pid < 0) {
    printf("failed to fork...\n");
    exit(1);
  } else if (pid == 0) {
    printf("I'm a child with PID = %d and PPID = %d\n", getpid(), getppid());
    for (int i = 0; i < ITERATIONS; ++i) printf("c%d\n", i);
  } else {
    printf("I'm the parent with PID = %d and child PID = %d\n", getpid(), pid);
    for (int i = 0; i < ITERATIONS; ++i) printf("p%d\n", i);
  }

  printf("This prints from both!\n");

  return EXIT_SUCCESS;

}
