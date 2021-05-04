class Solution {
  /*
  problem: https://leetcode.com/problems/integer-break/
  brute force: find maximum from 2 -> n. O(n^2)
  other ways: thinking.
  */
  /*
  public int integerBreak(int n) {
      int[] core = new int[n+1];
      for (int i = 2; i < n + 1; i++) {
          integerBreakHelper(i, core);
      }
      return core[n];
  }

  private void integerBreakHelper(int n, int[] array) {
      for (int i = 1, j = n - 1; j >= i; i++, j--) {
          int max_i = Math.max(array[i], i);
          int max_j = Math.max(array[j], j);
          array[n] = Math.max(array[n], max_i * max_j);
      }
  }
  */

  /* DP O(n) */
  public int integerBreak(int n) {
    int[] core = new int[n + 1];
    integerBreakHelper(n, core);
    return core[n];
  }

  private int integerBreakHelper(int n, int[] array) {
    if (n < 2) {
      array[n] = 1;
    }
    if (array[n] != 0) {
      return array[n];
    }
    for (int i = 1, j = n - 1; j >= i; i++, j--) {
      array[i] = integerBreakHelper(i, array);
      array[j] = integerBreakHelper(j, array);
      int max_i = Math.max(array[i], i);
      int max_j = Math.max(array[j], j);
      array[n] = Math.max(array[n], max_i * max_j);
    }

    return array[n];
  }
}
