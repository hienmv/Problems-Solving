// https://www.interviewbit.com/problems/invert-the-binary-tree/
// #tree #binary-tree
/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) {
 *      val = x;
 *      left=null;
 *      right=null;
 *     }
 * }
 */
public class Solution {
    public TreeNode invertTree(TreeNode A) {
        invertHelper(A);
        return A;
    }
    private TreeNode invertHelper(TreeNode node) {
        if (node == null) {
            return null;
        }
        
        TreeNode right = invertHelper(node.left);
        TreeNode left = invertHelper(node.right);
        
        node.left = left;
        node.right = right;
        return node;
    }
}
