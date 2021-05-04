// https://www.interviewbit.com/problems/kth-smallest-element-in-tree/
// #tree
/**
 * Definition for binary tree class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int
 * x) { val = x; left=null; right=null; } }
 */
public class Solution {
  public int kthsmallest(TreeNode A, int B) {

    int[] result = {A.val};
    int[] order = {B};
    // traversal binary search tree inorder
    inorder(A, order, result);

    return result[0];
  }

  private void inorder(TreeNode node, int[] order, int[] result) {
    if (node == null) {
      return;
    }

    inorder(node.left, order, result);

    // check current node is kth smallest element
    if (order[0] > 0) {
      result[0] = node.val;
      order[0]--;
    }
    if (order[0] == 0) {
      return;
    }

    inorder(node.right, order, result);
  }
}
