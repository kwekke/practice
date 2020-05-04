/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3319/
Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.
==================================================================================================================================
1) run through all the bits of num, adding the comlement bit to the ans variable
and shifting the num and one after each time

2) counting (my initial method)
2^n - 1 -num
*/

import java.util.*;

public class NumberComplement {
    public int findComplement(int num) {
        int res = 0;
        int one = 1;
        while (num != 0) {
            if ((num & 1) == 0) {
                res |= one;
            }
            one <<= 1;
            num >>= 1;
        }
        return res;
    }

    public int findComplement2(int num) {
        if (num <= 1) {
            return 0;
        }
        int i = 0;
        int a = 1;
        while (a <= num) {
            a *= 2;
            i++;
            if (a == num) {
                return a - 1;
            }
        }
        return num ^ (a - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.close();
        NumberComplement nc = new NumberComplement();
        System.out.println(nc.findComplement(num));
    }
}
