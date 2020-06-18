import java.util.*;
/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/530/week-3/3300/
Given an array nums of n integers where n > 1,  
return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array 
(including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? 
(The output array does not count as extra space for the purpose of space complexity analysis.)
========================================================================================================
1) brute force
Time Complexity: O(N^2) 
Space Complexity: O(N) for ans array

2) One pass and booleans (clunky and complicated)
Time Complexity: O(N)
calculate prduct
Iterate through, product except it product divide it, given the conditions
Space Complexity: O(N) for ans array

3) Two pass (no division)
Time Complexity: O(N)
First pass from right to left, updating ans array to be cumulative product of numbers
on its right
Second pass from left to right, updating ans array to multiply the cumulative product of numbers
on it s left
Space Complexity: O(N) for ans array
*/

public class ProductOfArrayExceptSelf {

    public static int[] productExceptSelfBrute(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }
            ans[i] = product;
        }
        return ans;
    }

    public static int[] productExceptSelfBoolean(int[] nums) {
        int product = 1;
        boolean hasZero = false;
        boolean hasZeroes = false;
        boolean hasProduct = false;
        for (int i : nums) {
            if (i == 0) {
                if (hasZero) {
                    hasZeroes = true;
                }
                hasZero = true;
            } else {
                hasProduct = true;
                product *= i;
            }
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (hasProduct && !hasZeroes) {
                    ans[i] = product;
                } else {
                    ans[i] = 0;
                }
            } else {
                if (hasZero) {
                    ans[i] = 0;
                } else {
                    ans[i] = product / nums[i];
                }
            }
        }
        return ans;
    }

    public static int[] productExceptSelf(int[] nums) {
        if (nums.length == 0) {
            return nums;
        }
        int n = nums.length;
        int[] ans = new int[n];
        int product = 1;
        ans[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            ans[i] = ans[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < n; i++) {
            ans[i] *= product;
            product *= nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tokens = sc.nextLine().split(" ");
        sc.close();
        int[] arr = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }
        int[] ans = productExceptSelf(arr);
        System.out.println(Arrays.toString(ans));
    }
}