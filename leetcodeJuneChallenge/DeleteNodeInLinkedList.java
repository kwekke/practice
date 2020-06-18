/*
https://leetcode.com/explore/featured/card/june-leetcoding-challenge/539/week-1-june-1st-june-7th/3348/
Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Given linked list -- head = [4,5,1,9], which looks like following:
4-5-1-9

Note:

The linked list will have at least two elements.
All of the nodes' values will be unique.
The given node will not be the tail and it will always be a valid node of the linked list.
Do not return anything from your function.
============================================================================================================
1) copy next value and point to the following node
this problem does not provide an input to the head of the list. 
so instead of deleting the node, we reassign the values.
Time Complexity: O(1)
Space Complexity: O(1)

*/

import java.util.*;

public class DeleteNodeInLinkedList {

    class Solution {
        ListNode head;

        Solution(ListNode head) {
            this.head = head;
        }

        public void deleteNode(ListNode node) {
            node.val = node.next.val;
            node.next = node.next.next;
        }

        public void printNodes() {
            Listnode cur = head;
            while (cur != null) {
                System.out.print(cur.val + " ");
                cur = cur.next;
            }
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /*
     * inputs number of nodes, the index of node to be deleted, followed by the
     * values of all the nodes.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();
        if (d >= n) {
            System.out.println("invalid index " + d + " for " + n + " nodes");
            sc.close();
            return;
        }
        ListNode toBeDeleted = null;
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        for (int i = 0; i < n; i++) {
            ListNode tmp = new ListNode(sc.nextInt());
            if (n == d) {
                toBeDeleted = tmp;
            }
            cur.next = tmp;
            cur = cur.next;
        }
        sc.close();
        Solution solution = new Solution(dummyHead.next);
        if (toBeDeleted != null)
            solution.deleteNode(toBeDeleted);
        solution.printNodes();
    }
}