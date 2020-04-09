import java.util.*;

/*
https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1205/
Reverse a singly linked list.

Time Complexity: O(N)
Iterate through maintaining 2 pointers.
one pointing to current and the other pointing to previous node.
Manipulate the pointers to switch the ordering.
*/
public class ReverseLinkedList {
    public static ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;
        ListNode temp;
        while (cur != null) {
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
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

        ListNode head = reverseList(arr[0]);
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