/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/529/week-2/3291/
Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

1) Use stacks - O(M + N)
for each string, maintain a stack containing characters in the string.
pop if '#', push otherwise
at the end, compare both stacks. 

2) Iterating from back of each string -O(Min(M,N))
if #, increment backspace counter
if not, decrement backspace counter if counter is positive
otherwise the character to compare is found
repeat until string has been completely ran through
loop ends when finish iterating through at least one string, or if different chars are detected. 

*/

import java.util.*;

public class BackspaceStringCompare {
    public static boolean backspaceCompareStack(String S, String T) {
        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();

        for (Character c : S.toCharArray()) {
            if (c.equals('#')) {
                if (!s1.empty()) {
                    s1.pop();
                }
            } else {
                s1.push(c);
            }
        }
        for (Character c : T.toCharArray()) {
            if (c.equals('#')) {
                if (!s2.empty()) {
                    s2.pop();
                }
            } else {
                s2.push(c);
            }
        }

        while (!s1.empty() && !s2.empty()) {
            if (!(s1.pop() == (s2.pop()))) {
                return false;
            }
        }
        if (s1.empty() && s2.empty()) {
            return true;
        }
        return false;
    }

    public static boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1;
        int j = T.length() - 1;
        int b1 = 0;
        int b2 = 0;
        while (i >= 0 || j >= 0) {
            // finds next char in S
            while (i >= 0 && (S.charAt(i) == '#' || b1 > 0)) {
                if (S.charAt(i) == '#') {
                    b1++;
                } else {
                    b1--;
                }
                i--;
            }
            // finds next char in T
            while (j >= 0 && (T.charAt(j) == '#' || b2 > 0)) {
                if (T.charAt(j) == '#') {
                    b2++;
                } else {
                    b2--;
                }
                j--;
            }

            // if the entire of both strings have been compared, return true
            if (i == -1 && j == -1) {
                return true;
            }
            // if one string has been completely iterated through
            // but we can find one char in the other, return false
            if (i == -1 || j == -1) {
                return false;
            }

            // if the characters are different, return false
            if (S.charAt(i) != T.charAt(j)) {
                return false;
            }

            // compare next char
            i--;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.nextLine();
        String T = sc.nextLine();
        sc.close();
        System.out.println(backspaceCompare(S, T));
    }
}
