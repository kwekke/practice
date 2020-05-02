/*
https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3317/
You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  
Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. 
Letters are case sensitive, so "a" is considered a different type of stone from "A".
========================================================================================================================
1) use hashset to store unique jewels
iterate through all stones checking if the stone is a jewel

2) double for loops

*/

import java.util.*;

public class JewelsAndStones {
    public int numJewelsInStones(String J, String S) {
        HashSet<Character> unique = new HashSet<>();
        for (char c : J.toCharArray()) {
            unique.add(c);
        }
        char[] arr = S.toCharArray();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (unique.contains(arr[i])) {
                count++;
            }
        }
        return count;
    }

    public int numJewelsInStones2(String J, String S) {
        if (J == null || J.length() == 0) {
            return 0;
        }
        int count = 0;
        for (Character c : S.toCharArray()) {
            for (Character m : J.toCharArray()) {
                if (m == c) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String J = sc.nextLine();
        String S = sc.nextLint();
        sc.close();
        JewelsAndStones jas = new JewelsAndStones();
        System.out.println(jas.numJewelsInStones(J, S));
    }
}
