
/*=============================================================================
 |   Assignment:  Lab2 Sorting
 |
 |       Author:  Leonardo Filippeschi
 |       Contac:  lfil@kth.se
 |
 |      Created:  19.09.2020
 |  Last edited:  19.09.2020
 |
 |        Class:  ID1020 HT2020 - ALgorithms and Data Structures 
 |
 |   Instructor:  Robert Rönngren
 |
 +-----------------------------------------------------------------------------
 |
 |  Description:  4. Implement a function in C which takes an array of integers 
 |                (both positive and negative) and orders the elements in the 
 |                array so that all negative elements come before the positive.
 |                You are not allowed to sort the array - only collect all 
 |                negative values first. 
 |                The algorithm should only use O(1) extra memory
 |
 |    Algorithm:  Start a loop where we keep track with i and j of the array.
 |                i Starts from the first element and j from the last. The algorithm 
 |                ends when i and j cross each other. If the sign of arr[i] is 
 |                positive and the sign of arr[j] is negative we swap the values.
 |                Otherwise we keep on traversing the array from both sides until 
 |                i and j find an element that is out of place. 
 |
 |   Time 
 |   complexity:  The algorithm will be in the class O(N), since it's one nested 
 |                loops that run through the whole array. At each iteration either
 |                both of the indexes get updated or only one of the two.
 |                In the best case scenario the loop runs N/2 times, which happens 
 |                when the first half is all positive numbers and the second half 
 |                all negatives. The worst case scenario will happen when the 
 |                array is either all positive or negative numbers and the loop
 |                will run N times.
 |
 *===========================================================================*/
#include <stdio.h>
#include <stdlib.h>

int sign(int x)
{
    return (x > 0) - (x < 0);
}

void swap(int *arr, int i, int j)
{
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
}

void printArray(int *arr, int size)
{
    int i = 0;
    while (i < size - 1)
    {
        printf("[%d],", arr[i]);
        i++;
    }
    printf("[%d]", arr[i]);
}

void splitNegativePositive(int *arr, int size)
{
    int i = 0;
    int j = size - 1;
    while (i <= j)
    {
        if (sign(arr[i]) >= 0 && sign(arr[j]) < 0)
        {
            swap(arr, i, j);
            i++;
            j--;
        }
        else if (sign(arr[i]) < 0)
        {
            i++;
        }
        else if (sign(arr[j]) > 0)
        {
            j--;
        }
    }
}

void run()
{
    int size;
    printf("Insert size of array: ");
    scanf("%d", &size);
    printf("%d", size);
    int arr[size];
    printf("\nInsert elements in the array.\n");
    for (int i = 0; i < size; i++)
    {
        scanf("%d", &arr[i]);
    }
    splitNegativePositive(arr, size);
    printArray(arr, size);
}

int main(int argc, char const *argv[])
{
    run();
    // int arr[] = {1, -3, 5, 3, -9, 5, -10};
    // int size = (int)sizeof(arr) / (int)sizeof(int);
    // splitNegativePositive(arr, size);
    // printArray(arr, size);
    return 0;
}
