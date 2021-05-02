// https://leetcode.com/problems/diameter-of-binary-tree/
// #tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // count number of straight-nodes in left and right of current node.
    private int diameterRecursive(TreeNode node, int[] result) {
        if (node == null) {
            return 0;
        }
        
        int retLeft = diameterRecursive(node.left, result);
        int retRight = diameterRecursive(node.right, result);
        
        if (retLeft + retRight > result[0]) {
            result[0] = retLeft + retRight;
        }

        return 1 + Math.max(retLeft, retRight);
    }
    public int diameterOfBinaryTree(TreeNode root) {
        int[] result = new int[1];
        diameterRecursive(root, result);
        return result[0];
    }
}