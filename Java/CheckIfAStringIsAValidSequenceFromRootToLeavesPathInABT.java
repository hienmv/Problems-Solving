// https://leetcode.com/problems/check-if-a-string-is-a-valid-sequence-from-root-to-leaves-path-in-a-binary-tree/
// #tree
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
    private boolean helper(TreeNode root, int[] arr, int idx) {
        if (root == null || idx >= arr.length || root.val != arr[idx]) return false;
    
        if (idx == arr.length - 1 && root.left == null && root.right == null) {
            return true;
        }
        
        return (helper(root.left, arr, idx + 1) || helper(root.right, arr, idx + 1));
    }
    public boolean isValidSequence(TreeNode root, int[] arr) {
        if (arr.length == 0) return false;
        return helper(root, arr, 0);
    }
}