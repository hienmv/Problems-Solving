// https://leetcode.com/problems/first-missing-positive/
// #array
class Solution {
  /*
  -Time O(n)
  -Space O(n)
  */
  /*
  public int firstMissingPositive(int[] nums) {
      if (nums.length == 0) {
          return 1;
      }
      int n = nums.length;
      int[] tmp = new int[n+1];
      for(int i = 0; i < n; i++) {
          // dont care about lowerbound numbers (negative) and upperbound numbers (which greater than length of array)
          if (nums[i] <= 0 || nums[i] > n) {
              continue;
          }
          tmp[nums[i]] = 1;
      }
      for (int i=1; i < n+1; i++) {
          if (tmp[i] != 1) {
              return i;
          }
      }
      return n+1;
  }
  */

  /*
  - Time O(n)
  - Space O(1)
  */
  /*
  // space recycle.
  public int firstMissingPositive(int[] nums) {
      if (nums.length == 0) {
          return 1;
      }
      int n = nums.length;
      for(int i = 0; i < n; i++) {
          // dont care above lowerbound number (negative) and upperbound number (numbers which greater than length of array)
          if (nums[i] <= 0 || nums[i] > n) {
              continue;
          }
          // correct position
          if (nums[i] == i+1) {
              continue;
          }

          // swap elements in range
          int k = nums[i];
          while (k > 0 && k <= n && nums[k-1] != k) {
              int tmp = nums[k-1];
              nums[k-1] = k;
              k = tmp;
          }
      }
      for (int i= 0; i < n; i++) {
          if (nums[i] != i+1) {
              return i+1;
          }
      }
      return n+1;
  }
  */
  public int firstMissingPositive(int[] nums) {
    int n = nums.length;
    // for (int i = 0; i < n; i++) {
    //     // We don't care about non-positive numbers
    //     // so we mark it as positive and larger than n.
    //     // These numbers will be ignored in the next steps
    //     if (nums[i] <= 0) {
    //         nums[i] = Math.abs(nums[i]) + n + 1;
    //     }
    // }
    System.out.println(Arrays.toString(nums));
    int index;
    for (int i = 0; i < n; i++) {
      if (nums[i] <= 0) {
        continue;
      }
      if (nums[i] > n) {
        nums[i] = -nums[i];
        continue;
      }

      index = nums[i] - 1;
      // Ignore numbers with index larger than n
      if (index < n && nums[index] > 0) {
        nums[index] = -nums[index];
      }
    }
    for (int i = 0; i < n; i++) {
      if (nums[i] >= 0) return i + 1;
    }
    return n + 1;
  }
}
