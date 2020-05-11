/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/530/week-3/3305/
Return the root node of a binary search tree that matches the given preorder traversal.
(Recall that a binary search tree is a binary tree where for every node,
any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.
Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)
================================================================================================================================================
1) Stack
1. Create an empty stack.
2. Make the first value as root. Push it to the stack.
3. Keep on popping while the stack is not empty and the next value is greater than stack’s top value. Make this value as the right child of the last popped node. Push the new node to the stack.
4. If the next value is less than the stack’s top value, make this value as the left child of the stack’s top node. Push the new node to the stack.
5. Repeat steps 2 and 3 until there are items remaining in pre[].

Time Complexity: O(N)
run through each once

2) Recursive
1. Find next element larger than cur
2. split recursively split function of that pivot point.
Time Complexity: O(N)
Divide and Conquer

*/

import java.util.*;

public class BSTPreTraversal {
    int index = 0;

    public TreeNode bstFromPreorderStack(int[] preorder) {
        return helperStack(preorder, preorder.length);
    }

    TreeNode helperStack(int[] preorder, int size) {
        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);

        for (int i = 1; i < size; i++) {
            TreeNode temp = null;
            while (!s.isEmpty() && preorder[i] > s.peek().val) {
                temp = s.pop();
            }
            if (temp != null) {
                temp.right = new TreeNode(preorder[i]);
                s.push(temp.right);
            } else {
                temp = s.peek();
                temp.left = new TreeNode(preorder[i]);
                s.push(temp.left);
            }
        }
        return root;
    }

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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}