// https://leetcode.com/problems/symmetric-tree/
// #tree #dfs #bfs
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode() {} TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left,
 * TreeNode right) { this.val = val; this.left = left; this.right = right; } }
 */
class Solution {
  public boolean isSymmetric(TreeNode root) {
    return check(root.left, root.right);
  }

  private boolean check(TreeNode nodeLeft, TreeNode nodeRight) {
    if (nodeLeft == null && nodeRight == null) {
      return true;
    }
    if ((nodeLeft == null && nodeRight != null) || (nodeLeft != null && nodeRight == null)) {
      return false;
    }
    if (nodeLeft.val != nodeRight.val) {
      return false;
    }

    boolean result1 = check(nodeLeft.left, nodeRight.right);
    if (result1 == false) {
      return false;
    }
    return check(nodeLeft.right, nodeRight.left);
  }
}
