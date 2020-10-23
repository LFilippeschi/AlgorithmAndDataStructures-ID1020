#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char const *argv[])
{
    int nrElements = 120000000;
    // srand(time(0) * 4517);
    // int x;
    // for(int i = 0; i<= nrElements; i++){
    //     x = rand() % 5;
    //     switch (x)
    //     {
    //     case 0:
    //         putchar('(');
    //         break;
    //     case 1:
    //         putchar('[');
    //         break;
    //     case 2:
    //         putchar('{');
    //         break;
    //     case 3:
    //         putchar(')');
    //         break;
    //     case 4:
    //         putchar(']');
    //         break;
    //     case 5:
    //         putchar('}');
    //         break;

    //     default:
    //         break;
    //     }
    // }
    // putchar('\n');
    int i =0;
    for (; i <= (nrElements/6)*1; i++)
    {
        putchar('{');
    }
    for (; i <= (nrElements/6)*2; i++)
    {
        putchar('[');
    }
    for (; i <= (nrElements/6)*3; i++)
    {
        putchar('(');
    }
    for (; i <= (nrElements/6)*4; i++)
    {
        putchar(')');
    }
    for (; i <= (nrElements/6)*5; i++)
    {
        putchar(']');
    }
    for (; i <= (nrElements/6)*6+1; i++)
    {
        putchar('}');
    }
    putchar('\n');

    return 0;
}
