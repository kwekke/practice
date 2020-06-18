/*
https://leetcode.com/explore/featured/card/june-leetcoding-challenge/539/week-1-june-1st-june-7th/3349/
There are 2N people a company is planning to interview. 
The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].
Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.

Note:
1 <= costs.length <= 100
It is guaranteed that costs.length is even.
1 <= costs[i][0], costs[i][1] <= 1000
================================================================================================================================
1) greedy
sort differences in descending order. 
keep count of the number of people going to each city.
when iterating for all N pairs, take the smaller cost until the quota for either city has been fulfilled. 
Time Complexity: O(nlgn)
Space Complexity: O(1)

2) greedy. sort by differences in ascending order
Time Complexity: O(nlgn)
Space Complexity: O(1)

3) quick select


*/

import java.util.*;

public class TwoCityScheduling {
    class Solution {
        public int twoCitySchedCost(int[][] costs) {
            int ans = 0;
            Arrays.sort(costs, (x, y) -> Math.abs(y[0] - y[1]) - Math.abs(x[0] - x[1]));
            int a = 0;
            int b = 0;
            for (int i = 0; i < costs.length; i++) {
                if (a == costs.length / 2) {
                    ans += costs[i][1];
                    b++;
                } else if (b == costs.length / 2) {
                    ans += costs[i][0];
                    a++;
                } else if (costs[i][0] < costs[i][1]) {
                    ans += costs[i][0];
                    a++;
                } else {
                    ans += costs[i][1];
                    b++;
                }
            }
            return ans;
        }
    }

    class Solution2 {
        public int twoCitySchedCost(int[][] costs) {

            Arrays.sort(costs, new Comparator<int[]>() {

                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o1[1] - (o2[0] - o2[1]);
                }
            });

            int total = 0;

            int n = costs.length / 2;

            for (int i = 0; i < n; ++i) {

                total += costs[i][0] + costs[i + n][1];
            }

            return total;

        }
    }

    class Solution3 {
        public int twoCitySchedCost(int[][] costs) {
            quickSelect(costs, costs.length / 2);
            int total = 0;
            for (int i = 0; i < costs.length / 2; i++) {
                total += costs[i][0];
            }
            for (int i = costs.length / 2; i < costs.length; i++) {
                total += costs[i][1];
            }
            return total;
        }

        public void quickSelect(int[][] data, int k) {
            int l = 0;
            int r = data.length - 1;
            int end = -1;
            int currentK = k;
            do {
                end = quickSelect(data, currentK, l, r);
                if (end < k) {
                    currentK = k - end - 1;
                    l = end + 1;
                } else {
                    r = end - 1;
                }
            } while (end != k && end != k - 1);
        }

        public int nextPivot(int max, int min) {
            return min + (int) (Math.random() * (max - min + 1));
        }

        public int quickSelect(int[][] data, int k, int l, int r) {
            int end = l;
            if (k < r - l + 1) {
                int pivotIndex = nextPivot(r, l);
                swap(data, l, pivotIndex);
                for (int i = l + 1; i <= r; i++) {
                    if (compare(data[l], data[i]) < 0) {
                        swap(data, ++end, i);
                    }
                }
                swap(data, end, l);
            }
            return end;
        }

        public void swap(int[][] data, int i, int j) {
            int[] temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }

        public int compare(int[] a, int[] b) {
            int comp = Integer.compare(a[1] - a[0], b[1] - b[0]);
            return comp;
        }
    }

}