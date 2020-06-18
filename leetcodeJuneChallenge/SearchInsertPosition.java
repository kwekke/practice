
/*
https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3356/
Given a sorted array and a target value, return the index if the target is found.
If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.
==================================================================================================================================
1) binary search
Time Complexity: O(lgn)
Space Complexity: O(1)

*/
import java.util.*;

public class SearchInsertPosition {
    class Solution {
        public int searchInsert(int[] nums, int target) {
            int l = 0;
            int r = nums.length - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (nums[m] == target) {
                    return m;
                }
                if (nums[m] > target) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return l;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tokens = sc.nextLine().split(" ");
        int target = sc.nextInt();
        sc.close();
        int[] nums = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            nums[i] = Integer.parseInt(tokens[i]);
        }
        Solution s = new Solution();
        System.out.println(s.searchInsert(nums, target));
    }
}