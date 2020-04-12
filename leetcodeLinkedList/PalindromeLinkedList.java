import java.util.*;
/*
https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1209/
Given a singly linked list, determine if it is a palindrome.

Time Complexity: O(N), Space: O(1)
1) Find half way point
2) Account for if the list has odd no. of elements
3) Reverse 2nd half of list
4) Traverse 1st half and 2nd half 1 step at a time.
*/

public class PalindromeLinkedList {
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // find halfway point
        ListNode slow = head;
        ListNode fast = slow.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // if no. of elements in list is odd
        // ie if the 2nd half of the list has more elements than 1st half
        // then skip the element in the middle of the list
        if (fast.next != null && fast.next.next == null) {
            slow = slow.next;
        }

        ListNode firstHalf = head;
        ListNode reversedSecondHalf = reverseList(slow);
        while (firstHalf != null && reversedSecondHalf != null) {
            if (firstHalf.val != reversedSecondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            reversedSecondHalf = reversedSecondHalf.next;
        }
        return true;
    }

    public static boolean isPalindrome2(ListNode head) {
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        if (n <= 1) {
            return true;
        }
        cur = head;
        for (int i = 0; i < (n + 1) / 2; i++) {
            cur = cur.next;
        }

        ListNode firstHalf = head;
        ListNode reversedSecondHalf = reverseList(cur);
        while (reversedSecondHalf != null) {
            if (firstHalf.val != reversedSecondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            reversedSecondHalf = reversedSecondHalf.next;
        }
        return true;
    }

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

        System.out.println(isPalindrome2(arr[0]));
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