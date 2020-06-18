/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/538/week-5-may-29th-may-31st/3345/
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
(Here, the distance between two points on a plane is the Euclidean distance.)
You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

Note:
1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000

=====================================================================================================================
1) sort by distance
take the first k points
Time Complexity: O(nlgn)
Space Complexity: O(k)

2) Quick select partition
Find the pivot that splits K points of distance which are the smallest 
Time Complexity: O(n)
Space Complexity: O(k)
*/

import java.util.*;

public class KClosestPointsToOrigin {
    class Solution1 {
        public int[][] kClosest(int[][] points, int K) {
            Arrays.sort(points, new Comparator<int[]>() {
                public int compare(int[] a1, int[] a2) {
                    int x1 = a1[0];
                    int y1 = a1[1];
                    int d1 = x1 * x1 + y1 * y1;

                    int x2 = a2[0];
                    int y2 = a2[1];
                    int d2 = x2 * x2 + y2 * y2;

                    return d1 - d2;
                }
            });
            int[][] res = new int[K][2];
            for (int i = 0; i < K; i++) {
                res[i] = points[i];
            }
            return res;
        }
    }

    class Solution2 {

        public int[][] kClosest(int[][] points, int K) {

            int len = points.length;
            int left = 0;
            int right = len - 1;

            while (left <= right) {
                int partitionIndex = partition(points, left, right);
                if (partitionIndex == K) {
                    break;
                }
                if (partitionIndex < K) {
                    left = partitionIndex + 1;
                } else {
                    right = partitionIndex - 1;
                }
            }

            return Arrays.copyOfRange(points, 0, K);
        }

        public int partition(int[][] points, int left, int right) {
            int[] pivot = points[left];

            while (left < right) {
                while (left < right && compare(points[right], pivot) <= 0) {
                    right--;
                }
                points[left] = points[right];
                while (left < right && compare(points[left], pivot) >= 0) {
                    left++;
                }
                points[right] = points[left];
            }
            points[left] = pivot;

            return left;
        }

        public int compare(int[] point1, int[] point2) {
            return (point2[1] * point2[1] + point2[0] * point2[0]) - point1[1] * point1[1] - point1[0] * point1[0];
        }
    }
}