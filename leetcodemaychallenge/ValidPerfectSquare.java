
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/535/week-2-may-8th-may-14th/3324/
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.
====================================================================================
1) Binary search
search for int m such that m * m == num
note that m is in the range of [1,  46340]
because the largest integer which is a perfect square is 46340 ^ 2
Time Complexity: O(lgn)
Space Complexity: O(1)
*/
import java.util.*;

public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        return binarySearch(1, 46340, num);
    }

    boolean binarySearch(int l, int r, int num) {
        while (l <= r) {
            int m = (l + r) / 2;
            int s = m * m;
            if (s == num) {
                return true;
            }
            if (s < num) {
                l = m + 1;
            }
            if (s > num) {
                r = m - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.close();
        ValidPerfectSquare vps = new ValidPerfectSquare();
        System.out.println(vps.isPerfectSquare(num));
    }
}
