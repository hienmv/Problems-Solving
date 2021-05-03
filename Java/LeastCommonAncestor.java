// https://www.interviewbit.com/problems/least-common-ancestor/
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
    public int lca(TreeNode A, int B, int C) {
        // idea: find in left => 0,1,2 => not found any target, find 1 target, find 2 target
        // if find both in left 
        //   ignore right
        //   find in left,
        // if find one target in left 
        //    find the other in right
        // if find 0 in left
        //     find both in right
        int[] result = new int[1];
        result[0] = -1;
        helper(A, B, C, result);
        return result[0];
    }
    private int helper(TreeNode node, int B, int C, int[] result) {
        int val = 0;
        if (node == null) {
            return val;
        }
        
        if (node.val == B && node.val == C) {
            val = 2;
        } else if (node.val == B || node.val == C) {
            val = 1;
        }

        int left = helper(node.left, B, C, result);
        int right = 0;
        if ((val + left) == 2) {
            if (result[0] == -1) {
                if (left == 2) {
                    result[0] = node.left.val;
                } else {
                    result[0] = node.val;
                }
            }
        } else {
            right = helper(node.right, B, C, result);
            if ((val + left + right) == 2) {
                if (result[0] == -1) {
                    if (right == 2) {
                        result[0] = node.right.val;
                    } else {
                        result[0] = node.val;
                    }
                }
            }
        }
        
        return val + left + right;
    }
}
