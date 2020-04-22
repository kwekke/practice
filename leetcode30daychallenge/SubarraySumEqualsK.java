/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/531/week-4/3307/
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
==================================================================================================================================
1) One pass hashmap 
use hashmap to keep track of cumulative sum to its freq
while iterating, check if there is a key of (sum - k). 
making use of this: sum(i,j) = sum(0,j) - sum(0,i)

Time Complexity: O(N)
Space Complexity: O(N)

2) Two pass array
similar idea to 1), but uses array to store the freq
the arr is set up to be of size of difference between min sum and max sum

Time Complexity: O(N)
Space Complexity: O(N)
*/

import java.util.*;

public class SubarraySumEqualsK {
    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> hm = new HashMap<>(); // hm cumulative sum to freq
        hm.put(0, 1);
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            ans += hm.getOrDefault(sum - k, 0);
            hm.put(sum, hm.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }

    public int subarraySumArray(int[] nums, int k) {
        int sum = 0, ans = 0, tmp, min = 1 << 30, max = -1 << 30;
        for (int i : nums) {
            sum += i;
            if (sum > max)
                max = sum;
            if (sum < min)
                min = sum;
        }
        int[] hash = new int[max - min + 1];
        sum = 0;

        for (int i : nums) {
            sum += i;
            if (sum == k)
                ++ans;
            tmp = sum - k;
            if (tmp >= min && tmp <= max) {
                ans += hash[tmp - min];
            }
            ++hash[sum - min];
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tokens = sc.nextLine().split(" ");
        int[] arr = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }
        int k = sc.nextInt();
        sc.close();
        System.out.println(subarraySum(arr, k));
    }
}