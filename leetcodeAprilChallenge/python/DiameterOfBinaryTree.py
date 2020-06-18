# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right


class Solution:
    def diameterOfBinaryTree(self, root: TreeNode) -> int:
        def getHeight(treeNode: TreeNode) -> int:
            if treeNode == None:
                return 0
            else:
                hl = getHeight(treeNode.left)
                hr = getHeight(treeNode.right)
                self.num = max(self.num, hl + hr + 1)
                return max(hl, hr) + 1
        self.num = 1
        getHeight(root)
        return self.num - 1
