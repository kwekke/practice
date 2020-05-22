
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/537/week-4-may-22nd-may-28th/3337/
Given a string, sort it in decreasing order based on the frequency of characters.
===================================================================================================
1) use a max heap as the data structure where frequency is used to sort
As printing, remove and downHeapify

Time Complexity: O(mlgm) where m is the number of unique characters 
Space Compelxity: O(m) 

2) Count frequency of each character in an array.
iterate through the freq array, finding the max each time until all characters have been taken.
Since we the length of the freq array is fixed at 128 despite there being only 52 alphabet characters, 
the time and space complexities wrt length of freq array is constant
Time Complexity: O(n) where n is length of string 
Space Complexity: O(n)
*/
import java.util.*;

public class SortCharactersByFrequency {
    ArrayList<Pair> heap;
    HashMap<Character, Pair> map;

    public String frequencySort(String s) {
        heap = new ArrayList<>();
        map = new HashMap<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            Pair p = map.getOrDefault(arr[i], null);
            if (p != null) {
                p.incr();
            } else {
                p = new Pair(arr[i]);
                heap.add(p);
                map.put(arr[i], p);
            }
        }

        // sort
        // last parent
        int last = s.length() / 2 - 1;
        for (int i = last; i >= 0; i--) {
            downHeapify(i);
        }

        StringBuilder ans = new StringBuilder();

        while (heap.size() != 0) {
            Pair p = remove();
            int freq = p.freq;
            char ch = p.c;
            for (int i = 0; i < freq; i++) {
                ans.append(ch);
            }
        }
        return ans.toString();
    }

    private Pair remove() {
        Pair p = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downHeapify(0);
        return p;
    }

    private void swap(int i, int j) {
        Pair p1 = heap.get(i);
        Pair p2 = heap.get(j);
        heap.set(i, p2);
        heap.set(j, p1);
    }

    private void downHeapify(int s) {
        int tmp = s;
        int li = 2 * s + 1;
        int ri = 2 * s + 2;

        if (li < heap.size() && heap.get(li).freq > heap.get(tmp).freq) {
            tmp = li;
        }
        if (ri < heap.size() && heap.get(ri).freq > heap.get(tmp).freq) {
            tmp = ri;
        }

        if (tmp != s) {
            swap(tmp, s);
            downHeapify(tmp);
        }
    }

    class Pair {
        Character c;
        int freq;

        public Pair(Character c) {
            this.c = c;
            this.freq = 1;
        }

        public void incr() {
            this.freq++;
        }
    }

    public String frequencySort2(String s) {

        if (s.isEmpty() || s.length() == 0)
            return s;

        int[] arr = new int[128];
        char[] cA = s.toCharArray();

        for (char c : cA)
            arr[c]++;

        char[] result = new char[s.length()];
        for (int i = 0; i < s.length();) {
            int max = 0;
            int index = 0;
            for (int j = 0; j < 128; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                    index = j;
                }
            }
            while (arr[index] > 0) {
                result[i++] = (char) index;
                arr[index]--;
            }
        }
        return new String(result);
    }

}
