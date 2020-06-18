
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/537/week-4-may-22nd-may-28th/3338/
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  
The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval. 
For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
Note:

0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
=============================================================================================================================
1) Merge two lists
Time Complexity: O(N + M)
Space Complexity: O(N + M)
*/
import java.util.*;

public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < A.length && j < B.length) {
            int[] inti = A[i];
            int[] intj = B[j];

            int left = Math.max(inti[0], intj[0]);
            int right = Math.min(inti[1], intj[1]);

            if (left <= right) {
                ans.add(new int[] { left, right });
            }

            if (inti[1] < intj[1]) {
                i++;
            } else {
                j++;
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int[][] A = new int[a][2];
        for (int i = 0; i < a; i++) {
            A[i][0] = nextInt();
            A[i][1] = nextInt();
        }
        int b = sc.nextInt();
        int[][] B = new int[b][2];
        for (int i = 0; i < b; i++) {
            B[i][0] = nextInt();
            B[i][1] = nextInt();
        }
        sc.close();
        IntervalListIntersections ili = new IntervalListIntersections();
        int[][] ans = ili.intervalIntersection(A, B);
        for (int[] arr : ans) {
            System.out.println(arr[0] + ", " + arr[1]);
        }
    }
}