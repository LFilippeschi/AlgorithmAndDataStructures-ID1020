/*=============================================================================
 |   Assignment:  LAB
 |
 |       Author:  Leonardo Filippeschi
 |       Contac:  lfil@kth.se
 |
 |      Created:  01.09.2020
 |  Last edited:  01.09.2020
 |
 |        Class:  ID1020 HT202- ALgorithms and Data Structures 
 |
 |   Instructor:  Robert Rönngren
 |
 +-----------------------------------------------------------------------------
 |
 |  Description:  2. In java implement a recursive and an iterative version of 
 |                a function which reads characters from stdin until a newline 
 |                character is read and then prints them on stdout in reverse order.
 *===========================================================================*/

#include <stdio.h>
#include <stdlib.h>

void iterativePrint()
{
    const int MAX_LENGTH = 50;
    char c;
    char arr[MAX_LENGTH];
    int count;
    while (c != EOF)
    {
        c = getchar();
        count = 0;
        if (c == EOF)
            break;
        while (c != '\n')
        {
            if (count >= MAX_LENGTH)
                break;
            arr[count++] = c;
            c = getchar();
        }
        while (count >= 0)
        {
            putchar(arr[--count]);
        }
        putchar('\n');
    }
}

void recursivePrint()
{
    char c = getchar();
    if (c == EOF)
        return;
    else
    {
        if (c == '\n')
        {
            putchar(c);
            recursivePrint();
        }
        recursivePrint();
        putchar(c);
    }
}

int main(int argc, char const *argv[])
{
    printf("Input 1 for iterative, 2 for recursive.\n");
    int choice;
    scanf("%d", &choice);
    switch (choice)
    {
    case 1:
        iterativePrint();
        break;

    case 2:
        recursivePrint();
        break;
    default:
        break;
    }
    return 0;
}
