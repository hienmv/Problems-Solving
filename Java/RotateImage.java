// https://leetcode.com/problems/rotate-image/
// #array
class Solution {
  public void rotate(int[][] matrix) {
    // time: O(n*n), space: O(1)
    /* A(x,y) => A(y, side of square - x)
       rotate big square => smaller square
        i, j: i=0, j=i; i < n; j < size of square
    */
    int len = matrix.length;
    for (int i = 0; i < len; i++) {
      for (int j = i; j < len - i - 1; j++) {
        int m = i, n = j;
        int value = matrix[m][n];
        for (int t = 0; t < 4; t++) {
          int new_m = n;
          int new_n = len - 1 - m;
          int tmp = matrix[new_m][new_n];
          matrix[new_m][new_n] = value;
          value = tmp;
          m = new_m;
          n = new_n;
        }
      }
    }
  }
}
