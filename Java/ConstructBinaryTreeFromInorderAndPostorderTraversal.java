// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
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
    // inorder: left->node->right
    // postorder: left->right->node
    // preorder: node->left->right.
    /*
    idea: build binary tree from postorder with order by inorder
    */
    
    // O(nlogn)
    public TreeNode buildTreeNotOptimize(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> order = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            order.put(inorder[i], i);
        }
        
        // build binary tree from postorder 
        TreeNode left_most_node = new TreeNode(postorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerLast(left_most_node);
        for(int i = 1; i < postorder.length; i++) {
            TreeNode node = new TreeNode(postorder[i]);
                while(!stack.isEmpty() && order.get(stack.peekLast().val) > order.get(postorder[i])) {
                    // insert Right
                    insert(node, stack.pollLast(), order);
                }
                stack.offerLast(node);
        }
        // insert Left
        TreeNode root = stack.pollLast();
        while(!stack.isEmpty()) {
            insert(root, stack.pollLast(), order);
        }
        
        return root;
    }
    
    private void insert(TreeNode root, TreeNode node, HashMap<Integer, Integer> order) {
        if (order.get(node.val) > order.get(root.val)) {
            if (root.right == null) {
                root.right = node;
                return;
            } else {
                insert(root.right, node, order);
            }
        } else {
            if (root.left == null) {
                root.left = node;
                return;
            } else {
                insert(root.left, node, order);
            }
        }
    }
    
    // O(N)
    // postorder: left-right-node
    // interate: N-1 -> 0 (postorder).
     public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> order = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            order.put(inorder[i], i);
        }
        
        // build binary tree from postorder 
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerLast(root);
         
        for(int i = postorder.length - 2; i >= 0; i--) {
            TreeNode node = new TreeNode(postorder[i]);
            TreeNode parent = stack.peekLast();
            if (order.get(postorder[i]) > order.get(stack.peekLast().val)) {
                parent.right = node;
            } else {
                while(!stack.isEmpty() && order.get(postorder[i]) < order.get(stack.peekLast().val)) {
                  parent = stack.pollLast();
                }
                parent.left = node;
                
            }
            stack.offerLast(node);
        }        
        return root;
    }
}