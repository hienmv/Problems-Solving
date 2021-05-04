// https://leetcode.com/problems/maximum-subarray/
// #array #divide-and-conquer #dynamic-programming
class Solution {
  // slicing window
  public int maxSubArray(int[] nums) {
    int sum = nums[0];
    int maxSum = nums[0];
    for (int i = 1; i < nums.length; i++) {
      sum += nums[i];
      if (sum < nums[i]) {
        sum = nums[i];
      }

      if (sum > maxSum) {
        maxSum = sum;
      }
    }

    return maxSum;
  }
}
