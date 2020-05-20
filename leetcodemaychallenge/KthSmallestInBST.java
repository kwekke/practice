/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/536/week-3-may-15th-may-21st/3335/
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
How would you optimize the kthSmallest routine?

Hint #1  
Try to utilize the property of a BST.

Hint #2  
Try in-order traversal.

Hint #3  
What if you could modify the BST node's structure?

Hint #4  
The optimal runtime complexity is O(height of BST).
========================================================================================================================
1) In order traversal
count keeps tracks on the number of nodes visited thus far. 
Time Complexity: O(n)
Space Complexity: O(h)

2) Modify BST node's structure
Maintain the rank of each node; keep track of eleemnts in the left subtree of every node while building the tree.
Since we need the kth smallest element, we can maintain the number of elements of the left subtree in every node. 

Case 1: if k = count + 1, then root is the kth node. we are done.
Case 2: if k < count, then the kth node is in left subtree. continue search for k th smallest element in this subtree.
Case 3: if k > count, then the kth node is in right subtree. continue search for (k - count - 1) th smallest element in this subtree.


Time Complexity: O(h)
Space Complexity: O(h)

*/

public class KthSmallestInBST {
    int count;

    public int KthSmallest(TreeNode root, int k) {
        count = 0;
        return helper(root, k).val;
    }

    TreeNode helper(TreeNode root, int k) {
        if (root == null) {
            return null;
        }

        TreeNode left = helper(root.left, k);

        if (left != null) {
            return left;
        }

        count++;
        if (count == k) {
            return root;
        }

        return helper(root.right, k);
    }

    public TreeNode KthSmallest2(TreeNode root, int k) {
        if (root == null) {
            return null;
        }
        int count = root.count + 1;
        if (count == k) {
            return root;
        }
        if (count > k) {
            return KthSmallest2(root.left, k);
        }

        return KthSmallest2(root.right, k - count);
    }
}