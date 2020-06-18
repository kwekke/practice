
/*
https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/541/week-3-june-15th-june-21st/3363/
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. 
Two cells are connected if they are adjacent cells connected horizontally or vertically.
=====================================================================================================================
1) dfs on perimeter cells
mark those connected to board perimeter's 'O's as 'V's which stand for visited.
convert the non-visited 'O's to 'X's and change back the 'V's to 'O's.

Time Complexity: O(v)
Space Complexity: O(1) 

*/
import java.util.*;

public class SurroundedRegions {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int r = board.length;
        int c = board[0].length;

        for (int j = 0; j < c; j++) {
            dfs(board, 0, j);
            dfs(board, r - 1, j);
        }

        for (int i = 0; i < r; i++) {
            dfs(board, i, 0);
            dfs(board, i, c - 1);
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'V') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int sr, int sc) {
        if (sr >= 0 && sr < board.length && sc >= 0 && sc < board[0].length && board[sr][sc] == 'O') {
            board[sr][sc] = 'V';
            dfs(board, sr + 1, sc);
            dfs(board, sr, sc + 1);
            dfs(board, sr - 1, sc);
            dfs(board, sr, sc - 1);
        } else
            return;
    }

}