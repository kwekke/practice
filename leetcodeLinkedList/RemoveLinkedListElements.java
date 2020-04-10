import java.util.*;

/*
https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1207/
Remove all elements from a linked list of integers that have value val.

1) Iterate and recursion mix - O(N)
checking if succeeding node has target val. 
reassign the next pointer.

2) Iterate and use dummy head - O(N)
check if succeeding node has target val.
if so, then skip it. otherwise, continue;

*/
public class RemoveLinkedListElements {
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        if (head.val == val) {
            return removeElements(head.next, val);
        }

        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.next.val == val) {
                cur.next = cur.next.next;
            }
            cur = cur.next;
        }
        return head;
    }

    public static ListNode removeElementsDummy(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
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

        ListNode head = removeElementsDummy(arr[0], n);
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