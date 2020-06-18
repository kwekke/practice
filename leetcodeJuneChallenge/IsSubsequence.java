/*
https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3355/
Given a string s and a string t, check if s is subsequence of t.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none)
of the characters without disturbing the relative positions of the remaining characters. 
(ie, "ace" is a subsequence of "abcde" while "aec" is not).

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, 
and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?

Constraints:
0 <= s.length <= 100
0 <= t.length <= 10^4
Both strings consists only of lowercase characters.
================================================================================================================
1) two pointers
iterate from the back
Time Complexity: O(n + m) ; 0 ms
Space Complexity: O(1)

2) java library index of which finds the first index of the character

Time Complexity: O(n + m) ; 0 ms
Space Complexity: O(1)
*/

import java.util.*;

public class IsSubsequence {
    class Solution1 {
        public boolean isSubsequence(String s, String t) {
            int n = s.length();
            int m = t.length();
            int j = m - 1;
            for (int i = n - 1; i >= 0; i--) {
                char c = s.charAt(i);
                boolean isFound = false;
                for (int k = j; k >= 0; k--) {
                    if (t.charAt(k) == c) {
                        j = k - 1;
                        isFound = true;
                        break;
                    }
                }
                if (!isFound) {
                    return false;
                }
            }
            return true;
        }
    }

    class Solution2 {
        public boolean isSubsequence(String s, String t) {
            int index = -1;
            for (char c : s.toCharArray()) {
                index = t.indexOf(c, index + 1);
                if (index == -1)
                    return false;
            }
            return true;
        }
    }
}