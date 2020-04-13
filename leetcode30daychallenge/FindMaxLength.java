import java.util.*;

/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/529/week-2/3298/
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

1) HashMap
Iterate through array, add to the current sum and put the current sum in a hashmap along with the index.
If encounter the current same value somewhere in the future, 
it means that there were equal number of zeros and ones in between the two indices, 
thus resulting in the length of contiguous subarray of equal no. of 0 and 1.
Because zero does not contribute to the sum, we add -1 instead to count zeros within the sum. 
One pass - visit every number only once. 
Time Complexity: O(N). 
Map of maximum N values. 
Space Compexity: O(N)

2) Array
Use array instead of hashmap. 
Array initialised to 2N + 1 size because the range of values = [-N,N].
The variable counting the sum is initialised to length of array for convenience.
Iterate through array only once. O(1) accesses. 
Time Complexity: O(N). 
Array of size 2N + 1. 
Space Compexity: O(N)
*/
public class FindMaxLength {
    public static int findMaxLengthHashMap(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return 0;
        }

        int max = 0;
        int sum = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();

        hm.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                sum--;
            } else {
                sum++;
            }
            if (hm.containsKey(sum)) {
                int index = hm.get(sum);
                max = Math.max(max, i - index);
            } else {
                hm.put(sum, i);
            }
        }
        return max;
    }

    public static int findMaxLength(int[] nums) {
        int[] map = new int[nums.length * 2 + 1];
        // int arbitraryNumber = -2;
        for (int i = 0; i < map.length; i++) {
            map[i] = -2;
        }

        int max = 0;
        int sum = nums.length;
        map[nums.length] = -1; // initialise value 0 to be -1 index.
        for (int i = 0; i < nums.length; i++) {
            // if (nums[i] == 0) {
            // sum--;
            // } else {
            // sum++;
            // }
            sum += (nums[i] * 2 - 1);

            if (map[sum] == -2) {
                map[sum] = i;
            } else {
                max = Math.max(max, i - map[sum]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] tokens = line.split(" ");
        int[] array = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            array[i] = Integer.parseInt(tokens[i]);
        }
        System.out.println(findMaxLength(array));
    }
}