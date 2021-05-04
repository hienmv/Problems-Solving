// https://leetcode.com/problems/continuous-subarray-sum/
// #math #dynamic-programming
class Solution {
  /**
   * for i: 0 -> n-1 for j: 0 -> i - 1 sum[i][j] = (sum[i] - sum[j-1]) % k == 0 <=> sum[i] % k ==
   * sum[j - 1] % k <=> sum_mod[i] == sum_mod[j - 1] -> return true return false
   */
  /*
  [0,0]
  1
  => false
  true
  */
  public boolean checkSubarraySum(int[] nums, int k) {
    HashMap<Long, Integer> sum_mod = new HashMap<>();
    long current_sum = nums[0];
    sum_mod.put(
        0L,
        -1); // sum_mod[-1] = 0 // (0, 0) - sum of vi tri ma chua co mang () - sum of [0..n-1] = k
    if (current_sum % k != 0) {
      sum_mod.put(current_sum % k, 0);
    }
    for (int i = 1; i < nums.length; i++) {
      current_sum += nums[i]; // 9
      long cur_mod = current_sum % k; // 1
      if (sum_mod.containsKey(cur_mod)) {
        if (sum_mod.get(cur_mod) < i - 1) { // 0, 3, 1
          return true;
        }
      } else {
        sum_mod.put(cur_mod, i); //
      }
    }

    return false;
  }
}
