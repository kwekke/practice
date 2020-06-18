/*
https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3359/
Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:

Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

Input: [1,2,3]
Output: [1,2] (of course, [1,3] will also be ok)
Example 2:

Input: [1,2,4,8]
Output: [1,2,4,8]
===================================================================================================================
1) dp
Time Complexity: O(n^2) ; 2 ms
Space Complexity: O(n)

2) dp
Time Complexity: O(n^2) ; 23 ms
Space Complexity: O(n
1. Initially sort an array so that all divisors of a number appear before it.
2. Create two arrays arr, index. arr is used for storing divisor count(least will be 1) and index is used for storing all index value(which satisfy the condition Si % Sj==0).
3. max is used for max divisor count and maxIndex is used to store the maximum index.
4. Finally using those index value we are returning largest divisible subset numbers.
)
*/

import java.util.*;

public class LargestDivisibleSubset {
    class Solution {
        int maxV;
        int maxL;
        int cs;
        int next[];
        int len[];

        public List<Integer> largestDivisibleSubset(int[] nums) {

            if (nums.length == 0)
                return new ArrayList<>();

            next = new int[nums.length];
            len = new int[nums.length];

            Arrays.sort(nums);
            maxV = nums[nums.length - 1];

            for (int j = 0; j < nums.length; j++) {
                dp(1, j, nums);

            }

            List<Integer> list = new ArrayList<>();
            int i = cs;
            while (i != -1) {
                list.add(nums[i]);
                i = next[i];
            }
            return list;

        }

        private void dp(int cL, int start, int[] nums) {

            if (len[start] == 0) {
                len[start] = 1;
                next[start] = -1;
            }
            if (len[start] > maxL) {
                maxL = len[start];
                cs = start;
            }

            int limit = maxV >> Math.max(maxL - cL, 0);
            int max = 0;
            for (int j = start + 1; j < nums.length && nums[j] <= limit; j++) {

                if (nums[j] % nums[start] == 0) {

                    if (len[j] == 0) {
                        dp(cL + 1, j, nums);
                    }

                    if (len[j] > max) {
                        max = len[j];
                        next[start] = j;
                        len[start] = len[j] + 1;
                        if (len[start] > maxL) {
                            maxL = len[start];
                            cs = start;
                            limit = maxV >> Math.max(0, maxL - cL);
                        }

                    }

                }

            }

        }
    }

    class Solution2 {
        public List<Integer> largestDivisibleSubset(int[] nums) {
            List<Integer> result = new ArrayList<Integer>();
            if (nums == null || nums.length == 0)
                return result;

            Arrays.sort(nums);

            int[] arr = new int[nums.length];
            int[] index = new int[nums.length];
            Arrays.fill(arr, 1);
            Arrays.fill(index, -1);

            int max = 0;
            int maxIndex = -1;

            for (int i = 0; i < arr.length; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    if (nums[i] % nums[j] == 0 && arr[j] + 1 > arr[i]) {
                        arr[i] = arr[j] + 1;
                        index[i] = j;
                    }
                }

                if (max < arr[i]) {
                    max = arr[i];
                    maxIndex = i;
                }
            }

            int i = maxIndex;
            while (i >= 0) {
                result.add(nums[i]);
                i = index[i];
            }

            return result;
        }
    }
}