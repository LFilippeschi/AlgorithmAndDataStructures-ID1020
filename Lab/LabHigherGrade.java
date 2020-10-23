/*=============================================================================
 |   Assignment:  LAB1 higher grade java implementation
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
 |       Memory: O(N), 2 bytes(char) * N.
 |
 *===========================================================================*/

package Lab;

public class LabHigherGrade {

    public static class Stack {
        private int index = 0;
        private char[] arr = new char[16];

        public Stack() {
        }

        private void resize(int size) {
            char[] rtn = new char[size];
            for (int i = 0; i < arr.length; i++) {
                rtn[i] = arr[i];
            }
            arr = rtn;
        }

        private void shrink(int size) {
            char[] rtn = new char[size];
            for (int i = 0; i < rtn.length; i++) {
                rtn[i] = arr[i];
            }
            arr = rtn;
        }

        public char top() {
            return arr[index - 1];
        }

        public void push(char c) {
            if (index > arr.length / 2)
                resize(arr.length * 2);
            arr[index++] = c;
        }

        public char pop() {
            if (index > 0) {
                if (index < arr.length / 4)
                    shrink(arr.length / 2);
                return arr[--index];
            }
            char rtn = (char) -1;
            return rtn;
        }

        public boolean isEmpty() {
            if (index == 0)
                return true;
            else
                return false;
        }
    }

    /**
     * Input given from console or file
     * 
     * @return
     * @throws Exception
     */
    public static boolean isBalanced() throws Exception {
        char c = (char) System.in.read();
        Stack s = new Stack();
        while (c != '\n') {
            if (c == '(' || c == '[' || c == '{')
                s.push(c);
            else {
                char x = s.pop();
                if (x == '(' && c == ')' || x == '[' && c == ']' || x == '{' && c == '}') {
                } else
                    return false;
            }
            c = (char) System.in.read();
        }
        return s.isEmpty();
    }

    /**
     * Input passed as a String to the function
     *
     * @param s
     * @return
     * @throws Exception
     */
    public static boolean isBalanced(String msg) throws Exception {
        int index = 0;
        char c = msg.charAt(index++);
        Stack s = new Stack();
        while (c != '\0' && index <= msg.length()) {
            if (c == '(' || c == '[' || c == '{')
                s.push(c);
            else {
                char x = s.pop();
                if (x == '(' && c == ')' || x == '[' && c == ']' || x == '{' && c == '}') {
                } else
                    return false;
            }
            if (index == msg.length())
                break;
            else
                c = msg.charAt(index++);
        }
        return s.isEmpty();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(isBalanced());
        System.out.println("{}{}{} isBalanced? "+isBalanced("{}{}{}"));
        System.out.println("}[[][]]] isBalanced? "+isBalanced("}[[][]]]"));
        System.out.println("{{{{[[[()]]]}}}} isBalanced? "+isBalanced("{{{{[[[()]]]}}}}"));
    }
}
