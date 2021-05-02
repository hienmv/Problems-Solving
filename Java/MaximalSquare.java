// https://leetcode.com/problems/maximal-square/
// #dynamic-programming
class Solution {
    /*
    1. brute force: O(n^2 * m^2)
    
    
    2. Improve O(n * m * min(n, m))
    (4,3) => r = 3;
    
    result = 0;
    (i, j) => r = Min(i,j);
    for (k=r; k >= 0; k--) {
      => update result
    }
    
    
    3. DP (O(m*n))
      . . . .
      . . * #
      . . @ x
      
      r * (r+1)
      (r+1) * r
      
     r => (r + 1)
     
     0,1,2
     => r = 0;
     => r + 1;
    */
        public int maximalSquare(char[][] matrix) {
            int m = matrix.length;
            if (m == 0) return 0;
            int n = matrix[0].length;
            
            int[][] dp = new int[m+1][n+1];
            int result = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '0') {
                        dp[i+1][j+1] = 0;
                    }
                    else {
                        int r = Math.min(dp[i][j], Math.min(dp[i][j+1], dp[i+1][j]));
                        dp[i+1][j+1] = r + 1;
                        result = Math.max(result, dp[i+1][j+1]);
                    }
                }
            }
            return result * result;
        }
    }