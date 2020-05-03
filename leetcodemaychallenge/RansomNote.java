/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3318/
Given an arbitrary ransom note string and another string containing letters from all the magazines,
 write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.
====================================================================================================================================
1) use hashmap to store the characters in magazine and their counts

2) use array instead
assumes magazine is sorted

*/

import java.util.*;

public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> hm = new HashMap<>();
        for (Character c : magazine.toCharArray()) {
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if (hm.getOrDefault(c, 0) == 0) {
                return false;
            } else {
                hm.put(c, hm.get(c) - 1);
            }
        }
        return true;
    }

    public boolean canConstruct2(String ransom, String magazine) {
        if (magazine.length() < ransom.length())
            return false;
        int caps[] = new int[26];
        for (char c : ransom.toCharArray()) {
            int index = magazine.indexOf(c, caps[c - 'a']);
            if (index == -1)
                return false;
            caps[c - 97] = index + 1;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ransomNote = sc.nextLine();
        String magazine = sc.nextLine();
        sc.close();
        RansomNote rn = new RansomNote();
        System.out.println(rn.canConstruct(ransomNote, magazine));
    }
}