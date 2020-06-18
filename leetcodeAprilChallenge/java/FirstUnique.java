/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/531/week-4/3313/
You have a queue of integers, you need to retrieve the first unique integer in the queue.

Implement the FirstUnique class:

FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
void add(int value) insert value to the queue.
==============================================================================================================================
1) double linked list and hashmap
create a double linked list representing a queue
create a hashmap to store the unique numbers
when encountering a number, check if it already exists in the map.
if it does, remove the entry in the list.
otherwise, add it to the tail of the list, and add a mapping of that key to that node in the map
*/

import java.util.*;

public class FirstUnique {

    private MyList nodeList;
    private HashMap<Integer, Node> map;

    public FirstUnique(int[] nums) {
        nodeList = new MyList();
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (map.get(nums[i]) != null) {
                    nodeList.remove(map.get(nums[i]));
                    map.put(nums[i], null);
                }
            } else {
                Node node = new Node(nums[i]);
                nodeList.addToTail(node);
                map.put(nums[i], node);
            }
        }
    }

    public int showFirstUnique() {
        if (nodeList.getHead() == null) {
            return -1;
        } else {
            return nodeList.getHead().val;
        }
    }

    public void add(int value) {
        if (map.containsKey(value)) {
            if (map.get(value) != null) {
                nodeList.remove(map.get(value));
                map.put(value, null);
            }
        } else {
            Node node = new Node(value);
            nodeList.addToTail(node);
            map.put(value, node);
        }
    }
}

/**
 * Your FirstUnique object will be instantiated and called as such: FirstUnique
 * obj = new FirstUnique(nums); int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */

class Node {
    int val;
    Node prev;
    Node next;

    public Node(int val) {
        this.val = val;
        this.prev = null;
        this.next = null;
    }
}

class MyList {
    private Node head;
    private Node tail;

    public Node getHead() {
        return head;
    }

    public void addToHead(Node newNode) {
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public void addToTail(Node newNode) {
        if (tail == null) {
            head = tail = newNode;
            return;
        }
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
    }

    public void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        // detach node
        node.prev = null;
        node.next = null;

        // link other nodes

        // if mid node
        if (prev != null && next != null) {
            prev.next = next;
            next.prev = prev;
        }
        // if tail node
        else if (prev != null && next == null) {
            prev.next = null;
            tail = prev;
        }
        // if head node
        else if (prev == null && next != null) {
            next.prev = null;
            head = next;
        }
        // if only 1 node in list
        else {
            head = null;
            tail = null;
        }
    }

    private void removeLast() {
        if (tail != null) {
            Node prev = tail.prev;
            if (prev != null) {
                // tail.prev = null;
                prev.next = null;
                tail = prev;
            } else {
                tail = null;
            }
        }
    }
}