
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/535/week-2-may-8th-may-14th/3327/
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Find this single element that appears only once.
Note: Your solution should run in O(log n) time and O(1) space.
=======================================================================================================================================
1) XOR (from Leetcode April 30 problems)
Use bit manipulation to find out single number. 
A XOR A = 0, XOR is commutative and its operator in java is ^
Hence A XOR B XOR A = A XOR A XOR B = B.
Time Complexity: O(n)
Space Complexity: O(1)

2) binary search

Time Complexity: O(lgn)
Space Complexity: O(1)
*/
import java.util.*;

public class SingleElementSortedArray {
    public int singleNonDuplicateXOR(int[] nums) {
        int a = 0;
        for (int i : nums) {
            a ^= i;
        }
        return a;
    }

    public int singleNonDuplicateBin(int[] nums) {
        int l = 0;
        int r = nums.length / 2;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[2 * m] == nums[2 * m + 1]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return nums[2 * l];
    }
}