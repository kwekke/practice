
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/536/week-3-may-15th-may-21st/3330/
Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.

Here, a circular array means the end of the array connects to the beginning of the array.
(Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)

Also, a subarray may only include each element of the fixed buffer A at most once.
(Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)

Hint #1  
For those of you who are familiar with the Kadane's algorithm, think in terms of that. 
For the newbies, Kadane's algorithm is used to finding the maximum sum subarray from a given array. 
This problem is a twist on that idea and it is advisable to read up on that algorithm first before starting this problem. 
Unless you already have a great algorithm brewing up in your mind in which case, go right ahead!

Hint #2
What is an alternate way of representing a circular array so that it appears to be a straight array? 
Essentially, there are two cases of this problem that we need to take care of. 

Hint #3  
The first case can be handled by the good old Kadane's algorithm. 
However, is there a smarter way of going about handling the second case as well?
==================================================================================================================
This problem seems like an extension to a similar problem from Leetcode April 30 edition

case 1: no wraparound
Kadane

case 2: wraparound
Consider the inverted sign arrays ie every element's sign is flipped. 
Calculate the total accumulation sum of the entire array
Then use Kadane's algo to find the greatest decrease accumulation.
max Sum with wraparound is the total accumulation - the greatest decrease accumulation
However, if the array is all negative, the max sum with wraparound will be 0.
In that case, the result should be case 1.

Time Complexity: O(n)
Space Complexity: O(n)

*/
import java.util.*;

public class MaximumSumCircularSubarray {
    public int maxSubarraySumCircular(int[] A) {
        int[] B = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }

        int maxSum = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] > 0) {
                A[i] += A[i - 1];
            }
            maxSum = Math.max(maxSum, A[i]);
        }

        int acc = 0;
        for (int i = 0; i < B.length; i++) {
            acc += B[i];
            B[i] = -B[i];
        }
        int maxSum2 = 0;
        for (int i = 1; i < B.length; i++) {
            if (B[i - 1] > 0) {
                B[i] += B[i - 1];
            }
            maxSum2 = Math.max(maxSum2, B[i]);
        }
        maxSum2 += acc;

        if (maxSum2 == 0) {
            return maxSum;
        }
        return Math.max(maxSum, maxSum2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tokens = sc.nextLine().split(" ");
        sc.close();
        int[] A = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            A[i] = Integer.parseInt(tokens[i]);
        }
        MaximumSumCircularSubarray mscs = new MaximumSumCircularSubarray();
        System.out.println(mscs.maxSubarraySumCircular(A));
    }
}