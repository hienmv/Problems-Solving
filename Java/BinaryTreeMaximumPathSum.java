// https://leetcode.com/problems/binary-tree-maximum-path-sum/
// #tree #dfs
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode() {} TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left,
 * TreeNode right) { this.val = val; this.left = left; this.right = right; } }
 */
class Solution {
  private int helper(TreeNode root, int[] result) {
    if (root == null) return 0;

    int left = helper(root.left, result);
    int right = helper(root.right, result);

    if (left + right + root.val > result[0]) {
      result[0] = left + right + root.val;
    }
    if (left + root.val > result[0]) {
      result[0] = left + root.val;
    }
    if (right + root.val > result[0]) {
      result[0] = right + root.val;
    }
    if (root.val > result[0]) {
      result[0] = root.val;
    }

    return Math.max(left + root.val, Math.max(right + root.val, root.val));
  }

  public int maxPathSum(TreeNode root) {
    int[] result = new int[1];
    result[0] = Integer.MIN_VALUE;
    helper(root, result);

    return result[0];
  }
}
