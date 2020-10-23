
/*=============================================================================
 |   Assignment:  Lab2 Sorting
 |
 |       Author:  Leonardo Filippeschi
 |       Contac:  lfil@kth.se
 |
 |      Created:  20.09.2020
 |  Last edited:  20.09.2020
 |
 |        Class:  ID1020 HT2020 - ALgorithms and Data Structures 
 |
 |   Instructor:  Robert Rönngren
 |
 +-----------------------------------------------------------------------------
 |
 |  Description:  1. Implement insertionsort. Augment the sorting process so that 
 |                all the content of the array that is being sorted is printed
 |                after each inner loop iteration. Write a unit test in main()
 |                which allows the user to define the size of the input (N) 
 |                and then input (N) integers from stdin which is to be sorted.
 |
 |    Algorithm:  Start a loop from the second element in the array, save that 
 |                value in a temporary variable. Start an inner loop taking as 
 |                first element the previous element to the current element in 
 |                the outer loop. Keep on exploring the inner loop by descending
 |                until the first element in the array has been reached or 
 |                until an element smaller than the temporary value has been found.
 |                At each iteration of the inner loop swap the elements, so that 
 |                the order is kept in place and the greatest value is to the right.
 |                After each itertion of the outer loop the array is sorted until 
 |                the current value of the outer loop.
 |
 |  Description:  2. Augment the above implementation so that it prints the number
 |                of swaps performed when sorting the array.
 |
 |  Description:  3. Add a method which counts the number of inversions in the 
 |                input array and prints a list of all inversions on the format 
 |                [i,a[i]], [j, a[j]] where i and j are indices and a[i], a[j] 
 |                are the values of the elements. Call the method from main() 
 |                before the array is sorted. Calculate the time complexity for 
 |                the algorithm.
 |
 |   Time 
 |   complexity:  The algorithm will be in the class O(N^2), since it's two nested 
 |                loops that run through the whole array.
 |
 |  Description:  5. Compare the execution times for sorting large arrays of 
 |                integers with insertionsort and merge sort. When should one
 |                select mergesort over insertionsort?
 |
 |  Description:  6. Experiment with the cut-off to insertionsort in merge. 
 |                How is the execution time affected by different values for the cut-off?
 |
 +-----------------------------------------------------------------------------
 |
 |                              Higher grade assignments
 |
 |  Description:  1. Augment the test code from assignment 1 so that the array 
 |                is sorted in descending order instead of ascending order.
 |
 |  Description:  2. Compare the execution times for sorting large arrays of 
 |                integers with quicksort and merge sort. When should one select
 |                quicksort over mergesort?
 |
 |  Description:  3. Compare the execution times of quicksort where the first 
 |                element in each sub-array is selected as partitioning element 
 |                to that of quicksort with median-of-three partitioning.
 |
 |    Algorithm:  InsertionSort, MergeSort, MergeSort with cutoff, QuickSort
 |                QuickSort with median of three.     
 |
 *===========================================================================*/

package Lab2;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class Lab2 {

    public static int recursive = 0;
    public static int countRecursion = 0;

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void printArr(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            System.out.print("[" + arr[i] + "],");
        System.out.println("[" + arr[n - 1] + "]");
    }

    /**
     * Time complexity: worst case scenario, every element is swapped or the array
     * is in reverse order. O(N^2)
     * 
     * @param arr
     */
    public static void countInversions(int[] arr) {
        int inversions = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    inversions++;
                    System.out.println("[" + i + "," + arr[i] + "],[" + j + "," + arr[j] + "]");
                }
            }
        }
        System.out.println("number of inversions: " + inversions);

    }

    public static void splitNegativePositive(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            if (arr[i] >= 0 && arr[j] < 0) {
                swap(arr, i, j);
                i++;
                j--;
            } else if (arr[i] < 0) {
                i++;
            } else if (arr[j] > 0) {
                j--;
            }
        }

    }

    // --------------------------------------------------------------------------
    // InsertionSort

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        int swaps = 0;
        for (int i = 1; i < n; ++i) {
            int value = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > value) {
                arr[j + 1] = arr[j];
                swaps++;
                j--;
            }
            arr[j + 1] = value;
            printArr(arr);
        }
        System.out.println("Number of swaps: " + swaps);
    }

    // --------------------------------------------------------------------------
    // InsertionSort descending order

    public static void insertionSortDescending(int[] arr) {
        int n = arr.length;
        // int swaps = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = -arr[i];
        }
        for (int i = 1; i < n; ++i) {
            int value = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > value) {
                arr[j + 1] = arr[j];
                // swaps++;
                j--;
            }
            arr[j + 1] = value;
            // printArr(arr);
        }
        for (int i = 0; i < n; i++) {
            arr[i] = -arr[i];
        }
    }

    // --------------------------------------------------------------------------
    // MergeSort

    private static void merge(int[] arr, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) { // copy values in support array, use indices instead of objects, save memory
                                         // and time
            aux[k] = arr[k];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) { // run size of array times and finish!
            if (i > mid) {
                arr[k] = aux[j++];// if left part has been copied, finish the right part
            } else if (j > hi) {
                arr[k] = aux[i++];// if right part has been copied, finish the left part
            } else if (aux[j] < aux[i]) // actual check to swap values, if right element > left element. copy greater
                                        // and increment
            {
                arr[k] = aux[j++];
            } else {
                arr[k] = aux[i++];
            }
        }
    }

    private static void sort(int[] arr, int[] aux, int lo, int hi) {
        // countRecursion++;
        // System.out.println("recursive: " + recursive++);
        if (hi <= lo) // return statement when the array has been sorted
            return;
        int mid = lo + (hi - lo) / 2; // define where mid is in the new instance of sort
        sort(arr, aux, lo, mid); // recursive call to sort for the inner left side of the array
        sort(arr, aux, mid + 1, hi); // recursive call to sort for the inner right side of the array
        merge(arr, aux, lo, mid, hi); // merge all the elements staring from the right and going to the left,
                                      // recursive callback
    }

    public static void mergeSort(int[] arr) {
        // countRecursion = 0;
        int size = arr.length;
        int[] aux = new int[size];
        for (int i = 0; i < size; i++) {
            aux[i] = arr[i];
        }
        sort(arr, aux, 0, size - 1);
        // System.out.println(countRecursion);
    }

    // --------------------------------------------------------------------------
    // MergeSort with cutoff to insertionsort

    public static void insertionSortCutoff(int[] arr, int lo, int hi) {
        int n = hi;
        // int swaps = 0;
        for (int i = lo + 1; i <= n; ++i) {
            int value = arr[i];
            int j = i - 1;

            while (j >= lo && arr[j] > value) {
                arr[j + 1] = arr[j];
                // swaps++;
                j--;
            }
            arr[j + 1] = value;
            // printArr(arr);
        }
        // System.out.println("Number of swaps: " + swaps);
    }

    private static void mergeCut(int[] arr, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) { // copy values in support array, use indices instead of objects, save memory
                                         // and time
            aux[k] = arr[k];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) { // run size of array times and finish!
            if (i > mid) {
                arr[k] = aux[j++];// if left part has been copied, finish the right part
            } else if (j > hi) {
                arr[k] = aux[i++];// if right part has been copied, finish the left part
            } else if (aux[j] < aux[i]) // actual check to swap values, if right element > left element. copy greater
                                        // and increment
            {
                arr[k] = aux[j++];
            } else {
                arr[k] = aux[i++];
            }
        }
    }

    private static void sortCut(int[] arr, int[] aux, int lo, int hi, int cutoff) {
        if (hi - lo < cutoff) {
            insertionSortCutoff(arr, lo, hi);
            return;
        }
        // System.out.println("recursive: " + recursive++);
        if (hi <= lo) // return statement when the array has been sorted
            return;
        int mid = lo + (hi - lo) / 2; // define where mid is in the new instance of sort
        sortCut(arr, aux, lo, mid, cutoff); // recursive call to sort for the inner left side of the array
        sortCut(arr, aux, mid + 1, hi, cutoff); // recursive call to sort for the inner right side of the array
        mergeCut(arr, aux, lo, mid, hi); // merge all the elements staring from the right and going to the left,
                                         // recursive callback
    }

    public static void mergeSortCutOff(int[] arr, int cutoff) {
        int size = arr.length;
        int[] aux = new int[size];
        for (int i = 0; i < size; i++) {
            aux[i] = arr[i];
        }
        sortCut(arr, aux, 0, size - 1, cutoff);
    }

    // --------------------------------------------------------------------------
    // QuickSort

    private static int partition(int[] arr, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int tmp;
        while (true) {
            while (arr[++i] < arr[lo])
                if (i == hi)
                    break;
            while (arr[lo] < arr[--j])
                if (j == lo)
                    break;
            if (i >= j)
                break;
            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        tmp = arr[lo];
        arr[lo] = arr[j];
        arr[j] = tmp;
        return j;
    }

    private static void sort(int[] arr, int lo, int hi) {
        // countRecursion ++;
        if (hi <= lo)
            return;
        int j = partition(arr, lo, hi);
        sort(arr, lo, j - 1);
        sort(arr, j + 1, hi);
    }

    public static void quickSort(int[] arr) {
        // countRecursion = 0;
        sort(arr, 0, arr.length - 1);
        // System.out.println(countRecursion);
    }

    // --------------------------------------------------------------------------
    // QuickSort Median of three

    private static int partitionMOT(int[] arr, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int tmp;
        int mid;
        mid = (hi - lo) / 2;
        if (arr[mid] < arr[lo])
            swap(arr, lo, mid);
        if (arr[hi] < arr[lo])
            swap(arr, lo, hi);
        if (arr[hi] < arr[mid])
            swap(arr, mid, hi);
        swap(arr, mid, lo);

        while (true) {
            while (arr[++i] < arr[lo]) {
                if (i == hi)
                    break;
            }
            while (arr[lo] < arr[--j]) {
                if (j == lo)
                    break;
            }
            if (i >= j)
                break;

            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            // printArr(arr);
        }

        tmp = arr[lo];
        arr[lo] = arr[j];
        arr[j] = tmp;
        // printArr(arr);
        // System.out.println("sep: " + j);
        return j; // position of my "splitter" this case median of lo, hi, mid
    }

    private static void sortMOT(int[] arr, int lo, int hi) {
        // countRecursion++; //count recursive calls
        if (hi <= lo)
            return;
        int j = partitionMOT(arr, lo, hi);
        sortMOT(arr, lo, j - 1);
        sortMOT(arr, j + 1, hi);
    }

    public static void quickSortMOT(int[] arr) {
        // countRecursion=0;
        sortMOT(arr, 0, arr.length - 1);
        // System.out.println(countRecursion);
    }

    public static void run() {
        Scanner s = new Scanner(System.in);
        System.out.println("Select sorting method:\n 0 - Insertion Sort\n 1 - Merge Sort\n"
                + " 2 - Merge Sort with cutoff\n 3 - Quick Sort\n 4 - Quick Sort median of three\n 5 - Count Inversion \n");
        int choice = s.nextInt();
        System.out.println("Insert size of array: ");
        long startTime = 0, stopTime = 0;
        int size = s.nextInt();
        System.out.println("Insert max value: ");
        int maxInt = s.nextInt();
        Random r = new Random(6113);
        int[] arr1 = new int[size];
        for (int i = 0; i < size; i++) {
            arr1[i] = r.nextInt(maxInt);
        }
        if (size < 50)
            printArr(arr1);

        switch (choice) {
            case 0:
                if (size < 1000000) {
                    System.out.println("Override values in randomly created array...\nEnter elements.");
                    for (int i = 0; i < size; i++) {
                        arr1[i] = s.nextInt();
                    }
                    startTime = System.nanoTime();
                    insertionSort(arr1);
                    stopTime = System.nanoTime();
                }
                break;
            case 1:
                startTime = System.nanoTime();
                mergeSort(arr1);
                stopTime = System.nanoTime();
                break;
            case 2:
                System.out.println("Insert cutoff value: ");
                int cutoff = s.nextInt();
                startTime = System.nanoTime();
                mergeSortCutOff(arr1, cutoff);
                stopTime = System.nanoTime();
                break;
            case 3:
                startTime = System.nanoTime();
                quickSort(arr1);
                stopTime = System.nanoTime();
                break;
            case 4:
                startTime = System.nanoTime();
                quickSortMOT(arr1);
                stopTime = System.nanoTime();
                break;
            case 5:
                startTime = System.nanoTime();
                countInversions(arr1);
                stopTime = System.nanoTime();
                break;

            default:
                break;
        }
        System.out.println("Time elapsed: " + (stopTime - startTime));
        if (size < 50)
            printArr(arr1);
        s.close();
    }

    public static void debug(String[] args) {
        Scanner s = new Scanner(System.in);

        int count = 0;
        int val = 0;
        int cutoff = 0;
        String choice = "";
        long startTime = 0;
        long stopTime = 0;
        long totTime = 0;

        /**
         * create array locally, used for debugging and for data creation
         */
        // int arrSize = s.nextInt();
        // val = s.nextInt();
        // cutoff = s.nextInt();
        int arrSize = Integer.parseInt(args[0]);
        val = Integer.parseInt(args[1]);
        if (val == 2)
            cutoff = Integer.parseInt(args[2]);

        int[] arr1 = new int[arrSize];
        for (int n = 0; n < 500; n++) {
            if (arrSize >= 50000 && val == 0 && n > 3)
                continue;
            if (arrSize >= 1000000 && n > 50)
                continue;
            Random r = new Random();
            r.setSeed(6113);
            int i;
            // for (i = 0; i < arrSize / 4; i++) {
            // arr1[i] = i;
            // }
            for (i = 0; i < arrSize; i++) {
                arr1[i] = r.nextInt();
            }
            switch (val) {
                case 0:
                    if (arrSize < 1000000) {
                        choice = "InserionSort";
                        startTime = System.nanoTime();
                        insertionSort(arr1);
                        stopTime = System.nanoTime();
                    }
                    break;
                case 1:
                    choice = "MergeSort";
                    startTime = System.nanoTime();
                    mergeSort(arr1);
                    stopTime = System.nanoTime();
                    break;
                case 2:
                    choice = "MergeSort with cut off " + cutoff;
                    startTime = System.nanoTime();
                    mergeSortCutOff(arr1, cutoff);
                    stopTime = System.nanoTime();
                    break;
                case 3:
                    choice = "QuickSort";
                    startTime = System.nanoTime();
                    quickSort(arr1);
                    stopTime = System.nanoTime();
                    break;
                case 4:
                    choice = "QuickSortMOT";
                    startTime = System.nanoTime();
                    quickSortMOT(arr1);
                    stopTime = System.nanoTime();
                    break;

                default:
                    break;
            }
            if (n > 299) {
                totTime += stopTime - startTime;
                count++;
            } else if (n > 19 && arrSize >= 1000000) {
                totTime += stopTime - startTime;
                count++;
            } else if (n > 1 && arrSize >= 50000 && val == 0) {
                totTime += stopTime - startTime;
                count++;
            }

            // System.out.println("Array sorted, time elapsed: " + (stopTime - startTime));
        }
        if (val == 0)
            System.out.print(arrSize + " ");
        System.out.print(totTime / count + " ");
        if (val == 4)
            System.out.println();
        // System.out.println("Size: " + arrSize + " " + choice);
        // System.out.println("Time elapsed over an average of " + count + " runs: " +
        // totTime / count);

        /**
         * read from System.in
         */

        // size = s.nextInt();
        // int[] arr = new int[size];
        // while (index < size) {
        // arr[index++] = s.nextInt();
        // }
        // System.out.println("Sorting array...");
        // startTime = System.nanoTime();
        // // quickSort(arr);
        // quickSortMOT(arr);
        // // mergeSort(arr);
        // // insertionSort(arr);
        // // splitNegativePositive(arr);
        // // mergeSortCutOff(arr, cutoff);
        // stopTime = System.nanoTime();
        // System.out.println("Array sorted, time elapsed: " + (stopTime - startTime));
        // // countInversions(arr);
        s.close();

    }

    public static void main(String[] args) throws IOException {
        run();
        // debug(args);
    }
}
