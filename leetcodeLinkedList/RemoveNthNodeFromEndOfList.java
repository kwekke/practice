import java.util.*;

/*
https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1296/
Given a linked list, remove the n-th node from the end of list and return its head.
Example Given linked list: 1->2->3->4->5, and n = 2.
After removing the second node from the end, the linked list becomes 1->2->3->5.

Assume input is always valid and that index from the last node is refered to as n, n-1,..,2,1

Maintain two pointers. - O(N)
Traverse fast pointer by n steps first. 
if its next is null, that means that the slow pointer is currently pointing to the node before the actual-node-to-be-removed.
otherwise, traverse the slow and fast pointers 1 step each at the same time. 
when the fast pointer has traverse to a node which next is null, it means that the slow pointer is
pointing towards the node before the actual-node-to-be-removed. 

Either way, after reassigning the node to point towards the actual-to-be-removed node's successor we are done.
*/
public class RemoveNthNodeFromEndOfList {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        ListNode temp = head;
        while (temp != null && i < n) {
            temp = temp.next;
            i++;
        }

        // removes assumption that input is always valid.
        // ie if above loop short circuits bc it has found a null,
        // then the given index is more than size of list.
        if (i != n) {
            return null;
        }

        // edge case when n is the length of the list
        // ie node to be removed is the first node and it does not have any predecessor
        if (temp == null) {
            return head.next;
        }

        while (temp.next != null) {
            cur = cur.next;
            temp = temp.next;
        }

        cur.next = cur.next.next;
        return head;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        int n = sc.nextInt();
        sc.close();
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

        ListNode head = removeNthFromEnd(arr[0], n);
        ArrayList<String> ansList = new ArrayList<>();
        while (head != null) {
            ansList.add(String.valueOf(head.val));
            head = head.next;
        }
        System.out.println(Arrays.toString(ansList.toArray()));

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