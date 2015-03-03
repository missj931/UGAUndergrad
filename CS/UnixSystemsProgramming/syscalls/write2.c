#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

#define BUFF_SIZE 256

int main() {
  
  char buffer [256] = "test\n";
  int len = strlen(buffer);

  printf("Writing to stdout...\n");
  if (write(STDOUT_FILENO, buffer, len + 1) != len + 1) {
    printf("write error!\n");
  }

  return EXIT_SUCCESS;

}
