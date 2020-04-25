
/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/531/week-4/3310/
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.
========================================================================================================================
1) dp 
Time Complexity: O(N^2)
Space Complexity: O(N)
memoisation table

Reverse the order of the steps from the top down approach. 
The observation to make here is that we only ever jump to the right. 
This means that if we start from the right of the array, every time we will query a position to our right,
that position has already been determined to be GOOD or BAD, this means that we dont need to recurse anymore,
as will always hit the mem table. 


2) greedy
Time Complexity: O(N)
Space Complexity: O(1)

Keep track of left most GOOD positition as a variable to avoid searching for it when iterating.
Iterating right-to-left, for each position we check if there was a potential jump that reaches a GOOD index `pos + nums[pos] >= lastPos`
If we can reach a GOOD index, then our position is itself GOOD, also this new GOOD position will be the new leftmost GOOD index. 
Iteration continues until the beginning of the array. 
If first position is a GOOD index, then we can reach the last index form the first position. 


3) Jump over zeroes
Time Complexity: O(N)
Space Complexity: O(1)

Check if we can avoid a zero if we encounter it. 
So, we start from the second last index and go leftwards, and each time we encounter a zero we check
all the elements before our current index to see if we can jump over it.    
    
*/
import java.util.*;

public class JumpGame {
    public boolean canJumpDP(int[] nums) {
        if (nums == null || nums.length == 1) {
            return true;
        }
        int pos = 0;
        int n = nums.length;
        int[] mem = new int[n];
        mem[n - 1] = 1;

        for (int i = n - 2; i >= 0; i--) {
            int furthest = Math.min(i + nums[i], n - 1);
            for (int j = i + 1; j <= furthest; j++) {
                if (mem[j] == 1) {
                    mem[i] = 1;
                    break;
                }
            }
        }
        return mem[0] == 1;
    }

    public boolean canJumpGreedy(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    public boolean canJump(int[] nums) {
        if (nums.length <= 1)
            return true;

        for (int pos = nums.length - 2; pos >= 0; pos--) {
            if (nums[pos] == 0) {
                int neededJumps = 1;

                while (neededJumps > nums[pos]) {
                    neededJumps++;
                    pos--;

                    if (pos < 0)
                        return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        JumpGame jg = new JumpGame();
        Scanner sc = new Scanner(System.in);
        String[] tokens = sc.nextLine().split(" ");
        sc.close();
        int[] nums = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            nums[i] = Integer.parseInt(tokens[i]);
        }
        System.out.println(jg.canJump(nums));
    }
}
