import java.util.*;

/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/528/week-1/3289/
Given an integer array arr, count element x such that x + 1 is also in arr.
If there're duplicates in arr, count them seperately.
 
Time Complexity: O(nlgn)
Sort arr - O(nlgn) optimized by java 
iterate through array - O(n) 
Maintain the number of repeats of the smallest integer. 
If the next integer is the same as current smallest integer, then increment the number of repeats.
if not, the next integer is either larger by 1 or larger by more than 1. 
if next integer is larger by 1, then increment count with number of repeats found so far.
and then reupdate the current smallest integer and its number of repeats.
if next integer is larger by more than 1, then reupdate the current smallest integer and its number of repeats. 
*/

public class CountingElements {
    public static int countElements(int[] arr) {
        if (arr == null || arr.length == 1) {
            return 0;
        }
        Arrays.sort(arr);
        int count = 0;
        int cur = arr[0];
        int repeats = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == cur) {
                repeats++;
            } else {
                if (cur == arr[i] - 1) {
                    count += repeats;
                }
                cur = arr[i];
                repeats = 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] tokens = line.split(" ");
        int[] array = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            array[i] = Integer.parseInt(tokens[i]);
        }
        System.out.println(countElements(array));
        sc.close();
    }
}