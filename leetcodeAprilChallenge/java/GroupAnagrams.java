import java.util.*;

/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/528/week-1/3288/
Given an array of strings, group anagrams together.
All inputs will be in lowercase.
The order of your output does not matter.

1) Use hashmap to store sorted string form as key and a list containing the real strings as value
Time Complexity: O(NK lg K) where N is the number of strings and K is the max length of a string.
The outer loop has complexity of O(N) as we iterate through each string. Then, we sort each string in O(KlgK) time.

2) Use hashmap to store integer value of a string as key and a list containing the real strings as value
Time Complexity: O(N K) where N is the number of strings and K is the max length of a string.
The outer loop has complexity of O(N) as we iterate through each string. Then, we convert each string to integer using primes in O(K) time.
use primes to generate a unique sum per unique string

*/

public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return res;
        }
        Map<String, List<String>> hm = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String sortedStr = new String(arr);
            if (hm.containsKey(sortedStr)) {
                List<String> list = hm.get(sortedStr);
                list.add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                hm.put(sortedStr, list);
            }
        }
        List<List<String>> valueList = new ArrayList<List<String>>(hm.values());
        return valueList;
    }

    public static List<List<String>> groupAnagramsInteger(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return res;
        }
        HashMap<Integer, List<String>> hm = new HashMap<>();
        for (String str : strs) {
            int score = score(str);
            List<String> list = hm.get(score);
            if (list == null) {
                list = new ArrayList<>();
                hm.put(score, list);
                res.add(list);
            }
            list.add(str);
        }
        return res;
    }

    private static int score(String str) {
        // first 26 primes for 26 alphabets
        int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
                101 };
        int score = 1;
        for (char c : str.toCharArray()) {
            score *= primes[c - 'a'];
        }
        return score;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String tokens = sc.nextLine();
        String[] array = tokens.split(" ");
        System.out.println(groupAnagramsInteger(array));
        sc.close();
    }
}
