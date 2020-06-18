/*
https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3354/
Given an integer, write a function to determine if it is a power of two.
=========================================================================================================
1) bitwise AND with its 2s complement


2) bitwise AND with n - 1.
If a number n is a power of 2 then bitwise & of n and n-1 will be zero. 
We can say n is a power of 2 or not based on value of n&(n-1). 
The expression n&(n-1) will not work when n is 0. 
To handle this case also, our expression will become n& (!n&(n-1))
*/

import java.util.*;

public class PowerOfTwo {
    class Solution {
        public boolean isPowerOfTwo(int n) {
            if (n <= 0) {
                return false;
            }
            return (n & (-n)) == n;
            // return (n & (n - 1)) == 0;
        }
    }
}