
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3316/
You are a product manager and currently leading a team to develop a new product. 
Unfortunately, the latest version of your product fails the quality check. 
Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. 
Implement a function to find the first bad version. You should minimize the number of calls to the API.
===============================================================================================================================================
1) binary search

2) binary search w/o min

3) binary search with alternative implementation
*/
import java.util.*;

public class FirstBadVersion {
    int i;

    FirstBadVersion(int i) {
        this.i = i;
    }

    boolean isBadVersion(int n) {
        return (n >= i);
    }

    public int firstBadVersion(int n) {
        int l = 1;
        int r = n;
        int min = Integer.MAX_VALUE;
        int mid = 1;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (isBadVersion(mid)) {
                min = Math.min(min, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return min;
    }

    public int firstBadVersion2(int n) {
        int l = 1;
        int r = n;
        int mid = 1;
        while (l < r) {
            mid = l + (r - l) / 2;
            if (isBadVersion(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public int firstBadVersion3(int n) {
        return helper(1, n);
    }

    // use binary search
    private int helper(int up, int down) {
        int mid = up + (down - up) / 2;
        if (up >= down) {
            return up;
        }
        if (isBadVersion(mid)) {
            return helper(up, mid);
        } else {
            return helper(mid + 1, down);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.close();
        FirstBadVersion fbv = new FirstBadVersion(b);
        System.out.println(fbv.firstBadVersion(a));
    }
}
