import java.io.*;
import java.util.*;
import java.lang.*;

/*
https://leetcode.com/explore/other/card/30-day-leetcoding-challenge/528/week-1/3284/
A happy number is a number defined by the following process:
Starting with any positive integer, 
replace the number by the sum of the squares of its digits,
and repeat the process until the number equals 1 (where it will stay),
or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy numbers.

Happy Numbers on wikipedia.
base-10 happy numbers ending with 4 will go to cycle. 

1) iterative look for 1 or 4
2) recursive look for 1 or 4
3) recursive add n to set, if encounter a number in set = cycle => false
this method takes more space so i omit it.
*/
public class HappyNumber {

    public static boolean solve(int n) {
        // iterative
        while (n != 1 && n != 4) {
            int sum = 0;
            while (n >= 10) {
                sum += Math.pow(n % 10, 2);
                n /= 10;
            }
            sum += Math.pow(n, 2);
            n = sum;
        }
        if (n == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean solveRecursive(int n) {
        if (n == 1) {
            return true;
        } else if (n == 4) {
            return false;
        } else {
            int sum = 0;
            while (n >= 10) {
                sum += ((n % 10) * (n % 10));
                n = n / 10;
            }
            sum += n * n;
            return solveRecursive(sum);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        System.out.println(solve(number));
        sc.close();
    }
}