// https://leetcode.com/problems/validate-binary-search-tree/
// #tree #dfs
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode() {} TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left,
 * TreeNode right) { this.val = val; this.left = left; this.right = right; } }
 */

/*
                 3
                  \
                   30
                 /
               10
                  \
                   15
                    \
                     45

                3
                 \
                   30
                 /
               10
              /
             2
*/
class Solution {
  // O(n)
  // each node should be in a range(min, max)
  // each step:
  // .    - left node: update max in range
  // .    - right node: update min in range
  public boolean isValidBST(TreeNode root) {
    return !(isInValidRecursion(root, Long.MIN_VALUE, Long.MAX_VALUE));
  }

  private boolean isInValidRecursion(TreeNode root, long min, long max) {
    if (root == null) {
      return false;
    }
    if ((long) root.val >= max || (long) root.val <= min) {
      return true;
    }
    if (root.left != null && root.left.val >= root.val) {
      return true;
    }
    if (root.right != null && root.right.val <= root.val) {
      return true;
    }
    return (isInValidRecursion(root.left, min, Math.min(max, root.val))
        || isInValidRecursion(root.right, Math.max(min, root.val), max));
  }

  /* 0(nlogn)
  // create a new BST from the input tree
  // traversal the new BST and compare to the input tree
  public boolean isValidBST(TreeNode root) {
      TreeNode newRoot = insertChildNode(null, root);
      return !isInvalid(newRoot, root);
  }

  private boolean isInvalid(TreeNode node1, TreeNode node2) {
      if (node1 == null && node2 != null) {
          return true;
      }
      if (node1 != null && node2 == null) {
          return true;
      }

      if (node1 == null && node2 == null) {
          return false;
      }

      if (node1.val != node2.val) {
          return true;
      }

      return isInvalid(node1.left, node2.left) || isInvalid(node1.right, node2.right);
  }

  private TreeNode insertChildNode(TreeNode root, TreeNode node) {
      if (node == null) {
          return root;
      }
      root = insertNode(root, node.val);
      root = insertChildNode(root, node.left);
      root = insertChildNode(root, node.right);
      return root;
  }

  private TreeNode insertNode(TreeNode root, int x) {
      if (root == null) {
          return new TreeNode(x);
      }
      if (x < root.val) {
          root.left = insertNode(root.left, x);
      } else if (x > root.val) {
          root.right = insertNode(root.right, x);
      }
      return root;
  }*/
}
