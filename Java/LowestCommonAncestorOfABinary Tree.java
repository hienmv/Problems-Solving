// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
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
class Solution {
    /* better idea: use state: 0 (not found both p,q), 1 (found p or q), 2 (found q)
    // binary approach: => 0(n)
        -> check left side: 
            if state = 0,
                -> result will be in right side
            if state = 1,
                -> return parent node
            if state = 2, no not need to check right side.
    
    */
    
    // idea: find parent path from root to p
    // idea: find parent path from root to q.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        HashMap<Integer, TreeNode> mapResult = preorderTraversal(root);
        HashSet<Integer> parentOfPSet = new HashSet<>();
        parentOfPSet.add(p.val);
        TreeNode parentOfP = mapResult.get(p.val);
        while(parentOfP != null) {
            parentOfPSet.add(parentOfP.val);
            parentOfP = mapResult.get(parentOfP.val);
        }
        if (parentOfPSet.contains(q.val)) {
            return q;
        } else {
            TreeNode parentOfQ = mapResult.get(q.val);
            while(parentOfQ != null) {
                if (parentOfPSet.contains(parentOfQ.val)) {
                    return parentOfQ;
                }
                parentOfQ = mapResult.get(parentOfQ.val);
            }
            return root;
        }
    }
    public HashMap<Integer, TreeNode> preorderTraversal(TreeNode root) {
        HashMap<Integer, TreeNode> result = new HashMap<>();
        invokePreOrderTraversal(root, result, null);
        return result;
    }
    private void invokePreOrderTraversal(TreeNode root, HashMap<Integer, TreeNode> result, TreeNode p) {
        // postorder: left -> right -> root
        if (root == null) {
            return;
        }
        result.put(root.val, p);
        invokePreOrderTraversal(root.left, result, root);
        invokePreOrderTraversal(root.right, result, root);

    }
}