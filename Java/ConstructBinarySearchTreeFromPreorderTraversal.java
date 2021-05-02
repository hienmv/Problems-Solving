// https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
// #tree 
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*

BEST WAYS:
helper(preorder, mn, mx, idx[]):
  value = preorder[idx[0]]
  if value > mx || value < mn:
    return
  root = new TreeNode(value)
  idx[0] += 1
  root.left = helper(preorder, mn, value, idx)
  root.right = helper(preorder, value, mx, idx)


helper(-INF, INF, 0)  // 8
  helper(-INF, 8, 1)  // 5
    helper(-INF, 5, 2) // 1
      helper(-INF, 1, 3)
      helper(1, 5, 3)
    helper(5, 8, 3)    // 7
      helper(5, 7, 4)
      helper(7, 8, 4)
  helper(8, INF, 4)    // 10

          8
        /  \
      5     10
    /  \
  1     7




          8
       /    \
     5      10
   /  \        \
 1     7        12

[mn, mx]

[mn, preorder[l]-1]       [preorder[l]+1, mx]

GOOD WAYS:
helper(l, r) -> TreeNode (root)
  root = new TreeNode(preorder[l])
  // loop
  root.left = helper(idx, mn, preorder[l])
  root.right = helper(idx, preorder[l], mx)

*/
/* SIMPLE WAY
class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        for(int i = 1; i < preorder.length; i++) {
            addToTree(root, preorder[i]);
        }
        return root;
    }
    private void addToTree(TreeNode root, int x) {
        TreeNode iter = root;
        while(iter != null) {
            if (iter.val > x) {
                if (iter.left == null) {
                    iter.left = new TreeNode(x);
                    break;
                }
                iter = iter.left;
            }
            else { // iter.val < x
                if (iter.right == null) {
                    iter.right = new TreeNode(x);
                    break;
                }
                iter = iter.right;
            }
        }
    }
}
*/
class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder.length == 0) return null;
        int[] idx = {0};
        int INF = 1000000007;
        TreeNode root = helper(preorder, -INF, INF, idx);
        return root;
    }
    private TreeNode helper(int[] preorder, int mn, int mx, int[] idx) {
        if (idx[0] >= preorder.length) {
            return null;
        }
        int value = preorder[idx[0]];
        if (value > mx || value < mn) {
            return null;
        }
        TreeNode root = new TreeNode(value);
        idx[0] += 1;
        root.left = helper(preorder, mn, value, idx);
        root.right = helper(preorder, value, mx, idx);
        
        return root;
    }
}