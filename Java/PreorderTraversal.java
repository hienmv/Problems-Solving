// https://www.interviewbit.com/problems/preorder-traversal/
// #tree #binary-tree
/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) {
 *      val = x;
 *      left=null;
 *      right=null;
 *     }
 * }
 */
public class Solution {
    public int[] preorderTraversal(TreeNode A) {
        ArrayList<Integer> result = new ArrayList<>();
        preorder(A, result);
        int[] ret = new int[result.size()];
        for(int i=0; i< result.size(); i++) {
            ret[i] = result.get(i);
        }
        return ret;
    }
    private void preorder(TreeNode node, ArrayList<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        preorder(node.left, result);
        preorder(node.right, result);
    }
}
