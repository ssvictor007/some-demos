#include <stdio.h>
#include <signal.h>
#include <unistd.h>

static void signal_handler(int signal_no) {
    if (signal_no == SIGQUIT)
    {
        printf("quit signal receive: %d\n", signal_no);
    } else if (signal_no == SIGTERM)
    {
        printf("term signal receive: %d\n", signal_no);
    } else if (signal_no == SIGINT)
    {
        printf("interrupt signal receive: %d\n", signal_no);
    }
        
}

int main(int argc, char const *argv[])
{
    signal(SIGQUIT, signal_handler);
    signal(SIGTERM, signal_handler);
    signal(SIGINT, signal_handler);

    for(int i = 0;; i++) {
        printf("%d\n", i);
        sleep(3);
    }

}
