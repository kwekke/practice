
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/536/week-3-may-15th-may-21st/3336/
Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
===================================================================================================
1) dp

Time Complexity: O(mn)
Space Complexity: O(mn)

*/
import java.util.*;

public class CountSquareSubmatricesWithAllOnes {
    public int countSquares(int[][] matrix) {
        int mat[][] = new int[matrix.length + 1][matrix[0].length + 1];
        int sum = 0;
        for (int i = 1; i <= matrix.length; i++)
            for (int j = 1; j <= matrix[0].length; j++)
                if (matrix[i - 1][j - 1] != 0)
                    sum += (mat[i][j] = Math.min(Math.min(mat[i - 1][j], mat[i][j - 1]), mat[i - 1][j - 1]) + 1);
        return sum;
    }
}