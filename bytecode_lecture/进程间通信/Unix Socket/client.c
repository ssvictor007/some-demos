#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <errno.h>
#include <malloc.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/ioctl.h>
#include <stdarg.h>
#include <fcntl.h>
#include <fcntl.h> 
#include <sys/un.h>

int main(int argc, char const *argv[])
{
    int fd = socket(AF_UNIX, SOCK_STREAM, 0);
    struct sockaddr_un addr;
    memset(&addr, 0, sizeof(addr));
    addr.sun_family = AF_UNIX;
    strcpy(addr.sun_path, "tmp.sock");
    

    connect(fd, (struct sockaddr *) &addr, sizeof(addr));

    int rc;
    char buf[100];

    while ((rc = read(STDIN_FILENO, buf, sizeof(buf))) >0 )
    {
        write(fd, buf, rc);
    }
    
    
}
