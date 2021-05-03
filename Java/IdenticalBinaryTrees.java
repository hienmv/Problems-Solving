// https://www.interviewbit.com/problems/identical-binary-trees/
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
    // traversal preorder and check same level
    public int isSameTree(TreeNode A, TreeNode B) {
        
        return check(A, B) ? 1 : 0; 
    }
    
    private boolean check(TreeNode A, TreeNode B) {
        if ((A == null) != (B == null)) {
            return false;
        }
        if (A == null && B == null) {
            return true;
        }
        if (A.val != B.val) {
            return false;
        }
        
        return check(A.left, B.left) && check(A.right, B.right);
    }
}
