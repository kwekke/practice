/*
https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/529/week-2/3293/
Given a binary tree, you need to compute the length of the diameter of the tree. 
The diameter of a binary tree is the length of the longest path between any two nodes in a tree. 
This path may or may not pass through the root.

1) Compute height and diameter for each subtree
and get the Max of both diameters for each subtree
max of tree = max height of left subtree + max height of right subtree
get the Max of that
Time Complexity: O(N^2)

2) Use a global variable to store the max diameter of a tree
Diameter of a tree can be calculated by only using the height function, because
the diameter is essentially the Max of left height + right height + 1 (assuming a tree of 1 node is 1 height)
for each node and update the global variable.
Time Complexity: O(N) 
we visit each node once.
Space Complexity: O(N)
the size of implicit call stack during the depth first search

*/
public class DiameterOfBinaryTree {
    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int hl = maxHeight(root.left);
        int hr = maxHeight(root.right);

        int dl = diameterOfBinaryTree(root.left);
        int dr = diameterOfBinaryTree(root.right);

        return Math.max(hl + hr, Math.max(dl, dr));
    }

    public static int maxHeight(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(maxHeight(node.left), maxHeight(node.right)) + 1;
        }
    }

    private int numOfNodes;

    public int diameterOfBinaryTree2(TreeNode root) {
        numOfNodes = 1;
        getHeight(root);
        return numOfNodes - 1;
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int hl = getHeight(node.left);
        int hr = getHeight(node.right);
        numOfNodes = Math.max(numOfNodes, hl + hr + 1);
        return Math.max(hl, hr) + 1;
    }

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);

        one.left = two;
        one.right = three;
        two.left = four;
        two.right = five;

        System.out.println(diameterOfBinaryTree(one));
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