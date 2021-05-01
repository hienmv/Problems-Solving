class OnesAndZeroes {
    // https://leetcode.com/problems/ones-and-zeroes/submissions/
    /*
    // DP: bottom-up O(m*n*len) - better way
    // line i depend on only i-1 => no need dp[m][n][len]
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] dp = new int[m+1][n+1];
        
        for (int i = 1; i < len+1; i++) {
            int num_zero = 0, num_one = 0;
            for (int j = 0; j < strs[i-1].length(); j++) {
                if (strs[i-1].charAt(j) == '0') {
                    num_zero++;
                } else {
                    num_one++;
                }
            }
            
            for (int k = m; k >= num_zero; k--) {
                for (int h = n; h >= num_one; h--) {
                    dp[k][h] = Math.max(1 + dp[k - num_zero][h - num_one], dp[k][h]);
                }
            }
        }

        return dp[m][n];
    }*/
    
    class Node {
        int num_one;
        int num_zero;
        Node(int num_zero, int num_one) {
            this.num_one = num_one;
            this.num_zero = num_zero;
        }
    }
    public int findMaxForm(String[] strs, int m, int n) {

        int len = strs.length;
        Node[] array = new Node[len];
        
        for (int i = 0; i < len; i++) {
            int num_zero = 0, num_one = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0') {
                    num_zero++;
                } else {
                    num_one++;
                }
            }
            array[i] = new Node(num_zero, num_one);
        }
        
        
        int[][][] dp = new int[m+1][n+1][len];

        for (int i = 0; i < m+1; i++){
            for (int j = 0; j < n+1; j++) {
                for (int z = 0; z < len; z++) {
                    dp[i][j][z] = -1;
                }
            }
        }
        
        find(array, dp, m, n, len-1);
        return dp[m][n][len-1];
    }
    
    // DP: 0(m*n*len)
    // find the way to choose maximum str in [0, len) with m 0 and n 1
        //      case 1: find the way to choose maximum str in [0, len-1) with m' 0 and n' 1
        //      case 2: find the way to choose maximum str in [0, len-1) with m 0 and n 1
    private int find(Node[] array, int[][][] dp, int m, int n, int idx) {
        if (idx < 0) {
            return 0;
        }
        if (m < 1 && n < 1) {
            return 0;
        }
        if (dp[m][n][idx] != -1) {
            return dp[m][n][idx];
        }
        
        int option_choose = 0, option_not_choose = 0;
        if (array[idx].num_zero <= m && array[idx].num_one <= n) {
            option_choose = 1 + find(array, dp, m - array[idx].num_zero, n - array[idx].num_one, idx - 1);
        }
        option_not_choose = find(array, dp, m, n, idx - 1);
        dp[m][n][idx] = Math.max(option_choose, option_not_choose);   
            
        return dp[m][n][idx];
    }
}