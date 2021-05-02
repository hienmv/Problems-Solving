// https://leetcode.com/problems/binary-tree-inorder-traversal/
// #hash-table #tree #stack #todo
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        invokeInorderTraversal(root, result);
        return result;
    }
    
    private void invokeInorderTraversal(TreeNode root, ArrayList<Integer> result) {
        // inorder: left root right
        if (root == null) {
            return;
        }
        
        invokeInorderTraversal(root.left, result);
        result.add(root.val);
        invokeInorderTraversal(root.right, result);
    }
    
    
//    private void useStack(TreeNode root, )
}