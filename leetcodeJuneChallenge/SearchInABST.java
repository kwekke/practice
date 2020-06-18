/*
https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/541/week-3-june-15th-june-21st/3361/
Given the root node of a binary search tree (BST) and a value. 
You need to find the node in the BST that the node's value equals the given value. 
Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.
======================================================================================================
1) binary search 
Time Complexity: O(lgn)
Space Compelexity: O(1) additional space,  O(lgn) for stack call
*/

import java.util.*;

public class SearchInABST {
    class Solution {
        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null) {
                return null;
            }

            if (root.val > val) {
                return searchBST(root.left, val);
            }
            if (root.val < val) {
                return searchBST(root.right, val);
            }
            return root;
        }
    }
}