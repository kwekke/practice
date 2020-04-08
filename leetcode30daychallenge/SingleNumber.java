import java.util.*;

/*
https://leetcode.com/explore/other/card/30-day-leetcoding-challenge/528/week-1/3283/
Given a non-empty array of integers, every element appears twice except for one. Find that single one.
Requirements: linear runtime complexity and without extra space.

Use bit manipulation to find out single number. 
A XOR A = 0, XOR is commutative and its operator in java is ^
Hence A XOR B XOR A = A XOR A XOR B = B.
*/
public class SingleNumber {

    public static int solve(int[] array) {
        int a = 0;
        for (int i : array) {
            a ^= i;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] tokens = line.split(" ");
        int[] array = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            array[i] = Integer.parseInt(tokens[i]);
        }
        System.out.println(solve(array));
        sc.close();
    }
}