// https://leetcode.com/problems/get-maximum-in-generated-array/
// #array
class Solution {
  public int getMaximumGenerated(int n) {
    if (n <= 0) return 0;
    if (n == 1) return 1;
    int[] nums = new int[n + 1];
    nums[1] = 1;
    int result = 1;
    for (int i = 2; i <= n; i++) {
      if (i % 2 == 0) {
        nums[i] = nums[i / 2];
      } else {
        nums[i] = nums[i / 2] + nums[i / 2 + 1];
      }
      result = Integer.max(result, nums[i]);
    }
    return result;
  }
}
