// https://leetcode.com/problems/invert-binary-tree/
// #tree
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode() {} TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left,
 * TreeNode right) { this.val = val; this.left = left; this.right = right; } }
 */
class Solution {
  private TreeNode invertTreeRecursive(TreeNode root) {
    if (root == null) {
      return null;
    }

    TreeNode left = invertTreeRecursive(root.left);
    TreeNode right = invertTreeRecursive(root.right);
    root.left = right;
    root.right = left;
    return root;
  }

  public TreeNode invertTree(TreeNode root) {
    invertTreeRecursive(root);
    return root;
  }
}
