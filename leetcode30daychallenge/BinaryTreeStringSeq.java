
/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/532/week-5/3315/
Given a binary tree where each path going from the root to any leaf form a valid sequence, 
check if a given string is a valid sequence in such binary tree. 

We get the given string from the concatenation of an array of integers arr
and the concatenation of all values of the nodes along a path results in a sequence in the given binary tree.
==============================================================================================================================
use BFS and a variable storing the pointer of the arr to be checked
while iterating through the tree, if the val of the node corresponds to the arr,
then check if the arr is done, and that the node is a leaf. if it is, we are done; return true
otherwise, check if the sub trees contain the rest of the arr.

*/
import java.util.*;

public class BinaryTreeStringSeq {
    public boolean isValidSequence(TreeNode root, int[] arr) {
        if (root == null) {
            return false;
        }
        return bfs(root, arr, 0);
    }

    boolean bfs(TreeNode node, int[] arr, int i) {
        if (node == null) {
            return false;
        }

        if (i >= arr.length) {
            return false;
        }

        if (node.val == arr[i]) {
            if (i == arr.length - 1 && node.left == null && node.right == null) {
                return true;
            }
            return bfs(node.left, arr, i + 1) || bfs(node.right, arr, i + 1);
        } else {
            return false;
        }
    }
}