
/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/530/week-3/3306/ 
(This problem is an interactive problem.)

A binary matrix means that all elements are 0 or 1. 
For each individual row of the matrix, this row is sorted in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. 
If such index doesn't exist, return -1.

You can't access the Binary Matrix directly.  
You may only access the matrix using a BinaryMatrix interface:

BinaryMatrix.get(x, y) returns the element of the matrix at index (x, y) (0-indexed).
BinaryMatrix.dimensions() returns a list of 2 elements [n, m], which means the matrix is n * m.
Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.  
Also, any solutions that attempt to circumvent the judge will result in disqualification.

For custom testing purposes you're given the binary matrix mat as input in the following four examples. 
You will not have access the binary matrix directly.
========================================================================================================================
1) traverse from top right down to left. 
pointer(x,y) p starting from top right. p can only move left or down. 
if value at p is 0, move down. if value at p is 1, move left. 
Time Complexity: O(r + c)
Space Complextiy: O(1)

*/
import java.util.*;

public class LeftmostColWithAtLeastAOne {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int r = binaryMatrix.dimensions().get(0);
        int c = binaryMatrix.dimensions().get(1);
        int ans = -1;
        int i = c - 1;
        int j = 0;
        while (i >= 0 && j < r) {
            if (binaryMatrix.get(j, i) == 1) {
                ans = i;
                i--;
            } else {
                j++;
            }
        }
        return ans;
    }

}

interface BinaryMatrix {
    public int get(int x, int y);

    public List<Integer> dimensions();
};