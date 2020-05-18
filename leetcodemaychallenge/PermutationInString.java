
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/536/week-3-may-15th-may-21st/3333/
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
In other words, one of the first string's permutations is the substring of the second string.

Note:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
========================================================================================================
1) Sliding window
Keep count of alphabets' frequency. when the window has the same frequency as s1, 
it implies that s1 can be permutated to form a substring of s2, which is the current window. 
Time Complexity: O(M)
Space Complexity: O(1)

*/
import java.util.*;

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int N = s1.length();
        int M = s2.length();
        if (N == 0 || M == 0 || M < N) {
            return false;
        }
        int[] expectedCount = new int[26];
        int[] curCount = new int[26];

        for (int i = 0; i < N; i++) {
            expectedCount[s1.charAt(i) - 'a']++;
            curCount[s2.charAt(i) - 'a']++;
        }

        for (int i = N; i < M; i++) {
            if (isSame(expectedCount, curCount)) {
                return true;
            }
            curCount[s2.charAt(i - N) - 'a']--;
            curCount[s2.charAt(i) - 'a']++;
        }

        if (isSame(expectedCount, curCount)) {
            return true;
        }

        return false;
    }

    private boolean isSame(int[] a1, int[] a2) {
        for (int i = 0; i < 26; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }
}