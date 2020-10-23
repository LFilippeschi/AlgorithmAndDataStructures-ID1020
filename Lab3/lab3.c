/*=============================================================================
 |   Assignment:  ASSIGNMENT 
 |
 |       Author:  Leonardo Filippeschi
 |       Contac:  lfil@kth.se
 |
 |      Created:  26.09.2020
 |  Last edited:  26.09.2020
 |
 |        Class:  ID1020 HT2020 - ALgorithms and Data Structures 
 |
 |   Instructor:  Robert Rönngren
 |
 +-----------------------------------------------------------------------------
 |
 |  Description:  1. Write a simple filter to clean a text, i.e. to remove all 
 |                characters that are not alphabetic, blank or newline - 
 |                replacing every such character by a blank to keep the number 
 |                of characters constant to the original text.
 |
 *===========================================================================*/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

int main(int argc, char const *argv[])
{
    char c;
    while ((c = getchar()) != EOF)
    {
        if (isalnum(c) || c == '\n')
        {
            putchar(c);
        }
        else
            putchar(' ');
    }
}
