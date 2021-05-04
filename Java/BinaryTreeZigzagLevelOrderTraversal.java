// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
// #stack #tree #bfs
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode() {} TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left,
 * TreeNode right) { this.val = val; this.left = left; this.right = right; } }
 */
class Solution {
  // O(n)
  // traversal add to list at each depth.
  // change order.
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

    List<List<Integer>> result = new ArrayList<>();
    preOrderTraversal(root, 0, result);

    for (int i = 0; i < result.size(); i++) {
      if (i % 2 != 0) {
        Collections.reverse(result.get(i));
      }
    }

    return result;
  }

  private void preOrderTraversal(TreeNode root, int idx, List<List<Integer>> result) {
    if (root == null) {
      return;
    }
    if (result.size() <= idx) {
      result.add(new ArrayList<Integer>());
    }
    result.get(idx).add(root.val);
    preOrderTraversal(root.left, idx + 1, result);
    preOrderTraversal(root.right, idx + 1, result);
  }
}
