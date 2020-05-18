
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/536/week-3-may-15th-may-21st/3332/
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.
================================================================================================================
1) Sliding Window
Keep count of alphabets' frequency.
Time Complexity: O(26 N) = O(N)
because length of array being compared to in the isSame method is of length 26, the number of alphabets
Space Complexity: O(1)

*/
import java.util.*;

public class AllAnagramsInString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int N = s.length();
        int M = p.length();

        if (N == 0 || M == 0 || N < M) {
            return list;
        }

        int i;

        int[] expectedCounts = new int[26];
        int[] currentCounts = new int[26];

        for (i = 0; i < M; i++) {
            expectedCounts[p.charAt(i) - 'a']++;
            currentCounts[s.charAt(i) - 'a']++;

        }

        for (i = 0; i < N - M; i++) {
            if (isSame(expectedCounts, currentCounts)) {
                list.add(i);
            }
            currentCounts[s.charAt(i) - 'a']--;
            currentCounts[s.charAt(i + M) - 'a']++;
        }

        if (isSame(expectedCounts, currentCounts)) {
            list.add(N - M);
        }

        return list;
    }

    private boolean isSame(int[] a1, int[] a2) {
        if (a1.length != a2.length) {
            return false;
        }

        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }
}