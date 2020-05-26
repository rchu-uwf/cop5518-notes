#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <unistd.h>
/*
Date: 2020-05-23

Usage:
gcc slides_fork1.c -o slides_fork1
./slides_fork1

*/


int main(int argc, char ** argv)
{
   pid_t pid;
   printf("Output line 1\n");
   pid = fork();
   printf("Output line 2 with pid = %d\n", pid);
   return 0;
}
