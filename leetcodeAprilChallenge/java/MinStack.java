import java.util.*;
/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/529/week-2/3292/
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

Can assume the commands are valid. 
Design data structure
Each node has its own value, and the pointer to the next node. It also stores a variable called
min which is the cumulative min in the stack including this node.

At any point in time, the node on the top of stack (head) has the most updated min 
which is the minimum of all values in the stack. 

push(x) - O(1)
pop() - O(1)
top() - O(1)
getMin() - O(1)
*/

public class MinStack {

    /** initialize your data structure here. */
    private Node head;

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x);
        } else {
            head = new Node(x, Math.min(x, head.min), head);
        }
    }

    public void pop() {
        if (head != null) {
            head = head.next;
        }
    }

    // return -1 if invalid
    public int top() {
        if (head != null) {
            return head.val;
        } else {
            return -1;
        }
    }

    public int getMin() {
        if (head != null) {
            return head.min;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private class Node {
        int val;
        int min;
        Node next;

        Node(int val, int min) {
            this(val, min, null);
        }

        Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MinStack obj = new MinStack();
        while (sc.hasNext()) {
            String command = sc.nextLine();
            switch (command) {
                case "push":
                    int val = sc.nextInt();
                    sc.nextLine(); // consume new line character
                    obj.push(val);
                    break;
                case "pop":
                    obj.pop();
                    break;
                case "top":
                    System.out.println(obj.top());
                    break;
                case "getMin":
                    System.out.println(obj.getMin());
                    break;
                default:
                    System.out.println("invalid command; closing program");
                    sc.close();
            }
        }
    }
}