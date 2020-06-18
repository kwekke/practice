
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/537/week-4-may-22nd-may-28th/3339/
Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, 
any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  
Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

It's guaranteed that for the given test cases there is always possible to find a binary search tree with the given requirements.
Constraints:

1 <= preorder.length <= 100
1 <= preorder[i] <= 10^8
The values of preorder are distinct
==============================================================================================================
1) Recursive
1. Find next element larger than cur
2. split recursively split function of that pivot point.
Time Complexity: O(N)
Divide and Conquer
*/
import java.util.*;

public class BSTPreTraversal {
    public TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, 0, preorder.length - 1);
    }

    TreeNode helper(int[] preorder, int l, int r) {
        if (l > r) {
            return null;
        }
        TreeNode n = new TreeNode(preorder[l]);
        int i;
        for (i = l; i <= r; i++) {
            if (preorder[i] > n.val) {
                break;
            }
        }
        n.left = helper(preorder, l + 1, i - 1);
        n.right = helper(preorder, i, r);
        return n;
    }
}