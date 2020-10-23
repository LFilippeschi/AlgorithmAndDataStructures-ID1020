/*=============================================================================
 |   Assignment:  LAB1
 |
 |       Author:  Leonardo Filippeschi
 |       Contac:  lfil@kth.se
 |
 |      Created:  04.09.2020
 |  Last edited:  04.09.2020
 |
 |        Class:  ID1020 HT202- ALgorithms and Data Structures 
 |
 |   Instructor:  Robert Rönngren
 |
 +-----------------------------------------------------------------------------
 |
 |  Description:  2. In java implement a recursive and an iterative version of a 
 |                function which reads characters from stdin until a newline 
 |                character is read and then prints them on stdout in reverse order.
 |      
 |                3. Implement a generic iterable FIFO-queue based on a double 
 |                linked circular list. You should print the content of the list
 |                after each insertion/deletion of an element.
 |                
 |                4. Implement a generic iterable circular linked list which 
 |                allows the user to insert and remove elements to/from the front
 |                and back end of the queue.
 |                You should print the content of the list after each 
 |                insertion/deletion of an element.
 |
 |                5. Implement a generalized queue which allows the user to 
 |                remove the kth element from the queue. Assume the most 
 |                recently added element has index 1. You should print the 
 |                content of the list after each insertion/deletion of an element.
 |      
 |                6. Implement an ordered queue based on one of the 
 |                implementations above. The elements stored in the queue should
 |                be integer values. The elements should be ordered at insertion
 |                so that all elements are stored in ascending order starting 
 |                from when you insert the first element and in all following 
 |                insertions. You should print the content of the list after 
 |                each insertion/deletion of an element.
 |
 *===========================================================================*/
package Lab;

import java.io.IOException;
import Assignment.Assignment3a.SingleLinkedList;

public class Lab {
    /**
     * Implements a scanner of characters from System.in and prints them in reverse
     * order, using a SingleLinkedList as data structure
     * 
     * @throws IOException
     * @throws Exception
     */
    public static void iterativePrint() throws IOException, Exception {
        System.out.println("2.Iterative start:\n");
        SingleLinkedList<Character> list = new SingleLinkedList<Character>();
        Character c = (char) System.in.read();
        while (c.charValue() != (char) -1) {
            list.insert(c);
            c = (char) System.in.read();
        }
        while (!list.isEmpty()) {
            System.out.print(list.remove());
        }
        System.out.println("\nIterative end.\n");
    }

    public static void recursivePrint() throws IOException {
        char c = (char) System.in.read();
        if (c == (char) -1)
            return;
        else {
            if (c == '\n') {
                System.out.print(c);
                recursivePrint();
            }
            recursivePrint();
            System.out.print(c);
        }
    }

    public static void main(String[] args) throws IOException, Exception {
        iterativePrint();
        System.out.println("Recursive start:\n");
        recursivePrint();
        System.out.println("\nRecursive end.\n");

        System.out.println("3.FIFO queue.\n");
        DoubleLinkedList<Character> FIFO = new DoubleLinkedList<Character>();
        FIFO.insert('c');
        FIFO.insert('i');
        FIFO.insert('a');
        FIFO.insert('o');
        FIFO.remove();
        FIFO.insert('!');
        FIFO.remove();
        FIFO.remove();
        FIFO.remove();
        FIFO.remove();

        System.out.println("4.Generic iterable circular linked list which allows");
        System.out.println("the user to insert and remove elements to/from the front and back end of the queue\n");
        DoubleLinkedList<Integer> frontBackQueue = new DoubleLinkedList<Integer>();
        frontBackQueue.insertLast(-1);
        frontBackQueue.removeFirst(); //both remove work when only one element in the list
        //frontBackQueue.removeLast();
        frontBackQueue.insertFirst(10);
        frontBackQueue.insertLast(15);
        frontBackQueue.insertFirst(1);
        frontBackQueue.insertLast(3);
        frontBackQueue.removeFirst();
        frontBackQueue.removeFirst();
        frontBackQueue.removeLast();

        System.out.println("5.Generalized queue which allows the user to remove the kth element from the queue");
        System.out.println("Assume that the most recently added element has index 1");
        DoubleLinkedList<Double> generalizedQueue = new DoubleLinkedList<Double>();
        generalizedQueue.insert(1.0);
        generalizedQueue.insert(10.6);
        generalizedQueue.insert(1.7);
        generalizedQueue.insert(42.42);
        generalizedQueue.removeK(1);
        generalizedQueue.removeK(1);
        generalizedQueue.removeK(2);

        // test for invalid position throws exception.

        // generalizedQueue.removeK(0);
        // generalizedQueue.removeK(10);

        System.out.println("6.Ordered queue\n");
        DoubleLinkedList<Integer> orderedQueue = new DoubleLinkedList<Integer>();
        orderedQueue.insertSortedElement(10);
        orderedQueue.insertSortedElement(-1);
        orderedQueue.insertSortedElement(100);
        orderedQueue.insertSortedElement(0);
        orderedQueue.insertSortedElement(-15);
        Integer[] arr = { 10, 10, -20, 34, 7 };
        orderedQueue.insertSortedElements(arr);

    }

}