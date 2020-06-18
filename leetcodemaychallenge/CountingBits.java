/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/537/week-4-may-22nd-may-28th/3343/
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.

Hint #1  
You should make use of what you have produced already.

Hint #2  
Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous.

Hint #3  
Or does the odd/even status of the number help you in calculating the number of 1s?
=========================================================================================================
1) Observe pattern
0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
0 1
    1 2
        1 2 2 3
                1 2  2  3  2  3  3  4  1
the range [4-7] copies range [2-3] for the first half and then copies range [2-3] incrementing one.

Maintain two pointers i and j pointing to the start of the current range and previous range respectively. 
use a variable, t, to iterate through the array. 

2) Observe pattern
0   1    2   3   4   5   6   7   8   9   10  11  12  13  14  15  16
0        1       1       2       1       2       2       3       1      (if even, bit count is same as (num / 2) th)
    1/2+1    3/2+1   5/2+1   7/2+1   9/2+1  11/2+1   13/2+1  15/2+1     (if odd, bit count is same as (num / 2)th + 1)
    0th+1    1st+1   2nd+1   3rd+1   4th+1  5th+1    6th+1   7th+1
    1        2       2       3       2      3        3       4
general formula: countBitArray[i] = countBitArray[i >> 1] + i % 2;

3) Recursive implementation
(if even, bit count is same as (num / 2) th)
(if odd, bit count is same as (num / 2)th + 1)

*/

import java.util.*;

public class CountingBits {

    public int[] countBits(int num) {
        if (num == 0) {
            return new int[1];
        }
        int[] res = new int[num + 1];
        res[1] = 1;
        int i = 2;
        int j = 1;
        int t = 0;
        while (i + t <= num) {
            if (j + t < i) {
                res[i + t] = res[j + t];
            } else {
                res[i + t] = res[j + t] + 1;
            }
            t++;
            if (t - 1 == i) {
                i *= 2;
                j *= 2;
                t = 0;
            }
        }
        return res;
    }

    public int[] countBits2(int num) {
        if (num < 0) {
            return new int[1];
        }
        int[] countBitArray = new int[num + 1];
        countBitArray[0] = 0;

        for (int i = 1; i <= num; i++) {
            // if num is even, bit count is same as num / 2
            // if odd, bit count is same as (num / 2) + 1
            countBitArray[i] = countBitArray[i >> 1] + i % 2;
        }
        return countBitArray;
    }

    public int[] countBits3(int num) {
        if (num < 0) {
            return new int[0];
        }
        int[] setbits = new int[num + 1];
        countBitsRec(num, 1, 1, setbits); // start from 1 w/ count == 1 (skip 0 - as it's 0 set bits for 0 num)
        return setbits;
    }

    private void countBitsRec(int needed, int curnum, int setbitcount, int[] setbits) {
        if (curnum > needed) { // increase cur till hit needed num
            return;
        }
        setbits[curnum] = setbitcount;
        countBitsRec(needed, curnum << 1, setbitcount, setbits); // cur << 1 == cur*2 - extend with 0
        countBitsRec(needed, (curnum << 1) + 1, setbitcount + 1, setbits); // extend with 1
    }
}
