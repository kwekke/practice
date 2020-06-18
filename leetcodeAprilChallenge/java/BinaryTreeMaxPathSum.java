/*
https://prod.liveshare.vsengsaas.visualstudio.com/join?E07DC3298735434D8E622FD0F9EE76C30858
Given a non-empty binary tree, find the maximum path sum.

A path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
The path must contain at least one node and does not need to go through the root.
=======================================================================================================================================

1) iterate through the tree
maintain a global variable storing the maximum path sum
for each node, consider the max of the left subtree + self, right subtree + self, and self
obtain the maximum if self is the subroot of the path. reupdate global variable if necessary

2) alternative implementation


*/

import java.util.*;

public class BinaryTreeMaxPathSum {
    int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        // maintain global maximum reupdated for every root.
        findMax(root);
        return ans;
    }

    int findMax(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int l = findMax(node.left);
        int r = findMax(node.right);
        // a path either from left subtree to subroot, or right subtree to subroot, or
        // just subroot
        int max_single = Math.max(Math.max(l, r) + node.val, node.val);
        // when left subtree, node and right subtree is the path
        int max_top = Math.max(max_single, l + r + node.val);
        ans = Math.max(ans, max_top);
        return max_single;
    }

    // implementation 2
    int max_path_sum = Integer.MIN_VALUE;

    public int maxPathSum2(TreeNode root) {
        maxPathSumUtil(root);
        return max_path_sum;
    }

    public int maxPathSumUtil(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, maxPathSumUtil(root.left));
        int right = Math.max(0, maxPathSumUtil(root.right));
        max_path_sum = Math.max(left + right + root.val, max_path_sum);
        return Math.max(left, right) + root.val;
    }
}