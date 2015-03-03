#include <stdlib.h>
#include <stdio.h>

void exit_handler() {
  printf("prints at exit\n");
}

int main() {
  atexit(exit_handler);
  printf("in the program\n");
  return EXIT_SUCCESS;
}
