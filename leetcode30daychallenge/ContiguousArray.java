
/*
https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/529/week-2/3298/
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Note: The length of the given binary array will not exceed 50,000.
==================================================================================================
1) maintain a variable sum which is incremented if it encounters a 1 and decrements if it encounters a 0.
at each sum value calculated, if the mapping to sum is not stored ie map[i] == -2, then update its mapping of sum -> index
otherwise if we sum encounters a value that has been stored already,
then consider the difference between the current index and the value of the sum's mapping. 
the sums having the same value imply that there are equal number of 1s and 0s.
Since we are interested in the maximum, we do not reupdate the index of the sum's mapping. 
*/
import java.util.*;

public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        int[] map = new int[nums.length * 2 + 1];
        // int arbitraryNumber = -2;
        for (int i = 0; i < map.length; i++) {
            map[i] = -2;
        }

        int max = 0;
        int sum = nums.length;
        map[nums.length] = -1; // initialise value 0 to be -1 index.
        for (int i = 0; i < nums.length; i++) {
            sum += (nums[i] * 2 - 1);
            if (map[sum] == -2) {
                map[sum] = i;
            } else {
                max = Math.max(max, i - map[sum]);
            }
        }
        return max;
    }
}
