#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

#define BUFF_SIZE 256

int main() {
  
  int fd, cr;
  char filename [BUFF_SIZE];
  
  printf("Enter a filename: ");
  scanf(" %s", &filename);

  fd = open(filename, O_WRONLY | O_CREAT | O_TRUNC);

  if (fd != -1) {
    printf("Successfully openned '%s'; fd = %d.\n", filename, fd);
  } else {
    printf("Could not open '%s'; fd = %d (should be -1)\n", filename, fd);
  } 

  int n;
  char buffer [256] = "test\n";
  int len = strlen(buffer);

  printf("Writing to file...\n");
  if (write(fd, buffer, len + 1) != len + 1) {
    printf("write error!\n");
  }

  cr = close(fd);

  if (cr != -1) {
    printf("Successfully closed '%s'; cr = %d.\n", filename, cr);
  } else {
    printf("Could not close '%s'; cr = %d (should be -1)\n", filename, cr);
  } 

  return EXIT_SUCCESS;

}
