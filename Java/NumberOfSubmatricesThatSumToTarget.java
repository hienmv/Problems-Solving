// https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/submissions/
// #array #dynamic-programming #sliding-window
class NumberOfSubmatricesThatSumToTarget {
  public int numSubmatrixSumTarget(int[][] matrix, int target) {
    /*  vs 1 array:
        sum[i] la tong cac phan tu tu index 0 -> index i.
        ket thuc tai vi tri i, dem so luong sum = target
        -> sum (j,i) = sum[i] - sum[j-1];
          => moi vi tri i: kiem tra xem co bao nhieu j ma thoa man: sum[j-1] = sum[i] - k.
                => map(value, count);

        vs matrix: scale
            fix colums (l, r)
                -> how many (t, b)?  // => (bottom, right)

            time: (mxn)^2  =>  m x n^2
    */

    int m = matrix.length;
    int n = matrix[0].length;
    int[][] sum_colums = new int[m][n + 1];
    for (int j = 1; j <= n; j++) {
      for (int i = 0; i < m; i++) {
        sum_colums[i][j] = matrix[i][j - 1] + sum_colums[i][j - 1];
      }
    }

    int result = 0;
    for (int l = 1; l <= n; l++) {
      for (int r = l; r <= n; r++) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        for (int i = 0; i < m; i++) {
          // sum of columns [l-r] and from row [0->i]
          sum += sum_colums[i][r] - sum_colums[i][l - 1];
          if (map.containsKey(sum - target)) {
            result += map.get(sum - target);
          }
          map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
      }
    }
    return result;
  }
}
