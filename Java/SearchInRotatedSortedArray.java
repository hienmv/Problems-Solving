// https://leetcode.com/problems/search-in-rotated-sorted-array/
// #array #binary-search
class Solution {
  public int search(int[] nums, int target) {
    int n = nums.length;
    int l = 0, r = n - 1;
    int ret = binarySearch(nums, l, r, target);

    return ret;
  }

  private int binarySearch(int[] nums, int l, int r, int target) {
    int ret = -1;
    while (l <= r) {
      int mid = l + (r - l) / 2;
      if (nums[mid] == target) {
        return mid;
      }

      if (nums[mid] < nums[r]) {
        if (nums[mid] <= target && target <= nums[r]) {
          l = mid + 1;
        } else {
          r = mid - 1;
        }
      } else {
        if (nums[l] <= target && target <= nums[mid]) {
          r = mid - 1;
        } else {
          l = mid + 1;
        }
      }
    }
    return ret;
  }
}
