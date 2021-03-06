#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

#define BUFF_SIZE 256

int main() {
  
  int fd, cr;
  char filename [BUFF_SIZE];
  
  printf("Enter a filename: ");
  scanf(" %s", &filename);

  fd = open(filename, O_RDONLY);

  if (fd != -1) {
    printf("Successfully openned '%s'; fd = %d.\n", filename, fd);
  } else {
    printf("Could not open '%s'; fd = %d (should be -1)\n", filename, fd);
  } 

  cr = close(fd);

  if (cr != -1) {
    printf("Successfully closed '%s'; cr = %d.\n", filename, cr);
  } else {
    printf("Could not close '%s'; cr = %d (should be -1)\n", filename, cr);
  } 

  return EXIT_SUCCESS;

}

