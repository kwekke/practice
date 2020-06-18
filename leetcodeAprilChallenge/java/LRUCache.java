
/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/531/week-4/3309/
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. 
When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?
================================================================================================================================================
1) Double linked list to implement queue. HashMap to store reference from key to Node
Time Complexity:
get - O(1)
put - O(1)

Space Complexity: O(N) where N is capacity

2) Using array to keep track of recently used and least recently used. 
each node stores the key and value. where the key is used to find the node in the array

*/
import java.util.*;

class LRUCache {

    private int capacity;
    private Map<Integer, Node> map;
    private MyList nodeList;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        nodeList = new MyList();
        map = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            int val = node.val;
            nodeList.remove(node);
            nodeList.addToHead(node);
            return val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            nodeList.addToHead(newNode);

            if (map.size() > this.capacity) {
                int delKey = nodeList.tail.key;
                map.remove(delKey);
                nodeList.removeLast();
            }
        } else {
            Node delNode = map.get(key);
            delNode.val = value;
            nodeList.remove(delNode);
            nodeList.addToHead(delNode);
        }
    }

    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }

    class MyList {
        private Node head;
        private Node tail;

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

}

/*
 * class LRUCache2 { class Node{ int key; int value; Node next; Node prev; Node
 * least; Node recent; public Node(int k, int v){ key = k; value = v; } } int
 * curCapacity, capacity; Node recent, least; Node[] cache; public LRUCache2(int
 * capacity) { curCapacity = 0; recent = null; least = null; cache = new
 * Node[capacity]; this.capacity = capacity; }
 * 
 * public int get(int key) { int idx = key % capacity; Node findNode =
 * cache[idx]; while(findNode != null){ if(findNode.key == key){
 * moveToRecent(findNode); return findNode.value; } findNode = findNode.next; }
 * return -1; }
 * 
 * public void put(int key, int value) { int idx = key % capacity; Node findNode
 * = cache[idx]; Node prevNode = findNode;
 * 
 * while(findNode != null){ prevNode = findNode; if(findNode.key == key){
 * findNode.value = value; break; } findNode = findNode.next; }
 * 
 * if(findNode == null){ ++curCapacity; findNode = new Node(key, value);
 * if(cache[idx] == null){ cache[idx] = findNode; }else{ prevNode.next =
 * findNode; findNode.prev = prevNode; } } moveToRecent(findNode);
 * if(curCapacity > capacity){ evicLeast(least); } }
 * 
 * private void moveToRecent(Node node){ if(node == recent) return; if(recent ==
 * null){ least = node; recent = node; return; } if(node == least){ least =
 * least.recent; } if(node.least != null){ node.least.recent = node.recent; }
 * if(node.recent != null){ node.recent.least = node.least; }
 * 
 * recent.recent = node; node.least = recent;
 * 
 * recent = node; } private void evicLeast(Node node){ // remove node;
 * if(node.prev != null){ node.prev.next = node.next; }else{ int idx = node.key
 * % capacity; cache[idx] = node.next; }
 * 
 * if(node.next != null){ node.next.prev = node.prev; }
 * 
 * least = least.recent; --curCapacity; } }
 */