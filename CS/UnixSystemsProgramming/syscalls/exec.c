#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <sys/types.h>

int main() {

  pid_t pid = fork();

  if (pid < 0) {
    printf("couldn't fork\n");
    exit(1);
  } else if (pid == 0) {
    printf("in child with pid = %d and parent = %d\n", getpid(), getppid());
    int fd = creat("output.txt", 0644);
    dup2(fd, STDOUT_FILENO);
    execlp("ls", "ls", "-a", (char *) NULL);
    printf("exec failed\n");
    close(fd);
    exit(1);
  } else {
    printf("in parent with pid = %d and chile = %d\n", getpid(), pid);
    int status;
    wait(&status);
    printf("after child terminates\n");
    if (WIFEXITED(status)) {
      printf("child terminated with exit status = %d\n", WEXITSTATUS(status));
    } else {
      printf("child terminated abnormall...\n");
    }
    exit(0);
    // wait
  }

  return EXIT_SUCCESS;
}
