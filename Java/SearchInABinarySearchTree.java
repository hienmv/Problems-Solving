// https://leetcode.com/problems/search-in-a-binary-search-tree/
// #tree
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode() {} TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left,
 * TreeNode right) { this.val = val; this.left = left; this.right = right; } }
 */
class Solution {
  public TreeNode searchBSTRecusion(TreeNode root, int val) {
    if (root == null) return null;
    if (root.val == val) {
      return root;
    }
    if (root.val > val) {
      return searchBSTRecusion(root.left, val);
    } else {
      return searchBSTRecusion(root.right, val);
    }
  }

  public TreeNode searchBST(TreeNode root, int val) {
    return searchBSTRecusion(root, val);
  }
}
