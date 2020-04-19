/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/530/week-3/3304/
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
You are given a target value to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.
Your algorithm's runtime complexity must be in the order of O(log n).

1) Binary searches - O(lg n)
a) Find index of minimum in rotated array
b) See which of the 2 portions of the array to search for target using binary search
Time Complexity: O(lg n) + O(lg n) = O(lg n)
Space Complexity: O(1)

2) Binary Searches iterative - O(lg n)
Similar to 1) but iterative. 
Time Complexity: O(lg n) + O(lg n) = O(lg n)
Space Complexity: O(1)
*/

import java.util.*;

public class SearchInRotatedSortedArray {
    public static int searchRecursive(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length;
        if (nums[0] <= nums[n - 1]) {
            // if array not rotated
            return binarySearch(nums, 0, n - 1, target);
        } else {
            int ind = findMinimumInRotatedSortedArray(nums, 0, n - 1, target);
            if (target <= nums[n - 1]) {
                return binarySearch(nums, ind, n - 1, target);
            } else {
                return binarySearch(nums, 0, ind - 1, target);
            }
        }
    }

    private static int findMinimumInRotatedSortedArray(int arr[], int l, int r, int x) {
        while (r >= l) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > arr[mid + 1]) {
                return mid + 1;
            }
            if (arr[mid - 1] > arr[mid]) {
                return mid;
            }
            if (arr[mid] > arr[l]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    private static int binarySearch(int arr[], int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == x) {
                return mid;
            }
            if (arr[mid] > x) {
                return binarySearch(arr, l, mid - 1, x);
            }
            return binarySearch(arr, mid + 1, r, x);
        }
        return -1;
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int start = left;
        left = 0;
        right = nums.length - 1;
        if (target >= nums[start] && target <= nums[right]) {
            left = start;
        } else {
            right = start;
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String tokens[] = sc.nextLine().split(" ");
        int[] nums = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            nums[i] = Integer.parseInt(tokens[i]);
        }
        int target = sc.nextInt();
        sc.close();
        System.out.println(search(nums, target));
    }
}