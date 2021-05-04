// https://leetcode.com/problems/permutations-ii/
// #backtracking
class Solution {
  // fix the each element at the first position => append the remaining element
  // 1, 2, 3
  /*
  start from 1:
  1-2-3
  1-3-2
  start from 2:
  2-1-3
  2-3-1
  start from 3:
  3-1-2
  3-2-1
  */
  public List<List<Integer>> permuteUnique(int[] nums) {

    // store frequency of each element
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      int frequency = 1;
      if (map.containsKey(num)) {
        frequency += map.get(num);
      }
      map.put(num, frequency);
    }

    LinkedList<Integer> permutation = new LinkedList<>();
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    backtracking(result, permutation, map, nums.length);

    return result;
  }

  private void backtracking(
      List<List<Integer>> result,
      LinkedList<Integer> permutation,
      HashMap<Integer, Integer> map,
      int limit) {

    if (permutation.size() == limit) {
      result.add(new ArrayList<Integer>(permutation));
      return;
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      int num = entry.getKey();
      int frequency = entry.getValue();
      if (frequency == 0) {
        continue;
      }

      // forward
      permutation.addLast(num);
      map.put(num, frequency - 1);

      backtracking(result, permutation, map, limit);

      // backward
      permutation.removeLast();
      map.put(num, frequency);
    }
  }
}
