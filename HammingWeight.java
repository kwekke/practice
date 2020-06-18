/*
https://leetcode.com/problems/number-of-1-bits/
Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).

Example 1:
Input: 00000000000000000000000000001011
Output: 3
Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.

Example 2:
Input: 00000000000000000000000010000000
Output: 1
Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.

Example 3:
Input: 11111111111111111111111111111101
Output: 31
Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.

Note:
Note that in some languages such as Java, there is no unsigned integer type. In this case, the input will be given as signed integer type and should not affect your implementation, as the internal binary representation of the integer is the same whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 3 above the input represents the signed integer -3.
 
Follow up:
If this function is called many times, how would you optimize it?

============================================================================================================================================================
1) naive
Check if each bit equals 1 and return the sum:
timeout

2) optimized naive
After the first right shift, 31 “0”s’ left and the function can return count immediately:
there is no need adding to count when n becomes “0” after one certain right shift. 
timeout

3) Loop and flip
Maintain a mask and iterate through 32 times
Time Complexity: O(1) since n is 32-bit integer ; 0ms
Space Complexity: O(1)


4) Brian Kernighan’s way (bit manipulation)
Repeatedly flip the least-significant 11-bit of the number to 00, and add 11 to the sum. 
As soon as the number becomes 00, we know that it does not have any more 11-bits,
n & (n – 1) will clear the last significant bit set, 
Hence loop will go through as many iterations as there are set bits, and when n becomes zero, count is exactly the answer.
Time Complexity: O(1) since n is 32-bit integer ; 0ms
Space Complexity: O(1)

*/

public class HammingWeight {
    public int hammingWeight1(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    public int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n >>= 1;
        }
        return count;
    }

    public int hammingWeight3(int n) {
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                count++;
            }
            mask <<= 1;
        }
        return count;
    }

    public int hammingWeight4(int n) {
        int count = 0;
        while (n != 0) {
            n &= n - 1;
            count++;
        }
        return count;
    }

}
