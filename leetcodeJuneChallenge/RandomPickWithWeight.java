/*
https://leetcode.com/explore/featured/card/june-leetcoding-challenge/539/week-1-june-1st-june-7th/3351/
Given an array w of positive integers, where w[i] describes the weight of index i, 
write a function pickIndex which randomly picks an index in proportion to its weight.

Note:
1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
Example 1:

Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
Example 2:

Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. 
Solution's constructor has one argument, the array w. pickIndex has no arguments.
Arguments are always wrapped with a list, even if there aren't any.
=====================================================================================================================
1) An array the length of the total slots (FAIL)
Time Complexity: O(n), O(1) 
Space Complexity: O(nm) ; Out of memory

2) An array to store the range of slots
Use variant of binary search to find the correct interval at which the random number was generated belongs to.
d is of w.length + 1 because the first start of first interval has to be 0. 
This solution is viable because of the input assumption that all weights are at least 1. If there was a weight of
an index which is 0, it might be troublesome (doable with interval objects with fields: start, end, and index).
Time Complexity: O(n), O(lgn) ; 20ms
Space Complexity: O(n)

3) An array to store the range of slots (2)
Uses normal binary search since we store the ranges differently. 
Time Complexity: O(n), O(lgn) ; 18ms
Space Complexity: O(n)

*/

import java.util.*;

public class RandomPickWithWeight {
    class Solution1 {

        int total = 0;
        int d[];
        Random rand = new Random();

        public Solution(int[] w) {
            for (int i : w) {
                total += i;
            }
            d = new int[total];
            int j = 0;
            for (int i = 0; i < w.length; i++) {
                for (int k = 0; k < w[i]; k++) {
                    d[j + k] = i;
                }
                j += w[i];
            }
        }

        public int pickIndex() {
            return d[rand.nextInt(total)];
        }
    }

    class Solution2 {
        Random rand = new Random();
        int total = 0;
        int d[];

        public Solution(int[] w) {
            d = new int[w.length + 1];
            for (int i = 0 ; i < w.length ; i++) {
                total += w[i];
                d[i + 1] = total;
            }
        }

        public int pickIndex() {
            int r = rand.nextInt(total);
            return binarySearchIndex(d, r);
        }

        public int binarySearchIndex(int[] arr, int x) {
            int l = 0;
            int r = arr.length - 1;
            while (l <= r) {
                if (l == r) {
                    return l;
                }
                int m = l + (r - l) / 2;
                if (arr[m] <= x && x <= arr[m + 1] - 1) {
                    return m;
                }

                if (arr[m] > x) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return -1;
        }
    }

    class Solution3 {

        Random r; // from [0,n-1]
        int[] buckets;

        public Solution(int[] w) {
            int wsum=0;
            buckets=new int[w.length];
            for(int i=0;i<buckets.length;i++) {
                wsum+=w[i];
                buckets[i]=wsum-1;   //important 
            }
            r=new Random();
        }

        public int pickIndex() {
            int sum = r.nextInt(buckets[buckets.length - 1] + 1);
            int tmp = Arrays.binarySearch(buckets, sum);

            if (tmp < 0)
                tmp = -tmp - 1;

            return tmp;

        }
    }
}