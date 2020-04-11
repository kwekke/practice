import java.util.*;
/*
https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1208/
Given a singly linked list, group all odd nodes together followed by the even nodes.
Please note here we are talking about the node number and not the value in the nodes.
You should try to do it in place. 
The program should run in O(1) space complexity and O(nodes) time complexity.

1) Iterate through maintaining a boolean - O(N)

2) Iterate through w/o boolean - O(N)

*/

public class OddEvenLinkedList {
    public static ListNode oddEvenList(ListNode head) {
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

    public static ListNode oddEvenList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p1 = head;
        ListNode p2 = head.next;
        ListNode evenHead = p2;
        while (p2 != null && p2.next != null) {
            p1.next = p2.next;
            p1 = p1.next;
            p2.next = p1.next;
            p2 = p2.next;
        }
        p1.next = evenHead;
        return head;
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

        ListNode head = oddEvenList2(arr[0]);
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