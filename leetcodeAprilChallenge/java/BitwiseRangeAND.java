/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/531/week-4/3308/
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
=======================================================================================================================
1) Shift logical right
1. Find position of Most Significant Bit (MSB) in both numbers.
2. If positions of MSB are different, then result is 0.
3. If positions are same. Let positions be msb_p.
 3b. return 2^msb_p.

For step 2, if positions of MSB are different, then the smallest int with MSBb, 100..000, the '0's will cancel out whatever a is
For step 3, if positions of MSB are same, then by intuition, all the digits on the right of MSB will cancel out 
because consecutive numbers between them WILL contain '0' at each digit, thus their bitwise AND will be '0'.
Thus, the result would be the 2^MSB

2) LSB
for a <= b,
1. Flip the LSB of b.
2. And check if the new number is in range(a < number < b) or not
if the number greater than 'a' again flip LSB
if it is not then that's the answer
(Source: https://www.geeksforgeeks.org/bitwise-and-or-of-a-range/)
*/

import java.util.*;

public class BitwiseRangeAND {
    public static int rangeBitwiseAndSLR(int m, int n) {
        int shift = 0;
        while (m < n) { // when equals 0, stop
            m >>= 1;
            n >>= 1;
            shift++;
        }
        return m << shift;
    }

    public static int rangeBitwiseAnd(int m, int n) {
        while (n > m) {
            n = n & (n - 1);
        }
        return m & n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.close();
        System.out.println(rangeBitwiseAndSLR(m, n));
    }
}