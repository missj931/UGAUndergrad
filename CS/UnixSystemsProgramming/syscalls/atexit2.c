#include <stdlib.h>
#include <stdio.h>

void exit_handler3() {
  printf("prints handler3 at exit\n");
}

void exit_handler2() {
  printf("prints handler2 at exit\n");
}

void exit_handler1() {
  printf("prints handler1 at exit\n");
}

int main() {
  /* It's generally good practice to have all of your atexits set as early as 
   * possible */
  atexit(exit_handler1);
  printf("in the program\n");
  atexit(exit_handler2);
  atexit(exit_handler3);
  atexit(exit_handler3);
  printf("before exit\n");
  return EXIT_SUCCESS;
}
