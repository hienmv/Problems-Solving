// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
// #tree #dfs
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode() {} TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left,
 * TreeNode right) { this.val = val; this.left = left; this.right = right; } }
 */
class Solution {
  // left root right => : root -> right -> left
  public void flatten(TreeNode root) {
    if (root != null) {
      preOrderTraversal(root);
    }
  }
  /*
          1
        2.   5
     3.    4.   6

     1 =>
          left_node = f(2) => [3,4] =>
                          left f(3):  3
                          right f(4) : 4;

                     f(3).right = f(4)

                  f(2)->[r]f(3)->[r]f(4)
          right_node = f(5): [null, 6]
                          left: null
                          right:6
                      f(5)->[r]f(6)

  */
  private TreeNode preOrderTraversal(TreeNode root) {
    if (root.left == null && root.right == null) {
      return root;
    }
    TreeNode left_node = null;
    if (root.left != null) {
      left_node = preOrderTraversal(root.left);
    }
    TreeNode right_node = null;
    if (root.right != null) {
      right_node = preOrderTraversal(root.right);
    }
    if (right_node != null) {
      if (left_node != null) {
        TreeNode temp = left_node;
        while (temp.right != null) {
          temp = temp.right;
        }
        temp.right = right_node;
      } else {
        left_node = right_node;
      }
    }
    root.left = null;
    root.right = left_node;

    return root;
  }
}
