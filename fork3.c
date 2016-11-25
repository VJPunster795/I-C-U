/* fork */
#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>

int main()
{
    int pid0, pid1;

     	/* fork a child process */
	pid0 = fork();
	if (pid0 > 0)  {                  // parent   
		printf(" pid0 = %d \n", pid0);
		pid1 = getpid();
		printf(" pid1 = %d \n", pid1); 
	} 
	else if (pid0 == 0) {             // child
		printf(" pid0 = %d \n", pid0); 
		pid1 = getpid();
		printf(" pid1 = %d \n", pid1); 
	}   
     else {                             // error 
		fprintf(stderr, "Fork Failed \n"); 
		return 1; 
	} 
	return 0;
} 
