import java.util.*;

/**
 * https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1215/
 * Write a program to find the node at which the intersection of two singly
 * linked lists begins.
 * 
 * Traverse both lists to find out lengths of lists A and B to find out
 * difference between lengths. Maintain two pointers at the start of the lists
 * again. Traverse the longer list by that difference from the start again. Now
 * the remaining distance to intersection for both pointers are the same.
 * Traverse both pointers while checking if they point to the same object.
 */
public class IntersectionOfTwoLinkedLists {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int a = 0;
        ListNode tempA = headA;
        while (headA != null) {
            headA = headA.next;
            a++;
        }
        int b = 0;
        ListNode tempB = headB;
        while (headB != null) {
            headB = headB.next;
            b++;
        }

        if (a < b) {
            for (int i = 0; i < (b - a); i++) {
                tempB = tempB.next;
            }
        } else if (a > b) {
            for (int i = 0; i < (a - b); i++) {
                tempA = tempA.next;
            }
        }

        while (tempA != null && tempB != null) {
            if (tempA == tempB) {
                return tempA;
            } else {
                tempA = tempA.next;
                tempB = tempB.next;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String val = sc.nextLine();
        String[] listA = sc.nextLine().split(" ");
        String[] listB = sc.nextLine().split(" ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.close();

        ListNode commonNode = new ListNode(val);

        ListNode curA;
        if (a == 0) {
            curA = commonNode;
        } else {
            curA = new ListNode(listA[0]);
        }
        ListNode tempA = curA;
        for (int i = 1; i < listA.length; i++) {
            ListNode newA;
            if (i == a) {
                newA = commonNode;
            } else {
                newA = new ListNode(listA[i]);
            }
            curA.next = newA;
            curA = curA.next;
        }

        ListNode curB;
        if (b == 0) {
            curB = commonNode;
        } else {
            curB = new ListNode(listB[0]);
        }
        ListNode tempB = curB;
        for (int i = 1; i < listB.length; i++) {
            ListNode newB;
            if (i == b) {
                newB = commonNode;
            } else {
                newB = new ListNode(listB[i]);
            }
            curB.next = newB;
            curB = curB.next;
        }

        ListNode ans = getIntersectionNode(tempA, tempB);
        if (ans != null) {
            System.out.println(ans.val);
            System.out.println(val.equals(ans.val));
        }

    }

}

class ListNode {
    String val;
    ListNode next;

    ListNode(String x) {
        val = x;
        next = null;
    }
}