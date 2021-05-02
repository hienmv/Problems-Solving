// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
// #array #tree #dfs
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
class Solution {
/*
pre-order: node -> left -> right
in-order: left->node->right
post-order: left->right->node

// idea: coi in-order la thu tu sap xep cac node 
    => dua vao thu tu nay => build tree bang pre-order.
// in-order: HashMap<int, index>
// use idea of mock_35 (treat as BST): https://www.interviewbit.com/problems/valid-bst-from-preorder/
// with new order
// extend: pre-post, post-in
*/
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // store order of each node
    HashMap<Integer, Integer> order = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
        order.put(inorder[i], i);
    }
    
    // build Binary Tree from Pre-order list with order of nodes
    TreeNode root = new TreeNode(preorder[0]);
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
    for (int i = 1; i < preorder.length; i++) {
        TreeNode parent = stack.peekLast();
        TreeNode node = new TreeNode(preorder[i]);
        if (order.get(preorder[i]) < order.get(parent.val)) {
            parent.left = node;
        }
        else {
            while(!stack.isEmpty() && (order.get(stack.peekLast().val) < order.get(preorder[i]))) {
                parent = stack.pollLast();
            }
            parent.right = node;
        }
         stack.offerLast(node);
    }
    
    return root;
    }
}