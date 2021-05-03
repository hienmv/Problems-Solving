// https://www.interviewbit.com/problems/postorder-traversal/
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
    // TODO with stack
    // post order: left - right - node
    public int[] postorderTraversal(TreeNode A) {
        ArrayList<Integer> result = new ArrayList<>();
        postOrder(A, result);
        
        int[] ret = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            ret[i] = result.get(i);
        }
        return ret;
    }
    
    private void postOrder(TreeNode node, ArrayList<Integer> result) {
        if (node == null) {
            return;
        }
        
        postOrder(node.left, result);
        postOrder(node.right, result);
        result.add(node.val);
    }
}
