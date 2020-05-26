#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
/*
Date: 2020-05-25

Usage:
gcc execDemo.c -o execDemo
./execDemo

*/


int main(int argc, char ** argv)
{
   pid_t pid;

   printf("Before fork \n");
   pid = fork();

   if (pid) {
     printf ("parent: pid = %d\n",pid);
   } else {
     execl("./pa", "pa", "-m", "file", NULL);
     printf("child: pid = %d\n", pid);
     exit(1);
   }
   printf("more output\n");

   return 0;
}
