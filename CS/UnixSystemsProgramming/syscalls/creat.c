#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>

#define BUFF_SIZE 256

int main() {
  
  int fd;
  char filename [BUFF_SIZE];
  
  printf("Enter a filename to create: ");
  scanf(" %s", &filename);

  fd = creat(filename, 0666);

  if (fd != -1) {
    printf("Successfully created '%s'; fd = %d.\n", filename, fd);
  } else {
    printf("Could not create '%s'; fd = %d (should be -1)\n", filename, fd);
  } 

  return EXIT_SUCCESS;

}
