// https://leetcode.com/problems/count-square-submatrices-with-all-ones/
// #array #dynamic-programming
class Solution {
    public int countSquares(int[][] matrix) {
        /*
        Input: matrix =
        [
          [0,1,1,1],
          [1,1,1,1],
          [0,1,1,1]
              (i, j, r) -> n^3
              dp[i][j][r] = dp[i-1][j][r-1] && dp[i][j-1][r-1] && dp[i-1][j-1][r-1];

              (i, j) = 1
                square_max_size[i][j] = min(square_max_size[i-1][j], square_max_size[i][j-1], square_max_size[i-1][j-1]) + 1;
        ]
        Output: 15
        Explanation: 
        There are 10 squares of side 1.
        There are 4 squares of side 2.
        There is  1 square of side 3.
        Total number of squares = 10 + 4 + 1 = 15.
        */
        
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        
        int[][] dp = new int[m+1][n+1]; 
        // dp[i][j] - max_size of square which bottom right is (i,j) 
        // => additional square is dp[i][j] : max_size (sum of : 1 of size 1, 1 of size 2 ,... 1 of size max_size)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    dp[i+1][j+1] = 0;
                }
                else {
                    int r = Math.min(dp[i][j], Math.min(dp[i][j+1], dp[i+1][j]));
                    dp[i+1][j+1] = r + 1;
                }
                // (1) count by type => size: min(m,n) => better than (2)
                // cnt[dp[i+1][j+1]] += 1;
            }
        }
        int ret = 0;
        // (2)
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                ret += dp[i][j];
            }
        }
        
        return ret;
    }
}