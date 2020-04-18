/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/530/week-3/3303/
Given a m x n grid filled with non-negative numbers, 
find a path from top left to bottom right which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.
======================================================================================================

1) greedy Dijkstra Priority Queue
"Note: You can only move either down or right at any point in time"
Initially did not realise the note. So i implemented Dijkstra.
Time Complexity: O(MN)
call stack size m * n
Space Complexity: O(MN)
memoisation table

2) greedy
Time Complexity: O(MN)
call stack size m * n
Space Complexity: O(MN)
memoisation table
*/

import java.util.*;

public class MinimumPathSum {
    public int minPathSumDijkstra(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        return dijkstra(grid, 0, 0, grid.length - 1, grid[0].length - 1);
    }

    int dijkstra(int[][] grid, int srcr, int srcc, int a, int b) {
        int r = grid.length;
        int c = grid[0].length;

        int dist[][] = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[srcr][srcc] = grid[srcr][srcc];
        PriorityQueue<myPair> pq = new PriorityQueue<>((x, y) -> x.d - y.d);
        pq.add(new myPair(srcr, srcc, grid[srcr][srcc]));

        while (!(pq.isEmpty())) {
            myPair p = pq.poll();
            // if (p.i == a && p.j == b) {
            // dist[p.i][p.j] = p.d;
            // break;
            // }
            // if (p.i > 0) {
            // if (dist[p.i][p.j] + grid[p.i-1][p.j] < dist[p.i-1][p.j]) {
            // pq.add(new myPair(p.i-1, p.j, dist[p.i][p.j] + grid[p.i-1][p.j]));
            // dist[p.i-1][p.j] = dist[p.i][p.j] + grid[p.i-1][p.j];
            // }
            // }
            if (p.i < r - 1) {
                if (dist[p.i][p.j] + grid[p.i + 1][p.j] < dist[p.i + 1][p.j]) {
                    pq.add(new myPair(p.i + 1, p.j, dist[p.i][p.j] + grid[p.i + 1][p.j]));
                    dist[p.i + 1][p.j] = dist[p.i][p.j] + grid[p.i + 1][p.j];
                }
            }
            // if (p.j > 0) {
            // if (dist[p.i][p.j] + grid[p.i][p.j-1] < dist[p.i][p.j-1]) {
            // pq.add(new myPair(p.i, p.j-1, dist[p.i][p.j] + grid[p.i][p.j-1]));
            // dist[p.i][p.j-1] = dist[p.i][p.j] + grid[p.i][p.j-1];
            // }
            // }
            if (p.j < c - 1) {
                if (dist[p.i][p.j] + grid[p.i][p.j + 1] < dist[p.i][p.j + 1]) {
                    pq.add(new myPair(p.i, p.j + 1, dist[p.i][p.j] + grid[p.i][p.j + 1]));
                    dist[p.i][p.j + 1] = dist[p.i][p.j] + grid[p.i][p.j + 1];
                }
            }
        }
        return dist[a][b];
    }

    public int minPathSum(int[][] grid) {
        if (grid.length == 0)
            return 0;
        int m = grid.length, n = grid[0].length;
        int[][] memo = new int[m][n];
        return helper(grid, m - 1, n - 1, memo);
    }

    public int helper(int[][] grid, int i, int j, int[][] memo) {
        if (i == 0 && j == 0)
            return grid[i][j];
        if (i < 0 || j < 0)
            return Integer.MAX_VALUE;
        if (memo[i][j] > 0)
            return memo[i][j];

        memo[i][j] = grid[i][j] + Math.min(helper(grid, i - 1, j, memo), helper(grid, i, j - 1, memo));
        return memo[i][j];
    }
}

class myPair {
    int i;
    int j;
    int d;

    myPair(int i, int j) {
        this.i = i;
        this.j = j;
    }

    myPair(int i, int j, int d) {
        this(i, j);
        this.d = d;
    }
}