// https://leetcode.com/problems/maximum-sum-circular-subarray/
// #array #todo
class Solution {
  public int maxSubarraySumCircular(int[] A) {
    int n = A.length;
    int[] arr = new int[2 * n];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < n; j++) {
        arr[i * n + j] = A[j];
      }
    }
    for (int i : arr) {
      System.out.print(i + ", ");
    }
    System.out.println("");
    int ret = arr[0];
    int sum = arr[0];
    int start = 0;
    for (int i = 1; i < arr.length; i++) {
      if ((i % n) != (start % n)) {
        sum += arr[i];
      } else {
        sum = arr[i];
        start = i;
      }
      if (sum > ret) {
        ret = sum;
      }
      if (arr[i] > ret) {
        ret = arr[i];
        start = i;
      }
      if (sum <= 0) {
        sum = 0;
        start = i;
      }
    }
    return ret;
  }
}
