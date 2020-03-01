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
    int ret = bind(fd, (struct sockaddr *) &addr, sizeof(addr));
    listen(fd, 5);

    int accept_fd;
    char buf[100];
    while(1) {
        if((accept_fd = accept(fd, NULL, NULL)) == -1);
        while ((ret = read(accept_fd, buf, sizeof(buf))) > 0) {
            printf("receive %u bytes: %s\n", ret, buf);
        }

    }
    
}
