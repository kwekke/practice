import java.util.*;
/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/529/week-2/3290/
Given a non-empty, singly linked list with head node head, return a middle node of linked list.
If there are two middle nodes, return the second middle node.

Maintain two pointers - O(N)
one slow pointer moving 1 step at a time. one fast pointer moving 2 steps at a time. 
when the fast pointer is on a node whose next is null, it means it is the last node in the list.
ie fast pointer has traversed the entire length while the slow pointer would have traversed half the length
*/

public class MiddleOfTheLinkedList {

    public static ListNode middleNode(ListNode head) {
        ListNode one = head;
        if (one.next == null) {
            return one;
        }
        ListNode two = head.next;
        while (two != null) {
            one = one.next;
            two = two.next;
            if (two != null) {
                two = two.next;
            }
        }
        return one;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] tokens = line.split(" ");
        int[] array = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            array[i] = Integer.parseInt(tokens[i]);
        }

        ListNode[] arr = new ListNode[array.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ListNode(array[i]);
        }

        for (int i = 0; i < arr.length - 1; i++) {
            arr[i].next = arr[i + 1];
        }
        System.out.println(middleNode(arr[0]).val);
        sc.close();
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}