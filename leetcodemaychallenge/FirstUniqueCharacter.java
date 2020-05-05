/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3320/
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
Note: You may assume the string contain only lowercase letters.
=====================================================================================================================
1) two passes
Iterate through string s, incrementing an array representing the count of characters
Iterate through s again, return the pointer if the count is 1.

2) one pass
Iterate through all lowercase letters, checking if the first index and last index are the same
if it is -1, the letter does not exist in the string
otherwise, then it is a unique character.
maintain the minimum index of unique characters. 
*/

import java.util.*;

public class FirstUniqueCharacter {
    public int firstUniqChar(String s) {
        char[] arr = s.toCharArray();
        int[] a = new int[26];
        for (int i = 0; i < a.length; i++) {
            a[i] = 0;
        }
        for (int i = 0; i < arr.length; i++) {
            a[arr[i] - 97]++;
        }

        for (int i = 0; i < arr.length; i++) {
            if (a[arr[i] - 97] == 1) {
                return i;
            }
        }
        return -1;
    }

    public int firstUniqChar2(String s) {
        int ans = s.length();

        for (char c = 'a'; c <= 'z'; c++) {
            if (s.indexOf(c) == s.lastIndexOf(c) && s.indexOf(c) != -1) {
                ans = Math.min(ans, s.indexOf(c));
            }
        }
        return ans == s.length() ? -1 : ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();
        FirstUniqueCharacter fuc = new FirstUniqueCharacter();
        System.out.println(fuc.firstUniqChar(s));
    }
}
