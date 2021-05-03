// https://www.interviewbit.com/problems/inorder-traversal/
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
    public int[] inorderTraversal(TreeNode A) {
        ArrayList<Integer> result = new ArrayList<>();
        inorder(A, result);
        int[] ret = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            ret[i] = result.get(i);
        }
        return ret;
    }
    private void inorder(TreeNode node, ArrayList<Integer> result) {
        if (node == null) {
            return;
        }
        
        inorder(node.left, result);
        result.add(node.val);
        inorder(node.right, result);
    }
}