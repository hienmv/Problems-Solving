// https://leetcode.com/problems/contiguous-array/
// #hash-table
class Solution {
  /*
  note: EQUAL number 0s and 1s =>
  make 0 as -1, => in the accepted contiguous subarray => sum of all elements equal 0
  */
  public int findMaxLength(int[] nums) {
    int n = nums.length;
    int[] arr = new int[n + 1];
    for (int i = 0; i < n; i++) {
      if (nums[i] == 1) {
        arr[i + 1] = arr[i] + 1;
      } else {
        arr[i + 1] = arr[i] - 1;
      }
    }

    // <value, position>
    HashMap<Integer, Integer> hashMap = new HashMap<>();
    int ret = 0;
    for (int i = 0; i < arr.length; i++) {
      if (hashMap.containsKey(arr[i])) {
        int val = i - hashMap.get(arr[i]);
        if (val > ret) {
          ret = val;
        }
      } else {
        hashMap.put(arr[i], i);
      }
    }
    return ret;
  }
}
