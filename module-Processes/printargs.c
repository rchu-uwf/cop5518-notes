#include <stdio.h>
/*
Usage:
gcc -g -Wall printargs.c -o pa
./pa -m file

*/
int main (int argc, char ** argv) {

  int i;
  for (i=0; i< argc; ++i)
    printf("argv[%d] = |%s|\n", i, argv[i]);
  return 0;

}
