/*
https://leetcode.com/explore/featured/card/june-leetcoding-challenge/539/week-1-june-1st-june-7th/3350/

Write a function that reverses a string. The input string is given as an array of characters char[].

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

You may assume all the characters consist of printable ascii characters.
Hint #1  
The entire logic for reversing a string is based on using the opposite directional two-pointer approach!
==================================================================================================
1) for loop, swap two ends of the string
Time Complexity: O(n) ; 1ms
Space Complexity: O(1)

2) use 2 pointers, swap two ends of the string
Time Complexity: O(n) ; 0ms
Space Complexity: O(1)
*/

import java.util.*;

public class ReverseString {
    class Solution {
        public void reverseString(char[] s) {
            for (int i = 0; i < s.length / 2; i++) {
                char tmp = s[i];
                s[i] = s[s.length - i - 1];
                s[s.length - i - 1] = tmp;
            }
        }
    }

    class Solution2 {
        public void reverseString(char[] s) {
            int left = 0;
            int right = s.length - 1;
            while (left < right) {
                char temp = s[left];
                s[left] = s[right];
                s[right] = temp;
                left++;
                right--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.next();
        sc.close();

        Solution s1 = new Solution();
        char[] s = word.toCharArray();
        s1.reverseString(s);
        System.out.println(s.toString());

        Solution2 s2 = new Solution2();
        s2.reverseString(s);
        System.out.println(s.toString());
    }
}