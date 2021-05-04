// https://leetcode.com/problems/arranging-coins/
// #math #binary-search
class Solution {
  public int arrangeCoins(int n) {
    long r = 0;
    long limit = (long) Math.sqrt(2 * (long) n);
    for (r = limit; r > 0; r--) {
      if (r * (r + 1) / 2 <= n) {
        break;
      }
    }
    return (int) r;
  }
}
