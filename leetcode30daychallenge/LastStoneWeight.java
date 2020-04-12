import java.util.*;
/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/529/week-2/3297/
We have a collection of stones, each stone has a positive integer weight.
Each turn, we choose the two heaviest stones and smash them together.  
Suppose the stones have weights x and y with x <= y.  
The result of this smash is:
If x == y, both stones are totally destroyed;
If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)


1) Heap
10ms
initialise max heap - O(N)
iterate until heap has 1 or less items
insert - O(lg N)
extractMax - O(lg N)
worst case N iterations * 3 lg N = O(N lg N)
Total time complexity: O(N lg N)

2) PQ
1ms
Initialise pq: N inserts * lg N - O(N lg N)
iterate until pq has 1 or less items:
insert/add - O(lgN)
poll - O(lgN)
worst case N iterations * 3 lg N = O(N lg N)
Total time complexity: O(N lg N)

3) bucket
0ms
Counting sort: O(N)
1. linear search for max - O(N)
2. iterate through all stones, putting them into buckets - O(N)

iterate until all buckets are emptied - O(N)
from right to left, 
1. find biggest weight - O(1) 
2. mod 2 the number of stones of biggest weight bc          - O(1)
    2 stones of the highest weight can cancel out each other
3. find 2nd biggest weight (which is current weight) - worst case O(N)        
4. add remainder of biggest - 2nd biggest.  - O(1)
    4a. if remainder is smaller than 2nd biggest, then increase the respective bucket and the biggest becomes 0
    4b. if remainder is between biggest and 2nd biggest, then the biggest becomes the remainder

all buckets are visited once
Total time complexity: O(N)
*/

public class LastStoneWeight {
    public static int lastStoneWeightHeap(int[] stones) {
        BinaryHeap heap = new BinaryHeap(stones);
        while (heap.size() >= 2) {
            int a = heap.extractMax();
            int b = heap.extractMax();
            heap.insert(a - b);
        }
        return heap.extractMax();
    }

    public static int lastStoneWeightPQ(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        for (int stone : stones) {
            pq.add(stone);
        }
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a - b > 0) {
                pq.add(a - b);
            }
        }
        if (pq.size() == 0) {
            return 0;
        }
        return pq.poll();
    }

    public static int lastStoneWeight(int[] stones) {
        // Set up the bucket array.
        int maxWeight = stones[0];
        for (int stone : stones) {
            maxWeight = Math.max(maxWeight, stone);
        }
        int[] buckets = new int[maxWeight + 1];

        // Bucket sort.
        for (int weight : stones) {
            buckets[weight]++;
        }

        // Scan through the buckets.
        int biggestWeight = 0;
        int currentWeight = maxWeight;
        while (currentWeight > 0) {
            if (buckets[currentWeight] == 0) {
                currentWeight--;
            } else if (biggestWeight == 0) {
                // find biggest weight stone
                // 2 stones of the heaviest weight cancel out each other
                buckets[currentWeight] %= 2;
                if (buckets[currentWeight] == 1) {
                    biggestWeight = currentWeight;
                }
                currentWeight--;
            } else {
                buckets[currentWeight]--;
                if (biggestWeight - currentWeight <= currentWeight) {
                    buckets[biggestWeight - currentWeight]++;
                    biggestWeight = 0;
                } else {
                    biggestWeight -= currentWeight;
                }
            }
        }
        return biggestWeight;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 10, 4, 2, 10 };
        System.out.println(lastStoneWeightPQ(arr));
    }
}

class BinaryHeap {
    private ArrayList<Integer> A;
    private int BinaryHeapSize;

    BinaryHeap() {
        A = new ArrayList<Integer>();
        A.add(0); // dummy
        BinaryHeapSize = 0;
    }

    BinaryHeap(int[] array) {
        CreateHeap(array);
    }

    int parent(int i) {
        return i >> 1;
    } // shortcut for i/2, round down

    int left(int i) {
        return i << 1;
    } // shortcut for 2*i

    int right(int i) {
        return (i << 1) + 1;
    } // shortcut for 2*i + 1

    void shiftUp(int i) {
        while (i > 1 && A.get(parent(i)) < A.get(i)) {
            int temp = A.get(i);
            A.set(i, A.get(parent(i)));
            A.set(parent(i), temp);
            i = parent(i);
        }
    }

    void insert(int key) {
        BinaryHeapSize++;
        if (BinaryHeapSize >= A.size())
            A.add(key);
        else
            A.set(BinaryHeapSize, key);
        shiftUp(BinaryHeapSize);
    }

    void shiftDown(int i) {
        while (i <= BinaryHeapSize) {
            int maxV = A.get(i), max_id = i;

            // compare value of this node with its left subtree, if possible
            if (left(i) <= BinaryHeapSize && maxV < A.get(left(i))) {
                maxV = A.get(left(i));
                max_id = left(i);
            }
            // now compare with its right subtree, if possible
            if (right(i) <= BinaryHeapSize && maxV < A.get(right(i))) {
                maxV = A.get(right(i)); // can remove this statement since biggest value already found, i.e
                                        // maxV no longer needed
                max_id = right(i);
            }

            if (max_id != i) {
                int temp = A.get(i);
                A.set(i, A.get(max_id));
                A.set(max_id, temp);
                i = max_id;
            } else
                break;
        }
    }

    // Question: what happens if heap is empty?
    int extractMax() {
        int maxV = A.get(1);

        A.set(1, A.get(BinaryHeapSize));
        BinaryHeapSize--; // virtual decrease
        shiftDown(1);
        System.out.println(maxV);
        return maxV;
    }

    void CreateHeapSlow(int[] arr) { // the O(N log N) version, array arr is 0-based
        A = new ArrayList<Integer>();
        A.add(0); // dummy, this BinaryHeap is 1-based
        for (int i = 1; i <= arr.length; i++)
            insert(arr[i - 1]);
    }

    void CreateHeap(int[] arr) { // the O(N) version, array arr is 0-based
        BinaryHeapSize = arr.length;
        A = new ArrayList<Integer>();
        A.add(0); // dummy, this BinaryHeap is 1-based
        for (int i = 1; i <= BinaryHeapSize; i++) // copy the content
            A.add(arr[i - 1]);
        for (int i = parent(BinaryHeapSize); i >= 1; i--)
            shiftDown(i);
    }

    ArrayList<Integer> HeapSort(int[] arr) { // array arr is 0-based
        CreateHeap(arr);
        int N = arr.length;
        for (int i = 1; i <= N; i++)
            A.set(N - i + 1, extractMax());
        return A; // ignore the first index 0
    }

    int size() {
        return BinaryHeapSize;
    }

    boolean isEmpty() {
        return BinaryHeapSize == 0;
    }

}
