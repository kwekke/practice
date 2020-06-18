/*
https://leetcode.com/explore/featured/card/june-leetcoding-challenge/539/week-1-june-1st-june-7th/3352/
Suppose you have a random list of people standing in a queue. 
Each person is described by a pair of integers (h, k), 
where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. 
Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.
Example
Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

Hint #1  
What can you say about the position of the shortest person?
If the position of the shortest person is i, how many people would be in front of the shortest person?

Hint #2  
Once you fix the position of the shortest person, what can you say about the position of the second shortest person?
========================================================================================================================
1) sort people to increasing height, and then by decreasing k
if the position of the shortest person is i, then there would be i number of empty positions in the queue to be filled with taller people.
repeat this process until all people are in the queue.
Time Complexity: O(n^2) ; 17 ms
Space Complexity: O(n)

2) sort people to decreasing height, and then by increasing k
assign starting from the tallest person to the queue. insert the next tallest by its k value.
[[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
->
[[7,0],[7,1],[6,1],[5,0],[5,2],[4,4]]
so it inserts (7,0), (7,1), ...

Time Complexity: O(nlgn) ; 2 ms
Space Complexity: O(n)
*/

import java.util.*;

public class QueueReconstructionByHeight {
    class Solution1 {
        public int[][] reconstructQueue(int[][] people) {
            int[][] q = new int[people.length][2];
            for (int i = 0; i < q.length; i++) {
                q[i][0] = -1;
            }
            Arrays.sort(people, (x, y) -> {
                if (x[0] == y[0]) {
                    return y[1] - x[1];
                } else {
                    return x[0] - y[0];
                }
            });
            for (int i = 0; i < people.length; i++) {
                int j = 0;
                int ind = 0;
                for (j = 0; j < people.length && ind < people[i][1] + 1; j++) {
                    if (q[j][0] == -1) {
                        ind++;
                    }
                }
                q[j - 1] = people[i];
            }
            return q;
        }
    }

    class Solution {
        public int[][] reconstructQueue(int[][] people) {
            quickSort(people, 0, people.length - 1);
            ArrayList<int[]> res = new ArrayList<>();
            for (int[] person : people) {
                res.add(person[1], person);
            }

            int i = 0;
            for (int[] r : res) {
                people[i++] = r;
            }

            return people;
        }

        private void quickSort(int[][] people, int left, int right) {
            if (left >= right)
                return;
            int[] pivot = people[left];
            int i = left;
            int j = right;
            while (i < j) {
                while ((people[j][0] < pivot[0] || (people[j][0] == pivot[0] && people[j][1] >= pivot[1])) && i < j) {
                    j--;
                }
                while ((people[i][0] > pivot[0] || (people[i][0] == pivot[0] && people[i][1] <= pivot[1])) && i < j) {
                    i++;
                }
                if (i < j) {
                    int[] tmp = people[i];
                    people[i] = people[j];
                    people[j] = tmp;
                }
            }
            // Swap pivot with left(start)
            people[left] = people[i];
            people[i] = pivot;
            quickSort(people, left, i - 1);
            quickSort(people, i + 1, right);
        }
    }
}