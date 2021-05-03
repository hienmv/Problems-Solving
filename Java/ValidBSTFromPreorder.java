// https://www.interviewbit.com/problems/valid-bst-from-preorder/
// #tree #binary-search-tree
public class Solution {
    public int solve(int[] A) {
        int lower_bound = Integer.MIN_VALUE;
        Deque<Integer> stack = new LinkedList<>();
        stack.offerLast(A[0]);
        for (int i = 1; i < A.length; i++) {
            if (A[i] < stack.peekLast()) {
                if (A[i] < lower_bound) {
                    return 0;   
                }
            }
            else {
                while(!stack.isEmpty() && stack.peekLast() < A[i]) {
                    lower_bound = stack.pollLast();
                }
            }
            stack.offerLast(A[i]);
        } 
        return 1;
    }
}
