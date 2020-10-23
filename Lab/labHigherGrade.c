/*=============================================================================
 |   Assignment:  LAB1 higher grade c implementation
 |
 |       Author:  Leonardo Filippeschi
 |       Contac:  lfil@kth.se
 |
 |      Created:  05.09.2020
 |  Last edited:  05.09.2020
 |
 |        Class:  ID1020 HT202- ALgorithms and Data Structures 
 |
 |   Instructor:  Robert Rönngren
 |
 +-----------------------------------------------------------------------------
 |
 |  Description:  The program should check if the parentheses are "balanced" or not
 |
 |        Input:  Series of parentheses: '(', ')', '[', ']', '{', '}'.
 |
 |       Output:  TRUE if the series is balanced, FALSE otherwise.
 |
 |    Algorithm:  Djikstra's stack: push characters on the stack if they are equal 
 |                to: '(','[','{'. otherwise pop and check with current character.
 |                If we get a matching pair keep going otherwise return false. 
 |                Keep going until the end and then check the stack, if empty return true 
 |                else false.
 |
 +-----------------------------------------------------------------------------
 |
 |   Complexity
 |
 |         Time: O(N), Algorithm goes thorugh the array once and therefore dependent 
 |                     on the size of the input.
 |
 |       Memory: O(N), 2 bytes * N.
 |
 *===========================================================================*/

#include <stdio.h>
#include <stdlib.h>

int isBalanced()
{
    char *arr;
    int size=4;
    arr = (char *)malloc(size * sizeof(char));
    char c = getchar();
    char x;
    int index = 0;
    while (c != (char)'\n')
    {
        if (c == '(' || c == '[' || c == '{')
        {
            if(index>size){
                size*= 2;
                arr = realloc(arr, size);
            }
            arr[index++] = c;
        }
        else
        {
            x = arr[--index];
            if(index<size/4){
                size/=4;
                arr = realloc(arr, size);
            }
            if ((x == '(' && c == ')') || (x == '[' && c == ']') || (x == '{' && c == '}'))
            {
            }
            else
            {
                return -1;
            }
        }
        c = getchar();
    }
    if (index == 0)
    {
        return 1;
    }
    else
        return -1;
}

int main(int argc, char const *argv[])
{
    printf("isBalanced? %d", isBalanced());
}
