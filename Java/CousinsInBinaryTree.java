// https://leetcode.com/problems/cousins-in-binary-tree/
// #tree #bfs
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode() {} TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left,
 * TreeNode right) { this.val = val; this.left = left; this.right = right; } }
 */
class Solution {
  public boolean isCousins(TreeNode root, int x, int y) {
    Deque<TreeNode> q = new LinkedList<>();
    q.addLast(root);
    while (!q.isEmpty()) {
      int l = q.size(); // get number of node in depth k
      int[] found = new int[2]; // 0 - found, 1 - foundParent / 1:x, 2:y, 3:x+y
      for (int i = 0; i < l; i++) {
        TreeNode u = q.pollFirst();
        found[1] = 0;
        checkFound(u.left, x, y, found);
        checkFound(u.right, x, y, found);
        if (found[1] == 3) // same parent
        return false;
        if (u.left != null) q.addLast(u.left);
        if (u.right != null) q.addLast(u.right);
      }

      if (found[0] == 3) // found x, y with different parent
      return true;
    }

    return false;
  }

  private void checkFound(TreeNode node, int x, int y, int[] found) {
    if (node == null) return;
    if (node.val == x) {
      found[0] |= 1;
      found[1] |= 1;
    }
    if (node.val == y) {
      found[0] |= 2;
      found[1] |= 2;
    }
  }
  /*
  public boolean isCousins(TreeNode root, int x, int y) {
      int[] depth = new int[2];
      depth[0] = -1; // x;
      depth[1] = -2; // y;
      TreeNode[] parent = new TreeNode[2];

      return (findDepth(root, parent, null, x, y, depth, 0) == 1);
  }

  private int findDepth(TreeNode root, TreeNode[] parent, TreeNode par,
  int x, int y, int[] depth, int curDepth) {

      if (root == null) return 0;
      if (root.val == x) {
          parent[0] = par;
          depth[0] = curDepth;
      }
      if (root.val == y) {
          parent[1] = par;
          depth[1] = curDepth;
      }

      if (depth[0] == depth[1]){
          if (parent[0] == parent[1]) {
              return -1;
          }
          else {
              return 1;
          }
      }

      int ret = findDepth(root.left, parent, root, x, y, depth, curDepth + 1);
      if (ret == 0) {
          ret = findDepth(root.right, parent, root, x, y, depth, curDepth + 1);
      }
      return ret;
  }
  */
}
