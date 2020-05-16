
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/536/week-3-may-15th-may-21st/3331/
Given a singly linked list, group all odd nodes together followed by the even nodes. 
Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
Note:
The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...
========================================================================================================================
1) Use boolean (1ms)
Time Complexity: O(n)
Space Complexity: O(1)

2) Iterate 2 nodes at a time. (2nd attempt; 0ms)
Time Complexity: O(n)
Space Complextiy: O(1)
*/
import java.util.*;

public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        ListNode oddHead = new ListNode(0);
        ListNode odd = oddHead;
        ListNode evenHead = new ListNode(0);
        ListNode even = evenHead;
        boolean isOdd = true;
        while (cur != null) {
            if (isOdd) {
                odd.next = cur;
                odd = odd.next;
            } else {
                even.next = cur;
                even = even.next;
            }
            cur = cur.next;
            isOdd = !isOdd;
        }
        even.next = null;
        odd.next = evenHead.next;
        return oddHead.next;
    }

    public ListNode oddEvenList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode odd = head;
        ListNode evenHead = head.next;
        ListNode even = evenHead;
        while (cur != null && cur.next != null) {
            odd.next = cur.next.next;
            if (odd.next != null) {
                odd = odd.next;
            } 
            even.next = odd.next;
            if (even.next != null) {
                even = even.next;
            }
            cur = cur.next;
        }
        even.next = null;
        odd.next = evenHead;
        return head;
    }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}