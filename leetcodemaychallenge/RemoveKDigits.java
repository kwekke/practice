
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/535/week-2-may-8th-may-14th/3328/
Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
================================================================================================================================================
idea: when deciding to remove a digit from 2 adjacent digits, remove the larger one regardless the position
1) Stack
"stack" implemented here is to ensure that digits are considered in the right order. 
if the previous number is larger than the current number, then we remove the previous number by decrementing top and k
then we add the current number to the stack
repeat until the string has been iterated through
Then remove leading '0's
Time Complexity: O(n)
Space Complexity: O(n)

2) Iterative
Initial idea using a StringBuilder but can be improved to the first implementation
iterate through the string k times, removing one character each time. 
To remove a character, compare between two adjacent characters. Remove the larger digit. 
Note that we remove from the left to the right because we need to output an increasing sequence of digits as far as k allows.
Time Complexity: O(kn)
Space Complexity: O(n)
*/
import java.util.*;

public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        int newLength = num.length() - k;
        char[] stack = new char[num.length()];
        int top = -1;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (top >= 0 && stack[top] > c && k > 0) {
                top--;
                k--;
            }
            stack[++top] = c;
        }

        int start = 0;
        while (start < newLength && stack[start] == '0') {
            start++;
        }

        return start == newLength ? "0" : new String(stack, start, newLength - start);
    }

    public String removeKdigits2(String num, int k) {
        int len = num.length();
        if (k == 0) {
            return num;
        }
        if (len <= k) {
            return "0";
        }
        StringBuilder sb = new StringBuilder(num);
        for (int i = 0; i < k; i++) {
            int j = 0;
            while (j < sb.length() - 1 && sb.charAt(j) <= sb.charAt(j + 1)) {
                j++;
            }
            sb.delete(j, j + 1);
        }
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.delete(0, 1);
        }
        if (sb.length() == 0) {
            return "0";
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num = sc.nextLine();
        int k = sc.nextInt();
        sc.close();
        RemoveKDigits rkd = new RemoveKDigits();
        System.out.println(removeKDigits(num, k));
    }

}