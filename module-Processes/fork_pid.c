#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <unistd.h>

/*
Date: 2020-05-20

Usage:
gcc fork_pid.c -o fork_pid
./fork_pid

Reference:
Operating System Concepts Page 118
(Silberschatz, 2018-04-04)

Silberschatz, A., Gagne, G., Galvin, P. B.  (2018-04-04). Operating System Concepts, Enhanced eText, 10th Edition [VitalSource Bookshelf version].  Retrieved from vbk://9781119320913


*/

int main()
{
    pid_t pid;

    /* fork a child process  */
    pid = fork();

    if (pid < 0)
    { /* error occurred */
        fprintf(stderr, "Fork Failed");
        return 1;
    }
    else if (pid == 0)
    { /* child process */
        execlp("/bin/ls", "ls", NULL);
    }
    else
    { /* parent process */
        wait(NULL);
        printf("Child Complete");
    }
    return 0;
}
