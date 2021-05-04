// https://leetcode.com/problems/peak-index-in-a-mountain-array/
// #binary-search
class Solution {
  /* binary-search
  => Dua vao 1 yeu to, phan loai dc tap du lieu => binary-search.
  A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... >
  true    true        true   false    false   false
  */
  public int peakIndexInMountainArray(int[] A) {
    int l = 0;
    int r = A.length;
    int mid = 0;
    while (l < r) {
      mid = l + (r - l) / 2;
      if (mid > 0 && A[mid - 1] < A[mid] && A[mid] > A[mid + 1]) {
        break;
      }
      if (A[mid] < A[mid + 1]) {
        l = mid;
      } else if (A[mid] > A[mid + 1]) {
        r = mid;
      }
    }
    return mid;
  }
}
