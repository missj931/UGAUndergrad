#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

#define BUFF_SIZE 256

int main() {
  
  int fd;
  char filename [BUFF_SIZE];
  
  printf("Enter a filename to redirect output to: ");
  scanf(" %s", &filename);

  fd = creat(filename, 0644);

  if (fd == -1) {
    printf("Error openning file '%s'\n", filename);
    exit(1);
  }

  if (dup2(fd, STDOUT_FILENO) == -1) {
    printf("Could not redirect standard out to '%s'\n", filename);
    exit(1);
  }

  printf("This message should\n");
  printf("written to the file\n");
  printf("and not to the scren.\n");

  close(fd);

  return EXIT_SUCCESS;

}
