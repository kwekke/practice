import java.io.*;
import java.util.*;
import java.lang.*;

/*
https://leetcode.com/explore/other/card/30-day-leetcoding-challenge/528/week-1/3285/
Given an integer array nums, 
find the contiguous subarray (containing at least one number)
which has the largest sum and return its sum.

1) Divide and Conquer, derive max from max of 2 partitions and O(n) merge
T(n) = 2T(n/2) + O(n) 
Time complexity: O(nlgn)
2) Divide and Conquer using objects storing pre, post, max, total O(1) merge
T(n) = 2T(n/2) + O(1) 
Time complextiy: O(n)
3) Iterate through - O(n)
*/
public class MaximumSubarray {

    // 1) divide and conquer
    public static int maxCrossingSum(int[] arr, int l, int m, int h) {
        int temp;
        // sum of left partition; iterate from middle.
        temp = 0;
        int leftSum = Integer.MIN_VALUE;
        for (int i = m; i >= l; i--) {
            temp += arr[i];
            if (temp > leftSum) {
                leftSum = temp;
            }
        }

        // sum of right partition; iterate from middle
        temp = 0;
        int rightSum = Integer.MIN_VALUE;
        for (int i = m + 1; i <= h; i++) {
            temp += arr[i];
            if (temp > rightSum) {
                rightSum = temp;
            }
        }
        return leftSum + rightSum;
    }

    public static int maxSubArraySum(int[] arr, int l, int h) {
        if (l == h) {
            return arr[l];
        }
        int m = (l + h) / 2;
        return Math.max(Math.max(maxSubArraySum(arr, l, m), maxSubArraySum(arr, m + 1, h)),
                maxCrossingSum(arr, l, m, h));
    }

    // 2) Divide and conquer using objects
    public static Partition mergeObjects(Partition left, Partition right) {
        int total, max, pre, post;
        total = left.total + right.total;
        pre = max(left.pre, left.total + right.pre, left.total + right.total);
        post = max(right.post, right.total + left.post, left.total + right.total);
        max = max(pre, post, total, left.max, right.max, left.post + right.pre);
        return new Partition(total, pre, post, max);
    }

    public static Partition maxSubArraySumObjects(int[] arr, int l, int h) {
        if (l == h) {
            return new Partition(arr[l]);
        }
        int m = (l + h) / 2;
        Partition leftPartition = maxSubArraySumObjects(arr, l, m);
        Partition rightPartition = maxSubArraySumObjects(arr, m + 1, h);
        return mergeObjects(leftPartition, rightPartition);
    }

    // helper function returns Max of given values
    private static int max(int... list) {
        int ans = Integer.MIN_VALUE;
        for (int i : list) {
            ans = Math.max(ans, i);
        }
        return ans;
    }

    // 3) Iterate through array - O(n)
    // while iterating, if sum up to i is positive, then include this sum to A[i].
    // then update maxSum.
    // invariant: arr[i] = maxSum up to i including the original value in arr[i]
    // after iteration
    public static int maxSubArray(int[] arr) {
        int maxSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > 0) {
                arr[i] += arr[i - 1];
            }
            maxSum = Math.max(maxSum, arr[i]);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] tokens = line.split(" ");
        int[] array = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            array[i] = Integer.parseInt(tokens[i]);
        }
        // System.out.println(maxSubArraySum(array, 0, array.length - 1));
        // System.out.println(maxSubArraySumObjects(array, 0, array.length - 1).max);
        System.out.println(maxSubArray(array));
        sc.close();
    }
}

class Partition {
    int total;
    int pre;
    int post;
    int max;

    Partition(int total, int pre, int post, int max) {
        this.total = total;
        this.pre = pre;
        this.post = post;
        this.max = max;
    }

    Partition(int x) {
        this(x, x, x, x);
    }
}