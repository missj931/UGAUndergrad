#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>

#define BUFF_SIZE 256

int main() {
  
  int fd;
  char filename [BUFF_SIZE];
  
  printf("Enter a filename: ");
  scanf(" %s", &filename);

  fd = open(filename, O_RDONLY);

  if (fd != -1) {
    printf("Successfully openned '%s'; fd = %d.\n", filename, fd);
  } else {
    printf("Could not open '%s'; fd = %d (should be -1)\n", filename, fd);
  } 

  return EXIT_SUCCESS;

}

