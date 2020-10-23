# AlgorithmAndDataStructures-ID1020
KTH course

Lab: The Fundamentals

   1. In C implement a recursive and an iterative version of a function which reads characters from stdin until a newline character is read and then prints them on stdout in reverse order.
   2. Implement the above program in JAVA for the iterative version.
   3. Implement a generic iterable FIFO-queue based on a double linked circular list. You should print the content of the list after each insertion/deletion of an element.
   4. Implement a generic iterable circular linked list which allows the user to insert and remove elements to/from the front and back end of the queue. Be careful when designing the API. You should print the content of the list after each insertion/deletion of an element.
   5. Implement a generalized queue which allows the user to remove the kth element from the queue. Assume the most recently added element has index 1. You should print the content of the list after each insertion/deletion of an element.
   6. Implement an ordered queue based on one of the implementations above. The elements stored in the queue should be integer values. The elements should be ordered at insertion so that all elements are stored in ascending order starting from when you insert the first element and in all following insertions. You should print the content of the list after each insertion/deletion of an element.
   
   Higher Grade
   
   7. Implement a program which takes as input a series of parentheses , that is a series of the characters: '(', ')', '[', ']', '{', '}'. The program should check if the parentheses are "balanced" or not. Also show the time and memory complexity of the algorithm.


Lab2: Sorting

    1. Implement insertionsort. Augment the sorting process so that all the content of the array that is being sorted is printed after each inner loop iteration. Write a unit test in main() which allows the user to define the size of the input (N) and then input (N) integers from stdin which is to be sorted.
    2. Augment the above implementation so that it prints the number of swaps performed when sorting the array.
    3. Add a method which counts the number of inversions in the input array and prints a list of all inversions on the format [i,a[i]], [j, a[j]] where i and j are indices and a[i], a[j] are the values of the elements. Call the method from main() before the array is sorted. Calculate the time complexity for the algorithm.
    4. Implement a function in C which takes an array of integers (both positive and negative) and orders the elements in the array so that all negative elements come before the positive. You are not allowed to sort the array - only collect all negative values first. The algorithm should only use O(1) extra memory.
    5. Compare the execution times for sorting large arrays of integers with insertionsort and merge sort. When should one select mergesort over insertionsort?
    6. Experiment with the cut-off to insertionsort in merge. How is the execution time affected by different values for the cut-off? A suitable range for cut-off values to test with could be [0-30]. Upload code, tests and a graphs.
    
  Higher Grade

   1. Augment the test code from assignment 1 so that the array is sorted in descending order instead of ascending order (you may add O(N) operations)
    Clarification: You should not change (not alter/modify any code in) the sorting method, nor should you sort the array an extra time. You may traverse the array once before sorting and once after sorting. During these traversals you may not move (re-order) any elements.
   2. Compare the execution times for sorting large arrays of integers with quicksort and merge sort. When should one select quicksort over mergesort?
   3. Compare the execution times of quicksort where the first element in each sub-array is selected as partitioning element to that of quicksort with median-of-three partitioning
   
   
Lab3: Searching

    1. Write a simple filter to clean a text, i.e. to remove all characters that are not alphabetic, blank or newline - replacing every such character by a blank to keep the number of characters constant to the original text.
    2. Use the first N (N in the order of hundred words) words from the text to compare the running times of the ordered array ST to the Binary Search Tree algorithm. Show tables or graphs of your measurements.
    3. Write a program that shows how evenly the built-in hashcode() function for strings in Java distributes the hashcodes for the words found in the text.
    4. Write an "index"-program which allows the user to ask questions "on which positions in the text (i.e. the number of characters from the beginning) you find the word X". The program should list the position of all occurrences of X as answer to a query. Questions to the index should be answered in time less or equal to O(log(N)) where N is the number of keys.  
    5. Show how a binary search tree  is built when the following sequence of keys are inserted: W O E C A L H
    6. Show the output if the content of the trees is printed in pre-, in- and postfix order
    7. Explain/show how you measured the execution times in programming assignment 2 above and how the results compare to what the theoretical calculations would suggest
 
  Higher Grade
  1. Implement a program which takes as input a text file and allows the user to (repeatedly without re-reading the input file) ask questions: 
     i) Which is the k:th most common word
     ii) Which are the k:th to the k+n:th most common words
Use https://introcs.cs.princeton.edu/java/data/leipzig/leipzig1m.txt  as input. The questions should be answered in constant time (assume k and n << N). You need to be able to explain the choices of data structures and algorithms, and their complexities. The time to read the input and build the data structures must not be longer than 4 min (this goal is easy to meet on all relatively modern PCs if one uses efficient algorithms and data structures)


Lab4: Graphs 

Part 1: Undirected graphs 
  For this part you should assume that the edges defined by the vertex pairs are two-way.
    1. Write a program based on DFS which can answer questions of the type: "Find the a path from X to Y" Which should result in a list of vertices traversed from         X to Y if there is a path.
    2. Change the program to use BFS.
Part 2: Directed graphs
  For this part you should assume that the edges defined by the vertex pairs in the data base are one-way.
     3. Write a program that can answer if there is a path between any to vertices.
     
 Higher Grade
 Implement a program which allows the user to find the shortest path between two nodes in a graph possibly passing through a third node. I.e. the user should be able to ask questions like:
Which is the shortest path from A to B passing through C? 
The program should output an ordered list of the nodes to traverse from A to B if such a path exists. If no such path exists then the program should output that no such path exists.
Use NYC.txt as input when not executing tests (in the case that the tests should be executed you may use another input). This is the undirected road network of New York City. The graph contains 264346 vertices and 733846 edges. It is connected, contains parallel edges, but no self-loops. The edge weights are travel times and are strictly positive. You should also calculate/show the time complexity of your algorithm.

