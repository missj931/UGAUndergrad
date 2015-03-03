#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>

void print(char * string) {
  write(STDOUT_FILENO, string, strlen(string));
}

int main() {

  printf("Before the fork!\n");
  pid_t pid = fork();

  if (pid < 0) {
    printf("failed to fork...\n");
    exit(1);
  } else if (pid == 0) {
    char buffer [200];
    sprintf(buffer, "I'm a child with PID = %d and PPID = %d\n", getpid(), getppid());
    print(buffer);
  } else {
    sleep(1);
    char buffer [200];
    sprintf(buffer, "I'm the parent with PID = %d and child PID = %d\n", getpid(), pid);
    print(buffer);
  }

  printf("This prints from both!\n");

  return EXIT_SUCCESS;

}
