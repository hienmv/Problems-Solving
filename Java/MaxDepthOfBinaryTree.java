// https://www.interviewbit.com/problems/max-depth-of-binary-tree/
// #tree #binary-tree
/**
 * Definition for binary tree class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int
 * x) { val = x; left=null; right=null; } }
 */
public class Solution {
  public int maxDepth(TreeNode A) {
    // DFS
    return DFS(A);
  }

  private int DFS(TreeNode node) {
    if (node == null) {
      return 0;
    }
    return Math.max(1 + DFS(node.left), 1 + DFS(node.right));
  }
}
