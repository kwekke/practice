/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/530/week-3/3301/
Given a string containing only three types of characters: '(', ')' and '*',
write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
==============================================================================================================

1) One pass (greedy)
Think of balancing the number of ( and ). With the inclusion of *, then can increase the number of +/-

Initialise two counters l and r denoting the smallest and largest possible number of open left brackets
after processing the current character in the string
if we encounter '(', then l++, r++
if we encounter ')', then l--, r--
if we encounter '*', then l--, r++ because * could have been a left bracket (so increase max), and could have been a right backet (so decrease min)

If in any point in the loop, r < 0 , it means that there have been too many right brackets at this point
and it cannot be valid.
After every iteration, we reset l to 0 if it is negative, because we take into account for *s. 
If the substring of everything so far to current character is valid, then we carry on the algorithm on the right substring.
At the end, we check if we have exactly 0 open left brackets. we don't need to check for r because *s can take the form of empty string.

Time Complexity: O(N)
Space Complexity: O(N)


*/

import java.util.*;

public class ValidParenthesisString {
    public static boolean checkValidString(String s) {
        char[] arr = s.toCharArray();
        int l = 0;
        int r = 0;

        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];

            if (c == '(') {
                l++;
                r++;
            } else if (c == ')') {
                l--;
                r--;
            } else if (c == '*') {
                l--;
                r++;
            }
            if (r < 0) {
                break;
            }
            l = Math.max(l, 0);
        }
        return l == 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();
        System.out.println(checkValidString(s));
    }
}