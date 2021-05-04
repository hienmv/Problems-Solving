// https://leetcode.com/problems/single-element-in-a-sorted-array/
// #binary-search
class Solution {
  /* O(n) - O(1)
  public int singleNonDuplicate(int[] nums) {
      int ret = 0;
      for(int e : nums) {
          ret ^= e;
      }
      return ret;
  }*/

  // O(logN) - O(1) - set index of couple [even, odd]
  public int singleNonDuplicate(int[] nums) {
    int ret = nums[0];
    int l = 0, r = nums.length - 1;

    while (l <= r) {
      int mid = l + (r - l) / 2;
      if (mid > 0 && nums[mid] == nums[mid - 1]) {
        if (mid % 2 == 0) {
          r = mid - 1;
        } else {
          l = mid + 1;
        }
      } else if (mid < (nums.length - 1) && nums[mid] == nums[mid + 1]) {
        if (mid % 2 == 0) {
          l = mid + 1;
        } else {
          r = mid - 1;
        }
      } else {
        return nums[mid];
      }
    }

    return ret;
  }
}
