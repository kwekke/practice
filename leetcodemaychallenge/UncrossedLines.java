
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/537/week-4-may-22nd-may-28th/3340/
We write the integers of A and B (in the order they are given) on two separate horizontal lines.

Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:

A[i] == B[j];
The line we draw does not intersect any other connecting (non-horizontal) line.
Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.

Return the maximum number of connecting lines we can draw in this way.
Note:

1 <= A.length <= 500
1 <= B.length <= 500
1 <= A[i], B[i] <= 2000
==================================================================================================================================
1) dp top down
Main dp array in the recursion. Each time before calling the recursion, we have to check whether the value for the current state (i,j)
exists in the dp array or not. If so, we return that value. Otherwise, we evaluate the value using recursion and store it in the dp array. 
Time Complexity: O(mn)
Space Complexity: O(mn)

2) dp bottom up
Fill up the array line by line.
Time Complexity: O(mn)
Space Complexity: O(mn)

3) dp bottom up with space optimization
Time Complexity: O(mn)
Space Complexity: O(m) or O(n)
*/
import java.util.*;

public class UncrossedLines {
    public int maxUncrossedLines1(int[] A, int[] B) {
        int[][] dp = new int[A.length][B.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                dp[i][j] = -1;
            }
        }

        return lcs(A, B, 0, 0, dp);
    }

    int lcs(int[] A, int[] B, int i, int j, int[][] dp) {
        if (i == A.length || j == B.length) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (A[i] == B[j]) {
            return dp[i][j] = 1 + lcs(A, B, i + 1, j + 1, dp);
        } else {
            return dp[i][j] = Math.max(lcs(A, B, i + 1, j, dp), lcs(A, B, i, j + 1, dp));
        }
    }

    public int maxUncrossedLines2(int[] A, int[] B) {
        int[][] dp = new int[A.length + 1][B.length + 1];

        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = B.length - 1; j >= 0; j--) {
                dp[i][j] = (A[i] == B[j] ? dp[i + 1][j + 1] + 1 : Math.max(dp[i][j + 1], dp[i + 1][j]));
            }
        }
        return dp[0][0];
    }

    public int maxUncrossedLines3(int[] A, int[] B) {
        int[][] dp = new int[B.length + 1][2];
        int flag = 1;
        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = B.length - 1; j >= 0; j--) {
                dp[j][flag] = (A[i] == B[j] ? dp[j + 1][flag ^ 1] + 1 : Math.max(dp[j + 1][flag], dp[j][flag ^ 1]));
            }
            flag ^= 1;
        }
        return dp[0][flag ^ 1];
    }
}
