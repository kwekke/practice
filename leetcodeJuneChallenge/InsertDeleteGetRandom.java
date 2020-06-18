/*
https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3358/

Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
============================================================================================================
1) use a map and a list
map stores mapping from val to node in list
Time Complexity: O(1) ; 35 ms
Space Complexity: O(n)
2) uses map and array
replaces the removed index in the array with the value of the last index in the array.
Time Complexity: O(1) ; 6-7 ms
Space Complexity: O(n)
*/

import java.util.*;

public class InsertDeleteGetRandom {
    class RandomizedSet {
        Random rand = new Random();
        HashMap<Integer, Node> hm;
        ArrayList<Node> list;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            hm = new HashMap<Integer, Node>();
            list = new ArrayList<Node>();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain
         * the specified element.
         */
        public boolean insert(int val) {
            if (hm.containsKey(val)) {
                return false;
            } else {
                Node n = new Node(val);
                hm.put(val, n);
                list.add(n);
                return true;
            }
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified
         * element.
         */
        public boolean remove(int val) {
            if (!(hm.containsKey(val))) {
                return false;
            } else {
                list.remove(hm.get(val));
                hm.remove(val);
                return true;
            }
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return list.get(rand.nextInt(list.size())).v;
        }

        class Node {
            int v;

            Node(int v) {
                this.v = v;
            }
        }
    }

    class RandomizedSet2 {
        Random r = new Random();
        Map<Integer, Integer> m = new HashMap<>();
        int[] arr = new int[1024 * 8];
        int size;

        public RandomizedSet() {}

        public boolean insert(int val) {
            if (m.containsKey(val))
                return false;
            m.put(val, size);
            arr[size++] = val;
            return true;
        }

        public boolean remove(int val) {
            Integer index = m.remove(val);
            if (index == null)
                return false;
            if (--size != index) {
                arr[index] = arr[size];
                m.put(arr[size], index);
            }
            return true;
        }

        public int getRandom() {
            return arr[r.nextInt(size)];
        }
    }
}