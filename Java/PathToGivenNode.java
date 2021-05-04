// https://www.interviewbit.com/problems/path-to-given-node/
// #tree #binary-tree
/**
 * Definition for binary tree class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int
 * x) { val = x; left=null; right=null; } }
 */
public class Solution {
  public int[] solve(TreeNode A, int B) {
    Deque<TreeNode> deque = new LinkedList<>();
    DFS(deque, A, B);
    int[] result = new int[deque.size()];
    for (int i = 0; i < result.length; i++) {
      result[i] = deque.pollFirst().val;
    }
    return result;
  }

  private boolean DFS(Deque<TreeNode> stack, TreeNode node, int B) {
    if (node == null) {
      return false;
    }
    stack.offerLast(node);

    if (node.val == B) {
      return true;
    }
    if (DFS(stack, node.left, B) || DFS(stack, node.right, B)) {
      return true;
    }
    stack.pollLast();
    return false;
  }
}
