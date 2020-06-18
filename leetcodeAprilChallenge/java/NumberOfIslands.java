/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/530/week-3/3302/
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.
==============================================================================================================

1) dfs
Time Complexity: O(r * c) 
Space Complexity: O(r * c) 
*/

import java.util.*;

public class NumberOfIslands {
    public static int numIslands(char[][] grid) {
        if (grid.length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;
        boolean[][] visited = new boolean[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                ans += dfs(grid, m, n, visited, r, c);
            }
        }
        return ans;
    }

    static int[] DIR = new int[] { 0, 1, 0, -1, 0 };

    static int dfs(char[][] grid, int m, int n, boolean[][] visited, int r, int c) {
        if (r < 0 || r == m || c < 0 || c == n || visited[r][c] || grid[r][c] == '0')
            return 0;
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            dfs(grid, m, n, visited, r + DIR[i], c + DIR[i + 1]);
        }
        return 1;
    }

    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        sc.nextLine();
        char[][] grid = new char[r][c];
        for (int i = 0; i < r; i++) {
            char[] tokens = sc.nextLine().toCharArray();
            for (int j = 0; j < c; j++) {
                grid[i][j] = tokens[j];
            }
        }
        sc.close();

        char grid2[][] = new char[][] { { 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 1 }, { 1, 0, 0, 1, 1 }, { 0, 0, 0, 0, 0 },
                { 1, 0, 1, 0, 1 } };

        System.out.println(numIslands(grid));
    }

}