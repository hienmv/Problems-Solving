// https://leetcode.com/problems/reverse-pairs/
// #binary-search #segment-tree #divide-and-conquer #sorting #binary-indexed-tree
class Solution {
  // hint: no need to sort.
  // ? merge sort => two part
  //              => temp array => compare and add element from two part to temp array. ?

  private int lowerBound(int[] nums, int left, int right, int target) {
    int pos = right + 1;
    while (right >= left) {
      int mid = left + (right - left) / 2;
      if (2 * (long) nums[mid] >= (long) target) {
        pos = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return pos;
  }

  private int countMid(int[] nums, int left, int right) {
    int count = 0;
    int mid = left + (right - left) / 2;
    Arrays.sort(nums, mid + 1, right + 1);
    for (int i = left; i <= mid; i++) {
      int pos = lowerBound(nums, mid + 1, right, nums[i]);
      count += pos - 1 - mid;
    }
    return count;
  }

  private int count(int[] nums, int left, int right) {
    if (left >= right) {
      return 0;
    }
    int mid = left + (right - left) / 2;
    int left_result = count(nums, left, mid);
    int right_result = count(nums, mid + 1, right);
    int mid_result = countMid(nums, left, right);

    return left_result + right_result + mid_result;
  }

  public int reversePairs(int[] nums) {
    return count(nums, 0, nums.length - 1);
  }
}
