// https://www.interviewbit.com/problems/first-missing-integer/
// #array
public class Solution {
  public int firstMissingPositive(int[] nums) {
    if (nums.length == 0) {
      return 1;
    }
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      // dont care above lowerbound number (negative) and upperbound number (numbers which greater
      // than length of array)
      if (nums[i] < 1 || nums[i] > n) {
        continue;
      }
      // correct position
      if (nums[i] == i + 1) {
        continue;
      }

      // swap
      int k = nums[i];
      while (k > 0 && k <= n && nums[k - 1] != k) {
        int tmp = nums[k - 1];
        nums[k - 1] = k;
        k = tmp;
      }
    }
    for (int i = 0; i < n; i++) {
      if (nums[i] != i + 1) {
        return i + 1;
      }
    }
    return n + 1;
  }
}
