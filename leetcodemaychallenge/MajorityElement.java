/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3321/
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
====================================================================================================================================
1. HashMap
Map element to count and return element if count reaches at least ⌊ n/2 ⌋ 
Time Complexity: O(n)
Iterate through every element 
Space Complexity: O(n)
HashMap contains at most n - ⌊ n/2 ⌋ mappings because we are guaranteed a majority element

2. Sort
After sorting array, the majority element is guaranteed to be in the centre. 
The range of indices that could be the majority element overlap in the centre.
The (⌊ n/2 ⌋) th element from the front and from the back has to be same and is the majority element. 
Time Complexity: O(nlgn)
optimized by Java, could perhaps try counting sort to make it O(n)
Space Complexity: O(1)
If counting sort is done then space is O(n) since counting sort is not in place algo.

3. Divide and Conquer
Recursively split array until length of 1. 
When merging, compare the majority element in both halves. 
If they are the same element then return it. 
Otherwise, count the number of occurences of each majority element in both halves and compare. Return the element with the higher count.
Time Complexity: O(nlgn)
T(n) = 2T(n/2) + 2n
MT case 2
T(n) = O(nlgn)
Space Complexity: O(lgn)
lgn calls

4. Intuition splitting into subarrays
Iterate through the array in one pass, keeping track of the majority element thus far and its count.
Suppose the array can be split into 2 where the max no. of an element in the first subarray is exactly half of the lenth of the subarray.
Then, for it to be a majority element in the original array, the rest of the array has to have at least half of its length of that element. ie > ⌊ l2/2 ⌋.
Suppose that it is not the majority element in the original array, then the rest of the array still has to output > ⌊ n/2 ⌋ of the majority element.
In both cases, ignoring the first subarray does not affect the result. Hence, we can reset the majority element found thus far, and its count. 
Time Complexity: O(n)
One pass
Space Complexity: O(1)

*/

import java.util.*;

class MajorityElement {
    // 1.HashMap
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i : nums) {
            hm.put(i, hm.getOrDefault(i, 0) + 1);
            if (hm.get(i) > nums.length / 2) {
                return i;
            }
        }
        return -1;
    }

    // 2.Sorting
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // 3. Divide and Conquer
    public int majorityElement3(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    private int majorityElementRec(int[] nums, int l, int r) {
        if (l == r) {
            return nums[l];
        }

        int m = l + (r - l) / 2;
        int left = majorityElementRec(nums, l, m);
        int right = majorityelementRec(nums, m + 1, r);

        // if both subarrays agree on the same maj elemenet
        if (left == right) {
            return left;
        }

        // count each element globally
        int leftCount = countInRange(nums, left, l, r);
        int rightCount = countInRange(nums, right, l, r);
        return leftCount > rightCount ? left : right;
    }

    private int countInRange(int[] nums, int num, int l, int r) {
        int count = 0;
        for (int i = l; i <= r; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    public int majorityElement4(int[] nums) {
        int count = 1;
        int majE = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                majE = nums[i];
            }
            count += (nums[i] == majE) ? 1 : -1;
        }
        return majE;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tokens = sc.nextLine().split(" ");
        sc.close();
        int[] nums = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            nums[i] = Integer.parseInt(tokens[i]);
        }
        MajorityElement me = new MajorityElement();
        System.out.println(me.majorityElement(nums));
    }
}
