/*
https://leetcode.com/explore/featured/card/june-leetcoding-challenge/539/week-1-june-1st-june-7th/3353/
You are given coins of different denominations and a total amount of money. 
Write a function to compute the number of combinations that make up that amount. 
You may assume that you have infinite number of each kind of coin.

Note:
You can assume that
0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer
=============================================================================================================
1) dp bottom up approach
dp stores number of solutions. 
Time Complexity: O(mn)
Space Complexity: O(n)
*/

import java.util.*;

public class CoinChange2 {
    class Solution {
        public int change(int amount, int[] coins) {
            int[] dp = new int[amount + 1];
            dp[0] = 1;
            for (int i = 0; i < coins.length; i++)
                for (int j = coins[i]; j <= amount; j++)
                    dp[j] += dp[j - coins[i]];
            return dp[amount];
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int amount = 4;
        int[] coins = new int[] { 1, 2, 3, 5 };
        System.out.println(s.change(amount, coints));
    }
}