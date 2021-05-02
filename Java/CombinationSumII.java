// https://leetcode.com/problems/combination-sum-ii/
// #array #backtracking
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> combination = new LinkedList<>();
        
        Arrays.sort(candidates);
        
        backtracking(0, candidates, target, result, combination);
        
        return result;
    }
    
    private void backtracking(int idx, int[] candidates, int target, List<List<Integer>> result, LinkedList<Integer> combination) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(combination));
            return;
        }
        
        for (int i=idx; i < candidates.length; i++) {
            // duplicate
            if (i > idx && candidates[i] == candidates[i-1]) {
                continue;
            }
            if (target >= candidates[i]) {
                // forward
                combination.addLast(candidates[i]);
                
                backtracking(i+1, candidates, target-candidates[i], result, combination);
                
                // backward
                combination.removeLast();
            }
        }
    }
}

