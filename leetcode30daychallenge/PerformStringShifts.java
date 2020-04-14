import java.util.*;

/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/529/week-2/3299/
You are given a string s containing lowercase English letters, and a matrix shift, where shift[i] = [direction, amount]:
    direction can be 0 (for left shift) or 1 (for right shift). 
    amount is the amount by which string s is to be shifted.
    A left shift by 1 means remove the first character of s and append it to the end.
    Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
Return the final string after all operations.

1. Calculate offset and then shift array right. 
Time Complexity: O(N + M)
where N is the number of shift inputs - length of shift array
and M is the number of characters in the string. worst case, all characters have to be moved. 
Space Compexity: O(M)
extra space used was an additional array of char of M characters. 
*/
public class PerformStringShifts {
    public static String stringShift(String s, int[][] shift) {
        int offset = 0;
        for (int i = 0; i < shift.length; i++) {
            if (shift[i][0] == 0) {
                offset = offset - shift[i][1];
            } else {
                offset = offset + shift[i][1];
            }
        }
        offset %= s.length();
        if (offset < 0) {
            offset = offset + s.length();
        }

        char[] arr = s.toCharArray();
        char[] ans = new char[arr.length];
        for (int i = 0; i < offset; i++) {
            ans[i] = arr[i + (arr.length - offset)];
        }
        for (int i = offset; i < ans.length; i++) {
            ans[i] = arr[i - offset];
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] tokens = sc.nextLine().split(" ");
        sc.close();
        int[][] shift = new int[tokens.length / 2][2];
        for (int i = 0; i < tokens.length / 2; i++) {
            shift[i][0] = Integer.parseInt(tokens[2 * i]);
            shift[i][1] = Integer.parseInt(tokens[2 * i + 1]);
        }
        System.out.println(stringShift(s, shift));
    }
}