/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/531/week-4/3312/
Given a 2D binary matrix filled with 0's and 1's, 
find the largest square containing only 1's and return its area.

1) DP 
Time Complexity: O(rc)
Space Complexity: O(rc)

run through from top left to bottom right.
if cell on left, above, and top left are valid, increment the count including these cells.

*/

import java.util.*;

public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int[][] m = new int[matrix.length][matrix[0].length];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if (matrix[r][c] == ('1')) {
                    m[r][c] = 1;
                } else {
                    m[r][c] = 0;
                }
            }
        }

        int answer = 0;
        for (int r = 0; r < m.length; r++) {
            for (int c = 0; c < m[0].length; c++) {
                if (r != 0 && c != 0 && m[r][c] == 1 && m[r - 1][c] >= 1 && m[r][c - 1] >= 1 && m[r - 1][c - 1] >= 1) {
                    m[r][c] = 1 + Math.min(m[r - 1][c - 1], Math.min(m[r - 1][c], m[r][c - 1]));
                }
                answer = Math.max(m[r][c], answer);
            }
        }
        return answer * answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        sc.nextLine();
        String row = sc.nextLine();
        char[][] matrix = new char[r][row.length()];
        matrix[0] = row.toCharArray();
        for (int i = 1; i < r; i++) {
            matrix[i] = sc.nextLine().toCharArray();
        }
        sc.close();
        MaximalSquare ms = new MaximalSquare();
        System.out.println(ms.maximalSquare(matrix));
    }
}