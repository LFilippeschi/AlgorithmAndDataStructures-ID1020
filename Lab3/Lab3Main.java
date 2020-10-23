package Lab3;
/*=============================================================================
 |   Assignment:  Lab3 
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
 |  Description:  2. Use the first N words from the text to compare the running 
 |                times of the ordered array ST to the Binary Search Tree algorithm.
 |                Use the FrequencyCounter from page 372 as test program
 |
 |                3. Write a program that shows how evenly the built-in hashcode()
 |                function for strings in Java distributes the hashcodes for the
 |                words found in the text. 
 |
 |                4. Write an "index"-program which allows the user to ask questions 
 |                "on which positions in the text (i.e. the number of characters from the beginning)
 |                you find the word X". The program should list the position of 
 |                all occurrences of X as answer to a query. Questions to the 
 |                index should be answered in time less or equal to O(log(N)) 
 |                where N is the number of keys.
 |
 |                5. Implement a program which takes as input a text file and 
 |                allows the user to (repeatedly without re-reading the input file)
 |                ask questions: 
 |                i) Which is the k:th most common word
 |                ii) Which are the k:th to the k+n:th most common words
 |
 |
 *===========================================================================*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import Assignment.Assignment3a.SingleLinkedList;

public class Lab3Main {

    // =============================================================================
    // Frequency counter for orderedST

    public static arrayST<String, Integer> frequencyCounter(int k, int N, String f) throws FileNotFoundException {
        File file = new File(f);
        Scanner s = new Scanner(file);
        int minlen = k;
        arrayST<String, Integer> st = new arrayST<String, Integer>();
        int i = 0;
        while (s.hasNext() && i++ < N) {
            String word = s.next();
            if (word.length() < minlen)
                continue;
            if (!st.contains(word))
                st.put(word, 1);
            else
                st.put(word, st.get(word) + 1);
        }
        s.close();
        return st;
    }

    public static String frequencyCounterString(int k, int N, String f) throws FileNotFoundException {
        File file = new File(f);
        Scanner s = new Scanner(file);
        int minlen = k;
        arrayST<String, Integer> st = new arrayST<String, Integer>();
        int i = 0;
        while (s.hasNext() && i++ < N) {
            String word = s.next();
            if (word.length() < minlen)
                continue;
            if (!st.contains(word))
                st.put(word, 1);
            else
                st.put(word, st.get(word) + 1);
        }
        String max = "";
        st.put(max, 0);
        for (int j = 0; j < st.getSize(); j++) {
            String word = st.key(j);
            if (st.get(word) > st.get(max))
                max = word;
        }
        s.close();
        return max + " " + st.get(max);
    }

    // =============================================================================
    // Frequency counter for BST

    public static String frequencyCounterBST(int k, int j) throws FileNotFoundException {
        File file = new File(
                "/media/leonardo/SharedVolume/KTH1/Algorithms and data structures/Algorithm and data structure/Lab3/thetext.txt");
        Scanner s = new Scanner(file);
        int minlen = k;
        BST<String, Integer> st = new BST<String, Integer>();
        int i = 0;
        while (s.hasNext() && i++ < j) {
            String word = s.next();
            if (word.length() < minlen)
                continue;
            if (!st.contains(word))
                st.put(word, 1);
            else
                st.put(word, st.get(word) + 1);
        }
        String max = "";
        int count = 0;
        BST.Node<String, Integer> tmp = new BST.Node<String, Integer>(max, count, 0);
        st.put(max, count);
        st.max(st.root, tmp);
        s.close();
        return tmp.key + " " + tmp.val;
    }

    /**
     * 
     * @param k Minimum length of word allowed
     * @param j Maximum number of words read
     * @param f File used as input
     * @return BST object after having read frequency of String from file f
     * @throws FileNotFoundException
     */
    public static BST<String, Integer> frequencyCounterBSTobj(int k, int j, String f) throws FileNotFoundException {
        File file = new File(f);
        Scanner s = new Scanner(file);
        int minlen = k;
        BST<String, Integer> st = new BST<String, Integer>();
        int i = 0;
        while (s.hasNext() && i++ < j) {
            String word = s.next();
            if (word.length() < minlen)
                continue;
            if (!st.contains(word))
                st.put(word, 1);
            else
                st.put(word, st.get(word) + 1);
        }
        s.close();
        return st;
    }

    // =============================================================================
    // ArrayST implementation

    public static class arrayST<Key extends Comparable<Key>, Value> {
        public Key[] keys;
        public Value[] values;
        public int N = 0;

        @SuppressWarnings("unchecked")
        public arrayST() {
            keys = (Key[]) new Comparable[16];
            values = (Value[]) new Object[16];
        }

        @SuppressWarnings("unchecked")
        public void resize() {
            if (N == keys.length - 1) {
                Key[] tmpKey = (Key[]) new Comparable[keys.length * 2];
                Value[] tmpValues = (Value[]) new Object[keys.length * 2];
                for (int i = 0; i < keys.length; i++) {
                    tmpKey[i] = keys[i];
                    tmpValues[i] = values[i];
                }
                keys = tmpKey;
                values = tmpValues;
            }

        }

        public Key key(int i) {
            return this.keys[i];
        }

        public Value get(Key key) {
            if (N == 0)
                return null;
            int lo = 0, hi = N - 1;
            int i = position(key, lo, hi);
            if (i < N && keys[i].compareTo(key) == 0)
                return values[i];
            else
                return null;
        }

        public boolean contains(Key key) {
            if (get(key) != null)
                return true;
            else
                return false;
        }

        public String toString() {
            String s = "";
            for (int i = 0; i < N; i++) {
                s = s.concat("[" + keys[i] + "], [" + values[i] + "]\n");
            }
            return s;
        }

        /**
         * 
         * @param <T>   Element that we want to queue in the Linked List
         * @param key
         * @param value
         */
        @SuppressWarnings("unchecked")
        public <T> void putIndex(Key key, T value) {
            int lo = 0, hi = N - 1;
            int i = position(key, lo, hi);
            if (i < N && keys[i].compareTo(key) == 0) { // add value to the list
                ((SingleLinkedList<T>) values[i]).insert(value);
                return;
            }
            resize();
            for (int j = N; j > i; j--) { // moves all the element one position up
                keys[j] = keys[j - 1];
                values[j] = values[j - 1];
            }
            keys[i] = key;
            SingleLinkedList<T> tmp = new SingleLinkedList<T>();
            tmp.insert(value);
            values[i] = (Value) tmp;
            N++;
        }

        public void put(Key key, Value value) {
            int lo = 0, hi = N - 1;
            int i = position(key, lo, hi);
            if (i < N && keys[i].compareTo(key) == 0) { // override value with same key
                values[i] = value;
                return;
            }
            resize();
            for (int j = N; j > i; j--) { // moves all the element one position up
                keys[j] = keys[j - 1];
                values[j] = values[j - 1];
            }
            keys[i] = key;
            values[i] = value;
            N++;
        }

        /**
         * 
         * @param key That we want to locate
         * @param lo  Lowest index
         * @param hi  Highest index
         * @return position of Key
         */
        public int position(Key key, int lo, int hi) {
            if (hi < lo)
                return lo;
            int mid = lo + (hi - lo) / 2;
            int sign = key.compareTo(keys[mid]);
            if (sign < 0)
                return position(key, lo, mid - 1);
            else if (sign > 0)
                return position(key, mid + 1, hi);
            else
                return mid;
        }

        public int getSize() {
            return N;
        }

    }

    // =============================================================================
    // BST Implementation

    public static class BST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
        public Node<Key, Value> root;
        public int N;

        public static class Node<Key extends Comparable<Key>, Value extends Comparable<Value>> {
            public Key key;
            public Value val;
            public Node<Key, Value> left, right;
            public int N;

            public Node(Key key, Value val, int N) {
                this.key = key;
                this.val = val;
                this.N = N;
            }

            @SuppressWarnings("unchecked")
            public <T> Node(Key key, T val, int N) {
                SingleLinkedList<T> tmp = new SingleLinkedList<T>();
                tmp.insert(val);
                this.key = key;
                this.val = (Value) tmp;
                this.N = N;
            }

            public String toString() {
                String s = "";
                if (left != null) {
                    s = s.concat(left.toString());
                }
                s = s.concat("[" + key + "], [" + val + "]\n");
                if (right != null) {
                    s = s.concat(right.toString());
                }
                return s;
            }
        }

        public int getSize() {
            return N;
        }

        public int size() {
            return size(root);
        }

        public int size(Node<Key, Value> n) {
            if (n == null)
                return 0;
            else
                return n.N;
        }

        /**
         * 
         * @param n  Starting point of the BST
         * @param ll array where all the values get copied to
         */
        public void copy(Node<Key, Value> n, arrayST<Value, SingleLinkedList<Key>> ll) {
            if (n.left != null) {
                copy(n.left, ll);
            }
            ll.putIndex(n.val, n.key);
            if (n.right != null) {
                copy(n.right, ll);
            }
        }

        public <T> void copyReverse(Node<Key, SingleLinkedList<T>> n, ArrayList<SingleLinkedList<T>> ll, int index) {
            if (n.right != null) {
                copyReverse(n.right, ll, index);
            }
            ll.add(index++, n.val);
            if (n.left != null) {
                copyReverse(n.left, ll, index);
            }
        }

        public void copyBST(Node<Key, Value> n, BST<Value, SingleLinkedList<Key>> ll) {
            if (n.left != null) {
                copyBST(n.left, ll);
            }
            ll.putIndex(n.val, n.key);
            if (n.right != null) {
                copyBST(n.right, ll);
            }
        }

        public void max(Node<Key, Value> n, Node<Key, Value> tmp) {
            if (n.left != null) {
                max(n.left, tmp);
            }
            int cmp = n.val.compareTo(tmp.val);
            if (cmp < 0) {
            } else if (cmp > 0) {
                tmp.key = n.key;
                tmp.val = n.val;
            } else {
                if (n.key.equals(tmp.key)) {
                    tmp.val = n.val;
                    tmp.key = n.key;
                }
            }
            if (n.right != null) {
                max(n.right, tmp);
            }
        }

        public boolean contains(Key key) {
            if (get(key) != null)
                return true;
            else
                return false;
        }

        public Value get(Key key) { // interface
            return get(root, key);
        }

        /**
         * Recursive method to go thorugh the BST looking for key
         * 
         * @param n
         * @param key
         * @return
         */
        public Value get(Node<Key, Value> n, Key key) {
            if (n == null)
                return null;
            int cmp = key.compareTo(n.key);
            if (cmp < 0)
                return get(n.left, key);
            else if (cmp > 0)
                return get(n.right, key);
            else
                return (Value) n.val;
        }

        public <T> void putIndex(Key key, T val) {
            root = putIndex(root, key, val);
        }

        @SuppressWarnings("unchecked")
        public <T> Node<Key, Value> putIndex(Node<Key, Value> n, Key key, T val) {
            if (n == null) {
                N++;
                return new Node<Key, Value>(key, val, 1);
            }
            int cmp = key.compareTo((Key) n.key);
            if (cmp < 0)
                n.left = putIndex(n.left, key, val);
            else if (cmp > 0)
                n.right = putIndex(n.right, key, val);
            else
                ((SingleLinkedList<T>) n.val).insert(val);
            n.N = size(n.left) + size(n.right) + 1;
            return n;
        }

        public void put(Key key, Value val) {
            root = put(root, key, val);
        }

        /**
         * Recursive method to go through the BST until the right position has been
         * found
         * 
         * @param n
         * @param key
         * @param val
         * @return
         */
        public Node<Key, Value> put(Node<Key, Value> n, Key key, Value val) {
            if (n == null) {
                N++;
                return new Node<Key, Value>(key, val, 1);
            }
            int cmp = key.compareTo((Key) n.key);
            if (cmp < 0)
                n.left = put(n.left, key, val);
            else if (cmp > 0)
                n.right = put(n.right, key, val);
            else
                n.val = val;
            n.N = size(n.left) + size(n.right) + 1;
            return n;
        }

        public String toString() {
            return root.toString();

        }
    }

    // =============================================================================
    // Methods used for the lab assignments 2

    public static void arraSTRun() throws FileNotFoundException {
        File file = new File(
                "/media/leonardo/SharedVolume/KTH1/Algorithms and data structures/Algorithm and data structure/Lab3/thetext.txt");
        Scanner s = new Scanner(file);
        Scanner s1 = new Scanner(System.in);
        System.out.println("Insert the amount of words to read in: ");
        int N = s1.nextInt();
        arrayST<Integer, String> arr = new arrayST<Integer, String>();
        int i = 0;
        while (s.hasNext() && i++ < N)
            arr.put(i, s.next());
        System.out.println(arr);
        s.close();
    }

    public static void BSTRun() throws FileNotFoundException {
        File file = new File(
                "/media/leonardo/SharedVolume/KTH1/Algorithms and data structures/Algorithm and data structure/Lab3/thetext.txt");
        Scanner s = new Scanner(file);
        Scanner s1 = new Scanner(System.in);
        System.out.println("Insert the amount of words to read in: ");
        int N = s1.nextInt();
        BST<Integer, String> arr = new BST<Integer, String>();
        int i = 0;
        while (s.hasNext() && i++ < N)
            arr.put(i, s.next());
        System.out.println(arr);
        s.close();
    }

    public static void run() throws FileNotFoundException {
        arraSTRun();
        BSTRun();
    }

    public static void debug() {
        BST<Integer, String> b = new BST<Integer, String>();
        b.put(0, "a");
        b.put(3, "v");
        b.put(10, "x");
        b.put(5, "c");
        System.out.println(b.get(10));
        System.out.println(b);
    }

    // =============================================================================
    // ASSIGNMENT 2
    // time() used for timing the execution time

    public static void time(int i, int j) throws FileNotFoundException {
        int count = -1;
        double tot = 0, tot2 = 0;
        while (count++ < 50000 / j) {
            frequencyCounterString(i, j, "Lab3/thetext.txt");
            frequencyCounterBST(i, j);
        }
        String s1 = "", s2 = "";
        long startTime, stopTime;
        count = -1;
        while (count++ < 500000 / j) {
            startTime = System.nanoTime();
            s1 = frequencyCounterString(i, j, "Lab3/thetext.txt");
            stopTime = System.nanoTime();
            tot += (double) (stopTime - startTime) / 1000000;

            startTime = System.nanoTime();
            s2 = frequencyCounterBST(i, j);
            stopTime = System.nanoTime();
            tot2 += (double) (stopTime - startTime) / 1000000;
        }
        if (count == 1) {
            while (count++ < 4) {
                startTime = System.nanoTime();
                s1 = frequencyCounterString(i, j, "Lab3/thetext.txt");
                stopTime = System.nanoTime();
                tot += (double) (stopTime - startTime) / 1000000;

                startTime = System.nanoTime();
                s2 = frequencyCounterBST(i, j);
                stopTime = System.nanoTime();
                tot2 += (double) (stopTime - startTime) / 1000000;
            }
        }
        System.out.print(i + " " + j + " " + tot / count);
        System.out.print(" " + tot2 / count + "\t\t\t\t\t\t s1:" + s1 + " s2:" + s2 + " count:" + count + "\n");
    }

    // =============================================================================
    // ASSIGNMENT 3
    // Method to analyze spread of hash values

    public static void printArr(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            System.out.println(i + " " + arr[i]);
        System.out.println((n - 1) + " " + arr[n - 1]);
    }

    public static int hash(String s, int M) {
        return (s.hashCode() & 0x7fffffff) % M;
    }

    public static void hashSpread(int M) throws FileNotFoundException {
        int[] arr = new int[M];
        File file = new File("Lab3/thetext.txt");
        Scanner fs = new Scanner(file);
        arrayST<String, Integer> tmp = new arrayST<String, Integer>();
        tmp = frequencyCounter(1, 150000, "Lab3/thetext.txt");
        for (int i = 0; i < tmp.getSize(); i++) {
            arr[hash(tmp.key(i), M)]++;
        }
        printArr(arr);
        fs.close();
    }

    // =============================================================================
    // ASSIGNMENT 4
    // Index program: return the list of all occurrances of the word "X" in the text

    public static void index(int M) throws FileNotFoundException {
        arrayST<String, SingleLinkedList<Integer>> hm = new arrayST<String, SingleLinkedList<Integer>>();
        int index = 0, count = 0;
        File file = new File(
                "/media/leonardo/SharedVolume/KTH1/Algorithms and data structures/Algorithm and data structure/Lab3/thetext.txt");
        Scanner fs = new Scanner(file);
        Scanner sc = new Scanner(System.in);
        fs.useDelimiter("");
        // System.out.println("Insert max size of N");
        // int N = sc.nextInt();
        String s = "";
        while (fs.hasNext() && count < M) {
            index++;
            String c = fs.next();
            if (c.equals(" ") || c.equals("\n")) {
                if (s.length() == 0) {
                } else {
                    hm.putIndex(s, index - s.length());
                    count++;
                    s = "";
                }
            } else {
                s = s.concat(c);
            }
        }
        System.out.println("Insert word to find or enter 0 to exit: ");
        String x = "";
        long startTime, stopTime;
        double tot = 0;
        while (true) {
            x = sc.next();
            startTime = System.nanoTime();
            if (x.equals("0"))
                break;
            System.out.println(hm.get(x));
            stopTime = System.nanoTime();
            tot = (double) (stopTime - startTime) / 1000000;
            System.out.println(tot);
            // System.out.println(hm);
        }
        fs.close();
    }

    // =============================================================================
    // HIGHER ASSIGNMENT GRADE
    // Which are the Kth most common words?

    public static void kthCommonWord() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        long startTime, stopTime;
        double tot = 0;
        startTime = System.nanoTime();
        arrayST<String, Integer> tmp = frequencyCounter(0, 21914570, "Lab3/leipziglmfiltered.txt");
        arrayST<Integer, SingleLinkedList<String>> cmp = new arrayST<Integer, SingleLinkedList<String>>();
        int count = 0;
        String s = tmp.key(count);
        while (count < tmp.N) {
            cmp.putIndex(tmp.get(s), s);
            s = tmp.key(count);
            count++;
        }
        stopTime = System.nanoTime();
        tot = ((double) (stopTime - startTime) / 60000000) / 1000;
        System.out.println(tot);

        // System.out.println(tmp);
        // System.out.println(cmp);

        while (true) {
            System.out.println("Insert Kth most common words or 0 to continue: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                if (choice > cmp.getSize()) {
                    System.out.println("Choose between 1 and " + cmp.getSize());
                    continue;
                }
                if (choice == 0)
                    break;
                System.out.println(choice + " most common words with " + cmp.key(cmp.getSize() - choice)
                        + " occurences:" + cmp.get(cmp.key(cmp.getSize() - choice)));
            } else {
                sc.next();
            }
        }

        while (true) {
            System.out.println("Insert Kth most common words or 0 to continue: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                if (choice > cmp.getSize()) {
                    System.out.println("Choose between 1 and " + cmp.getSize());
                    continue;
                }
                if (choice == 0)
                    break;
                System.out.println("Insert nth most common word after kth: ");
                if (sc.hasNextInt()) {
                    int nth = sc.nextInt();
                    if (nth < 0 || nth > cmp.getSize() - choice) {
                        System.out.println("Choose between 1 and " + (cmp.getSize() - choice));
                        continue;
                    }
                    int i = choice;
                    while (i <= choice + nth) {
                        System.out.println(i + " most common words with " + cmp.key(cmp.getSize() - i) + " occurences:"
                                + cmp.get(cmp.key(cmp.getSize() - i)) + "\n");
                        i++;
                    }
                }
            } else {
                sc.next();
            }
        }

    }

    public static void kthCommonWordBST() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        long startTime, stopTime;
        double tot = 0;
        startTime = System.nanoTime();
        BST<String, Integer> tmp = frequencyCounterBSTobj(0, 21914570, "Lab3/leipziglmfiltered.txt");
        arrayST<Integer, SingleLinkedList<String>> cmp = new arrayST<Integer, SingleLinkedList<String>>();
        // System.out.println(tmp);
        tmp.copy(tmp.root, cmp);
        // System.out.println(cmp);
        stopTime = System.nanoTime();
        tot = ((double) (stopTime - startTime) / 60000000) / 1000;
        System.out.println(tot);

        while (true) {
            System.out.println("Insert Kth most common words or 0 to continue: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                if (choice > cmp.getSize()) {
                    System.out.println("Choose between 1 and " + cmp.getSize());
                    continue;
                }
                if (choice == 0)
                    break;
                System.out.println(choice + " most common words with " + cmp.key(cmp.getSize() - choice)
                        + " occurences:" + cmp.get(cmp.key(cmp.getSize() - choice)));
            } else {
                sc.next();
            }
        }

        while (true) {
            System.out.println("Insert Kth most common words or 0 to continue: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                if (choice > cmp.getSize()) {
                    System.out.println("Choose between 1 and " + cmp.getSize());
                    continue;
                }
                if (choice == 0)
                    break;
                System.out.println("Insert nth most common word after kth: ");
                if (sc.hasNextInt()) {
                    int nth = sc.nextInt();
                    if (nth < 0 || nth > cmp.getSize() - choice) {
                        System.out.println("Choose between 1 and " + (cmp.getSize() - choice));
                        continue;
                    }
                    int i = choice;
                    while (i <= choice + nth) {
                        System.out.println(i + " most common words with " + cmp.key(cmp.getSize() - i) + " occurences:"
                                + cmp.get(cmp.key(cmp.getSize() - i)) + "\n");
                        i++;
                    }
                }
            } else {
                sc.next();
            }
        }

    }

    public static void kthCommonWordBSTBST() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        long startTime, stopTime;
        double tot = 0;
        startTime = System.nanoTime();
        BST<String, Integer> tmp = frequencyCounterBSTobj(0, 21914570, "Lab3/leipziglmfiltered.txt");
        BST<Integer, SingleLinkedList<String>> tmp2 = new BST<Integer, SingleLinkedList<String>>();
        ArrayList<SingleLinkedList<String>> cmp = new ArrayList<SingleLinkedList<String>>();
        // System.out.println(tmp);
        tmp.copyBST(tmp.root, tmp2);
        int index = 0;
        tmp2.copyReverse(tmp2.root, cmp, index);
        // System.out.println(cmp);
        stopTime = System.nanoTime();
        tot = ((double) (stopTime - startTime) / 60000000) / 1000;
        System.out.println(tot);

        while (true) {
            System.out.println("Insert Kth most common words or 0 to continue: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                if (choice > cmp.size()) {
                    System.out.println("Choose between 1 and " + cmp.size());
                    continue;
                }
                if (choice == 0)
                    break;
                System.out.println(choice + " most common words with " + cmp.get(cmp.size() - choice) 
                        + " occurences:" + cmp.get(cmp.size() - choice));
            } else {
                sc.next();
            }
        }

        while (true) {
            System.out.println("Insert Kth most common words or 0 to continue: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                if (choice > cmp.size()) {
                    System.out.println("Choose between 1 and " + cmp.size());
                    continue;
                }
                if (choice == 0)
                    break;
                System.out.println("Insert nth most common word after kth: ");
                if (sc.hasNextInt()) {
                    int nth = sc.nextInt();
                    if (nth < 0 || nth > cmp.size() - choice) {
                        System.out.println("Choose between 1 and " + (cmp.size() - choice));
                        continue;
                    }
                    int i = choice;
                    while (i <= choice + nth) {
                        System.out.println(i + " most common words with " + cmp.get(cmp.size() - i) + " occurences:"
                                + cmp.get(cmp.size() - i) + "\n");
                        i++;
                    }
                }
            } else {
                sc.next();
            }
        }

    }

    // =============================================================================
    // Menu function used as ui

    public static void menu() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "==========================================================================================");
            System.out.println(
                    "Select Function:\n 0 - Exit \n 1 - FrequencyCounter array\n 2 - FrequencyCounter tree\n 3 - Time array vs tree \n"
                            + " 4 - HashSpread \n 5 - Index \n 6 - kth to kth+nth common words using Ordered array\n 7 - kth to kth+nth common words using BST");
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    System.out.println("Insert number of words to read: ");
                    int N = sc.nextInt();
                    System.out.println("Insert minimum length of words: ");
                    int i = sc.nextInt();
                    System.out.println(frequencyCounterString(i, N, "Lab3/thetext.txt"));
                    break;
                case 2:
                    System.out.println("Insert number of words to read: ");
                    int M = sc.nextInt();
                    System.out.println("Insert minimum length of words: ");
                    int j = sc.nextInt();
                    System.out.println(frequencyCounterBST(j, M));
                    break;
                case 3:
                    System.out.println("Insert number of words to read: ");
                    int L = sc.nextInt();
                    System.out.println("Insert minimum length of words: ");
                    int k = sc.nextInt();
                    time(k, L);
                    break;
                case 4:
                    System.out.println("Insert size of M: ");
                    int W = sc.nextInt();
                    hashSpread(W);
                    break;
                case 5:
                    System.out.println("Insert number of words to read: ");
                    int Wo = sc.nextInt();
                    index(Wo);
                    break;
                case 6:
                    kthCommonWord();
                    break;
                case 7:
                    kthCommonWordBST();
                    break;
                case 8:
                    kthCommonWordBSTBST();
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        menu();
    }
}