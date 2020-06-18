
/*
https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/541/week-3-june-15th-june-21st/3364/
Given an array of citations sorted in ascending order (each citation is a non-negative integer) 
of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: 
"A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
Note:

If there are several possible values for h, the maximum one is taken as the h-index.

Follow up:

This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.
Could you solve it in logarithmic time complexity?
=====================================================================================================================
1) binary search
Time Complexity: O(lgn)
Space Complexity: O(1)

2) linear search
Time Complexity: O(n)
Space Complexity: O(1)
*/
import java.util.*;

public class HIndex2 {
    class Solution {
        public int hIndex(int[] citations) {
            int len = citations.length;
            int l = 0;
            int r = citations.length - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (citations[m] == len - m)
                    return len - m;
                else if (citations[m] > len - m)
                    r = m - 1;
                else
                    l = m + 1;
            }
            return len - l;
        }
    }

    class Solution2 {
        public int hIndex(int[] citations) {
            int len = citations.length;
            int i = 0;
            while (i < len && len - i > citations[i]) {
                i++;
            }
            return len - i;
        }
    }
}