// https://www.interviewbit.com/problems/construct-binary-tree-from-inorder-and-preorder/
// #tree #binary-search-tree #binary-tree
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
    public TreeNode buildTree(int[] A, int[] B) {
        // store order of each node
        HashMap<Integer, Integer> order = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            order.put(B[i], i);
        }
        
        // build Binary Tree from Pre-order list with order of nodes
        TreeNode root = new TreeNode(A[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerLast(root);

        /*
        for (int i = 1; i < A.length; i++) {
            if (order.get(A[i]) < order.get(stack.peekLast().val)) {
                TreeNode parent = stack.peekLast();
                parent.left = new TreeNode(A[i]);
                stack.offerLast(parent.left);
            }
            else {
                TreeNode parent = stack.peekLast();
                while(!stack.isEmpty() && 
                    (order.get(stack.peekLast().val) < order.get(A[i]))) {
                    parent = stack.pollLast();
                }
                parent.right = new TreeNode(A[i]);
                stack.offerLast(parent.right);
            }
            
        }
        */
        for (int i = 1; i < A.length; i++) {
            TreeNode parent = stack.peekLast();
            TreeNode node = new TreeNode(A[i]);
            if (order.get(A[i]) < order.get(parent.val)) {
                parent.left = node;
            }
            else {
                while(!stack.isEmpty() && (order.get(stack.peekLast().val) < order.get(A[i]))) {
                    parent = stack.pollLast();
                }
                parent.right = node;
            }
             stack.offerLast(node);
        }
        
        return root;
    }
}
