/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/531/week-4/3311/
Given two strings text1 and text2, return the length of their longest common subsequence.

A subsequence of a string is a new string generated from the original string
 with some characters(can be none) deleted without changing the relative order of the remaining characters.
 (eg, "ace" is a subsequence of "abcde" while "aec" is not). 
 A common subsequence of two strings is a subsequence that is common to both strings.

If there is no common subsequence, return 0.
====================================================================================================
1) dp 2D array
Time Complexity: O(mn)
Space Compexity: O(mn)
Following steps build L[m+1][n+1] in bottom up fashion. 
Note that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1]

2) dp 1D array
Time Complexity: O(mn)
Space Compexity: O(m)

*/

import java.util.*;

public class LongestCommonSubsequence {
    public int longestCommonSubsequence2D(String text1, String text2) {
        return lcs(text1.toCharArray(), text2.toCharArray(), text1.length(), text2.length());
    }

    int lcs2D(char[] X, char[] Y, int m, int n) {
        int L[][] = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (X[i - 1] == Y[j - 1])
                    L[i][j] = L[i - 1][j - 1] + 1;
                else
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
            }
        }
        return L[m][n];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        if (len1 == 0 || len2 == 0) {
            return 0;
        }
        int[] dp = new int[len2 + 1];
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        for (int i = 0; i < len1; i++) {
            int x = 0;
            for (int j = 0; j < len2; j++) {
                int y = dp[j + 1];
                if (chars1[i] == chars2[j]) {
                    dp[j + 1] = x + 1;
                } else if (dp[j] > dp[j + 1]) {
                    dp[j + 1] = dp[j];
                }
                x = y;
            }
        }
        return dp[len2];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text1 = sc.nextLine();
        String text2 = sc.nextLine();
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        System.out.println(lcs.longestCommonSubsequence(text1, text2));
    }
}