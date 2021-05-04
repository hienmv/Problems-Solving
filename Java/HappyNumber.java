// https://leetcode.com/problems/happy-number/
// #hash-table #math
class Solution {
  public boolean isHappy(int n) {
    // max_integer = 2147483648; =? max = 10 * 9^2;
    int max = 1000;
    boolean ret = false;
    for (int i = 0; i < max; i++) {
      int b = 0;
      while (n > 0) {
        int c = n % 10;
        n /= 10;
        b += c * c;
      }
      if (b == 1) {
        ret = true;
        break;
      }
      n = b;
    }

    return ret;
  }
}
