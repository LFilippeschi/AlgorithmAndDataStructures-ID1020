
/*=============================================================================
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
 |  Description:  Implementation of a generic iterable DoubleLinkedList 
 |
 *===========================================================================*/

package Lab;

import java.util.Iterator;

/**
 * Iterable generic circular DoubleLinkedList
 * 
 * @param <T>
 */
public class DoubleLinkedList<T extends Comparable<T>> implements Iterable<T> {

    private int nrElements;
    private Node<T> head;

    private static class Node<T extends Comparable<T>> implements Comparable<T> {

        private T item;
        private Node<T> next;
        private Node<T> prev;

        public Node() {
            this.next = this;
            this.prev = this;
        }

        public Node(T x) {
            this.item = x;
            this.next = this;
            this.prev = this;
        }

        public String toString() {
            String next = "";
            String prev = "";
            if (this.next == null && this.prev == null) {
                next = "null";
                prev = "null";
            }

            String rtn = "\nitem: " + this.item + "\nnext: " + next + "\nprev: " + prev;
            return rtn;
        }

        @Override
        public int compareTo(T arg0) {
            return this.item.compareTo(arg0);
        }
    }

    public DoubleLinkedList() {
        head = new Node();
    }

    /**
     * Remove last item in the list. Implements a FIFO queue
     * 
     * @return Element removed
     * @throws Exception if List is empty
     */
    public Node remove() throws Exception {
        nrElements--;
        return remove(head.prev.item);
    }

    /**
     * Remove element 'value' from the list
     * 
     * @param value Element to be removed
     * @return
     * @throws Exception When trying to remove an element that isn't in the list or
     *                   when the list is empty
     */
    public Node remove(T value) throws Exception {
        if (value == null)
            throw new Exception("Invalid element: " + value);
        Node<T> rtn = new Node();
        rtn = head.next;
        while (rtn.item != value) {
            if (rtn.item == null) // one full lap completed
                throw new Exception("No such element: " + value);
            rtn = rtn.next;
        }
        rtn.prev.next = rtn.next;
        rtn.next.prev = rtn.prev;
        rtn.next = rtn;
        rtn.prev = rtn;
        System.out.println(toString());
        nrElements--;
        return rtn;
    }

    /**
     * Remove first element
     * 
     * @return Node
     * @throws Exception
     */
    public Node removeFirst() throws Exception {
        return removeK(1);
    }

    /**
     * Remove last element
     * 
     * @return Node
     * @throws Exception
     */
    public Node removeLast() throws Exception {
        return remove();
    }

    /**
     * Remove Kth element from the list
     * 
     * @param k
     * @return
     * @throws Exception if k is out of bounds
     */
    public Node removeK(int k) throws Exception {
        if (k < 1 || k > nrElements)
            throw new Exception("Invalid position: " + k);
        int count = 1;
        for (T iterable_element : this) {
            if (count++ == k)
                return this.remove(iterable_element);
        }
        return null;
    }

    public String toString() {
        String rtn = "";
        Node n = new Node();
        n = head.next;
        while (n.item != null) {
            rtn = rtn.concat("[" + n.item + "]");
            if (n.next.item != null)
                rtn = rtn.concat(",");
            n = n.next;
        }
        return rtn;
    }

    /**
     * Insert element in the List as a FIFO queue
     * 
     * @param item
     * @throws Exception
     */
    public void insert(T item) throws Exception {
        insertFirst(item);
    }

    /**
     * Insert Element 'value' after Element 'x'
     * 
     * @param x
     * @param value of Element to be inserted
     * @throws Exception
     */
    public void insertAfter(T x, T value) throws Exception {
        Node<T> p = new Node();
        Node<T> n = new Node(value);
        p = head.next;
        if (x == null) { //insert first
            n.next = head.next;
            head.next = n;
            n.next.prev = n;
            n.prev = head;
        } else {
            while (p.item != null) { //ends after one loop completed
                if (p.item == x)
                    if (p.next.item == x) { //one more Node with the same value, update and continue
                        p = p.next;
                        continue;
                    } else
                        break; // exit when item found
                p = p.next;
            }
            if (p.item != x) {
                throw new Exception("No element found!");
            }
            n.next = p.next;
            p.next = n;
            n.prev = p;
            n.next.prev = n;
        }
        nrElements++;
        System.out.println(this.toString());
    }

    /**
     * Insert element in first position
     * 
     * @param value
     * @throws Exception
     */
    public void insertFirst(T value) throws Exception {
        insertAfter(null, value);

    }

    /**
     * Insert element in last position
     * 
     * @param value
     * @throws Exception
     */
    public void insertLast(T value) throws Exception {
        insertAfter(head.prev.item, value);
    }

    /**
     * Insert element 'value' sorting it in relation with the elements already in
     * the list
     * 
     * @param value
     * @throws Exception
     */
    public void insertSortedElement(T value) throws Exception {
        Node<T> n = new Node();
        n = head.next;
        if (n.item == null) {
            insertFirst(value);
            return;
        }
        int compare;
        compare = value.compareTo(n.item);
        if (compare == -1) { 
            insertFirst(value);
            return;
        } else {
            while (compare >= 0) { // exits when value < next Item
                if (n.item == null) // break out when list has been looked at once
                    break;
                n = n.next;
                if (n.item == null) { // if no smaller value found, insert last and return
                    insertLast(value);
                    return;
                }
                compare = value.compareTo(n.item); // update compare for next loop
            }
            insertAfter(n.prev.item, value);
        }

    }

    /**
     * Insert an array of Elements in the list in a sorted manner
     * 
     * @param arr
     * @throws Exception
     */
    public void insertSortedElements(T[] arr) throws Exception {
        for (int i = 0; i < arr.length; i++) {
            this.insertSortedElement(arr[i]);
        }
    }

    public Iterator<T> iterator() {
        return new MyIterator<T>(this);
    }

    public class MyIterator<T extends Comparable<T>> implements Iterator<T> {
        private Node<T> current;

        public MyIterator(DoubleLinkedList<T> list) {
            current = list.head.next;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T item = current.item;
            current = current.next;
            return item;

        }

    }

    public static void main(String[] args) throws Exception {

        // test for all the functions including cases when the list is empty and non
        // empty
        // test implementation also with different types
        DoubleLinkedList<Character> FIFO = new DoubleLinkedList<Character>();
        //FIFO.remove();
        FIFO.insert('c');
        FIFO.insert('i');
        FIFO.remove();
        FIFO.insert('a');
        FIFO.insert('o');
        FIFO.remove();
        FIFO.remove();
        FIFO.remove();

        DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
        list.insertFirst(10);
        list.insertLast(20);
        list.insertSortedElement(15);
        list.insertSortedElement(15);
        list.remove(15);
        list.remove(15);
        // list.remove(15);
        list.insertLast(1);
        // list.remove(null);
        Integer[] arr = { 10, 3, 16, 3, -10, -50 };
        list.insertSortedElements(arr);
        // list.removeK(0);
        list.removeK(1);
        // list.removeK(10);
        list.removeK(5);
        for (Integer item : list) {
            if (item == null)
                break;
            list.remove(item);
        }
    }
}
