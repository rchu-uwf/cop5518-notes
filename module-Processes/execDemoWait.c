#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

#include <sys/types.h>
#include <sys/wait.h>

/*
Date: 2020-05-25

Usage:
gcc execDemoWait.c -o execDemoWait
./execDemoWait

*/


int main(int argc, char ** argv)
{
   pid_t pid;
   int  status;

   printf("Before fork \n");
   pid = fork();

   if (pid) {
     printf ("parent: pid = %d\n",pid);
   } else {
     execl("./pa", "pa", "-m", "file", NULL);
     printf("ERROR: child: pid = %d\n", pid);
     exit(1);
   }
   printf("more output\n");
  
   wait (&status);

   printf("child has terminated with exit code %d \n Finishing parent code\n", status);

   return 0;
}
