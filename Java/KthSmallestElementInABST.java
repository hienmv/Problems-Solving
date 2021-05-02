// https://leetcode.com/problems/kth-smallest-element-in-a-bst/
// #binary-search #tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/*
root = [3,1,4,null,2], k = 1
*/
class Solution {
    private void getKthSmallest(TreeNode root, int k, int[] currentCount, int[] ret) {
        if (root == null) {
            return;
        }
        
        getKthSmallest(root.left, k, currentCount, ret);
        // curr
        currentCount[0] += 1;
        if (currentCount[0] == k) {
            ret[0] = root.val;
            return;
        }
        System.out.println(root.val);
        getKthSmallest(root.right, k, currentCount, ret);
        
    }
    public int kthSmallest(TreeNode root, int k) {
        int[] currentCount = new int[1];        
        int[] ret = new int[1];
        ret[0] = -1;
        
        getKthSmallest(root, k, currentCount, ret);
        return ret[0];
    }
}