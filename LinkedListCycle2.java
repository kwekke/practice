/*
proof why head and tortoise will meet at entrance if each move 1 step at a time, after finding if a cycle exists.
x be the distance from head to start of loop
y be the cycle distance
z be the distance from start of loop to where rabbit and tortoise meet

when rabbit and tortoise meet, their distance travelled can be expressed as
x + ky + z and x + ly + z where k and l are no. of cycles traversed.
since rabbit moves twice as fast as tortoise, 
x + ky + z = 2 (x + ly + z)
simplify equation
x + z = (k - 2l)y 

after rabbit and tortoise meet, both of them are currently at x + z (or z away from start of loop).
we let the head pointer and tortoise move 1 step at a time each.
we claim that they will eventually meet at the entrance of the loop
since they would have travelled the same distance because
the tortoise is currently z away from the start of the loop. 
and after moving additional x steps, rabbit would have travelled x + z, an integer multiple of cycle distance
hence rabbit would have ended up at the start of the cycle. and so would head.

Also known as Floyd cycle detection algorithm
*/

public class LinkedListCycle2 {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode rabbit = head;
        ListNode tortoise = head;
        while (rabbit.next != null && rabbit.next.next != null) {
            rabbit = rabbit.next.next;
            tortoise = tortoise.next;
            if (rabbit == tortoise) {
                break;
            }
        }
        if (rabbit.next == null || rabbit.next.next == null) {
            return null;
        }

        while (head != tortoise) {
            tortoise = tortoise.next;
            head = head.next;
        }
        return head;
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
