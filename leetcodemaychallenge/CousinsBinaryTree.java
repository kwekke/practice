
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3322/
In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.
Note:

The number of nodes in the tree will be between 2 and 100.
Each node has a unique integer value from 1 to 100.
========================================================================================================================
1) find the parent of the nodes x and y
update the heights found for x and y
if heights have not changed or are not the same, or if a parent cannot be found, then return false
*/
import java.util.*;

public class CousinsBinaryTree {
    public boolean isCousins(TreeNode root, int x, int y) {
        TreeNode xp = findNode(root, x, x, y, 0);
        TreeNode yp = findNode(root, y, x, y, 0);

        if (xh == -1 || yh == -2) {
            return false;
        }

        if (xh != yh) {
            return false;
        }

        if (xp == null || yp == null) {
            return false;
        }

        if (xp == yp) {
            return false;
        }
        return true;
    }

    int xh = -1;
    int yh = -2;

    TreeNode findNode(TreeNode node, int v, int x, int y, int h) {
        if (node.left != null && node.left.val == v) {
            if (v == x) {
                xh = h + 1;
            } else {
                yh = h + 1;
            }
            return node;
        }

        if (node.right != null && node.right.val == v) {
            if (v == x) {
                xh = h + 1;
            } else {
                yh = h + 1;
            }
            return node;
        }

        TreeNode leftNode = null;
        TreeNode rightNode = null;
        if (node.left != null) {
            leftNode = findNode(node.left, v, x, y, h + 1);
        }
        if (node.right != null) {
            rightNode = findNode(node.right, v, x, y, h + 1);
        }
        return leftNode == null ? rightNode : leftNode;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
