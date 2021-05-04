// https://leetcode.com/problems/counting-bits/
// #dynamic-programming #bit-manipulation
class Solution {
  /*
  f(5) = 1 + f(5 - 4) =>
  f(7) = 1 + f(3) =>
  */
  public int[] countBits(int num) {
    int[] result = new int[num + 1];
    int extr = 0;
    for (int i = 1; i <= num; i++) {
      if ((i & (i - 1)) == 0) {
        extr = i;
        result[i] = 1;
      } else {
        result[i] = result[i - extr] + 1;
      }
    }
    return result;
  }
  /*
  other way:
          f[k] = f[k/2]
          if k & 1:
              f[k] += 1
  */
}
