// https://leetcode.com/problems/subsets-ii/
// #array #backtracking
class Solution {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> subset = new LinkedList<>();
    Arrays.sort(nums);
    backtracking(0, nums, subset, result);

    return result;
  }

  private void backtracking(
      int count, int[] nums, LinkedList<Integer> subset, List<List<Integer>> result) {
    if (!result.contains(subset)) { // slow
      result.add(new ArrayList<Integer>(subset));
    }
    for (int i = count; i < nums.length; i++) {
      // forward
      subset.addLast(nums[i]);

      backtracking(i + 1, nums, subset, result);

      // backward
      subset.removeLast();
    }
  }
}
