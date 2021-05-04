// https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
// #array
/**
 * // This is the BinaryMatrix's API interface. // You should not implement it, or speculate about
 * its implementation interface BinaryMatrix { public int get(int x, int y) {} public List<Integer>
 * dimensions {} };
 */
class Solution {
  public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
    int n = binaryMatrix.dimensions().get(0);
    int m = binaryMatrix.dimensions().get(1);
    int ret = -1;
    int x = n - 1, y = m - 1;
    while (x >= 0 && y >= 0) {
      if (binaryMatrix.get(x, y) == 1) {
        ret = y;
        y--;
      } else {
        x--;
      }
    }

    return ret;
  }
}
