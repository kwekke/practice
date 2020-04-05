import java.util.*;
import java.io.*;

/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/528/week-1/3287/
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. 
You may complete as many transactions as you like 
(i.e., buy one and sell one share of the stock multiple times).
Note: You may not engage in multiple transactions at the same time 
(i.e., you must sell the stock before you buy again).

Iterate through array. 
Profit gained when there is an increasing subsequence.
case1: increasing subsequence
if subsequence is in the form of a b c where a<b<c then total profit = b-a + c-b
which is the difference between all the adjacent elements in an increasing subsequence

case2: decreasing subsequence
if next int is smaller than current int then 0 profit.

array can be split into peaks(p) and valleys(v) where
p->v are increasing subsequences and v->p are decreasing subsequence

*/
class BestTimeToBuyAndSell2 {
    public static int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit += Math.max(prices[i] - prices[i - 1], 0);
        }
        return profit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] tokens = line.split(" ");
        int[] array = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            array[i] = Integer.parseInt(tokens[i]);
        }
        System.out.println(maxProfit(array));
        sc.close();
    }
}